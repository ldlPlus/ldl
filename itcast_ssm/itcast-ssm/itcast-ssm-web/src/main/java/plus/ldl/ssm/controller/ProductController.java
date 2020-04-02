package plus.ldl.ssm.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import plus.ldl.ssm.domain.Product;
import plus.ldl.ssm.service.ProductService;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月18日  23:16
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/findAll")
    public ModelAndView findAll(ModelAndView mv, @RequestParam(name = "page", defaultValue = "1") int page,
                                @RequestParam(name = "pageSize", defaultValue = "8") int size) throws Exception {
        List<Product> products = productService.findAll(page, size);
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("product-list");
        return mv;
    }

    @PostMapping("/save")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:/product/findAll";
    }
}
