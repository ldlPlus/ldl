package plus.ldl.day10eskuang.service.impl;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import plus.ldl.day10eskuang.domain.Content;
import plus.ldl.day10eskuang.service.ContentService;
import plus.ldl.day10eskuang.util.HtmlParseUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ldl.plus
 * @date 2020年05月15日  19:26
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Override
    public Boolean parseContent(String keywords) throws Exception {
        List<Content> list = HtmlParseUtil.parseJD(keywords);

        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10m");

        for (Content content : list) {
            bulkRequest.add(
                    new IndexRequest("jd_goods")
                            .source(JSON.toJSONString(content), XContentType.JSON));
        }
        BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        return !response.hasFailures();
    }

    @Override
    public List<Map<String, Object>> searchPage(String keywords, int pageNum, int pageSize) throws Exception {
        if (pageNum <= 1) {
            pageNum = 1;
        }
        HighlightBuilder highlightBuilder =
                new HighlightBuilder()
                        .field("title")
                        .preTags("<span style='color: red'>")
                        .postTags("</span>")
                        .requireFieldMatch(false);
        SearchSourceBuilder sourceBuilder = SearchSourceBuilder.searchSource()
                .query(QueryBuilders.termQuery("title", keywords))
                .from(pageNum * pageSize - pageSize)
                .size(pageSize)
                .timeout(new TimeValue(60, TimeUnit.SECONDS))
                .highlighter(highlightBuilder);
        SearchRequest searchRequest = new SearchRequest("jd_goods").source(sourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);


        ArrayList<Map<String, Object>> list = new ArrayList<>();
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField title = highlightFields.get("title");
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            if (title != null) {
                Text[] fragments = title.fragments();
                StringBuilder newTitle = new StringBuilder();
                for (Text fragment : fragments) {
                    newTitle.append(fragment);
                }
                sourceAsMap.put("title", newTitle);
            }
            list.add(sourceAsMap);
        }
        return list;
    }


}
