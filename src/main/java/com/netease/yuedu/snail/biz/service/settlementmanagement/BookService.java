package com.netease.yuedu.snail.biz.service.settlementmanagement;

import com.netease.yuedu.snail.biz.model.queryObject.BookQueryObject;
import com.netease.yuedu.snail.biz.model.vo.BookVO;
import com.netease.yuedu.snail.common.page.PageList;

public interface BookService {

    PageList<BookVO> queryBooks(BookQueryObject bookQueryObject);
}
