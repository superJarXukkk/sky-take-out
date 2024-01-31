package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.Annotation.AutoFill;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface DishMapper {


    @Select("select * from Dish where category_id = #{categoryId}")
    List<Dish> getDishByCategoryId(Long categoryId);

    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    @AutoFill(getOperationType = OperationType.INSERT)
    void add(Dish dish);

    Page<Dish> getDishWithPage(DishPageQueryDTO dishPageQueryDTO);

    @Select("select * from dish where id = #{id}")
    Dish getDishById(Long id);

    @AutoFill(getOperationType = OperationType.UPDATE)
    void update(Dish dish);

    void deleteBatch(Long[] ids);

    @Select("select id from dish where status = 1")
    List<Long> getDishByStatus(int i);

    @Select("select a.* from dish a left join setmeal_dish b on a.id = b.dish_id where b.setmeal_id = #{setmealId}")
    List<Dish> getBySetmealId(Long setmealId);
}
