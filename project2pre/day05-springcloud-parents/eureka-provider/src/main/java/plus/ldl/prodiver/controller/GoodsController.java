package plus.ldl.prodiver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.ldl.prodiver.domain.Goods;

import java.util.HashMap;

/**
 * @author ldl.plus
 * @date 2020年05月05日  11:40
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @GetMapping("/findOne/{id}")
    public Goods findGoodsById(@PathVariable("id") Integer id) {
        HashMap<Integer, Goods> data = new HashMap<>();
        Goods goods1 = new Goods();
        goods1.setId(1);
        goods1.setName("小米10pro 8+128");
        goods1.setPrice(4999d);
        goods1.setCount(100);

        Goods goods2 = new Goods();
        goods2.setId(2);
        goods2.setName("小米10pro 8+256");
        goods2.setPrice(5499d);
        goods2.setCount(80);

        Goods goods3 = new Goods();
        goods3.setId(3);
        goods3.setName("小米10pro 12+512");
        goods3.setPrice(5999d);
        goods3.setCount(60);

        data.put(goods1.getId(), goods1);
        data.put(goods2.getId(), goods2);
        data.put(goods3.getId(), goods3);
        if (id > data.size()) {
            id = data.size();
        }
        return data.get(id);
    }
}
