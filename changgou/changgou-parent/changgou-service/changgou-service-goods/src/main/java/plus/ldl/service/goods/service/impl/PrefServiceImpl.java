package plus.ldl.service.goods.service.impl;

import org.springframework.stereotype.Service;
import plus.ldl.service.goods.mapper.PrefMapper;
import plus.ldl.service.goods.service.PrefService;

import javax.annotation.Resource;

/**
 * @author ldl.plus
 * @date 2020年06月02日  14:27
 * $VAR1
 */
@Service
public class PrefServiceImpl implements PrefService {

    @Resource
    private PrefMapper prefMapper;

}
