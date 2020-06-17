package com.changgou;

import com.changgou.entity.util.HttpClient;
import com.github.wxpay.sdk.WXPayUtil;
import org.junit.Test;

/**
 * @author ldl.plus
 * @date 2020年06月16日  20:15
 */
public class HttpClientTest {
    /**
     * 发送http/https请求
     */
    @Test
    public void test12() throws Exception {
        HttpClient httpClient = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
        String xml = "<xml>\n" +
                "   <appid>wx2421b1c4370ec43b</appid>\n" +
                "   <mch_id>10000100</mch_id>\n" +
                "   <nonce_str>ec2316275641faa3aacf3cc599e8730f</nonce_str>\n" +
                "   <transaction_id>1008450740201411110005820873</transaction_id>\n" +
                "   <sign>FDD167FAA73459FD921B144BAF4F4CA2</sign>\n" +
                "</xml>";
        httpClient.setXmlParam(xml);
        httpClient.setHttps(true);
        httpClient.post();
        String content = httpClient.getContent();
        System.out.println("content = " + content);
        System.out.println(WXPayUtil.xmlToMap(content));
    }
}
