package plus.ldl.jd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import plus.ldl.jd.entity.JdItem;

/**
 * @author ldl.plus
 * @date 2020年03月15日  12:38
 */
public interface ItemDao extends JpaRepository<JdItem, Long> {

}
