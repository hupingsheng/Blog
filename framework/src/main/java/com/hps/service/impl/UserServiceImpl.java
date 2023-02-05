package com.hps.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hps.domain.entity.User;
import com.hps.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2023-02-05 11:43:04
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IService<User> {

}

