package plus.ldl.ssm.dao;

import org.apache.ibatis.annotations.Select;
import plus.ldl.ssm.domain.Traveller;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月20日  12:58
 */
public interface TravellerDao {

    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId = #{ordersId} )")
    List<Traveller> findByOrdersId(String ordersId) throws Exception;
}
