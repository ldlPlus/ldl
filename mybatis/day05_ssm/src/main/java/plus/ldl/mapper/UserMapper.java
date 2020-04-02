package plus.ldl.mapper;

import org.apache.ibatis.annotations.Select;
import plus.ldl.domain.User;


/**
 * @author ldl.plus
 * @date 2020年03月24日  16:29
 * $VAR1
 */
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Select("select * from db4.user where username = #{username} and password=#{password} ")
    User find2Login(User user);
}