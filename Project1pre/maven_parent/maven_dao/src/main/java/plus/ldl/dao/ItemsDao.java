package plus.ldl.dao;

import org.apache.ibatis.annotations.Select;
import plus.ldl.domain.Items;

/**
 * @author ldl.plus
 * @date 2020年03月27日  20:24
 */
public interface ItemsDao {
    @Select("select * from items where id=#{id} ")
    Items findById(Integer id);
}
