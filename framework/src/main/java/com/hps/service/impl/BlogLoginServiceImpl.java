package com.hps.service.impl;

import com.hps.config.JwtUtil;
import com.hps.domain.ResponseResult;
import com.hps.domain.entity.LoginUser;
import com.hps.domain.entity.User;
import com.hps.domain.vo.BlogUserLoginVo;
import com.hps.domain.vo.UserInfoVo;
import com.hps.service.BlogLoginService;
import com.hps.utils.BeanCopyUtils;
import com.hps.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());

        //authenticate默认会调用userDetailsService去内存进行认证，实际上我们是需要去数据库中进行查询认证，
        //因此我们需要重写userDetailsService进行数据库查询
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //判断是否认证通过，这里没通过应该是密码错误，但是从业务安全角度考虑，这里应该抛出 “用户名或密码错误”
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }

       // 通过 Authentication.getPrincipal() 可以获取到代表当前用户的信息，这个对象通常是 UserDetails 的实例。
        //  通过 UserDetails 的实例我们可以获取到当前用户的用户名、密码、角色等信息。
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        //Jwt接受的是String类型的
        String jwt = JwtUtil.createJWT(userId);

        redisCache.setCacheObject("bloglogin:" + userId, loginUser);

        //最后返回的data：{token,userInfo}
        //先封装userInfo, 再封装成data
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        BlogUserLoginVo vo = new BlogUserLoginVo(jwt, userInfoVo);
        return ResponseResult.okResult(vo);

    }
}
