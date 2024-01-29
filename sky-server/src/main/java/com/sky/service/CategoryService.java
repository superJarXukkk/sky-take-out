package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;

public interface CategoryService {


    PageResult getPage(CategoryPageQueryDTO categoryPageQueryDTO);

    void add(CategoryDTO categoryDTO);
}
