package plus.ldl.excelpoijob.mapper;

import plus.ldl.excelpoijob.domain.Jianguocun;

import java.util.List;


/**
 * @author ldl.plus
 * @date 2020年05月13日  11:34
 * $VAR1
 */
public interface JianguocunMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Jianguocun record);

    int insertSelective(Jianguocun record);

    Jianguocun selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Jianguocun record);

    int updateByPrimaryKey(Jianguocun record);

    int updateByName(Jianguocun jianguocun);

    Jianguocun findByName(String name, String sex);

    List<Jianguocun> findAll();

    List<String> findHousenumberonesByGroup();

    List<Jianguocun> findByHousenumberones(String housenumberone);
}