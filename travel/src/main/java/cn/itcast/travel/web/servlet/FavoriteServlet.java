/*
 * 版权所有(C)，ldl公司，2020，所有权利保留。
 *
 * 项目名： travel
 * 文件名： FavoriteServlet.java
 * 模块说明：
 * 修改历史:
 * 2020-2-29 - lidonglin - 创建。
 */

package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.util.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年02月29日  22:20
 */
@WebServlet("/favorite/*")
public class FavoriteServlet extends BaseServlet {
    private FavoriteService favoriteService = (FavoriteService) BeanFactory.getBean("favoriteService");
    private RouteService routeService = (RouteService) BeanFactory.getBean("routeService");

    public void isFavorite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ridStr = req.getParameter("rid");
        String toggleFavorite = req.getParameter("toggleFavorite");
        if (ridStr != null && ridStr.length() != 0 && !"null".equals(ridStr)) {
            int rid = Integer.parseInt(ridStr);
            User user = (User) req.getSession().getAttribute("user");
            boolean isFavorite;
            if (user != null) {
                int uid = user.getUid();

                if ("quxiao".equals(toggleFavorite)) {
                    //取消收藏
                    favoriteService.cancelCollection(rid, uid);
                    //收藏次数
                    int times = favoriteService.findTimes(rid);

                    HashMap<String, Object> map = new HashMap<>();
                    map.put("isFavorite", false);
                    map.put("times", times);
                    writeValue(map, resp);
                    return;
                } else if ("shouchang".equals(toggleFavorite)) {
                    //收藏
                    favoriteService.collect(rid, uid);

                    //收藏次数
                    int times = favoriteService.findTimes(rid);

                    HashMap<String, Object> map = new HashMap<>();
                    map.put("isFavorite", true);
                    map.put("times", times);
                    writeValue(map, resp);
                    return;
                }
                //是否收藏
                isFavorite = favoriteService.findByRidAndUid(rid, uid);
            } else {
                //未登录
                if ("shouchang".equals(toggleFavorite)) {
                    //尚未登录还想收藏，跳转到登录页面
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("login", true);
                    int times = favoriteService.findTimes(rid);
                    map.put("isFavorite", false);
                    map.put("times", times);
                    writeValue(map, resp);
                    return;
                }
                isFavorite = false;
            }

            //收藏次数
            int times = favoriteService.findTimes(rid);

            HashMap<String, Object> map = new HashMap<>();
            map.put("isFavorite", isFavorite);
            map.put("times", times);
            writeValue(map, resp);
        }
    }

    public void myFavorite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            //登录状态
            List<Map<String, Object>> ridList = favoriteService.findByUid(user.getUid());
            //查询条件in（list）；
            ArrayList<Integer> list = new ArrayList<>();
            for (Map<String, Object> rids : ridList) {
                int rid = (int) rids.get("rid");
                list.add(rid);
            }

            String currentPage = req.getParameter("currentPage");
            if (currentPage == null || currentPage.length() == 0 || "null".equals(currentPage)) {
                currentPage = "1";
            }
            int currentPages = Integer.parseInt(currentPage);

            List<Route> routeList = routeService.findRouteByRids(list);
            if (routeList != null) {

                PageBean<Route> pageBean = new PageBean<>();
                int pageSize = 12;
                pageBean.setPageSize(pageSize);
                pageBean.setCurrentPage(currentPages);
                int totalCount = routeList.size();
                pageBean.setTotalCount(totalCount);
                int totalPage = (totalCount - 1 + pageSize) / pageSize;
                pageBean.setTotalPage(totalPage);

                //判断当前页数，返回数据
                ArrayList<Route> returnList = new ArrayList<>();
                int start = (currentPages - 1) * pageSize;
                try {
                    for (int i = start; i < start + pageSize; i++) {
                        Route route = routeList.get(i);
                        if (route != null) {
                            returnList.add(route);
                        }
                    }
                } catch (Exception ignored) {
                }
                pageBean.setData(returnList);
                writeValue(pageBean, resp);
            }
        }
    }

    public void favoriteRank(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentPageStr = req.getParameter("currentPage");
        if (currentPageStr == null || "".equals(currentPageStr) || "null".equals(currentPageStr)) {
            currentPageStr = "1";
        }

        String name = req.getParameter("name");
        String minMoney = req.getParameter("minMoney");
        String maxMoney = req.getParameter("maxMoney");

        int currentPage = Integer.parseInt(currentPageStr);
        PageBean<Route> routePageBean = new PageBean<>();
        //一页显示8个数据
        int pageSize = 8;
        routePageBean.setPageSize(pageSize);

        routePageBean.setCurrentPage(currentPage);


        //收藏大于10个rid并且按收藏量降序排列  所有数据
        List<Route> routeBank = routeService.findRouteBank(currentPage, name, minMoney, maxMoney);

        ArrayList<Route> returnList = new ArrayList<>();

        int start = (currentPage - 1) * 8;
        int totalCount = routeBank.size();
        if (start >= totalCount) {
            start = 0;
        }
        int end = start + 8;
        if (end >= totalCount) {
            end = totalCount;
        }


        for (int i = start; i < end; i++) {
            //获取Route
            Route route = routeBank.get(i);
            //设置收藏次数
            route.setCount(favoriteService.findTimes(route.getRid()));
            returnList.add(route);
        }

        routePageBean.setTotalCount(totalCount);
        routePageBean.setTotalPage((totalCount - 1 + pageSize) / pageSize);
        routePageBean.setData(returnList);

        writeValue(routePageBean, resp);
    }

    public void bestHot(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String limit = req.getParameter("limit");
        if (limit == null || limit.length() == 0) {
            limit = "5";
        }
        List<Map<String, Object>> list = favoriteService.findHot(Integer.parseInt(limit));
        ArrayList<Integer> ridList = new ArrayList<>();
        for (Map<String, Object> map : list) {
            int rid = (int) map.values().toArray()[0];
            ridList.add(rid);
        }
        List<Route> routeList = routeService.findRouteByRids(ridList);
        writeValue(routeList, resp);
    }
}
