package com.hps.service;

import com.hps.domain.ResponseResult;
import com.hps.domain.entity.User;


public interface LoginService {
    ResponseResult login(User user);


}
