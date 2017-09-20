package com.netease.yuedu.snail.biz.model.queryObject;

import com.netease.yuedu.snail.common.page.Paginator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReadTimeTradeQueryObject extends Paginator{

    private Integer tradeStatus;

    private Long startTime;

    private Long endTime;

    private Long userId;

    private String userName;
}
