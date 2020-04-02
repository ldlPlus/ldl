package plus.ldl.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import plus.ldl.domain.User;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月26日  23:01
 */
public interface UserDao {

    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where id = #{id} ")
    User findById(Integer id);

    @Update("update user set username=#{username} ,password=#{password} ,age=#{age} ,email=#{email} ,sex=#{sex} where id=#{id} ")
    void updateUser(User user);
}
