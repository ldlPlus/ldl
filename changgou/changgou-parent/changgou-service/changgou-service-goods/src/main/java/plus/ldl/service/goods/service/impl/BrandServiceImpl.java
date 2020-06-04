package plus.ldl.service.goods.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plus.ldl.goods.domain.Brand;
import plus.ldl.service.goods.mapper.BrandMapper;
import plus.ldl.service.goods.service.BrandService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年06月02日  14:27
 * $VAR1
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findList() {
        return brandMapper.selectAll();
    }

    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int insert(Brand brand) {
        return brandMapper.insert(brand);
    }

    @Override
    public int update(Brand brand) {
        return brandMapper.updateByPrimaryKey(brand);
    }

    @Override
    @Transactional
    public int deleteById(Integer id) {
        return brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Brand> findByCondition(Map<String, Object> searchMap) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null) {
            // 品牌名称模糊查询
            String name = (String) searchMap.get("name");
            if (name != null && !"".equals(name)) {
                criteria.andLike("name", "%" + name + "%");
            }
            // 按照品牌首字母精确查询
            String letter = (String) searchMap.get("letter");
            if (letter != null && !"".equals(letter)) {
                criteria.andEqualTo("letter", letter);
            }
        }
        return brandMapper.selectByExample(example);
    }

    @Override
    public Page<Brand> findPage(Integer page, Integer size) {
        if (page == null || page < 0) {
            page = 1;
        }
        if (size == null || size < 0) {
            size = 10;
        }
        PageHelper.startPage(page, size);
        return (Page<Brand>) brandMapper.selectAll();
    }

    @Override
    public Page<Brand> findPageByCondition(Integer page, Integer size, Map<String, Object> searchMap) {
        if (page == null || page < 0) {
            page = 1;
        }
        if (size == null || size < 0) {
            size = 10;
        }
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null) {
            // 品牌名称模糊查询
            String name = (String) searchMap.get("name");
            if (name != null && !"".equals(name)) {
                criteria.andLike("name", "%" + name + "%");
            }
            // 按照品牌首字母精确查询
            String letter = (String) searchMap.get("letter");
            if (letter != null && !"".equals(letter)) {
                criteria.andEqualTo("letter", letter);
            }
        }
        PageHelper.startPage(page, size);
        return (Page<Brand>) brandMapper.selectByExample(example);
    }
}
