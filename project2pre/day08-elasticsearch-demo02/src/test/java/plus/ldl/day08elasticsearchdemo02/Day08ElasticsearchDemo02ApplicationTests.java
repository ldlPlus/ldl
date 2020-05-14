package plus.ldl.day08elasticsearchdemo02;


import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import plus.ldl.day08elasticsearchdemo02.domain.Person;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class Day08ElasticsearchDemo02ApplicationTests {

    @Autowired
    private RestHighLevelClient client;

    @Test
    void contextLoads() {
        IndicesClient client = this.client.indices();
        System.out.println("client = " + client);
    }

    /**
     * 添加索引
     */
    @Test
    public void test25() throws Exception {
        IndicesClient client = this.client.indices();
        CreateIndexRequest creatIndexRequest = new CreateIndexRequest("ldl_plus");
        CreateIndexResponse response = client.create(creatIndexRequest, RequestOptions.DEFAULT);
        boolean acknowledged = response.isAcknowledged();
        System.out.println("acknowledged = " + acknowledged);
    }

    /**
     * 删除索引
     */
    @Test
    public void test40() throws Exception {
        IndicesClient indicesClient = client.indices();
        DeleteIndexRequest deleteRequest = new DeleteIndexRequest("ldl_plus");
        AcknowledgedResponse response = indicesClient.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println("response = " + response.isAcknowledged());
    }

    /**
     * 设置mapping
     */
    @Test
    public void test53() throws Exception {
        IndicesClient client = this.client.indices();
        CreateIndexRequest creatIndexRequest = new CreateIndexRequest("ldl_plus");

        //language=JSON
        String mapping = "{\n" +
                "  \"properties\": {\n" +
                "    \"address\": {\n" +
                "      \"type\": \"text\",\n" +
                "      \"analyzer\": \"ik_max_word\"\n" +
                "    },\n" +
                "    \"age\": {\n" +
                "      \"type\": \"long\"\n" +
                "    },\n" +
                "    \"name\": {\n" +
                "      \"type\": \"keyword\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        creatIndexRequest.mapping(mapping, XContentType.JSON);

        CreateIndexResponse response = client.create(creatIndexRequest, RequestOptions.DEFAULT);
        boolean acknowledged = response.isAcknowledged();
        System.out.println("acknowledged = " + acknowledged);
    }

    /**
     * 查询索引
     */
    @Test
    public void test85() throws Exception {
        IndicesClient indicesClient = client.indices();
        GetIndexRequest getRequest = new GetIndexRequest("ldl_plus");
        GetIndexResponse response = indicesClient.get(getRequest, RequestOptions.DEFAULT);

        Map<String, MappingMetaData> mappings = response.getMappings();
        for (Map.Entry<String, MappingMetaData> entry : mappings.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue().getSourceAsMap());
        }
    }

    /**
     * 删除索引
     */
    @Test
    public void test105() throws Exception {
        IndicesClient indicesClient = client.indices();

        DeleteIndexRequest deleteRequest = new DeleteIndexRequest("ldl_plus");
        AcknowledgedResponse response = indicesClient.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println("response = " + response.isAcknowledged());
    }

    /**
     * 判断索引是否存在
     */
    @Test
    public void test117() throws Exception {
        IndicesClient indicesClient = client.indices();
        GetIndexRequest request = new GetIndexRequest("ldl_plus");
        boolean exists = indicesClient.exists(request, RequestOptions.DEFAULT);
        System.out.println("exists = " + exists);
    }

    /**
     * 添加文档
     */
    @Test
    public void test128() throws Exception {

        Map<String, Object> data = new HashMap<>();
        data.put("address", "北京昌平");
        data.put("name", "张珊珊");
        data.put("age", 20);

        IndexRequest request = new IndexRequest("ldl_plus").id("1").source(data);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        System.out.println("response = " + response.toString());
    }

    /**
     * 添加文档，使用对象作为数据
     */
    @Test
    public void test148() throws Exception {
        Person person = new Person();
        person.setId("8");
        person.setAge(10);
        person.setName("你觉得");
        person.setAddress("陕西西安");

        String data = JSON.toJSONString(person);

        IndexRequest request = new IndexRequest("ldl_plus").id(person.getId()).source(data, XContentType.JSON);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        System.out.println("response = " + response.toString());

    }

    /**
     * 修改文档：添加文档时：若果ID存在则修改，不存在则添加
     */
    @Test
    public void test169() throws Exception {
        Person person = new Person();
        person.setId("2");
        person.setAge(10);
        person.setName("我觉得");
        person.setAddress("陕西团上");

        String data = JSON.toJSONString(person);

        IndexRequest request = new IndexRequest("ldl_plus").id(person.getId()).source(data, XContentType.JSON);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        System.out.println("response = " + response.toString());

    }

    /**
     * 根据ID查询文档
     */
    @Test
    public void test188() throws Exception {

        GetRequest request = new GetRequest("ldl_plus", "1");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        System.out.println("response = " + response.getSourceAsString());
    }

    /**
     * 根据ID删除文档
     */
    @Test
    public void test201() throws Exception {
        DeleteRequest deleteRequest = new DeleteRequest("ldl_plus", "1");
        DeleteResponse response = client.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println("response = " + response.toString());
    }
}
