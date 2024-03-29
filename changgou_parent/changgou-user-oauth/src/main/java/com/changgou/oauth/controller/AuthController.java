package com.changgou.oauth.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.oauth.service.AuthService;
import com.changgou.oauth.util.AuthToken;
import com.changgou.oauth.util.CookieUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ldl.plus
 * @date 2020年06月13日  11:52
 */
@Controller
@RequestMapping("/oauth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Value("${auth.clientId}")
    private String clientId;
    @Value("${auth.clientSecret}")
    private String clientSecret;
    @Value("${auth.cookieDomain}")
    private String cookieDomain;
    @Value("${auth.cookieMaxAge}")
    private int cookieMaxAge;

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }


    @PostMapping("/login")
    @ResponseBody

    public Result<String> login(String username, String password, HttpServletResponse response) {
        // 校验参数
        if (StringUtils.isEmpty(username)) {
            throw new RuntimeException("请输入用户名");
        }
        if (StringUtils.isEmpty(password)) {
            throw new RuntimeException("请输入密码");
        }
        // 申请令牌
        AuthToken authToken = authService.login(username, password, clientId, clientSecret);
        // 将jti存入cookie
        this.saveJtiToCookie(authToken.getJti(), response);
        // 返回结果
        return new Result<>(true, StatusCode.OK, "登录成功", authToken.getJti());
    }

    /**
     * 将令牌存入cookie
     *
     * @param jti
     * @param response
     */
    private void saveJtiToCookie(String jti, HttpServletResponse response) {
        CookieUtil.addCookie(response, cookieDomain, "/", "uid", jti, cookieMaxAge, false);
    }
}
