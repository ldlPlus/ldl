package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.BeanFactory;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author ldl.plus
 * @date 2020年02月28日  20:02
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = (CategoryDao) BeanFactory.getBean("categoryDao");

    @Override
    public List<Category> findAll() {
        Jedis jedis = JedisUtil.getJedis();
        List<Category> categoryList = new ArrayList<>();
        Set<Tuple> categorySet = jedis.zrangeWithScores("category", 0, -1);
        if (categorySet == null || categorySet.size() == 0) {
            //缓存中没有数据,从数据库查询
            categoryList = categoryDao.findAll();
            //把查询到的数据存入Redis，键为category
            for (Category category : categoryList) {
                jedis.zadd("category", category.getCid(), category.getCname());
            }
            System.err.println("查询数据库category中。。。");
        } else {
            //缓存中有数据，把set转换为list返回
            for (Tuple tuple : categorySet) {
                Category category = new Category();
                category.setCid((int) tuple.getScore());
                category.setCname(tuple.getElement());
                categoryList.add(category);
            }
            System.err.println("查询Redis库category中。。。");
        }
        return categoryList;
    }

    @Override
    public String findCid(String cid) {
        Jedis jedis = JedisUtil.getJedis();
        int index = Integer.parseInt(cid);
        Set<String> category = jedis.zrange("category", index - 1, index - 1);
        if (category != null && category.size() > 0) {
            for (String id : category) {
                return id;
            }
        }
        return null;
    }

    @Override
    public Category findCidCategory(int cid) {
        return categoryDao.findCidCategory(cid);
    }
}
