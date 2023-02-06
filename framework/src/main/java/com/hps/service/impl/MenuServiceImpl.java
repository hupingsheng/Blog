package com.hps.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hps.Constants.SystemConstants;
import com.hps.domain.entity.Menu;
import com.hps.mapper.MenuMapper;
import com.hps.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2023-02-06 14:18:11
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<String> selectPermsByUserId(Long id) {
        if(id == 1L){
            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Menu::getMenuType,"C");
            queryWrapper.eq(Menu::getMenuType,"M");
            queryWrapper.eq(Menu::getStatus, SystemConstants.MENU_NORMAL_STATUS);
            queryWrapper.eq(Menu::getDelFlag,0);
            List<Menu> menus = list(queryWrapper);

            List<String> perms = menus.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return perms;
        }
        return getBaseMapper().selectPermsByUserId(id);
    }
}

