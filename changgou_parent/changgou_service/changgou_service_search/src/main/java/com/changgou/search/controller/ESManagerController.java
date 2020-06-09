package com.changgou.search.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.search.service.ESManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/import/{page}/{size}")
    public Result importAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        page = 1;
        size = 99;
        for (int i = 0; i < 918; i++) {
            // 每次909个
            esManagerService.importByPage(page++, size);
            System.err.println("esManagerService = " + i + " 次循环");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new Result(true, StatusCode.OK, "全部数据成功 page: " + page + " ,size: " + size);
    }

}
