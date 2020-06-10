package com.changgou.page.service;

import java.io.IOException;

/**
 * @author ldl.plus
 * @date 2020年06月10日  14:19
 */
public interface PageService {

    /**
     * 生成静态化页面
     */
    void generateHtml(String spuId) throws IOException;
}
