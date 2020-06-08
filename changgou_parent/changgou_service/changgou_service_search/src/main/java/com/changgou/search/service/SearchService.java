package com.changgou.search.service;

import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年06月07日  20:31
 */
public interface SearchService {

    /**
     * 按照查询条件进行数据查询
     *
     * @param condition 查询条件集合
     */
    Map<String, Object> search(Map<String, String> condition);
}
