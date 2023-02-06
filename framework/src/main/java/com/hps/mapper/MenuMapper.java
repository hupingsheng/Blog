package com.hps.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hps.domain.entity.Menu;

import java.util.List;


/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-06 14:18:04
 */
public interface MenuMapper extends BaseMapper<Menu> {
    
    List<String> selectPermsByUserId(Long id);
}
