package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @Override
    @Transactional
    public void add(DishDTO dishDTO) {

        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dish.setStatus(StatusConstant.DISABLE);
        dishMapper.add(dish);

        Long id = dish.getId();
        List<DishFlavor> flavors = dishDTO.getFlavors();
        flavors.forEach(dishFlavor -> {
            dishFlavor.setDishId(id);
        });

        dishFlavorMapper.addBatch(flavors);
    }

    @Override
    public PageResult getPage(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());

        Page<Dish> page = dishMapper.getDishWithPage(dishPageQueryDTO);
        long total = page.getTotal();
        List<Dish> content = page.getResult();
        return new PageResult(total, content);

    }

    @Override
    public void startOrDown(int status, Long id) {
        DishDTO dish = new DishDTO();
        dish.setStatus(status);
        dish.setId(id);
        update(dish);
    }

    @Override
    @Transactional
    public void update(DishDTO dishDTO) {
        Long id = dishDTO.getId();
        List<DishFlavor> flavors = dishDTO.getFlavors();


        //
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.update(dish);

        //
        dishFlavorMapper.deleteFlavorsByDishId(id);
        if (flavors.size() != 0)
            dishFlavorMapper.addBatch(flavors);
    }

    @Override
    public DishVO getDishById(Long id) {
        Dish dish = dishMapper.getDishById(id);

        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish, dishVO);
        dishVO.setFlavors(dishFlavorMapper.getFlavorsByDishId(id));
        return dishVO;
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] ids) {
        List<Long> longs = Arrays.asList(ids);
        List<Long> list1 = dishMapper.getDishByStatus(1);
        Collection<Long> intersection = CollectionUtils.intersection(longs, list1);

        List<Long> list2 = setmealDishMapper.getDishIdsByDishId(ids);
        intersection = CollectionUtils.intersection(intersection, list2);

        if (intersection.size() != 0)
            dishMapper.deleteBatch(intersection.toArray(new Long[0]));
        else if (longs.size() != intersection.size())
            throw new DeletionNotAllowedException(MessageConstant.DISH_DELETE_NOT_ALLOW);


    }

    @Override
    public List<Dish> getDishsByCategoryId(Long categoryId) {
        List<Dish> dishByCategoryId = dishMapper.getDishByCategoryId(categoryId);
        return dishByCategoryId;


    }
}
