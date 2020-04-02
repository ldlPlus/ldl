package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.impl.CategoryServiceImpl;
import cn.itcast.travel.util.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年02月28日  19:59
 * 路线相关Servlet
 */
@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private CategoryServiceImpl categoryService = (CategoryServiceImpl) BeanFactory.getBean("categoryService");

    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categoryList = categoryService.findAll();
        writeValue(categoryList, resp);
    }

}
