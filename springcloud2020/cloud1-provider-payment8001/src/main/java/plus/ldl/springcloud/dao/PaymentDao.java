package plus.ldl.springcloud.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import plus.ldl.springcloud.entitier.Payment;

/**
 * @author ldl.plus
 * @date 2020年05月01日  22:15
 */
@Mapper
@Repository
public interface PaymentDao {

    @Insert("insert into payment(serial) VALUES (#{serial} )")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    int create(Payment payment);

    @Select("select * from payment where id=#{id} ")
    Payment findPaymentById(Long id);
}
