package com.hps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hps.domain.ResponseResult;
import com.hps.domain.entity.User;
import org.springframework.stereotype.Service;


public interface BlogLoginService {
    ResponseResult login(User user);
}
