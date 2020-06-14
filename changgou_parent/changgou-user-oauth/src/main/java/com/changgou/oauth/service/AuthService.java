package com.changgou.oauth.service;

import com.changgou.oauth.util.AuthToken;

/**
 * @author ldl.plus
 * @date 2020年06月13日  11:33
 */
public interface AuthService {

    AuthToken login(String username, String password, String clientId, String clientSecret);
}
