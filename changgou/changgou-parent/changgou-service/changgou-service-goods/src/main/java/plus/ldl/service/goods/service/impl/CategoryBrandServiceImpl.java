package plus.ldl.service.goods.service.impl;

import org.springframework.stereotype.Service;
import plus.ldl.service.goods.mapper.CategoryBrandMapper;
import plus.ldl.service.goods.service.CategoryBrandService;

import javax.annotation.Resource;

/**
 * @author ldl.plus
 * @date 2020年06月02日  14:27
 * $VAR1
 */
@Service
public class CategoryBrandServiceImpl implements CategoryBrandService {

    @Resource
    private CategoryBrandMapper categoryBrandMapper;

}
