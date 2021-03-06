package com.netease.yuedu.snail.biz.controller;

import com.netease.yuedu.snail.biz.model.queryObject.ReadTimeTradeQueryObject;
import com.netease.yuedu.snail.biz.model.vo.ReadTimeTradeVO;
import com.netease.yuedu.snail.biz.service.trademanagement.ReadTimeTradeService;
import com.netease.yuedu.snail.common.core.Json;
import com.netease.yuedu.snail.common.page.PageList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/trade")
public class ReadTimeTradeController {

    @Resource
    private ReadTimeTradeService readTimeTradeService;

    @GetMapping("/toQuery")
    public String toQuery() {
        return "readtimetrade/readtimetradeList";
    }

    @GetMapping("/query")
    @ResponseBody
    public PageList<ReadTimeTradeVO> queryReadTimeTrades(@Json ReadTimeTradeQueryObject queryObject) {

        PageList<ReadTimeTradeVO> pageList = readTimeTradeService.queryByPage(queryObject);
        return pageList;
    }

    @GetMapping("/export")
    public String export(@Json ReadTimeTradeQueryObject queryObject, HttpServletResponse response) {
        readTimeTradeService.export(queryObject, response);
        return "readtimetrade/readtimetradeList";
    }
}
