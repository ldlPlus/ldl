package plus.ldl.mapper;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import plus.ldl.domain.Orders;
import plus.ldl.domain.User;

import java.util.List;

/**
 * @author plus.ldl.plus
 * @date 2020年03月23日  10:10
 */
public interface OrderMapper {
    /**
     * 查询所有订单并且查询订单所对应的User
     *
     * @return orders集合
     */
    @Select("select * from orders")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "total", property = "total"),
            @Result(
                    column = "uid",
                    property = "user",
                    javaType = User.class,
                    one = @One(select = "plus.ldl.mapper.UserMapper.findById"))
    })
    List<Orders> findAllAndUser();

    @Select("select * from orders where uid=#{id} ")
    List<Orders> findByUid(int id);

}
