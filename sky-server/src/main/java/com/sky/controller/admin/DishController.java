package com.sky.controller.admin;


import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("菜品管理模块")
@RequestMapping("/admin/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @PostMapping("")
    public Result add(@RequestBody DishDTO dishDTO){
        dishService.add(dishDTO);
        return Result.success();
    }

    @GetMapping("list")
    public Result<List> getDishList(Long categoryId){
        List<Dish> list =  dishService.getDishsByCategoryId(categoryId);
        return Result.success(list);
    }

    @GetMapping("/page")
    public Result<PageResult> getPage(DishPageQueryDTO dishPageQueryDTO){
        PageResult pageResult = dishService.getPage(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping("/status/{status}")
    public Result startOrDown(@PathVariable("status")int status,Long id){
        dishService.startOrDown(status,id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<DishVO> getDishById(@PathVariable Long id){
        DishVO dishVO = dishService.getDishById(id);

        return Result.success(dishVO);
    }

    @PutMapping("")
    public Result update(@RequestBody DishDTO dishDTO){
        dishService.update(dishDTO);
        return Result.success();
    }

    @DeleteMapping("")
    public Result deleteBatch(Long[] ids){
        dishService.deleteBatch(ids);
        return Result.success();
    }



}
