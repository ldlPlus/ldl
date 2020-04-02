package plus.ldl.jd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import plus.ldl.jd.dao.ItemDao;
import plus.ldl.jd.entity.JdItem;
import plus.ldl.jd.service.ItemService;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月15日  21:44
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public void save(JdItem item) {
        this.itemDao.save(item);
    }

    @Override
    public List<JdItem> findAll(JdItem item) {

        Example<JdItem> example = Example.of(item);

        return this.itemDao.findAll(example);
    }
}
