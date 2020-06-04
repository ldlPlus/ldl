package plus.ldl.service.goods.service.impl;

import org.springframework.stereotype.Service;
import plus.ldl.service.goods.mapper.AlbumMapper;
import plus.ldl.service.goods.service.AlbumService;

import javax.annotation.Resource;

/**
 * @author ldl.plus
 * @date 2020年06月02日  14:27
 * $VAR1
 */
@Service
public class AlbumServiceImpl implements AlbumService {

    @Resource
    private AlbumMapper albumMapper;

}
