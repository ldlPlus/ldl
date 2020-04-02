package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.util.BeanFactory;
import cn.itcast.travel.util.JedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年02月28日  22:36
 */
public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao = (RouteDao) BeanFactory.getBean("routeDao");

    @Override
    public PageBean<Route> pageQuery(String currentPageStr, String pageSizeStr, String cidStr, String rname) {
        PageBean<Route> routePageBean = new PageBean<>();
        int pageSize = Integer.parseInt(pageSizeStr);
        routePageBean.setPageSize(pageSize);

        int currentPage = Integer.parseInt(currentPageStr);
        routePageBean.setCurrentPage(currentPage);

        int totalCount = routeDao.findTotalCount(cidStr, rname);
        routePageBean.setTotalCount(totalCount);

        int totalPage = (totalCount - 1 + pageSize) / pageSize;
        routePageBean.setTotalPage(totalPage);

        int start = (currentPage - 1) * pageSize;
        List<Route> data = routeDao.findQueryPage(start, pageSize, cidStr, rname);
        routePageBean.setData(data);

        return routePageBean;
    }

    @Override
    public Route lineDetails(String rid) {
        //Route基本信息
        return routeDao.findLineDetails(Integer.parseInt(rid));
    }

    @Override
    public List<Route> findRouteByRids(ArrayList<Integer> list) {
        return routeDao.findRouteByRids(list);
    }

    @Override
    public String findNewest(int limit) {
        Jedis jedis = JedisUtil.getJedis();
        String newest = jedis.get("newest");
        if (newest == null || newest.length() == 0) {
            System.err.println("查询缓存中 --findNewest");
            List<Route> routes = routeDao.findNewest(limit);
            ObjectMapper mapper = new ObjectMapper();
            try {
                newest = mapper.writeValueAsString(routes);
                jedis.set("newest", newest);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("查询数据库中 --findNewest");
        }
        jedis.close();
        return newest;
    }

    @Override
    public String findTheme(int limit) {
        Jedis jedis = JedisUtil.getJedis();
        String theme = jedis.get("theme");
        if (theme == null || theme.length() == 0) {
            System.err.println("查询数据库中 --findTheme");
            List<Route> routes = routeDao.findTheme(limit);
            ObjectMapper mapper = new ObjectMapper();
            try {
                theme = mapper.writeValueAsString(routes);
                jedis.set("theme", theme);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("查询缓存中 --findTheme");
        }
        jedis.close();
        return theme;
    }

    @Override
    public String findRouteByCid(int cid) {
        Jedis jedis = JedisUtil.getJedis();
        String recommend = jedis.get("recommend" + cid);
        if (recommend == null || recommend.length() == 0) {
            System.err.println("查询数据库中 --findRouteByCid");
            List<Route> routes = routeDao.findRouteByCid(cid);
            ObjectMapper mapper = new ObjectMapper();
            try {
                recommend = mapper.writeValueAsString(routes);
                jedis.set("recommend" + cid, recommend);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("查询缓存中 --findRouteByCid");
        }
        jedis.close();
        return recommend;
    }

    @Override
    public List<Route> findRouteBank(int currentPage, String name, String minMoney, String maxMoney) {
        return routeDao.findRouteBank(currentPage, name, minMoney, maxMoney);
    }

}
