/*
 * 版权所有(C)，ldl公司，2020，所有权利保留。
 *
 * 项目名： travel
 * 文件名： RouteImgDaoImpl.java
 * 模块说明：
 * 修改历史:
 * 2020-2-29 - lidonglin - 创建。
 */

package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年02月29日  19:36
 */
public class RouteImgDaoImpl implements RouteImgDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<RouteImg> findByRid(int rid) {
        return template.query("select * from travel.tab_route_img where rid = ?",
                new BeanPropertyRowMapper<>(RouteImg.class), rid);
    }
}
