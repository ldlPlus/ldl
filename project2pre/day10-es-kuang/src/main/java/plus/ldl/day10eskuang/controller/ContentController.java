package plus.ldl.day10eskuang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import plus.ldl.day10eskuang.service.ContentService;

import java.util.List;
import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年05月15日  19:26
 */
@RestController
public class ContentController {
    @Autowired
    private ContentService contentService;

    @GetMapping("/search/{keywords}/{pageNum}/{pageSize}")
    public List<Map<String, Object>> searchPage(@PathVariable("keywords") String keywords, @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) throws Exception {
        return contentService.searchPage(keywords, pageNum, pageSize);
    }

    @GetMapping("/update/{keywords}")
    public Boolean parseContent(@PathVariable("keywords") String keywords) throws Exception {
        return contentService.parseContent(keywords);
    }
}
