package com.sky.controller.admin;


import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api("分类功能模块")
@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PutMapping("")
    public Result update(){
        return Result.success();
    }

    @GetMapping("/page")
    public Result<PageResult> getPage(CategoryPageQueryDTO categoryPageQueryDTO){
        PageResult pageResult = categoryService.getPage(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping("/status/{status}")
    public Result statusOnOrDown(@PathVariable Integer status,Long id){
        return Result.success();
    }

    @PostMapping("")
    public Result add(@RequestBody CategoryDTO categoryDTO){
        categoryService.add(categoryDTO);
        return Result.success();
    }

    @DeleteMapping("")
    public Result delete(Long id){
        return Result.success();
    }
}
