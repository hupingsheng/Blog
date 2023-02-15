package com.hps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hps.domain.entity.Menu;
import com.hps.domain.vo.MenuVo;

import java.util.List;


/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2023-02-06 14:18:09
 */
public interface MenuService extends IService<Menu> {


    List<String> selectPermsByUserId(Long id);

    List<MenuVo> selectRouterMenuTreeByUserId(Long userId);
}

