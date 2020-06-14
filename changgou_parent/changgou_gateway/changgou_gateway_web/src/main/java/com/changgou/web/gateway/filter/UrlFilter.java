package com.changgou.web.gateway.filter;

/**
 * @author ldl.plus
 * @date 2020年06月13日  17:59
 */
public class UrlFilter {

    public static final String filterPath =
            "/api/wseckillorder ,/api/seckill,/api/wxpay, /api/wxpay/**,/api/worder/**," +
                    "/api/user/**,/api/address/**,/api/wcart/**,/api/cart/**," +
                    "/api/categoryReport/**,/api/orderConfig/**,/api/order/**," +
                    "/api/orderItem/**,/api/orderLog/**,/api/preferential/**," +
                    "/api/returnCause/**,/api/returnOrder/**,/api/returnOrderItem/**";

    public static boolean hasAuthorize(String url) {
        String[] split = filterPath.replace("**", "").split(",");
        for (String value : split) {
            if (url.startsWith(value)) {
                // 需要令牌
                return true;
            }
        }
        // 不需要令牌
        return false;
    }
}
