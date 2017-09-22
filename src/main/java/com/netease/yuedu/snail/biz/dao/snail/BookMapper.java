package com.netease.yuedu.snail.biz.dao.snail;

import com.netease.yuedu.snail.biz.model.entity.Book;
import com.netease.yuedu.snail.biz.model.queryObject.BookQueryObject;

import java.util.List;

public interface BookMapper {

    List<Book> queryBooks(BookQueryObject bookQueryObject);

}
