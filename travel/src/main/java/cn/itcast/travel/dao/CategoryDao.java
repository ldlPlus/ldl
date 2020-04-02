package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年02月28日  20:03
 */
public interface CategoryDao {
    List<Category> findAll();

    Category findCidCategory(int cid);
}
