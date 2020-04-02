package plus.ldl.test;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.CategoryServiceImpl;
import cn.itcast.travel.util.BeanFactory;
import cn.itcast.travel.util.JDBCUtils;
import cn.itcast.travel.util.JedisUtil;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ldl.plus
 * @date 2020年02月28日  20:16
 */
public class DemoTest {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     *
     */
    @Test
    public void test14() throws Exception {
        CategoryServiceImpl categoryService = new CategoryServiceImpl();
        List<Category> list = categoryService.findAll();
        for (Category category : list) {
            System.out.println("category = " + category);
        }
    }

    /**
     *
     */
    @Test
    public void test30() throws Exception {
        Jedis jedis = JedisUtil.getJedis();
        String setex = jedis.setex("autoLoginCook", 60 * 60 * 24 * 30, "user.getUsername()");
        System.out.println("setex = " + setex);
    }

    /**
     *
     */
    @Test
    public void test42() throws Exception {
        List<Map<String, Object>> maps = template.queryForList(
                "select rid from travel.tab_favorite where uid = ?",
                4);
        for (Map<String, Object> rids : maps) {
            int rid = (int) rids.get("rid");
            System.out.println(rid);
        }
    }

    /**
     *
     */
    @Test
    public void test61() throws Exception {
        RouteDaoImpl routeDao = new RouteDaoImpl();
        ArrayList<Integer> list = new ArrayList<>();
        // list.add(1);
        List<Route> routeByRids = routeDao.findRouteByRids(list);
        for (Route routeByRid : routeByRids) {
            System.out.println(routeByRid.getRid());
            System.out.println(routeByRid.getPrice());
            System.out.println(routeByRid.getRimage());
        }
    }

    /**
     *
     */
    @Test
    public void test80() throws Exception {


        FavoriteDaoImpl favoriteDao = new FavoriteDaoImpl();
        List<Map<String, Object>> favoriteList = favoriteDao.findAll();
        ArrayList<Integer> listRid = new ArrayList<>();
        ArrayList<Integer> listTotal = new ArrayList<>();
        Object[] array;
        for (Map<String, Object> maps : favoriteList) {
            array = maps.values().toArray();
            int rid = (int) array[0];
            listRid.add(rid);
            long total = (long) array[1];
            listTotal.add((int) total);
        }
        System.out.println("listRid = " + listRid);
        System.out.println(listRid.size());
        System.out.println("listRid = " + listTotal);
        System.out.println(listTotal.size());
        System.out.println(favoriteList.size());
    }


    /**
     *
     */
    @Test
    public void test102() throws Exception {
        List<Map<String, Object>> list = template.queryForList(
                "SELECT rid,count(rid) total FROM travel.tab_favorite GROUP BY rid ORDER BY total DESC LIMIT 5");
        for (Map<String, Object> map : list) {
            for (Object value : map.values()) {
                System.out.println(value);
            }
            System.out.println();
        }

    }

    /**
     * 测试动态代理
     */
    @Test
    public void test124() throws Exception {
        UserDao userDao = (UserDao) BeanFactory.getBean("userDao");
        User zhangsan = userDao.findUserExist("zhangsan");
        System.out.println("zhangsan = " + zhangsan);
    }


    /**
     *
     */
    @Test
    public void test138() throws Exception {
        List<Route> query = template.query("select * from travel.tab_route order by rdate desc limit ?",
                new BeanPropertyRowMapper<>(Route.class), 4);
        for (Route route : query) {
            System.out.println("route = " + route.getRdate());
        }
    }

    /**
     *
     */
    @Test
    public void test151() throws Exception {
        FavoriteDao favoriteDao = (FavoriteDao) BeanFactory.getBean("favoriteDao");
        List<Map<String, Object>> hot = favoriteDao.findHot(6);
        for (Map<String, Object> map : hot) {
            System.out.println("map = " + map);
        }
    }

    /**
     *
     */
    @Test
    public void test164() throws Exception {
        Jedis jedis = JedisUtil.getJedis();
        Set<String> keys = jedis.keys("*");
        System.out.println("keys = " + keys);
        jedis.del("recommend4", "recommend5", "recommend");
    }

    /**
     *
     */
    @Test
    public void test176() throws Exception {
        FavoriteDaoImpl favoriteDao = new FavoriteDaoImpl();
        List<Map<String, Object>> favoriteList = favoriteDao.findAll();
        for (Map<String, Object> map : favoriteList) {
            System.out.println("map = " + map);
        }
        System.out.println();
    }
}
