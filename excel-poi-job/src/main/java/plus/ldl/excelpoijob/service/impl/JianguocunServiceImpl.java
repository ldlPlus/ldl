package plus.ldl.excelpoijob.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plus.ldl.excelpoijob.domain.Jianguocun;
import plus.ldl.excelpoijob.mapper.JianguocunMapper;
import plus.ldl.excelpoijob.service.JianguocunService;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年05月13日  11:34
 * $VAR1
 */
@Service
public class JianguocunServiceImpl implements JianguocunService {

    @Autowired
    private JianguocunMapper jianguocunMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return jianguocunMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Jianguocun record) {
        return jianguocunMapper.insert(record);
    }

    @Override
    public int insertSelective(Jianguocun record) {
        return jianguocunMapper.insertSelective(record);
    }

    @Override
    public Jianguocun selectByPrimaryKey(Integer id) {
        return jianguocunMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Jianguocun record) {
        return jianguocunMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Jianguocun record) {
        return jianguocunMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByName(Jianguocun jianguocun) {
        return jianguocunMapper.updateByName(jianguocun);
    }

    @Override
    public Jianguocun findByName(String name, String sex) {
        return jianguocunMapper.findByName(name, sex);
    }

    @Override
    public List<Jianguocun> findAll() {
        return jianguocunMapper.findAll();
    }

    @Override
    public List<String> findHousenumberonesByGroup() {
        return jianguocunMapper.findHousenumberonesByGroup();
    }

    @Override
    public List<Jianguocun> findByHousenumberones(String housenumberone) {
        return jianguocunMapper.findByHousenumberones(housenumberone);
    }

}
