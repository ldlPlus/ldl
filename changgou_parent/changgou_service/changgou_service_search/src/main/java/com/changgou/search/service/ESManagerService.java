package com.changgou.search.service;

/**
 * @author ldl.plus
 * @date 2020年06月07日  17:21
 */
public interface ESManagerService {
    /**
     * 创建索引库结构
     */
    void createMappingAndIndex();

    /**
     * 导入全部数据进入es
     */
    void importAll();

    /**
     * 根据spuID查询skuList，再导入索引库
     */
    void importDataBySpuId(String spuId);

    /**
     * 根据spuId删除es索引库中相关的sku数据
     */
    void deleteDataBySpuId(String spuId);

    void importByPage(Integer page, Integer size);
}
