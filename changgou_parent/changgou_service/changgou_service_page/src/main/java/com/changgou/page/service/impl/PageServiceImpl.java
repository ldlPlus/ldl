package com.changgou.page.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.goods.feign.CategoryFeign;
import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.feign.SpuFeign;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.pojo.Sku;
import com.changgou.goods.pojo.Spu;
import com.changgou.page.service.PageService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年06月10日  14:20
 */
@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private SpuFeign spuFeign;

    @Autowired
    private CategoryFeign categoryFeign;

    @Autowired
    private SkuFeign skuFeign;

    @Value("${pagepath}")
    private String pagepath;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 生成静态化页面
     *
     * @param spuId
     */
    @Override
    public void generateHtml(String spuId) {
        // 获取context对象，用于存储商品的相关数据
        Context context = new Context();
        // 获取静态化方法的相关数据
        Map<String, Object> itemData = this.getItemData(spuId);
        context.setVariables(itemData);
        // 获取页面存储位置
        File dir = new File(pagepath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 定义输出流，完成文件的生成
        File file = new File(dir + "/" + spuId + ".html");

        try (Writer out = new PrintWriter(file)) {
            templateEngine.process("item", context, out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取静态化方法的相关数据
     *
     * @param spuId
     * @return
     */
    private Map<String, Object> getItemData(String spuId) {
        HashMap<String, Object> resultMap = new HashMap<>();
        //获取spu
        Spu spu = spuFeign.findSpuById(spuId).getData();
        resultMap.put("spu", spu);
        if (spu != null) {
            // 获取图片信息
            if (StringUtils.isNotEmpty(spu.getImages())) {
                resultMap.put("imageList", spu.getImages().split(","));
            }

            // 获取商品的分类信息
            Category category1 = categoryFeign.findById(spu.getCategory1Id()).getData();
            resultMap.put("category1", category1);

            Category category2 = categoryFeign.findById(spu.getCategory2Id()).getData();
            resultMap.put("category2", category2);

            Category category3 = categoryFeign.findById(spu.getCategory3Id()).getData();
            resultMap.put("category3", category3);

            // 获取sku的相关信息
            List<Sku> skuList = skuFeign.findSkusBySpuId(spuId);
            resultMap.put("skuList", skuList);

            // 获取商品规格信息
            resultMap.put("specificationList", JSON.parseObject(spu.getSpecItems(), Map.class));
        }

        return resultMap;
    }
}
