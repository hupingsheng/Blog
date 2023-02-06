package com.hps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hps.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2023-02-06 14:31:22
 */
public interface RoleService extends IService<Role> {


    List<String> selectRoleByUserId(Long id);
}

