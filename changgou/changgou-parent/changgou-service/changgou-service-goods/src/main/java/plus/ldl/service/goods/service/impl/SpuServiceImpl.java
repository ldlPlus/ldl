package plus.ldl.service.goods.service.impl;

import org.springframework.stereotype.Service;
import plus.ldl.service.goods.mapper.SpuMapper;
import plus.ldl.service.goods.service.SpuService;

import javax.annotation.Resource;

/**
 * @author ldl.plus
 * @date 2020年06月02日  14:27
 * $VAR1
 */
@Service
public class SpuServiceImpl implements SpuService {

    @Resource
    private SpuMapper spuMapper;

}
