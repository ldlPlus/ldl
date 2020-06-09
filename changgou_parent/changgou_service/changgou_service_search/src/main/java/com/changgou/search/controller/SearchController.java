package com.changgou.search.controller;

import com.changgou.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年06月08日  19:06
 */
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping
    public Map search(@RequestParam(required = false) Map<String, String> searchMap) {
        // 特殊符号处理
        this.handleSearchMap(searchMap);
        return searchService.search(searchMap);
    }

    private void handleSearchMap(Map<String, String> searchMap) {
        for (Map.Entry<String, String> entry : searchMap.entrySet()) {
            if (entry.getKey().startsWith("spec_")) {
                searchMap.put(entry.getKey(), entry.getValue().replace("+", "%2B"));
            }
        }
    }
}
