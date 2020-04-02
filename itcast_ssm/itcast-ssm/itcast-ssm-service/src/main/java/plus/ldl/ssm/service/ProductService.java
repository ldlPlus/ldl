package plus.ldl.ssm.service;

import plus.ldl.ssm.domain.Product;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月18日  22:54
 */
public interface ProductService {
    List<Product> findAll(int page, int size) throws Exception;

    void save(Product product) throws Exception;
}
