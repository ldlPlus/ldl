/*
 * 版权所有(C)，ldl公司，2020，所有权利保留。
 *
 * 项目名： travel
 * 文件名： FavoriteDaoImpl.java
 * 模块说明：
 * 修改历史:
 * 2020-2-29 - lidonglin - 创建。
 */

package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年02月29日  22:53
 */
public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        try {
            return template.queryForObject(
                    "select * from travel.tab_favorite where rid = ? and uid = ?",
                    new BeanPropertyRowMapper<>(Favorite.class), rid, uid);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public int findTimes(int rid) {
        try {
            return template.queryForObject(
                    "select count(rid) from travel.tab_favorite where rid = ?",
                    Integer.class, rid);
        } catch (DataAccessException e) {
            return 0;
        }
    }

    @Override
    public void cancelCollection(int rid, int uid) {
        template.update(
                "delete FROM travel.tab_favorite where rid = ? and uid = ?",
                rid, uid);
    }

    @Override
    public void collect(int rid, int uid) {
        template.update(
                "insert into travel.tab_favorite (rid, date, uid) values (?, ?, ?);",
                rid, new Date(), uid);
    }

    @Override
    public List<Map<String, Object>> findByUid(int uid) {
        return template.queryForList(
                "select rid from travel.tab_favorite where uid = ?",
                uid);
    }

    @Override
    public List<Map<String, Object>> findAll() {
        try {
            return template.queryForList("SELECT rid,count(rid) total FROM travel.tab_favorite  GROUP BY rid having total > 10 order by total desc ");
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> findHot(int limit) {
        return template.queryForList(
                "SELECT rid,count(rid) total FROM travel.tab_favorite  GROUP BY rid ORDER BY total DESC LIMIT ?",
                limit);
    }
}
