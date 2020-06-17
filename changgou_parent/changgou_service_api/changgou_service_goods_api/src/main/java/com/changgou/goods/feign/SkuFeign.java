package com.changgou.goods.feign;

import com.changgou.entity.Result;
import com.changgou.goods.pojo.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年06月07日  17:11
 */
@FeignClient(name = "goods")
public interface SkuFeign {
    @GetMapping("/sku/spu/{spuId}")
    List<Sku> findSkusBySpuId(@PathVariable("spuId") String spuId);

    @GetMapping("/sku/spu/{page}/{size}")
    List<Sku> findSkusByPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size);

    @GetMapping("/sku/{id}")
    Result<Sku> findById(@PathVariable("id") Long id);
}
