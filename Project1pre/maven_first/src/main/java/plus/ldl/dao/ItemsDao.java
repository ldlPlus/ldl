package plus.ldl.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import plus.ldl.domain.Items;

/**
 * @author ldl.plus
 * @date 2020年03月27日  20:24
 */
public interface ItemsDao {
    @Select("select * from items where id=#{id} ")
    Items findById(Integer id);

    @Update("update items set name=#{name},price=#{price} where id=#{id} ")
    void update(Items items);
}
