package com.netease.yuedu.snail.biz.controller;

import com.netease.yuedu.snail.biz.model.entity.ReadTimeTrade;
import com.netease.yuedu.snail.biz.model.queryObject.ReadTimeTradeQueryObject;
import com.netease.yuedu.snail.biz.service.readtimetrade.ReadTimeTradeService;
import com.netease.yuedu.snail.common.page.PageList;
import com.netease.yuedu.snail.common.page.Paginator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class ReadTimeTradeController {

    @Resource
    private ReadTimeTradeService readTimeTradeService;

    @GetMapping("/query")
    @ResponseBody
    public PageList<ReadTimeTrade> queryReadTimeTrades(ReadTimeTradeQueryObject queryObject, Paginator paginator) {

        PageList<ReadTimeTrade> pageList = readTimeTradeService.queryByPage(queryObject, paginator);
        return pageList;
    }

    @GetMapping("/toQuery")
    public String toQuery() {
        return "readtimetrade/readtimetradeList";
    }
}
