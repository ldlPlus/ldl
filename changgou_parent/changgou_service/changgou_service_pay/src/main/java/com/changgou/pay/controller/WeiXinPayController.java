package com.changgou.pay.controller;

import com.alibaba.fastjson.JSON;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.pay.config.MqConfig;
import com.changgou.pay.service.WeiXinPayService;
import com.github.wxpay.sdk.WXPayUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年06月16日  21:12
 */
@RestController
@RequestMapping(value = "/weixin/pay")
public class WeiXinPayController {
    @Autowired
    private WeiXinPayService weiXinPayService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 获取二维码
     *
     * @param parameterMap 参数集合
     * @return
     */
    @RequestMapping(value = "/create/native")
    public Result<Map<String, String>> createNativePay(@RequestParam Map<String, String> parameterMap) {
        Map<String, String> resultMap = weiXinPayService.createNativePay(parameterMap);
        return new Result<>(true, StatusCode.OK, "创建二维码预付订单成功", resultMap);
    }

    /**
     * 查询支付状态
     *
     * @param outTradeNo 订单号
     * @return
     */
    @RequestMapping("/status/query")
    public Result<Map<String, String>> queryStatus(@RequestParam String outTradeNo) {
        Map<String, String> resultMap = weiXinPayService.queryStatus(outTradeNo);
        return new Result<>(true, StatusCode.OK, "查询二维码预付订单成功", resultMap);
    }

    @RequestMapping("/notify/url")
    public String notifyUrl(HttpServletRequest request) throws Exception {
        ServletInputStream inputStream = request.getInputStream();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len;
        while ((len = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
        }
        byte[] byteArray = outputStream.toByteArray();
        String s = new String(byteArray);
        Map<String, String> xmlToMap = WXPayUtil.xmlToMap(s);

        rabbitTemplate.convertAndSend(MqConfig.ORDER_EXCHANGE, MqConfig.ORDER_ROUTING, JSON.toJSONString(xmlToMap));

        String result = "";
        return s;
    }

}
