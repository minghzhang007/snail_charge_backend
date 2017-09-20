package com.netease.yuedu.snail.biz.dao.snail;

import com.netease.yuedu.snail.biz.model.queryObject.ReadTimeTradeQueryObject;
import com.netease.yuedu.snail.biz.model.vo.ReadTimeTradeVO;

import java.util.List;

public interface ReadTimeTradeMapper {

    List<ReadTimeTradeVO> queryReadTimeRecords(ReadTimeTradeQueryObject queryObject);
}
