package com.changgou;

import com.github.wxpay.sdk.WXPayUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年06月16日  20:02
 * 微信sdk相关测试
 */
public class WeiXin {
    /**
     * 生成随机字符
     */
    @Test
    public void test13() throws Exception {
        String str = WXPayUtil.generateNonceStr();
        System.out.println("str = " + str);

        HashMap<String, String> map = new HashMap<>();
        map.put("id", "No.001");
        map.put("title", "畅购商城杯具支付");
        map.put("price", "998");
        String mapToXml = WXPayUtil.mapToXml(map);
        System.out.println("mapToXml = \n" + mapToXml);

        String itcast = WXPayUtil.generateSignedXml(map, "itcast");
        System.out.println("itcast = \n" + itcast);


        Map<String, String> xmlToMap = WXPayUtil.xmlToMap(itcast);
        System.out.println("xmlToMap = " + xmlToMap);
    }
}
