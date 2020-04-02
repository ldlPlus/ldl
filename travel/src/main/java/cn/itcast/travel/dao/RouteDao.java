package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年02月28日  22:39
 */
public interface RouteDao {
    int findTotalCount(String cid, String rname);

    List<Route> findQueryPage(int start, int pageSize, String cid, String rname);

    Route findLineDetails(int rid);

    List<Route> findRouteByRids(ArrayList<Integer> list);

    List<Route> findNewest(int limit);

    List<Route> findTheme(int limit);

    List<Route> findRouteByCid(int cid);

    List<Route> findRouteBank(int currentPage, String name, String minMoney, String maxMoney);
}
