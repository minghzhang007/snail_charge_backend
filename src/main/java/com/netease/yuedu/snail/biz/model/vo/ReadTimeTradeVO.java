package com.netease.yuedu.snail.biz.model.vo;

import com.netease.yuedu.snail.biz.model.entity.ReadTimeTrade;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReadTimeTradeVO extends ReadTimeTrade {

    /**
     * 用户名（蜗牛ID）
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

}
