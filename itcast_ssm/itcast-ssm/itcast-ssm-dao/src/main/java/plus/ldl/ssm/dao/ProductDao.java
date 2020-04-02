package plus.ldl.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import plus.ldl.ssm.domain.Product;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月18日  22:23
 */
public interface ProductDao {

    @Select("select * from product")
    List<Product> findAll() throws Exception;

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    @Select("select * from product where id = #{id} ")
    Product findById(String id) throws Exception;
}
