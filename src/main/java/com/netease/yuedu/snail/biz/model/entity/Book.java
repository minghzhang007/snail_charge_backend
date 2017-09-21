package com.netease.yuedu.snail.biz.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Book implements Serializable {
    private static final long serialVersionUID = 6847614874621854970L;
    /**
     * '书籍id'
     */
    private Long bookId;
    /**
     * '书籍的Uuid，主要用于外部关联，内部关联用BookId'
     */
    private String uuid;
    /**
     * '书籍标题'
     */
    private String title;
    /**
     * '搜索引擎优化标题'
     */
    private String seoTitle;
    /**
     * '书籍描述'
     */
    private String description;
    /**
     * '书籍封面'
     */
    private String imageUrl;
    /**
     * '创建时间
     */
    private Long createTime;
    /**
     * 更新时间
     */
    private Long updateTime;
    /**
     * '状态'
     */
    private Integer status;
    /**
     * '作者id，多个以英文,隔开'
     */
    private String authorIds;
    /**
     * '作者别名'
     */
    private String authorAlias;
    /**
     * 分类id
     */
    private Long categoryId;
    /**
     * '书籍目录存在nos上的索引key'
     */
    private String catalogKey;
    /**
     * '总字数'
     */
    private Integer wordCount;
    /**
     * '书籍isbn编码'
     */
    private String ISBN;
    /**
     * '提供商id'
     */
    private Long providerId;
    /**
     * '出版时间'
     */
    private Long publishTime;
    /**
     * '出版社信息'
     */
    private String publisher;
    /**
     * '完整程度'
     */
    private Integer completeStatus;
    /**
     * '书籍类型，参考BookType常量'
     */
    private Integer bookType;

    /**
     * '书籍语言'
     */
    private String lang;
    /**
     * '页数'
     */
    private Integer pages;
    /**
     * '包装'
     */
    private String pack;
    /**
     * '用纸'
     */
    private String texture;
    /**
     * '开本'
     */
    private String format;
    /**
     * '书籍关键词,逗号分隔'
     */
    private String keyName;
    /**
     * '购买链接'
     */
    private String buyUrl;
    /**
     * '浏览量（点击量）'
     */
    private Integer viewCount;
    /**
     * '默认问题Id(原评论转过来的)'
     */
    private Long defaultQuestionId;
    /**
     * '兑换需要花费的蜗牛壳数'
     */
    private Integer costCoins;
    /**
     * '上次上报推荐系统的时间'
     */
    private Long recUploadTime;

}
