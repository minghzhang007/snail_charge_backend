package com.netease.yuedu.snail.biz.model.queryObject;

import com.netease.yuedu.snail.common.page.Paginator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookQueryObject extends Paginator{

    /**
     * '书籍的Uuid，主要用于外部关联，内部关联用BookId'
     */
    private String uuid;

    /**
     * '书籍标题'
     */
    private String title;
}
