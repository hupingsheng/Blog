package com.hps.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hps.domain.entity.LoginUser;
import com.hps.domain.entity.User;
import com.hps.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

//security自定义的查询用户名密码是在内存中查询的，我们需要重写UserDetailsService
//从数据库中查询
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(queryWrapper);

        //判断是否查到用户
        if(Objects.isNull(user)){
            throw new RuntimeException("用户不存在");
        }
        //返回用户信息
        //  TODO 查询权限信息封装  后台系统用户登陆，除了查询用户信息还要查询权限信息进行封装
//        return user;      不能直接返回user,因为规定返回值是UserDetails类型

        //定义LoginUser类实现UserDetails，封装user
        return new LoginUser(user);
    }
}
