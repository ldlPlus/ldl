package plus.ldl.ssm.dao;

import org.apache.ibatis.annotations.Select;
import plus.ldl.ssm.domain.Member;

/**
 * @author ldl.plus
 * @date 2020年03月20日  12:54
 */

public interface MemberDao {
    @Select("select * from member where id = #{id} ")
    Member findById(String id) throws Exception;
}
