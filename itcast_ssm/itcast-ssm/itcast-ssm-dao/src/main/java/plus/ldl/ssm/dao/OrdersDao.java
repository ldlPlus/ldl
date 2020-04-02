package plus.ldl.ssm.dao;

import org.apache.ibatis.annotations.*;
import plus.ldl.ssm.domain.Member;
import plus.ldl.ssm.domain.Orders;
import plus.ldl.ssm.domain.Product;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月20日  12:39
 */
public interface OrdersDao {

    @Select("select * from orders where id=#{ordersId} ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class,
                    one = @One(select = "plus.ldl.ssm.dao.ProductDao.findById")),
            @Result(property = "member", column = "memberId", javaType = Member.class,
                    one = @One(select = "plus.ldl.ssm.dao.MemberDao.findById")),
            @Result(property = "travellers", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "plus.ldl.ssm.dao.TravellerDao.findByOrdersId"))
    })
    Orders findById(int ordersId) throws Exception;

    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class,
                    one = @One(select = "plus.ldl.ssm.dao.ProductDao.findById"))
    })
    List<Orders> findAll() throws Exception;
}
