package com.changgou.goods.feign;

import com.changgou.entity.Result;
import com.changgou.goods.pojo.Spu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ldl.plus
 * @date 2020年06月10日  14:08
 */
@FeignClient(name = "goods")
@RequestMapping("/spu")
public interface SpuFeign {
    @GetMapping({"/findSpuById/{id}"})
    Result<Spu> findSpuById(@PathVariable String id);
}
