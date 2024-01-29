package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

public interface CategoryService {


    PageResult getPage(CategoryPageQueryDTO categoryPageQueryDTO);

    void add(CategoryDTO categoryDTO);

    void statusOnOrDown(Integer status, Long id);
    void update(Category category);

    void delete(Long id);

    List<Category> selectCategoryByType(Integer type);
}
