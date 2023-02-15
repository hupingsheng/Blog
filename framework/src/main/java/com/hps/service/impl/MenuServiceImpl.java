package com.hps.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hps.Constants.SystemConstants;
import com.hps.domain.entity.Menu;
import com.hps.domain.vo.MenuVo;
import com.hps.mapper.MenuMapper;
import com.hps.service.MenuService;
import com.hps.utils.BeanCopyUtils;
import com.hps.utils.SecurityUtils;
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
        if (id == 1L) {
            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Menu::getMenuType, "C");
            queryWrapper.eq(Menu::getMenuType, "M");
            queryWrapper.eq(Menu::getStatus, SystemConstants.MENU_NORMAL_STATUS);
            queryWrapper.eq(Menu::getDelFlag, 0);
            List<Menu> menus = list(queryWrapper);

            List<String> perms = menus.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return perms;
        }
        return getBaseMapper().selectPermsByUserId(id);
    }

    @Override
    public List<MenuVo> selectRouterMenuTreeByUserId(Long userId) {
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus = null;

        //判断是否是管理员
        if (SecurityUtils.isAdmin()) {
            //如果是 获取所有符合要求的Menu
            menus = menuMapper.selectAllRouterMenu();
        } else {
            //否则  获取当前用户所具有的Menu
            menus = menuMapper.selectRouterMenuTreeByUserId(userId);
        }

        List<MenuVo> menuVos = BeanCopyUtils.copyBeanList(menus, MenuVo.class);

        //构建tree
        //先找出第一层的菜单  然后去找他们的子菜单设置到children属性中
        List<MenuVo> menuTree = builderMenuTree(menuVos, 0L);
        return menuTree;

    }

    //构建成树的结构形式
    private List<MenuVo> builderMenuTree(List<MenuVo> menuVos, long parentId) {
        List<MenuVo> menuVoTree = menuVos.stream()
                .filter(menuVo -> menuVo.getParentId().equals(parentId))
                .map(menuVo -> menuVo.setChildren(getChildren(menuVo, menuVos)))
                .collect(Collectors.toList());
        return menuVoTree;
    }

    /**
     * 获取存入参数的 子菜单MenuVo集合
     * @param menuVo
     * @return
     */
    private List<MenuVo> getChildren(MenuVo menuVo, List<MenuVo> menuVos){
        List<MenuVo> childrenList = menuVos.stream()
                .filter(m -> m.getParentId().equals(menuVo.getId()))
                .map(m -> m.setChildren(getChildren(m, menuVos)))
                .collect(Collectors.toList());
        return childrenList;
    }

}

