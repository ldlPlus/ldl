package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年02月28日  22:45
 */
public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(String cid, String rname) {
        try {
            StringBuilder sb = new StringBuilder("select count(rid) from travel.tab_route where 1 = 1  ");
            ArrayList<Object> parameter = new ArrayList<>();
            if (rname != null && rname.length() > 0 && !"null".equals(rname)) {
                sb.append("and rname like ? ");
                parameter.add("%" + rname + "%");
            }
            if (cid != null && !"null".equals(cid) && !"".equals(cid)) {
                int intCid = Integer.parseInt(cid);
                sb.append("and cid = ? ");
                parameter.add(intCid);
            }
            return template.queryForObject(sb.toString(),
                    Integer.class, parameter.toArray());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<Route> findQueryPage(int start, int pageSize, String cid, String rname) {
        StringBuilder sb = new StringBuilder("select * from travel.tab_route where 1 = 1  ");
        ArrayList<Object> parameter = new ArrayList<>();
        if (rname != null && rname.length() > 0 && !"null".equals(rname)) {
            sb.append("and rname like ? ");
            parameter.add("%" + rname + "%");
        }
        if (cid != null && !"null".equals(cid) && !"".equals(cid)) {
            int intCid = Integer.parseInt(cid);
            sb.append("and cid = ? ");
            parameter.add(intCid);
        }
        sb.append(" limit ?,?");
        parameter.add(start);
        parameter.add(pageSize);
        return template.query(sb.toString(),
                new BeanPropertyRowMapper<>(Route.class), parameter.toArray());
    }

    @Override
    public Route findLineDetails(int rid) {
        try {
            return template.queryForObject("select * from travel.tab_route where rid = ?",
                    new BeanPropertyRowMapper<>(Route.class), rid);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Route> findRouteByRids(ArrayList<Integer> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder("select rid, rname,price,rimage from travel.tab_route where  rid IN ( ");
        for (Integer rid : list) {
            sb.append(" ? ,");
        }
        sb.append(" )");

        int lastIndexOf = sb.lastIndexOf(",");
        sb.replace(lastIndexOf, lastIndexOf + 1, "");
        return template.query(sb.toString(),
                new BeanPropertyRowMapper<>(Route.class), list.toArray());
    }

    @Override
    public List<Route> findNewest(int limit) {
        return template.query(
                "select * from travel.tab_route order by rdate desc limit ?",
                new BeanPropertyRowMapper<>(Route.class), limit);
    }

    @Override
    public List<Route> findTheme(int limit) {
        return template.query(
                "select * from travel.tab_route where isThemeTour = 1 order by rdate desc limit ?",
                new BeanPropertyRowMapper<>(Route.class), limit);
    }

    @Override
    public List<Route> findRouteByCid(int cid) {
        return template.query(
                "select favorite.rid, count(favorite.rid) total, route.rname, route.price, route.rimage\n" +
                        "from travel.tab_favorite favorite\n" +
                        "         left join travel.tab_route route ON favorite.rid = route.rid\n" +
                        "where cid = ?\n" +
                        "group by favorite.rid\n" +
                        "order by total DESC\n" +
                        "LIMIT 6",
                new BeanPropertyRowMapper<>(Route.class), cid);
    }

    @Override
    public List<Route> findRouteBank(int currentPage, String name, String minMoney, String maxMoney) {
        StringBuilder sql = new StringBuilder(
                "select favorite.rid, count(favorite.rid) total, route.rname, route.price, route.rimage from " +
                        "travel.tab_favorite favorite left join travel.tab_route route " +
                        "ON favorite.rid = route.rid where 1 = 1 ");
        ArrayList<Object> condition = new ArrayList<>();
        if (name != null && name.length() > 0) {
            sql.append(" and route.rname like ? ");
            condition.add("%" + name + "%");
        }
        if (minMoney != null && minMoney.length() > 0) {
            sql.append(" and route.price >= ? ");
            condition.add(minMoney);
        }
        if (maxMoney != null && maxMoney.length() > 0) {
            sql.append(" and route.price <= ? ");
            condition.add(maxMoney);
        }

        // sql.append(" group by favorite.rid having total > 10 order by total DESC limit ? , 8");
        sql.append(" group by favorite.rid having total > 10 order by total DESC");
        // condition.add((currentPage - 1) * 8);
        return template.query(sql.toString(),
                new BeanPropertyRowMapper<>(Route.class), condition.toArray());
    }

}
