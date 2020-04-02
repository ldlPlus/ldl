package plus.ldl.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plus.ldl.ssm.dao.ProductDao;
import plus.ldl.ssm.domain.Product;
import plus.ldl.ssm.service.ProductService;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月18日  22:55
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAll(int page, int size) throws Exception {
        PageHelper.startPage(page, size);
        return productDao.findAll();
    }

    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }
}
