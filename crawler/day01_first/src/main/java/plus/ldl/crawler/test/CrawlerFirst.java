package plus.ldl.crawler.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author ldl.plus
 * @date 2020年03月13日  21:14
 */
public class CrawlerFirst {

    public static void main(String[] args) throws IOException {

        //打开浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //输入网址
        HttpGet httpGet = new HttpGet("http://www.itcast.cn");
        //按回车，发起请求，返回响应
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //解析响应，获取数据
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity httpEntity = response.getEntity();
            String content = EntityUtils.toString(httpEntity, "UTF-8");
            System.out.println("content = " + content);
        }
    }
}
