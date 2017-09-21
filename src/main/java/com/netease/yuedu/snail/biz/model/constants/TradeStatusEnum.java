package com.netease.yuedu.snail.biz.model.constants;


/**
 * 交易状态，ReadTimeTrade.tradeStatus
 */
public enum TradeStatusEnum {
    UNPAID(0, "未付款"),

    PAID(10, "已付款"),

    CLOSE(20, "交易关闭");

    private TradeStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static TradeStatusEnum of(int code) {
        for (TradeStatusEnum tradeStatusEnum : TradeStatusEnum.values()) {
            if (tradeStatusEnum.getCode() == code) {
                return tradeStatusEnum;
            }
        }
        return null;
    }
}
