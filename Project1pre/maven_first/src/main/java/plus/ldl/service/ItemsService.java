package plus.ldl.service;

import plus.ldl.domain.Items;

/**
 * @author ldl.plus
 * @date 2020年03月27日  20:40
 */
public interface ItemsService {
    Items findById(Integer id);

    void update(Items items);
}
