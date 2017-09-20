package com.netease.yuedu.snail.biz.service.readtimetrade;

import com.netease.yuedu.snail.biz.model.queryObject.ReadTimeTradeQueryObject;
import com.netease.yuedu.snail.biz.model.vo.ReadTimeTradeVO;
import com.netease.yuedu.snail.common.page.PageList;
import com.netease.yuedu.snail.common.page.Paginator;

public interface ReadTimeTradeService {

    PageList<ReadTimeTradeVO> queryByPage(ReadTimeTradeQueryObject queryObject);
}
