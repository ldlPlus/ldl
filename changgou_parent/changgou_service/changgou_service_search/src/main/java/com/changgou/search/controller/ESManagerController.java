package com.changgou.search.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.search.service.ESManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ldl.plus
 * @date 2020年06月07日  18:58
 */
@RestController
@RequestMapping("/manager")
public class ESManagerController {

    @Autowired
    private ESManagerService esManagerService;

    @GetMapping("/create")
    public Result create() {
        esManagerService.createMappingAndIndex();
        return new Result(true, StatusCode.OK, "创建索引库结构成功");
    }

    @GetMapping("/importAll")
    public Result importAll() {
        esManagerService.importAll();
        return new Result(true, StatusCode.OK, "全部数据成功");
    }

}
