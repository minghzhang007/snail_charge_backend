package com.netease.yuedu.snail.biz.service.trademanagement;

import com.netease.yuedu.snail.biz.model.queryObject.ReadTimeTradeQueryObject;
import com.netease.yuedu.snail.biz.model.vo.ReadTimeTradeVO;
import com.netease.yuedu.snail.common.page.PageList;

import javax.servlet.http.HttpServletResponse;

public interface ReadTimeTradeService {

    PageList<ReadTimeTradeVO> queryByPage(ReadTimeTradeQueryObject queryObject);

    void export(ReadTimeTradeQueryObject queryObject,HttpServletResponse response);
}
