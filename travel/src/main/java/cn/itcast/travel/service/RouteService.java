package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年02月28日  22:24
 */
public interface RouteService {
    PageBean<Route> pageQuery(String currentPage, String pageSize, String cid, String rname);

    Route lineDetails(String rid);

    List<Route> findRouteByRids(ArrayList<Integer> list);

    String findNewest(int limit);

    String findTheme(int limit);

    String findRouteByCid(int cid);

    List<Route> findRouteBank(int currentPage, String name, String minMoney, String maxMoney);
}
