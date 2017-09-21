package com.netease.yuedu.snail.biz.controller;

import com.netease.yuedu.snail.biz.model.queryObject.BookQueryObject;
import com.netease.yuedu.snail.biz.model.vo.BookVO;
import com.netease.yuedu.snail.biz.service.settlementmanagement.BookService;
import com.netease.yuedu.snail.common.core.Json;
import com.netease.yuedu.snail.common.page.PageList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

@Controller
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;

    @GetMapping("/toQuery")
    public String toQuery() {
        return "settlementmanagement/bookList";
    }

    @GetMapping("/query")
    @ResponseBody
    public PageList<BookVO> query(@Json BookQueryObject bookQueryObject) {
        PageList<BookVO> bookVOPageList = bookService.queryBooks(bookQueryObject);
        return bookVOPageList;
    }
}
