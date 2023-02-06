package com.hps.controller;

import com.hps.domain.ResponseResult;
import com.hps.domain.entity.Article;
import com.hps.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
@Api(tags = "博文相关")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation("接口测试")
    @GetMapping("/list")
    public List<Article> test(){
        return articleService.list();
    }

    @ApiOperation("热门文章查询")
    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){
        ResponseResult result = articleService.hotArticleList();
        return result;
    }

    @ApiOperation("查看文章详情")
    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);
    }
}
