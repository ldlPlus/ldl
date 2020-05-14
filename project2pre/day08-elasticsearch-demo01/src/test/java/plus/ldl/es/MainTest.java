package plus.ldl.es;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
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
import org.elasticsearch.client.RestClient;
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
import plus.ldl.es.domain.Person;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年05月10日  23:40
 */
@SpringBootTest
public class MainTest {

    @Autowired
    private RestHighLevelClient client;

    /**
     *
     */
    @Test
    public void test17() throws Exception {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
                new HttpHost(
                        "192.168.31.42",
                        9200,
                        "http"
                )));
        System.out.println("client = " + client);
    }

    /**
     *
     */
    @Test
    public void test38() throws Exception {
        System.out.println("restHighLevelClient = " + client);
    }

    /**
     * 添加索引
     */
    @Test
    public void testAddIndex() throws Exception {
        IndicesClient indicesClient = client.indices();
        CreateIndexRequest createRequest = new CreateIndexRequest("itheima");
        CreateIndexResponse response = indicesClient.create(createRequest, RequestOptions.DEFAULT);
        boolean acknowledged = response.isAcknowledged();
        System.out.println("acknowledged = " + acknowledged);

    }

    /**
     *
     */
    @Test
    public void testAddMapping() throws Exception {
        IndicesClient indicesClient = client.indices();
        CreateIndexRequest createRequest = new CreateIndexRequest("itheima");
        String mapping = "{\n" +
                "  \"properties\": {\n" +
                "    \"name\": {\n" +
                "      \"type\": \"keyword\"\n" +
                "    },\n" +
                "    \"age\": {\n" +
                "      \"type\": \"integer\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        createRequest.mapping(mapping, XContentType.JSON);
        CreateIndexResponse response = indicesClient.create(createRequest, RequestOptions.DEFAULT);
        boolean acknowledged = response.isAcknowledged();
        System.out.println("acknowledged = " + acknowledged);

    }

    @Test
    public void addIndexAndMapping() throws IOException {
        //1.使用client获取操作索引的对象
        IndicesClient indicesClient = client.indices();
        //2.具体操作，获取返回值
        CreateIndexRequest createRequest = new CreateIndexRequest("itheima");
        //2.1 设置mappings
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
        createRequest.mapping(mapping, XContentType.JSON);


        CreateIndexResponse response = indicesClient.create(createRequest, RequestOptions.DEFAULT);

        //3.根据返回值判断结果
        System.out.println(response.isAcknowledged());

    }

    @Test
    public void queryIndex() throws IOException {
        IndicesClient indices = client.indices();

        GetIndexRequest getReqeust = new GetIndexRequest("itheima");
        GetIndexResponse response = indices.get(getReqeust, RequestOptions.DEFAULT);


        //获取结果
        Map<String, MappingMetaData> mappings = response.getMappings();
        for (String key : mappings.keySet()) {
            System.out.println(key + ":" + mappings.get(key).getSourceAsMap());

        }

    }

    /**
     * 删除索引
     */
    @Test
    public void deleteIndex() throws IOException {
        IndicesClient indices = client.indices();

        DeleteIndexRequest deleteRequest = new DeleteIndexRequest("itheima");
        AcknowledgedResponse response = indices.delete(deleteRequest, RequestOptions.DEFAULT);

        System.out.println(response.isAcknowledged());

    }

    /**
     * 判断索引是否存在
     */
    @Test
    public void existIndex() throws IOException {
        IndicesClient indices = client.indices();

        GetIndexRequest getRequest = new GetIndexRequest("itheima");
        boolean exists = indices.exists(getRequest, RequestOptions.DEFAULT);

        System.out.println(exists);

    }


    /**
     * 添加文档,使用map作为数据
     */
    @Test
    public void addDoc() throws IOException {
        //数据对象，map
        Map<String, Object> data = new HashMap<>();
        data.put("address", "北京昌平");
        data.put("name", "大胖");
        data.put("age", 20);


        //1.获取操作文档的对象
        IndexRequest request = new IndexRequest("itcast").id("1").source(data);
        //添加数据，获取结果
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        //打印响应结果
        System.out.println(response.getId());


    }


    /**
     * 添加文档,使用对象作为数据
     */
    @Test
    public void addDoc2() throws IOException {
        //数据对象，javaObject
        Person p = new Person();
        p.setId("7");
        p.setName("小胖2222");
        p.setAge(30);
        p.setAddress("陕西西安");

        //将对象转为json
        String data = JSON.toJSONString(p);

        //1.获取操作文档的对象
        IndexRequest request = new IndexRequest("itcast").id(p.getId()).source(data, XContentType.JSON);
        //添加数据，获取结果
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        //打印响应结果
        System.out.println(response.getId());


    }


    /**
     * 修改文档：添加文档时，如果id存在则修改，id不存在则添加
     */
    @Test
    public void updateDoc() throws IOException {

    }


    /**
     * 根据id查询文档
     */
    @Test
    public void findDocById() throws IOException {

        GetRequest getReqeust = new GetRequest("itcast", "1");
        //getReqeust.id("1");
        GetResponse response = client.get(getReqeust, RequestOptions.DEFAULT);
        //获取数据对应的json
        System.out.println(response.getSourceAsString());


    }


    /**
     * 根据id删除文档
     */
    @Test
    public void delDoc() throws IOException {


        DeleteRequest deleteRequest = new DeleteRequest("itcast", "1");
        DeleteResponse response = client.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(response.getId());


    }
}
