package plus.ldl.mapper;

import plus.ldl.domain.User;


/**
 * @author ldl.plus 
 * @date 2020年03月20日  10:22
 * $VAR1
 */
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}