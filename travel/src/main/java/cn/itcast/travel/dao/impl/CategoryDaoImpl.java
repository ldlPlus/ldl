package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年02月28日  20:03
 */
public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
        return template.query(
                "select * from travel.tab_category order by cid",
                new BeanPropertyRowMapper<>(Category.class));
    }

    @Override
    public Category findCidCategory(int cid) {
        try {
            return template.queryForObject("select * from travel.tab_category where cid = ?",
                    new BeanPropertyRowMapper<>(Category.class), cid);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
