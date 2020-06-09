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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年06月07日  20:33
 */
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

            NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();

            AggregatedPage<SkuInfo> page = elasticsearchRestTemplate.queryForPage(
                    searchQuery,
                    SkuInfo.class, new SearchResultMapper() {
                        @Override
                        public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                            // 查询结果操作
                            ArrayList<T> arrayList = new ArrayList<>();
                            SearchHit[] hits = searchResponse.getHits().getHits();
                            for (SearchHit hit : hits) {
                                String jsonStr = hit.getSourceAsString();
                                SkuInfo skuInfo = JSON.parseObject(jsonStr, SkuInfo.class);
                                arrayList.add((T) skuInfo);
                            }
                            return new AggregatedPageImpl<>(arrayList, pageable, arrayList.size(), searchResponse.getAggregations());
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
            return resultMap;
        }

        // elasticsearchRestTemplate.queryForPage()

        // 封装查询结果
        return null;
    }
}
