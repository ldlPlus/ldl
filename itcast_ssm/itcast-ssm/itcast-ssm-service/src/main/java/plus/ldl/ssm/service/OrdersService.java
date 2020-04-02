package plus.ldl.ssm.service;

import plus.ldl.ssm.domain.Orders;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月20日  12:36
 */
public interface OrdersService {
    Orders findById(int ordersId) throws Exception;

    List<Orders> findAll(int page, int size) throws Exception;
}
