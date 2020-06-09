package com.changgou.goods.service;

import com.changgou.goods.pojo.GoodsDto;
import com.changgou.goods.pojo.Spu;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface SpuService {

    /**
     * 商品审核
     *
     * @param spuId
     */
    void audit(Long spuId);

    /***
     * 查询所有
     * @return
     */
    List<Spu> findAll();

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    Spu findById(String id);

    /***
     * 新增
     * @param goodsDto
     */
    void add(GoodsDto goodsDto);

    /***
     * 修改
     * @param goodsDto
     */
    void update(GoodsDto goodsDto);

    /***
     * 删除
     * @param id
     */
    void delete(String id);

    /***
     * 多条件搜索
     * @param searchMap
     * @return
     */
    List<Spu> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Spu> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<Spu> findPage(Map<String, Object> searchMap, int page, int size);


    GoodsDto findGoodsById(String id);

    void pull(Long spuId);

    void put(Long spuId);

    void putMany(Long[] spuIds);

    void realDel(Long spuId);
}
