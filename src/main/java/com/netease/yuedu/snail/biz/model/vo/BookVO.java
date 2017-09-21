package com.netease.yuedu.snail.biz.model.vo;

import com.netease.yuedu.snail.biz.model.entity.Book;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookVO extends Book {

    private String authorNames;

    private String providerName;
}
