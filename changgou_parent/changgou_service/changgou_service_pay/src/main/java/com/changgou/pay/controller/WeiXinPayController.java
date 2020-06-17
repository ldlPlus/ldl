package com.changgou.pay.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.pay.service.WeiXinPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
