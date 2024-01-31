package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {
    void add(DishDTO dishDTO);

    PageResult getPage(DishPageQueryDTO dishPageQueryDTO);

    void startOrDown(int status, Long id);



    void update(DishDTO dishDTO);

    DishVO getDishById(Long id);

    void deleteBatch(Long[] ids);

    List<Dish> getDishsByCategoryId(Long categoryId);
}
