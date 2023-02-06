package com.hps.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hps.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-06 14:31:19
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleByUserId(Long id);
}

