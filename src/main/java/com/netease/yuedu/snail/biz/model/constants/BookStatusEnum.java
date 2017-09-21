package com.netease.yuedu.snail.biz.model.constants;


public enum BookStatusEnum {

    VALID(0, "有效"),

    TEST(1, "测试"),

    NOTSUB(2, "不可再订"),

    INVALID(-1, "过期");

    private int code;
    private String desc;

    private BookStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static BookStatusEnum of(int code) {
        for (BookStatusEnum bookStatusEnum : BookStatusEnum.values()) {
            if (bookStatusEnum.getCode() == code) {
                return bookStatusEnum;
            }
        }
        return null;
    }

}
