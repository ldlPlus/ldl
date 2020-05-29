package plus.ldl.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.ldl.domain.Goods;
import plus.ldl.feign.GoodsInterface;

import javax.annotation.Resource;

/**
 * @author ldl.plus
 * @date 2020年05月24日  16:55
 */
@RestController
@RequestMapping("/consumer")
public class GoodsController {

    @Resource
    private GoodsInterface goodsInterface;

    @GetMapping("/order/{id}")
    public Goods find(@PathVariable("id") int id) {
        return goodsInterface.findOne(id);
    }

}
