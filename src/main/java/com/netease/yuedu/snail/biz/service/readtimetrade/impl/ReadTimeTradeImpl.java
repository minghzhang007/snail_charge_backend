package com.netease.yuedu.snail.biz.service.readtimetrade.impl;

import com.netease.yuedu.snail.biz.dao.snail.ReadTimeTradeMapper;
import com.netease.yuedu.snail.biz.model.entity.ReadTimeTrade;
import com.netease.yuedu.snail.biz.model.queryObject.ReadTimeTradeQueryObject;
import com.netease.yuedu.snail.biz.model.vo.ReadTimeTradeVO;
import com.netease.yuedu.snail.biz.service.readtimetrade.ReadTimeTradeService;
import com.netease.yuedu.snail.common.page.AbstractPageTemplate;
import com.netease.yuedu.snail.common.page.PageList;
import com.netease.yuedu.snail.common.page.Paginator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReadTimeTradeImpl implements ReadTimeTradeService {

    @Resource
    private ReadTimeTradeMapper tradeMapper;


    @Override
    public PageList<ReadTimeTradeVO> queryByPage(final ReadTimeTradeQueryObject queryObject) {

        PageList<ReadTimeTradeVO> itemsByPage = new AbstractPageTemplate<ReadTimeTradeVO>() {
            @Override
            protected List<ReadTimeTradeVO> queryItems() {
                return tradeMapper.queryReadTimeRecords(queryObject);
            }
        }.getItemsByPage(queryObject);
        return itemsByPage;
    }
}
