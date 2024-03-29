package com.sky.controller.admin;


import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api("分类功能模块")
@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PutMapping("")
    public Result update(@RequestBody CategoryDTO categoryDTO){
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        categoryService.update(category);
        return Result.success();
    }

    @GetMapping("/page")
    public Result<PageResult> getPage(CategoryPageQueryDTO categoryPageQueryDTO){
        PageResult pageResult = categoryService.getPage(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping("/status/{status}")
    public Result statusOnOrDown(@PathVariable Integer status,Long id){
        categoryService.statusOnOrDown(status,id);
        return Result.success();
    }

    @PostMapping("")
    public Result add(@RequestBody CategoryDTO categoryDTO){
        categoryService.add(categoryDTO);
        return Result.success();
    }

    @DeleteMapping("")
    public Result delete(Long id){
        categoryService.delete(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<Category>> selectCategoryByType(Integer type){
        List<Category> list = categoryService.selectCategoryByType(type);
        return Result.success(list);
    }

}
