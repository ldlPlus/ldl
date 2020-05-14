package plus.ldl.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.ldl.user.domain.Article;
import plus.ldl.user.service.ArticleService;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年05月14日  19:15
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/findAll")
    public List<Article> findAll() {
        return articleService.findAll();
    }
}
