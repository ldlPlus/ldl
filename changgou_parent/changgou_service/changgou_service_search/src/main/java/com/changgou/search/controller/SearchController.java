package com.changgou.search.controller;

import com.changgou.entity.Page;
import com.changgou.search.pojo.SkuInfo;
import com.changgou.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年06月08日  19:06
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping
    @ResponseBody
    public Map search(@RequestParam(required = false) Map<String, String> searchMap) {
        // 特殊符号处理
        this.handleSearchMap(searchMap);
        return searchService.search(searchMap);
    }

    @GetMapping("/list")
    public String search(@RequestParam(required = false) Map<String, String> searchMap, Model model) {
        // 调用搜索微服务
        Map<String, Object> resultMap = searchService.search(searchMap);
        model.addAttribute("resultMap", resultMap);
        model.addAttribute("searchMap", searchMap);

        // 拼接url
        StringBuilder url = new StringBuilder("/search/list");
        if (searchMap != null && !searchMap.isEmpty()) {
            url.append("?");
            for (String paramKey : searchMap.keySet()) {
                if (!"sortRule".equals(paramKey) && !"sortField".equals(paramKey) && !"pageNum".equals(paramKey)) {
                    url.append(paramKey).append("=").append(searchMap.get(paramKey)).append("&");
                }
            }
            String urlStr = url.toString();
            urlStr = urlStr.substring(0, url.length() - 1);
            model.addAttribute("url", urlStr);
        } else {
            model.addAttribute("url", url.toString());
        }

        // 封装分页数据并返回
        Page<SkuInfo> page = new Page<SkuInfo>(
                Long.parseLong(String.valueOf(resultMap.get("total"))),
                Integer.parseInt(String.valueOf(resultMap.get("pageNum"))),
                Page.pageSize);
        model.addAttribute("page", page);
        return "search";
    }

    private void handleSearchMap(Map<String, String> searchMap) {
        for (Map.Entry<String, String> entry : searchMap.entrySet()) {
            if (entry.getKey().startsWith("spec_")) {
                searchMap.put(entry.getKey(), entry.getValue().replace("+", "%2B"));
            }
        }
    }
}
