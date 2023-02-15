package com.hps.controller;

import com.hps.domain.ResponseResult;
import com.hps.domain.entity.LoginUser;
import com.hps.domain.entity.Menu;
import com.hps.domain.entity.User;
import com.hps.domain.vo.AdminUserInfoVo;
import com.hps.domain.vo.MenuVo;
import com.hps.domain.vo.RoutersVo;
import com.hps.domain.vo.UserInfoVo;
import com.hps.enums.AppHttpCodeEnum;
import com.hps.exception.SystemException;
import com.hps.service.LoginService;
import com.hps.service.MenuService;
import com.hps.service.RoleService;
import com.hps.utils.BeanCopyUtils;
import com.hps.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@MapperScan("com.hps.mapper")
@Api("后台登录系统")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName()))
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        return loginService.login(user);
    }

    @ApiOperation("获取用户信息及相关角色，权限")
    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo(){
        //获取登录用户的id
        LoginUser loginUser = SecurityUtils.getLoginUser();

        //根据用户id查询权限信息
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());

        //根据用户id查询角色信息
        List<String> rolesKeyList = roleService.selectRoleByUserId(loginUser.getUser().getId());

        //获取用户信息
        User user = loginUser.getUser();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);

        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms,rolesKeyList, userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);

    }

    @ApiOperation("动态路由")
    @GetMapping("/getRouters")
    public ResponseResult<RoutersVo> getRouters(){
        //查询menu 结果是tree形式
        Long userId = SecurityUtils.getUserId();
        List<MenuVo> menus = menuService.selectRouterMenuTreeByUserId(userId);
        return ResponseResult.okResult(new RoutersVo(menus));
    }

}
