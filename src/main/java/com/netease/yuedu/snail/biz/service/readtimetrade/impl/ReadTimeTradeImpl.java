package com.netease.yuedu.snail.biz.service.readtimetrade.impl;

import com.netease.yuedu.snail.biz.dao.snail.ReadTimeTradeMapper;
import com.netease.yuedu.snail.biz.model.entity.ReadTimeTrade;
import com.netease.yuedu.snail.biz.model.queryObject.ReadTimeTradeQueryObject;
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
    public PageList<ReadTimeTrade> queryByPage(final ReadTimeTradeQueryObject queryObject, Paginator paginator) {

        PageList<ReadTimeTrade> itemsByPage = new AbstractPageTemplate<ReadTimeTrade>() {
            @Override
            protected List<ReadTimeTrade> queryItems() {
                return tradeMapper.queryReadTimeRecords(queryObject);
            }
        }.getItemsByPage(paginator);
        return itemsByPage;
    }
}
