package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年02月28日  20:01
 */
public interface CategoryService {
    List<Category> findAll();

    String findCid(String cid);

    Category findCidCategory(int cid);
}
