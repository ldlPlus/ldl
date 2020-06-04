package plus.ldl.service.goods.service.impl;

import org.springframework.stereotype.Service;
import plus.ldl.service.goods.mapper.CategoryMapper;
import plus.ldl.service.goods.service.CategoryService;

import javax.annotation.Resource;

/**
 * @author ldl.plus
 * @date 2020年06月02日  14:27
 * $VAR1
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

}
