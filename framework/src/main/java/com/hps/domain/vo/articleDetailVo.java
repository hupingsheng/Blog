package com.hps.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class articleDetailVo {

    private Long id;

    //标题
    private String title;
    //文章内容
    private String content;

    //所属分类ID
    private Long categoryId;

    //访问量
    private Long viewCount;

    //是否允许评论 1是，0否
    private String isComment;

    private Date createTime;

    //数据库中没有的，业务需要的字段
    private String categoryName;

}
