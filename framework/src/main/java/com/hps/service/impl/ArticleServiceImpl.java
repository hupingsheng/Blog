package com.hps.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.SelectById;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.hps.Constants.SystemConstants;
import com.hps.domain.ResponseResult;
import com.hps.domain.entity.Article;
import com.hps.domain.entity.Category;
import com.hps.domain.vo.HotArticleVo;
import com.hps.domain.vo.articleDetailVo;
import com.hps.mapper.ArticleMapper;
import com.hps.service.ArticleService;
import com.hps.service.CategoryService;
import com.hps.utils.BeanCopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //正式发布的文章，且不是删除的
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        queryWrapper.eq(Article::getDelFlag, 0);
        //降序
        queryWrapper.orderByDesc(Article::getViewCount);

        Page<Article> page = new Page(1, 10);
        page(page, queryWrapper);

        List<Article> articles = page.getRecords();

//        List<HotArticleVo> articleVos = new ArrayList<>();
//        //返回的实体类的字段拷贝
//        for (Article article : articles){
//            HotArticleVo vo = new HotArticleVo();
//            //BeanUtils是Spring自带的  (source,target)
//            BeanUtils.copyProperties(article,vo);
//            articleVos.add(vo);
//        }

        List<HotArticleVo> articleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
        return ResponseResult.okResult(articleVos);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {

        Article article = getById(id);

        articleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, articleDetailVo.class);

        //文章分类id，这是文章数据表中没有的，需要单独处理
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if(category != null){
            articleDetailVo.setCategoryName(category.getName());
        }

        return ResponseResult.okResult(articleDetailVo);
    }
}
