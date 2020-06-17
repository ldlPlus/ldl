package com.changgou.pay.service.impl;

import com.changgou.entity.util.HttpClient;
import com.changgou.pay.service.WeiXinPayService;
import com.github.wxpay.sdk.WXPayUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年06月16日  20:44
 */
@Service
public class WeiXinPayServiceImpl implements WeiXinPayService {
    /**
     * 应用ID
     */
    @Value("${weixin.appid}")
    private String appId;
    /**
     * 商户ID
     */
    @Value("${weixin.partner}")
    private String partner;
    /**
     * 秘钥
     */
    @Value("${weixin.partnerkey}")
    private String partnerKey;
    /**
     * 支付回调地址
     */
    @Value("${weixin.notifyurl}")
    private String notifyUrl;

    @Override
    public Map<String, String> createNativePay(Map<String, String> paramsMap) {
        try {
            // 参数
            HashMap<String, String> dataMap = new HashMap<>();
            dataMap.put("appid", appId);
            dataMap.put("mch_id", partner);
            // 随机字符
            dataMap.put("nonce_str", WXPayUtil.generateNonceStr());
            dataMap.put("body", "畅购商城商品销售");
            // 订单号
            dataMap.put("out_trade_no", paramsMap.get("outTradeNo"));
            // 交易金额，单位：分
            dataMap.put("total_fee", paramsMap.get("totalFee"));
            // pos机ip
            dataMap.put("spbill_create_ip", "127.0.0.1");
            // 支付回调地址
            dataMap.put("notify_url", notifyUrl);
            // 交易类型
            dataMap.put("trade_type", "NATIVE");

            String xmlDataMap = WXPayUtil.generateSignedXml(dataMap, partnerKey);


            // URL地址
            String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
            HttpClient httpClient = new HttpClient(url);
            httpClient.setHttps(true);
            httpClient.setXmlParam(xmlDataMap);
            httpClient.post();
            return WXPayUtil.xmlToMap(httpClient.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, String> queryStatus(String outTradeNo) {
        try {
            // 参数
            HashMap<String, String> dataMap = new HashMap<>();
            dataMap.put("appid", appId);
            dataMap.put("mch_id", partner);
            // 随机字符
            dataMap.put("nonce_str", WXPayUtil.generateNonceStr());
            // 订单号
            dataMap.put("out_trade_no", outTradeNo);

            String xmlDataMap = WXPayUtil.generateSignedXml(dataMap, partnerKey);


            // URL地址
            String url = "https://api.mch.weixin.qq.com/pay/orderquery";
            HttpClient httpClient = new HttpClient(url);
            httpClient.setHttps(true);
            httpClient.setXmlParam(xmlDataMap);
            httpClient.post();
            return WXPayUtil.xmlToMap(httpClient.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
