package com.netease.yuedu.snail.biz.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
public class ReadTimeTrade implements Serializable{

    private static final long serialVersionUID = -5506806907681486746L;
    /**
     * 主键id
     */
    private Long tradeId;

    /**
     * 订单号，主要用于显示和第三方关联，格式是yyyyMMddHHmmssSSS+3位随机整数
     */
    private String tradeNo;

    /**
     * '关联的用户id'
     */
    private Long userId;

    /**
     * 购买时长，单位天
     */
    private Integer days;

    /**
     * 交易金额，单位分
     */
    private Integer money;

    /**
     * 交易状态 0:未付款  10:已付款  20:交易关闭
     */
    private Integer tradeStatus;

    /**
     * 支付方式
     */
    private String payMethod;

    /**
     * 购买平台
     */
    private String platform;

    /**
     * 交易发生的设备id
     */
    private String deviceId;

    /**
     * 购买发生的ip
     */
    private String ip;

    /**
     * 支付时间
     */
    private Long payTime;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 状态(－1:删除；0:正常)
     */
    private Integer status;


}
