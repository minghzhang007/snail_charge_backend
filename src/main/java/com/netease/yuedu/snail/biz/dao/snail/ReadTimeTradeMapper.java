package com.netease.yuedu.snail.biz.dao.snail;

import com.netease.yuedu.snail.biz.model.entity.ReadTimeTrade;
import com.netease.yuedu.snail.biz.model.queryObject.ReadTimeTradeQueryObject;

import java.util.List;

public interface ReadTimeTradeMapper {

    List<ReadTimeTrade>  queryReadTimeRecords(ReadTimeTradeQueryObject queryObject);
}
