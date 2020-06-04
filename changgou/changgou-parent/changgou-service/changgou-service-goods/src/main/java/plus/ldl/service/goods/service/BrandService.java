package plus.ldl.service.goods.service;


import com.github.pagehelper.Page;
import plus.ldl.goods.domain.Brand;

import java.util.List;
import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年06月02日  14:27
 * $VAR1
 */
public interface BrandService {

    /**
     * 品牌列表查询
     *
     * @return
     */
    List<Brand> findList();

    /**
     * 根据id查询品牌
     *
     * @param id
     * @return
     */
    Brand findById(Integer id);

    /**
     * 新增
     *
     * @param brand
     * @return
     */
    int insert(Brand brand);

    int update(Brand brand);

    int deleteById(Integer id);

    List<Brand> findByCondition(Map<String, Object> searchMap);

    Page<Brand> findPage(Integer page, Integer size);

    Page<Brand> findPageByCondition(Integer page, Integer size, Map<String, Object> searchMap);
}
