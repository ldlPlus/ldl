package com.changgou.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.pojo.Sku;
import com.changgou.search.mapper.ESManagerMapper;
import com.changgou.search.pojo.SkuInfo;
import com.changgou.search.service.ESManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年06月07日  17:23
 */
@Service
public class ESManagerServiceImpl implements ESManagerService {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private ESManagerMapper esManagerMapper;

    @Autowired
    private SkuFeign skuFeign;

    /**
     * 创建索引库结构
     */
    @Override
    public void createMappingAndIndex() {
        // 创建索引
        elasticsearchRestTemplate.createIndex(SkuInfo.class);
        // 创建映射
        elasticsearchRestTemplate.putMapping(SkuInfo.class);
    }

    /**
     * 导入全部数据进入es
     */
    @Override
    public void importAll() {
        // 查询sku集合
        List<Sku> skuList = skuFeign.findSkusBySpuId("all");
        saveSkuInfoList(skuList);
    }

    /**
     * 根据spuID查询skuList，再导入索引库
     *
     * @param spuId
     */
    @Override
    public void importDataBySpuId(String spuId) {
        List<Sku> skuList = skuFeign.findSkusBySpuId(spuId);
        saveSkuInfoList(skuList);
    }

    @Override
    public void deleteDataBySpuId(String spuId) {
        List<Sku> skuList = skuFeign.findSkusBySpuId(spuId);
        if (skuList == null || skuList.isEmpty()) {
            throw new RuntimeException("当前没有数据被查询到，无法导入索引库");
        }
        for (Sku sku : skuList) {
            esManagerMapper.deleteById(Long.parseLong(sku.getId()));
        }
    }

    @Override
    public void importByPage(Integer page, Integer size) {
        // 查询sku集合
        List<Sku> skuList = skuFeign.findSkusByPage(page, size);
        saveSkuInfoList(skuList);
    }

    private void saveSkuInfoList(List<Sku> skuList) {
        if (skuList == null || skuList.isEmpty()) {
            throw new RuntimeException("当前没有数据被查询到，无法导入索引库");
        }
        String jsonSkuList = JSON.toJSONString(skuList);
        List<SkuInfo> skuInfoList = JSON.parseArray(jsonSkuList, SkuInfo.class);
        for (SkuInfo skuInfo : skuInfoList) {
            // 将规格信息转换为map
            Map specMap = JSON.parseObject(skuInfo.getSpec(), Map.class);
            skuInfo.setSpecMap(specMap);
        }
        esManagerMapper.saveAll(skuInfoList);
    }
}
