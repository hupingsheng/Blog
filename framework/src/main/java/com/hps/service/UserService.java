package com.hps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hps.domain.ResponseResult;
import com.hps.domain.entity.User;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2023-02-05 22:54:53
 */
public interface UserService extends IService<User> {

    ResponseResult getUserInfo();

    ResponseResult updateUserInfo(User user);
}

