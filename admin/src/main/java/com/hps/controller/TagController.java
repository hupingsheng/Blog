package com.hps.controller;

import com.hps.domain.ResponseResult;
import com.hps.domain.entity.Tag;
import com.hps.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult list(){
        List<Tag> list = tagService.list();
        return ResponseResult.okResult(list);
    }
}
