package com.hps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hps.domain.ResponseResult;
import com.hps.domain.entity.Article;

import java.util.List;

public interface ArticleService extends IService<Article> {

    ResponseResult hotArticleList();
}
