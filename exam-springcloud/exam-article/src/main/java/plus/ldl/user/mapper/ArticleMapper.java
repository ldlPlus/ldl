package plus.ldl.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import plus.ldl.user.domain.Article;

import java.util.List;


/**
 * @author ldl.plus
 * @date 2020年05月14日  18:27
 * $VAR1
 */
@Mapper
public interface ArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Article record);

    Article selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Article record);

    List<Article> findAll();
}