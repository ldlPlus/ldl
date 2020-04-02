package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.*;
import cn.itcast.travel.service.*;
import cn.itcast.travel.util.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年02月28日  22:24
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService routeService = (RouteService) BeanFactory.getBean("routeService");
    private CategoryService categoryService = (CategoryService) BeanFactory.getBean("categoryService");
    private RouteImgService routeImgService = (RouteImgService) BeanFactory.getBean("routeImgService");
    private SellerService sellerService = (SellerService) BeanFactory.getBean("sellerService");
    private FavoriteService favoriteService = (FavoriteService) BeanFactory.getBean("favoriteService");

    public void pageQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentPage = req.getParameter("currentPage");
        if (currentPage == null || currentPage.length() == 0 || "null".equals(currentPage) || "NaN".equals(currentPage)) {
            currentPage = "1";
        }

        String pageSize = req.getParameter("pageSize");
        if (pageSize == null || pageSize.length() == 0 || "null".equals(pageSize)) {
            pageSize = "5";
        }

        String cid = req.getParameter("cid");

        String rname = req.getParameter("rname");

        PageBean<Route> pb = routeService.pageQuery(currentPage, pageSize, cid, rname);

        writeValue(pb, resp);
    }

    public void findCid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cid = req.getParameter("cid");
        if (cid == null || cid.length() == 0) {
            cid = "1";
        }

        String cname = categoryService.findCid(cid);
        resp.getWriter().write(cname);
    }

    public void findDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rid = req.getParameter("rid");
        if (rid != null && rid.length() != 0 && !"null".equals(rid)) {
            //查询Route基本信息
            Route route = routeService.lineDetails(rid);
            //查询并设置RouteImg图片信息
            List<RouteImg> routeImgs = routeImgService.findByRid(rid);
            route.setRouteImgList(routeImgs);
            //查询并设置Category信息
            Category category = categoryService.findCidCategory(route.getCid());
            route.setCategory(category);
            //查询并设置Seller信息
            Seller seller = sellerService.findBySid(route.getSid());
            route.setSeller(seller);

            writeValue(route, resp);
        }
    }

    public void newest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int limit = 4;
        String json = routeService.findNewest(limit);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    public void theme(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int limit = 4;
        String json = routeService.findTheme(limit);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    public void recommend(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cid = req.getParameter("cid");
        String json = routeService.findRouteByCid(Integer.parseInt(cid));
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }
}
