package plus.ldl.mapper;

import plus.ldl.domain.Orders;

import java.util.List;

/**
 * @author plus.ldl.plus
 * @date 2020年03月23日  10:10
 */
public interface OrderMapper {
    List<Orders> findAll();
}
