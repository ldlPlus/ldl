/*
 * 版权所有(C)，ldl公司，2020，所有权利保留。
 *
 * 项目名： travel
 * 文件名： FavoriteDao.java
 * 模块说明：
 * 修改历史:
 * 2020-2-29 - lidonglin - 创建。
 */

package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

import java.util.List;
import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年02月29日  22:53
 */
public interface FavoriteDao {
    Favorite findByRidAndUid(int rid, int uid);

    int findTimes(int rid);

    void cancelCollection(int rid, int uid);

    void collect(int rid, int uid);

    List<Map<String, Object>> findByUid(int uid);

    List<Map<String, Object>> findAll();

    List<Map<String, Object>> findHot(int limit);
}
