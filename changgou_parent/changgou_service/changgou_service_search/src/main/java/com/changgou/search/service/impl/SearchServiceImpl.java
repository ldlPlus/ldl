package com.changgou.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.search.pojo.SkuInfo;
import com.changgou.search.service.SearchService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ldl.plus
 * @date 2020年06月07日  20:33
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 按照查询条件进行数据查询
     *
     * @param condition 查询条件集合
     */
    @Override
    public Map<String, Object> search(Map<String, String> condition) {
        // 构建查询
        if (condition != null) {
            // 开启查询
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

            nativeSearchQueryBuilder.withQuery(boolQuery);
            // 按照关键字查询
            String keywords = condition.get("keywords");
            if (StringUtils.isNotEmpty(keywords)) {
                boolQuery.must(QueryBuilders.matchQuery("name", keywords).operator(Operator.AND));
            }
            // 按照品牌进行过滤查询
            String brand = condition.get("brand");
            if (StringUtils.isNotEmpty(brand)) {
                boolQuery.filter(QueryBuilders.termQuery("brandName", brand));
            }

            // 按照价格进行区间查询
            String price = condition.get("price");
            if (StringUtils.isNotEmpty(price)) {
                String[] prices = price.split("-");
                if (prices.length == 2) {
                    boolQuery.filter(QueryBuilders.rangeQuery("price").gte(prices[0]).lte(prices[1]));
                }
                boolQuery.filter(QueryBuilders.rangeQuery("price").gte(prices[0]));
            }

            // 按照规则进行查询过滤
            for (String key : condition.keySet()) {
                if (key.startsWith("spec_")) {
                    // 代表是规格信息
                    String value = condition.get(key).replace("%2B", "+");
                    boolQuery.filter(QueryBuilders.termQuery(("specMap." + key.substring(5) + ".keyword"), value));
                }
            }

            // 按照品牌进行分组(聚合)查询
            nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("skuBrand").field("brandName"));

            // 按照规格进行分组查询
            nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("skuSpec").field("spec.keyword"));

            // 分页查询
            String pageNum = condition.get("pageNum");
            String pageSize = condition.get("pageSize");
            if (StringUtils.isEmpty(pageNum)) {
                pageNum = "1";
            }
            if (StringUtils.isEmpty(pageSize)) {
                pageSize = "30";
            }
            nativeSearchQueryBuilder.withPageable(
                    PageRequest.of(
                            Integer.parseInt(pageNum) - 1, Integer.parseInt(pageSize)));
            // 按照相关字段进行排序查询
            // 1、当前域 2、当前的排序操作 asc  desc
            String sortFiled = condition.get("sortFiled");
            String sortRule = condition.get("sortRule");
            if (StringUtils.isNotEmpty(sortFiled) && StringUtils.isNotEmpty(sortRule)) {
                if ("asc".equalsIgnoreCase(sortRule)) {
                    //升序
                    nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort(sortFiled).order(SortOrder.ASC));
                } else {
                    // 降序
                    nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort(sortFiled).order(SortOrder.DESC));
                }
            }

            // 高亮显示
            HighlightBuilder.Field field = new HighlightBuilder.Field("name");
            field.preTags("<span style='color: red'>").postTags("</span>");
            nativeSearchQueryBuilder.withHighlightFields(field);

            NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();

            AggregatedPage<SkuInfo> page = elasticsearchRestTemplate.queryForPage(
                    searchQuery,
                    SkuInfo.class, new SearchResultMapper() {
                        @Override
                        public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                            // 查询结果操作
                            ArrayList<T> list = new ArrayList<T>();

                            SearchHits hits = searchResponse.getHits();
                            for (SearchHit hit : hits) {
                                SkuInfo skuInfo = JSON.parseObject(hit.getSourceAsString(), SkuInfo.class);
                                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                                if (highlightFields != null && highlightFields.size() > 0) {
                                    skuInfo.setName(highlightFields.get("name").getFragments()[0].toString());
                                }
                                list.add((T) skuInfo);
                            }

                            return new AggregatedPageImpl<>(
                                    list,
                                    pageable,
                                    hits.getTotalHits().value,
                                    searchResponse.getAggregations());
                        }

                        @Override
                        public <T> T mapSearchHit(SearchHit searchHit, Class<T> aClass) {
                            return null;
                        }
                    });

            int totalPages = page.getTotalPages();
            List<SkuInfo> content = page.getContent();
            long totalElements = page.getTotalElements();
            HashMap<String, Object> resultMap = new HashMap<>();

            resultMap.put("rows", content);
            resultMap.put("total", totalElements);
            resultMap.put("totalPages", totalPages);

            // 封装品牌分组的结果
            ParsedStringTerms brandTerms = (ParsedStringTerms) page.getAggregation("skuBrand");
            List<String> brandList =
                    brandTerms.getBuckets().stream().map(bucket -> bucket.getKeyAsString()).collect(Collectors.toList());
            resultMap.put("brandList", brandList);

            // 封装规格分组的结果
            ParsedStringTerms specTerms = (ParsedStringTerms) page.getAggregation("skuSpec");
            List<String> specList = specTerms.getBuckets().stream().map(o -> o.getKeyAsString()).collect(Collectors.toList());
            resultMap.put("specList", specList);

            // 封装当前页
            resultMap.put("pageNum", pageNum);

            return resultMap;
        }

        // elasticsearchRestTemplate.queryForPage()

        // 封装查询结果
        return null;
    }
}
