package plus.ldl.mapperplusdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import plus.ldl.mapperplusdemo.entity.User;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年05月28日  23:42
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where deleted=1")
    List<User> findDeleted();
}
