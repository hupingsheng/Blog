package com.hps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hps.domain.entity.Role;
import com.hps.mapper.RoleMapper;
import com.hps.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2023-02-06 14:31:24
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<String> selectRoleByUserId(Long id) {
        if(id == 1L){
            ArrayList<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        return getBaseMapper().selectRoleByUserId(id);
    }
}

