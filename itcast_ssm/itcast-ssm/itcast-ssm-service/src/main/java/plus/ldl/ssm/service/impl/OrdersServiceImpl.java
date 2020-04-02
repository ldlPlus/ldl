package plus.ldl.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plus.ldl.ssm.dao.OrdersDao;
import plus.ldl.ssm.domain.Orders;
import plus.ldl.ssm.service.OrdersService;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月20日  12:37
 */
@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    @Override
    public Orders findById(int ordersId) throws Exception {
        return ordersDao.findById(ordersId);
    }

    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        PageHelper.startPage(page, size);
        return ordersDao.findAll();
    }
}
