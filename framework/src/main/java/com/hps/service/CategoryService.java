package com.hps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hps.domain.ResponseResult;
import com.hps.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-02-04 21:59:02
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}

