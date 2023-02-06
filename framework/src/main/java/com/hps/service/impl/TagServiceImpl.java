package com.hps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hps.domain.entity.Tag;
import com.hps.mapper.TagMapper;
import com.hps.service.TagService;
import org.springframework.stereotype.Service;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2023-02-05 23:36:11
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}

