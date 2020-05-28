package plus.ldl.day10eskuang.service;

import java.util.List;
import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年05月15日  19:26
 */
public interface ContentService {
    Boolean parseContent(String keywords) throws Exception;

    List<Map<String, Object>> searchPage(String keywords, int pageNum, int pageSize) throws Exception;
}
