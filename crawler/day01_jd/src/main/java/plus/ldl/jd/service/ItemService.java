package plus.ldl.jd.service;

import plus.ldl.jd.entity.JdItem;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年03月15日  21:43
 */
public interface ItemService {

    void save(JdItem item);

    List<JdItem> findAll(JdItem item);
}
