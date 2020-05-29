package plus.ldl.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import plus.ldl.domain.Goods;

/**
 * @author ldl.plus
 * @date 2020年05月24日  11:23
 */
@FeignClient("EUREKA-PROVIDER")
public interface GoodsInterface {
    @GetMapping("/provider/goods/{id}")
    Goods findOne(@PathVariable("id") int id);
}
