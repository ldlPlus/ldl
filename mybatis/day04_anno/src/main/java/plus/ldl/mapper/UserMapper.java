package plus.ldl.mapper;

import org.apache.ibatis.annotations.*;
import plus.ldl.domain.User;

import java.util.List;

/**
 * @author plus.ldl
 * @date 2020年03月23日  11:39
 */
public interface UserMapper {

    @Select("select * from user")
    List<User> findAll();

    @Insert("insert into user(username, password, birthday) VALUES (#{username} ,#{password} ,#{birthday} )")
    void insert(User user);

    @Select("select * from user where id = #{id} ")
    User findById(int id);

    @Select("select * from user")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "birthday", property = "birthday"),
            @Result(
                    property = "orders",
                    column = "id",
                    javaType = List.class,
                    many = @Many(select = "plus.ldl.mapper.OrderMapper.findByUid")
            )
    })
    List<User> findAllAndOrders();

    @Select("select * from user")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "birthday", property = "birthday"),
            @Result(
                    property = "orders",
                    column = "id",
                    javaType = List.class,
                    many = @Many(select = "plus.ldl.mapper.OrderMapper.findByUid")
            ),
            @Result(
                    property = "roles",
                    column = "id",
                    javaType = List.class,
                    many = @Many(select = "plus.ldl.mapper.RoleMapper.findByUid")
            )
    })
    List<User> findAllWithRoles();
}
