/*
 * 版权所有(C)，ldl公司，2020，所有权利保留。
 *
 * 项目名： travel
 * 文件名： FavoriteServiceImpl.java
 * 模块说明：
 * 修改历史:
 * 2020-2-29 - lidonglin - 创建。
 */

package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.util.BeanFactory;

import java.util.List;
import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年02月29日  22:51
 */
public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao = (FavoriteDao) BeanFactory.getBean("favoriteDao");

    @Override
    public boolean findByRidAndUid(int rid, int uid) {
        Favorite favorite = favoriteDao.findByRidAndUid(rid, uid);
        return favorite != null;
    }

    @Override
    public int findTimes(int rid) {
        return favoriteDao.findTimes(rid);
    }

    @Override
    public void cancelCollection(int rid, int uid) {
        favoriteDao.cancelCollection(rid, uid);
    }

    @Override
    public void collect(int rid, int uid) {
        favoriteDao.collect(rid, uid);
    }

    @Override
    public List<Map<String, Object>> findByUid(int uid) {
        return favoriteDao.findByUid(uid);
    }

    @Override
    public List<Map<String, Object>> findAll() {
        return favoriteDao.findAll();
    }

    @Override
    public List<Map<String, Object>> findHot(int limit) {
        return favoriteDao.findHot(limit);
    }
}
