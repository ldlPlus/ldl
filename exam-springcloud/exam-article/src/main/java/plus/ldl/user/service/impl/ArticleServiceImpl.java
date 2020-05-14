package plus.ldl.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plus.ldl.user.domain.Article;
import plus.ldl.user.mapper.ArticleMapper;
import plus.ldl.user.service.ArticleService;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年05月14日  18:27
 * $VAR1
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return articleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Article record) {
        return articleMapper.insert(record);
    }

    @Override
    public Article selectByPrimaryKey(Long id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Article record) {
        return articleMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Article> findAll() {
        return articleMapper.findAll();
    }
}
