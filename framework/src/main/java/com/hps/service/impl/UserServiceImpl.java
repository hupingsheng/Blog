package com.hps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hps.domain.ResponseResult;
import com.hps.domain.entity.LoginUser;
import com.hps.domain.entity.User;
import com.hps.domain.vo.UserInfoVo;
import com.hps.mapper.UserMapper;
import com.hps.service.UserService;
import com.hps.utils.BeanCopyUtils;
import com.hps.utils.SecurityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2023-02-05 22:57:10
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public ResponseResult getUserInfo() {
        Long userId = SecurityUtils.getUserId();
        User user = getById(userId);
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);

        return ResponseResult.okResult(userInfoVo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        return ResponseResult.okResult();
    }
}

