package plus.ldl.ssm.dao;

import org.apache.ibatis.annotations.*;
import plus.ldl.ssm.domain.User;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月21日  12:58
 */
public interface UserDao {
    @Select("select * from users")
    List<User> findAll() throws Exception;

    @Select("select * from users where id = #{id} ")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = List.class, many = @Many(select = "plus.ldl.ssm.dao.RoleDao.findById")),
    })
    User findById(Integer id) throws Exception;

    @Select("select u.*,r.roleName from users u,role r,users_role ur where u.id=ur.userId and r.id=ur.roleId")
    User findUserByIdAndAllRole(Integer id) throws Exception;

    @Select("select * from users where username = #{username} ")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = List.class, many = @Many(select = "plus.ldl.ssm.dao.RoleDao.findById"))
    })
    User findByUsername(String username);

    @Insert("insert into users(email, username, PASSWORD, phoneNum, STATUS) VALUES (#{email} ,#{username} ,#{password} ,#{phoneNum} ,#{status} )")
    void save(User user) throws Exception;
}
