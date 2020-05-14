package plus.ldl.day08elasticsearchmybatis;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.ReindexRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import plus.ldl.day08elasticsearchmybatis.domain.Goods;
import plus.ldl.day08elasticsearchmybatis.mapper.GoodsMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class Day08ElasticsearchMybatisApplicationTests {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    void contextLoads() {
    }

    /**
     * 测试查询
     */
    @Test
    public void test26() throws Exception {
        // 查询所有数据
        List<Goods> goodsList = goodsMapper.findAll();
    }

    /**
     * 批量导入
     */
    @Test
    public void test46() throws Exception {
        List<Goods> goodsList = goodsMapper.findAll();

        BulkRequest bulkRequest = new BulkRequest();

        for (Goods goods : goodsList) {

            IndexRequest indexRequest =
                    new IndexRequest("goods")
                            .id(goods.getId() + "")
                            .source(JSON.toJSONString(goods), XContentType.JSON);
            bulkRequest.add(indexRequest);
        }

        BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println("response = " + response.status());
    }

    /**
     * 批量删除
     */
    @Test
    public void test74() throws Exception {
        List<Goods> goodsList = goodsMapper.findAll();

        BulkRequest bulkRequest = new BulkRequest();
        for (Goods goods : goodsList) {

            DeleteRequest deleteRequest = new DeleteRequest("goods", goods.getId() + "");
            bulkRequest.add(deleteRequest);
        }


        client.bulk(bulkRequest, RequestOptions.DEFAULT);
    }


    /**
     * 在elasticSearch里查询所有
     * 1、matchAll
     * 2、将结果封装为List<Goods>
     * 3、分页。默认显示10条
     */
    @Test
    public void test100() throws Exception {

        SearchRequest searchRequest = new SearchRequest("goods");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        // QueryBuilder query = QueryBuilders.matchAllQuery();
        QueryBuilder query = new MatchAllQueryBuilder();

        sourceBuilder.from(0);
        sourceBuilder.size(100);

        sourceBuilder.query(query);

        searchRequest.source(sourceBuilder);

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        long total = hits.getTotalHits().value;
        System.out.println("total = " + total);

        List<Goods> goodsList = new ArrayList<>();

        SearchHit[] searchHits = hits.getHits();
        for (SearchHit searchHit : searchHits) {

            String source = searchHit.getSourceAsString();
            Goods goods = JSON.parseObject(source, Goods.class);
            goodsList.add(goods);
        }


        for (int i = 0; i < goodsList.size(); i++) {
            System.out.println("goods\t" + i + "\t= " + goodsList.get(i));
        }

    }


    /**
     * termQuery词条查询
     */
    @Test
    public void test149() throws Exception {

        SearchRequest searchRequest = new SearchRequest("goods");

        SearchSourceBuilder sourceBuilder = SearchSourceBuilder.searchSource();
        QueryBuilder query = QueryBuilders.termQuery("title", "华为");
        sourceBuilder.query(query);
        searchRequest.source(sourceBuilder);

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        List<Goods> goodsList = new ArrayList<>();

        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            Goods goods = JSON.parseObject(sourceAsString, Goods.class);
            goodsList.add(goods);
        }

        for (Goods goods : goodsList) {
            System.out.println("goods = " + goods);
        }
    }

    /**
     * match查询
     */
    @Test
    public void test171() throws Exception {

        SearchSourceBuilder sourceBuilder = SearchSourceBuilder.searchSource();
        sourceBuilder.size(100);
        SearchSourceBuilder query = sourceBuilder.query(QueryBuilders.matchQuery(
                "title", "华为手机").operator(Operator.OR));
        SearchRequest searchRequest =
                new SearchRequest("goods").source(query);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(response.getHits().getTotalHits());

        List<Goods> goodsList = new ArrayList<>();

        SearchHits hits = response.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit searchHit : searchHits) {

            String source = searchHit.getSourceAsString();
            Goods goods = JSON.parseObject(source, Goods.class);
            goodsList.add(goods);
        }

        for (int i = 0; i < goodsList.size(); i++) {
            System.out.println("goods\t" + i + "\t= " + goodsList.get(i));
        }
    }

    /**
     * 正则查询
     */
    @Test
    public void test204() throws Exception {

        SearchSourceBuilder sourceBuilder = SearchSourceBuilder.searchSource();
        sourceBuilder.query(QueryBuilders.regexpQuery("title", "\\w+(.)*"));
        SearchRequest searchRequest = new SearchRequest("goods").source(sourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            Goods goods = JSON.parseObject(hit.getSourceAsString(), Goods.class);
            System.out.println("goods = " + goods);
        }
    }

    /**
     * 模糊查询
     */
    @Test
    public void test220() throws Exception {
        SearchResponse response = client.search(
                new SearchRequest().indices("goods").source(
                        SearchSourceBuilder.searchSource().query(
                                QueryBuilders.wildcardQuery("title", "华*"))), RequestOptions.DEFAULT);
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            Goods goods = JSON.parseObject(hit.getSourceAsString(), Goods.class);
            System.out.println("goods = " + goods);
        }
    }

    /**
     * 范围查询
     */
    @Test
    public void test236() throws Exception {
        SearchResponse response = client.search(
                new SearchRequest("goods").source(
                        SearchSourceBuilder.searchSource().query(
                                QueryBuilders.rangeQuery("price")
                                        .gt(2000).lt(3000))
                                .sort("price", SortOrder.DESC)),
                RequestOptions.DEFAULT);
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            Goods goods = JSON.parseObject(hit.getSourceAsString(), Goods.class);
            System.out.println("goods = " + goods);
        }
    }

    /**
     * 多个条件查询
     */
    @Test
    public void test256() throws Exception {
        SearchResponse response =
                client.search(
                        new SearchRequest("goods").source(
                                SearchSourceBuilder.searchSource().query(
                                        QueryBuilders.queryStringQuery("华为手机").field("title").field("brandName").field("categoryName").defaultOperator(Operator.OR))),
                        RequestOptions.DEFAULT);
        System.out.println("response = " + response.getHits().getTotalHits());
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            Goods goods = JSON.parseObject(hit.getSourceAsString(), Goods.class);
            System.out.println("goods = " + goods);
        }
    }

    /**
     * 布尔查询 对多个查询条件连接
     */
    @Test
    public void test275() throws Exception {
        SearchResponse response =
                client.search(new SearchRequest("goods").source(SearchSourceBuilder.searchSource().query(QueryBuilders.boolQuery().filter(QueryBuilders.termQuery("brandName", "三星")).filter(QueryBuilders.wildcardQuery("title", "黑")))),
                        RequestOptions.DEFAULT);
        System.out.println("response = " + response.getHits().getTotalHits());
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            Goods goods = JSON.parseObject(hit.getSourceAsString(), Goods.class);
            System.out.println("goods = " + goods);
        }
    }

    /**
     * 聚合函数 指标聚合
     */
    @Test
    public void test291() throws Exception {
        SearchResponse response =
                client.search(new SearchRequest("goods").source(SearchSourceBuilder.searchSource().query(QueryBuilders.matchQuery("title", "手机")).aggregation(AggregationBuilders.terms("goods_brands").field("brandName").size(10))),
                        RequestOptions.DEFAULT);
        System.out.println("response = " + response.getHits().getTotalHits());
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            Goods goods = JSON.parseObject(hit.getSourceAsString(), Goods.class);
            System.out.println("goods = " + goods);
        }
        Aggregations aggregations = response.getAggregations();
        Terms aggregation = aggregations.get("goods_brands");
        List<? extends Terms.Bucket> aggregationBuckets = aggregation.getBuckets();
        for (Terms.Bucket aggregationBucket : aggregationBuckets) {
            System.out.println("aggregationBucket = " + aggregationBucket.getKeyAsString());
        }
    }

    /**
     * 高亮
     */
    @Test
    public void test316() throws Exception {
        SearchResponse response =
                client.search(
                        new SearchRequest("goods").source(
                                SearchSourceBuilder.searchSource().query(
                                        QueryBuilders.matchQuery("title", "手机")).highlighter(
                                        new HighlightBuilder().field("title").preTags(" ...").postTags("... ")).size(1000))
                        , RequestOptions.DEFAULT);
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            // Goods goods = JSON.parseObject(hit.getSourceAsString(), Goods.class);
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            String title = highlightFields.get("title").getFragments()[0].toString();
            // goods.setTitle(title);
            // System.out.println("goods = " + goods);
            System.out.println("title = " + title);
        }
    }

    /**
     * 重建索引  复制新建
     */
    @Test
    public void test342() throws Exception {
        BulkByScrollResponse response =
                client.reindex(new ReindexRequest().setSourceIndices("test_index_v1").setDestIndex("test_index_v2"),
                        RequestOptions.DEFAULT);
    }

}
