package com.hps.domain.vo;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkVo {

    @TableId
    private Long id;


    private String name;

    private String logo;

    private String description;

}
