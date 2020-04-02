package plus.ldl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plus.ldl.dao.ItemsDao;
import plus.ldl.domain.Items;
import plus.ldl.service.ItemsService;

/**
 * @author ldl.plus
 * @date 2020年03月27日  20:41
 */
@Service("itemsService")
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsDao itemsDao;

    @Override
    public Items findById(Integer id) {
        return itemsDao.findById(id);
    }
}
