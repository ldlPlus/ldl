package com.changgou.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.entity.util.IdWorker;
import com.changgou.goods.dao.CategoryBrandMapper;
import com.changgou.goods.dao.CategoryMapper;
import com.changgou.goods.dao.SkuMapper;
import com.changgou.goods.dao.SpuMapper;
import com.changgou.goods.pojo.*;
import com.changgou.goods.service.SpuService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    @Override
    public void audit(Long spuId) {
        // 查询商品
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        if ("1".equals(spu.getIsDelete())) {
            // “1” 代表已删除，不用审核
            throw new RuntimeException("商品已删除");
        }
        // 审核通过
        spu.setStatus("1");
        // 上架
        spu.setIsMarketable("1");
        spuMapper.updateByPrimaryKeySelective(spu);

    }

    /**
     * 查询全部列表
     *
     * @return
     */
    @Override
    public List<Spu> findAll() {
        return spuMapper.selectAll();
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public Spu findById(String id) {
        return spuMapper.selectByPrimaryKey(id);
    }


    /**
     * 增加
     *
     * @param goodsDto
     */
    @Override
    public void add(GoodsDto goodsDto) {
        // 添加spu
        Spu spu = goodsDto.getSpu();
        if (spu.getId() == null) {
            spu.setId(idWorker.nextId() + "");
            spu.setIsDelete("0");
            spu.setIsMarketable("0");
            spu.setStatus("0");
            spuMapper.insertSelective(spu);
        } else {
            spuMapper.updateByPrimaryKeySelective(spu);
            Sku sku = new Sku();
            sku.setSpuId(spu.getId());
            skuMapper.delete(sku);
        }
        saveSkus(goodsDto);

    }

    private void saveSkus(GoodsDto goodsDto) {
        Spu spu = goodsDto.getSpu();
        // 添加sku
        // 设置品牌与分类的关联信息
        CategoryBrand categoryBrand = new CategoryBrand();
        categoryBrand.setBrandId(spu.getBrandId());
        categoryBrand.setCategoryId(spu.getCategory3Id());
        int categoryBrandCount = categoryBrandMapper.selectCount(categoryBrand);
        // 如果等于0 建立关联关系
        if (categoryBrandCount == 0) {
            categoryBrandMapper.insert(categoryBrand);
        }

        List<Sku> skus = goodsDto.getSkus();
        if (skus != null && !skus.isEmpty()) {
            for (Sku sku : skus) {
                sku.setId(String.valueOf(idWorker.nextId()));
                if (StringUtils.isEmpty(sku.getSpec())) {
                    sku.setSpec("{}");
                }
                // sku名称
                StringBuilder name = new StringBuilder(spu.getName());
                // 将spec转换成map
                Map specMap = JSON.parseObject(sku.getSpec(), Map.class);
                if (specMap != null && !specMap.isEmpty()) {
                    for (Object value : specMap.values()) {
                        name.append(" ").append(value);
                    }
                }
                sku.setName(name.toString());
                // 设置spu ID
                sku.setSpuId(spu.getId());
                sku.setUpdateTime(new Date());
                sku.setCreateTime(new Date());

                // 查询分类id
                Category category = categoryMapper.selectByPrimaryKey(spu.getCategory3Id());
                sku.setCategoryId(category.getId());
                sku.setCategoryName(category.getName());

                skuMapper.insertSelective(sku);
            }
        }
    }


    /**
     * 修改
     *
     * @param goodsDto
     */
    @Override
    @Transactional
    public void update(GoodsDto goodsDto) {
        // 修改spu
        Spu spu = goodsDto.getSpu();
        spuMapper.updateByPrimaryKey(spu);
        // 删除sku
        Example example = new Example(Sku.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("spuId", spu.getId());
        skuMapper.deleteByExample(example);
        // 添加新sku
        this.saveSkus(goodsDto);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(String id) {
        spuMapper.deleteByPrimaryKey(id);
    }


    /**
     * 条件查询
     *
     * @param searchMap
     * @return
     */
    @Override
    public List<Spu> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return spuMapper.selectByExample(example);
    }

    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Spu> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        return (Page<Spu>) spuMapper.selectAll();
    }

    /**
     * 条件+分页查询
     *
     * @param searchMap 查询条件
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @Override
    public Page<Spu> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = createExample(searchMap);
        return (Page<Spu>) spuMapper.selectByExample(example);
    }

    @Override
    public GoodsDto findGoodsById(String id) {
        GoodsDto goodsDto = new GoodsDto();
        //spu
        Spu spu = spuMapper.selectByPrimaryKey(id);
        goodsDto.setSpu(spu);
        //skuList
        Example example = new Example(Sku.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("spuId", id);
        List<Sku> skus = skuMapper.selectByExample(example);
        goodsDto.setSkus(skus);

        return goodsDto;
    }

    @Override
    @Transactional
    public void pull(Long spuId) {
        // 查询商品
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        if ("1".equals(spu.getIsDelete())) {
            // “1” 代表已删除，不用审核
            throw new RuntimeException("商品已删除");
        }
        // 审核通过
        spu.setStatus("1");
        // 下架
        spu.setIsMarketable("0");
        spuMapper.updateByPrimaryKeySelective(spu);
    }

    @Override
    @Transactional
    public void put(Long spuId) {
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        // 上架
        if ("1".equals(spu.getIsDelete())) {
            // 已删除
            throw new RuntimeException("商品已删除");
        }
        if ("1".equals(spu.getStatus())) {
            throw new RuntimeException("商品未审核");
        }
        spu.setIsMarketable("1");
        spuMapper.updateByPrimaryKeySelective(spu);

    }

    @Override
    @Transactional
    public void putMany(Long[] spuIds) {
        // if (spuIds != null && spuIds.length > 0) {
        //     for (Long spuId : spuIds) {
        //         this.putMany(spuId);
        //     }
        // }
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andIn("spuId", Arrays.asList(spuIds));
        criteria.andEqualTo("isDelete", "0");
        criteria.andEqualTo("status", "1");
        Spu spu = new Spu();
        spu.setIsMarketable("1");
        spuMapper.updateByExampleSelective(spu, example);

    }

    @Override
    @Transactional
    public void realDel(Long spuId) {
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        if ("0".equals(spu.getIsDelete())) {
            // 未逻辑删除，不能物理删除
            throw new RuntimeException("未进行逻辑删除，不能物理删除");
        }
        Sku sku = new Sku();
        sku.setSpuId(spuId + "");
        skuMapper.delete(sku);
        spuMapper.deleteByPrimaryKey(spuId);

    }

    /**
     * 构建查询对象
     *
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap) {
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null) {
            // 主键
            if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                criteria.andEqualTo("id", searchMap.get("id"));
            }
            // 货号
            if (searchMap.get("sn") != null && !"".equals(searchMap.get("sn"))) {
                criteria.andEqualTo("sn", searchMap.get("sn"));
            }
            // SPU名
            if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                criteria.andLike("name", "%" + searchMap.get("name") + "%");
            }
            // 副标题
            if (searchMap.get("caption") != null && !"".equals(searchMap.get("caption"))) {
                criteria.andLike("caption", "%" + searchMap.get("caption") + "%");
            }
            // 图片
            if (searchMap.get("image") != null && !"".equals(searchMap.get("image"))) {
                criteria.andLike("image", "%" + searchMap.get("image") + "%");
            }
            // 图片列表
            if (searchMap.get("images") != null && !"".equals(searchMap.get("images"))) {
                criteria.andLike("images", "%" + searchMap.get("images") + "%");
            }
            // 售后服务
            if (searchMap.get("saleService") != null && !"".equals(searchMap.get("saleService"))) {
                criteria.andLike("saleService", "%" + searchMap.get("saleService") + "%");
            }
            // 介绍
            if (searchMap.get("introduction") != null && !"".equals(searchMap.get("introduction"))) {
                criteria.andLike("introduction", "%" + searchMap.get("introduction") + "%");
            }
            // 规格列表
            if (searchMap.get("specItems") != null && !"".equals(searchMap.get("specItems"))) {
                criteria.andLike("specItems", "%" + searchMap.get("specItems") + "%");
            }
            // 参数列表
            if (searchMap.get("paraItems") != null && !"".equals(searchMap.get("paraItems"))) {
                criteria.andLike("paraItems", "%" + searchMap.get("paraItems") + "%");
            }
            // 是否上架
            if (searchMap.get("isMarketable") != null && !"".equals(searchMap.get("isMarketable"))) {
                criteria.andEqualTo("isMarketable", searchMap.get("isMarketable"));
            }
            // 是否启用规格
            if (searchMap.get("isEnableSpec") != null && !"".equals(searchMap.get("isEnableSpec"))) {
                criteria.andEqualTo("isEnableSpec", searchMap.get("isEnableSpec"));
            }
            // 是否删除
            if (searchMap.get("isDelete") != null && !"".equals(searchMap.get("isDelete"))) {
                criteria.andEqualTo("isDelete", searchMap.get("isDelete"));
            }
            // 审核状态
            if (searchMap.get("status") != null && !"".equals(searchMap.get("status"))) {
                criteria.andEqualTo("status", searchMap.get("status"));
            }

            // 品牌ID
            if (searchMap.get("brandId") != null) {
                criteria.andEqualTo("brandId", searchMap.get("brandId"));
            }
            // 一级分类
            if (searchMap.get("category1Id") != null) {
                criteria.andEqualTo("category1Id", searchMap.get("category1Id"));
            }
            // 二级分类
            if (searchMap.get("category2Id") != null) {
                criteria.andEqualTo("category2Id", searchMap.get("category2Id"));
            }
            // 三级分类
            if (searchMap.get("category3Id") != null) {
                criteria.andEqualTo("category3Id", searchMap.get("category3Id"));
            }
            // 模板ID
            if (searchMap.get("templateId") != null) {
                criteria.andEqualTo("templateId", searchMap.get("templateId"));
            }
            // 运费模板id
            if (searchMap.get("freightId") != null) {
                criteria.andEqualTo("freightId", searchMap.get("freightId"));
            }
            // 销量
            if (searchMap.get("saleNum") != null) {
                criteria.andEqualTo("saleNum", searchMap.get("saleNum"));
            }
            // 评论数
            if (searchMap.get("commentNum") != null) {
                criteria.andEqualTo("commentNum", searchMap.get("commentNum"));
            }

        }
        return example;
    }


}
