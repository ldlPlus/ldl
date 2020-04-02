package plus.ldl.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import plus.ldl.dao.ItemsDao;
import plus.ldl.domain.Items;
import plus.ldl.service.ItemsService;

/**
 * @author ldl.plus
 * @date 2020年03月27日  20:38
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ItemsDaoTest {

    @Autowired
    private ItemsDao itemsDao;

    @Autowired
    private ItemsService itemsService;

    @Test
    public void findById() {
        Items items = itemsDao.findById(1);
        System.out.println("items = " + items);
    }

    /**
     *
     */
    @Test
    public void test1() throws Exception {
        Items items = itemsService.findById(1);
        System.out.println("items = " + items);
    }
}