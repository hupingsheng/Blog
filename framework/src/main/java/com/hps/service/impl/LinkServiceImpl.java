package com.hps.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hps.Constants.SystemConstants;
import com.hps.domain.ResponseResult;
import com.hps.domain.entity.Link;
import com.hps.domain.vo.LinkVo;
import com.hps.mapper.LinkMapper;
import com.hps.service.LinkService;
import com.hps.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2023-02-05 09:56:28
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {
        //查询所有审核通过的
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(queryWrapper);

        //封装vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);

        return ResponseResult.okResult(linkVos);
    }
}

