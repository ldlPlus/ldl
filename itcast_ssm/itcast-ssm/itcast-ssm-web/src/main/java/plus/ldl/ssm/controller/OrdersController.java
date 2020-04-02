package plus.ldl.ssm.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import plus.ldl.ssm.domain.Orders;
import plus.ldl.ssm.service.OrdersService;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月20日  12:22
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/findById")
    public ModelAndView findById(ModelAndView mv, @RequestParam(name = "id") int ordersId) throws Exception {
        Orders orders = ordersService.findById(ordersId);
        mv.addObject("orders", orders);
        mv.setViewName("orders-show");
        return mv;
    }

    @GetMapping("/findAll")
    public ModelAndView findAll(ModelAndView mv, @RequestParam(name = "page", defaultValue = "1") int page,
                                @RequestParam(name = "pageSize", defaultValue = "4") int size) throws Exception {
        List<Orders> orders = ordersService.findAll(page, size);
        PageInfo<Orders> pageInfo = new PageInfo<>(orders);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-list");
        return mv;
    }
}
