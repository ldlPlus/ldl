package com.changgou.pay.service;

import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年06月16日  20:43
 */
public interface WeiXinPayService {

    /**
     * 获取二维码
     *
     * @param paramsMap 参数集合
     * @return
     */
    Map<String, String> createNativePay(Map<String, String> paramsMap);

    Map<String, String> queryStatus(String outTradeNo);
}
