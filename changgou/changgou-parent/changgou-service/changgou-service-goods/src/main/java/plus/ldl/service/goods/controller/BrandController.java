package plus.ldl.service.goods.controller;

import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plus.ldl.entity.PageResult;
import plus.ldl.entity.Result;
import plus.ldl.goods.domain.Brand;
import plus.ldl.service.goods.service.BrandService;

import java.util.List;
import java.util.Map;

/**
 * @author ldl.plus
 * @date 2020年06月02日  14:44
 * 品牌
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/findList")
    public Result findList() {
        List<Brand> brands = brandService.findList();
        return Result.ok().data(brands);
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable("id") Integer id) {
        Brand brand = brandService.findById(id);
        return Result.ok().data(brand);
    }

    @PostMapping
    public Result add(@RequestBody Brand brand) {
        int insert = brandService.insert(brand);
        if (insert == 1) {
            return Result.ok();
        }
        return Result.error();
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable("id") Integer id, @RequestBody Brand brand) {
        brand.setId(id);
        int update = brandService.update(brand);
        if (update == 1) {
            return Result.ok();
        }
        return Result.error();
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") Integer id) {
        int delete = brandService.deleteById(id);
        if (delete == 1) {
            return Result.ok();
        }
        return Result.error();
    }

    @GetMapping("/search")
    public Result findByCondition(@RequestParam Map<String, Object> searchMap) {
        List<Brand> brands = brandService.findByCondition(searchMap);
        return Result.ok().data(brands);
    }

    @GetMapping("/search/{page}/{size}")
    public Result findPage(@PathVariable Integer page, @PathVariable Integer size) {
        Page<Brand> brandPage = brandService.findPage(page, size);
        PageResult<Brand> pageResult = new PageResult<>(brandPage.getTotal(), brandPage.getResult());
        return Result.ok().data(pageResult);
    }

    @GetMapping("/searchPage/{page}/{size}")
    public Result findPageByCondition(@PathVariable Integer page, @PathVariable Integer size, @RequestParam Map<String, Object> searchMap) {
        Page<Brand> brandPage = brandService.findPageByCondition(page, size, searchMap);
        PageResult<Brand> pageResult = new PageResult<>(brandPage.getTotal(), brandPage.getResult());
        return Result.ok().data(pageResult);
    }
}
