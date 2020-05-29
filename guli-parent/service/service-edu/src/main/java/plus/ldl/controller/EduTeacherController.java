package plus.ldl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plus.ldl.commonutils.Result;
import plus.ldl.domain.EduTeacher;
import plus.ldl.domain.TeacherQuery;
import plus.ldl.service.EduTeacherService;
import plus.ldl.servicebase.exception.GuliException;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年05月29日  17:10
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService teacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findAll")
    public Result findAll() {
        List<EduTeacher> teachers = teacherService.list(null);
        return Result.ok().data("items", teachers);
    }

    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("/{id}")
    public Result removeTeacher(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable("id") String id) {
        if ("9999".equals(id)) {
            int i = 10 / 0;
        }
        if ("99999".equals(id)) {
            throw new GuliException(20001, "id位数必须为19位");
        }
        boolean flag = teacherService.removeById(id);
        if (flag) {
            return Result.ok();
        }
        return Result.error();
    }

    @ApiOperation(value = "分页查询讲师")
    @GetMapping("/pageTeacher/{current}/{size}")
    public Result pageTeacher(
            @ApiParam(name = "current", value = "当前页码", required = true) @PathVariable("current") Long current,
            @ApiParam(name = "size", value = "每页记录数", required = true) @PathVariable("size") Long size) {
        Page<EduTeacher> page = new Page<>(current, size);
        teacherService.page(page, null);

        long total = page.getTotal();
        List<EduTeacher> teachers = page.getRecords();
        return Result.ok().data("rows", teachers).data("total", total);
    }

    @ApiOperation(value = "条件查询带分页的方法")
    @PostMapping("/pageTeacherCondition/{current}/{size}")
    public Result pageTeacherCondition(
            @ApiParam(name = "current", value = "当前页码", required = true) @PathVariable("current") Long current,
            @ApiParam(name = "size", value = "每页记录数", required = true) @PathVariable("size") Long size,
            @ApiParam(name = "teacherQuery", value = "查询条件", required = false) @RequestBody(required = false) TeacherQuery teacherQuery) {
        Page<EduTeacher> page = new Page<>(current, size);
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        // 动态拼接sql
        String name = teacherQuery.getName();
        if (StringUtils.isNotEmpty(name)) {
            queryWrapper.like("name", name);
        }

        Integer level = teacherQuery.getLevel();
        if (level != null && level >= 0) {
            queryWrapper.eq("level", level);
        }

        String begin = teacherQuery.getBegin();
        if (StringUtils.isNotEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }

        String end = teacherQuery.getEnd();
        if (StringUtils.isNotEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }

        teacherService.page(page, queryWrapper);
        long total = page.getTotal();
        List<EduTeacher> teachers = page.getRecords();
        return Result.ok().data("rows", teachers).data("total", total);
    }

    @ApiOperation(value = "添加讲师接口")
    @PostMapping("/addTeacher")
    public Result addTeacher(
            @ApiParam(name = "eduTeacher", value = "添加讲师信息", required = true) @RequestBody EduTeacher eduTeacher) {
        boolean isSave = teacherService.save(eduTeacher);
        if (isSave) {
            return Result.ok();
        }
        return Result.error();
    }

    @ApiOperation(value = "根据讲师id查询")
    @GetMapping("/getTeacher/{id}")
    public Result getTeacher(
            @ApiParam(name = "id", value = "讲师id", required = true) @PathVariable("id") String id) {
        EduTeacher teacher = teacherService.getById(id);
        if (teacher == null) {
            return Result.error();
        }
        return Result.ok().data("teacher", teacher);
    }

    @ApiOperation(value = "根据讲师id修改")
    @PostMapping("/updateTeacher")
    public Result updateTeacher(@ApiParam(name = "eduTeacher", value = "更改讲师信息", required = true) @RequestBody EduTeacher eduTeacher) {
        boolean flag = teacherService.updateById(eduTeacher);
        if (flag) {
            return Result.ok();
        }
        return Result.error();
    }


}
