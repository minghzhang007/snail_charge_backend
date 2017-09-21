package com.netease.yuedu.snail.biz.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Author implements Serializable {

    private static final long serialVersionUID = -3708210181658132664L;
    /**
     * 作者id
     */
    private Long authorId;
    /**
     * 作者名
     */
    private String name;
    /**
     * 作者描述
     */
    private String description;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 更新时间
     */
    private Long updateTime;
    /**
     * 状态(0: 正常, -1: 删除)
     */
    private Integer status;


}
