package com.netease.yuedu.snail.biz.dao.snail;

import com.netease.yuedu.snail.biz.model.entity.Book;
import com.netease.yuedu.snail.biz.model.queryObject.BookQueryObject;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface BookMapper {

    @MapKey("bookId")
    Map<Long, Book> queryBooksMap(BookQueryObject bookQueryObject);

    List<Book> queryBooks(BookQueryObject bookQueryObject);;
}
