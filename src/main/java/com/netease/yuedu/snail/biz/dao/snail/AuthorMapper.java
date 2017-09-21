package com.netease.yuedu.snail.biz.dao.snail;

import com.netease.yuedu.snail.biz.model.entity.Author;
import org.apache.ibatis.annotations.MapKey;

import java.util.Collection;
import java.util.Map;

public interface AuthorMapper {

    @MapKey("authorId")
    Map<Long, Author> queryAuthorsByIds(Collection<Long> authorIds);
}
