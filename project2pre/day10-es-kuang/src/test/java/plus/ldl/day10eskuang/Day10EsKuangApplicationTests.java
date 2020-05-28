package plus.ldl.day10eskuang;

import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import plus.ldl.day10eskuang.service.ContentService;

import java.util.List;
import java.util.Map;

@SpringBootTest
class Day10EsKuangApplicationTests {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Autowired
    private ContentService contentService;

    @Test
    void contextLoads() throws Exception {
        Boolean flag = contentService.parseContent("java");
        System.out.println("flag = " + flag);
    }

    /**
     *
     */
    @Test
    public void test30() throws Exception {
        List<Map<String, Object>> searchPage = contentService.searchPage("java", 2, 10);
        for (Map<String, Object> map : searchPage) {
            System.out.println(map);
        }
    }
}
