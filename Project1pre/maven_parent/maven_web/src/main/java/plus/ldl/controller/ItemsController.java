package plus.ldl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import plus.ldl.domain.Items;
import plus.ldl.service.ItemsService;

/**
 * @author ldl.plus
 * @date 2020年03月27日  20:32
 */
@Controller
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    @GetMapping("/findDetail")
    public ModelAndView findDetail(ModelAndView mv, Integer id) {
        Items items = itemsService.findById(id);
        mv.addObject("item", items);
        mv.setViewName("itemDetail");
        return mv;
    }

    @PostMapping("/findById/{id}")
    @ResponseBody
    public Items findById(@PathVariable Integer id) {
        return itemsService.findById(id);
    }
}
