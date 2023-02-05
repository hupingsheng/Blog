package com.hps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hps.domain.ResponseResult;
import com.hps.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2023-02-05 09:56:26
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}

