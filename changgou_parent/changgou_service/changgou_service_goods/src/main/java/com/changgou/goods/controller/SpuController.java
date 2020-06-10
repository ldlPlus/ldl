package com.changgou.goods.controller;

import com.changgou.entity.PageResult;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.pojo.GoodsDto;
import com.changgou.goods.pojo.Spu;
import com.changgou.goods.service.SpuService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/spu")
public class SpuController {


    @Autowired
    private SpuService spuService;

    /**
     * 查询全部数据
     *
     * @return
     */
    @GetMapping
    public Result findAll() {
        List<Spu> spuList = spuService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", spuList);
    }

    /***
     * 根据ID查询数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<GoodsDto> findById(@PathVariable String id) {
        GoodsDto goodsDto = spuService.findGoodsById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", goodsDto);
    }

    @GetMapping("/findSpuById/{id}")
    public Result<Spu> findSpuById(@PathVariable String id) {
        Spu spu = spuService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", spu);
    }


    /***
     * 新增数据
     * @param goodsDto
     * @return
     */
    @PostMapping
    public Result<GoodsDto> add(@RequestBody GoodsDto goodsDto) {
        spuService.add(goodsDto);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }


    /***
     * 修改数据
     * @param goodsDto
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody GoodsDto goodsDto) {
        spuService.update(goodsDto);
        return new Result(true, StatusCode.OK, "修改成功");
    }


    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        spuService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 多条件搜索品牌数据
     * @param searchMap
     * @return
     */
    @GetMapping(value = "/search")
    public Result findList(@RequestParam Map searchMap) {
        List<Spu> list = spuService.findList(searchMap);
        return new Result(true, StatusCode.OK, "查询成功", list);
    }


    /***
     * 分页搜索实现
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result findPage(@RequestParam Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Spu> pageList = spuService.findPage(searchMap, page, size);
        PageResult pageResult = new PageResult(pageList.getTotal(), pageList.getResult());
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    /**
     * 商品审核
     *
     * @param spuId
     * @return
     */
    @PutMapping("/audit/{spuId}")
    public Result audit(@PathVariable Long spuId) {
        spuService.audit(spuId);
        return new Result(true, StatusCode.OK, "审核成功");
    }

    /**
     * 商品下架
     */
    @PutMapping("/pull/{spuId}")
    public Result pull(@PathVariable Long spuId) {
        spuService.pull(spuId);
        return new Result(true, StatusCode.OK, "下架成功");
    }

    /**
     * 商品上架
     */
    @PutMapping("/put/{spuId}")
    public Result put(@PathVariable Long spuId) {
        spuService.put(spuId);
        return new Result(true, StatusCode.OK, "上架成功");
    }

    /**
     * 批量上架
     */
    @PutMapping("/put/many")
    public Result putMany(@RequestBody Long[] spuIds) {
        spuService.putMany(spuIds);
        return new Result(true, StatusCode.OK, "上架成功");
    }

    @DeleteMapping("/realDel/{spuId}")
    public Result realDel(@PathVariable("spuId") Long spuId) {
        spuService.realDel(spuId);
        return new Result(true, StatusCode.OK, "真实删除成功");
    }
}
