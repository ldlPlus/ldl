package plus.ldl.day08elasticsearchdemo02;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年05月11日  14:47
 */
@SpringBootTest
public class BlukTest {

    @Autowired
    private RestHighLevelClient client;

    /**
     * 批量操作
     * 删除1号记录
     * 添加10号记录
     * 修改3号记录
     */
    @Test
    public void test16() throws Exception {
        // 创建bulk对象，整合所有操作
        BulkRequest bulkRequest = new BulkRequest();

        DeleteRequest deleteRequest = new DeleteRequest("ldl_plus", "1");
        bulkRequest.add(deleteRequest);

        Map<String, Object> data = new HashMap<>();
        data.put("name", "十号");
        IndexRequest indexRequest = new IndexRequest("ldl_plus").id("10").source(data);
        bulkRequest.add(indexRequest);

        UpdateRequest updateRequest = new UpdateRequest("ldl_plus", "3").doc(data);
        bulkRequest.add(updateRequest);

        BulkResponse responses = client.bulk(bulkRequest, RequestOptions.DEFAULT);

        System.out.println("responses = " + responses.toString());
    }
}
