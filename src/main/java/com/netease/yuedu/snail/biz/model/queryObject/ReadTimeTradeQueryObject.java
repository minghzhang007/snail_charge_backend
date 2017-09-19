package com.netease.yuedu.snail.biz.model.queryObject;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReadTimeTradeQueryObject {

    private Integer tradeStatus;

    private Long startTime;

    private Long endTime;
}
