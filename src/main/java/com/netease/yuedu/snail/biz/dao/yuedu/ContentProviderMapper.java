package com.netease.yuedu.snail.biz.dao.yuedu;

import com.netease.yuedu.snail.biz.model.entity.ContentProvider;
import org.apache.ibatis.annotations.MapKey;

import java.util.Collection;
import java.util.Map;

public interface ContentProviderMapper {

    @MapKey("userId")
    Map<Long, ContentProvider> queryProvidersByIds(Collection<Long> userIds);
}
