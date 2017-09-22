package com.netease.yuedu.snail.biz.service.settlementmanagement.impl;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.netease.yuedu.snail.biz.dao.snail.AuthorMapper;
import com.netease.yuedu.snail.biz.dao.snail.BookMapper;
import com.netease.yuedu.snail.biz.dao.yuedu.ContentProviderMapper;
import com.netease.yuedu.snail.biz.model.entity.Author;
import com.netease.yuedu.snail.biz.model.entity.Book;
import com.netease.yuedu.snail.biz.model.entity.ContentProvider;
import com.netease.yuedu.snail.biz.model.queryObject.BookQueryObject;
import com.netease.yuedu.snail.biz.model.vo.BookVO;
import com.netease.yuedu.snail.biz.service.settlementmanagement.BookService;
import com.netease.yuedu.snail.common.page.AbstractPageTemplate;
import com.netease.yuedu.snail.common.page.PageList;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


@Service
public class BookServiceImpl implements BookService {

    private Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Resource
    private BookMapper bookMapper;

    @Resource
    private ContentProviderMapper providerMapper;

    @Resource
    private AuthorMapper authorMapper;

    @Override
    public PageList<BookVO> queryBooks(final BookQueryObject bookQueryObject) {
        PageList<Book> itemsByPage1 = new AbstractPageTemplate<Book>() {
            @Override
            protected List<Book> queryItems() {
                return bookMapper.queryBooks(bookQueryObject);
            }
        }.getItemsByPage(bookQueryObject);
        Map<Long, Book> bookIdMapping = mapping(itemsByPage1.getData());
        List<BookVO> bookVOs = convert(bookIdMapping);
        PageList<BookVO> retPageList = new PageList<>(bookVOs,itemsByPage1.getPaginator());
        return retPageList;
    }

    private Map<Long, Book> mapping(Collection<Book> books) {
        if (CollectionUtils.isNotEmpty(books)) {
            Map<Long, Book> bookIdMapping = Maps.newHashMapWithExpectedSize(books.size());
            for (Book book : books) {
                bookIdMapping.put(book.getBookId(), book);
            }
            return bookIdMapping;
        }
        return Maps.newHashMapWithExpectedSize(0);
    }

    private List<BookVO> convert(Map<Long, Book> bookIdMapping) {
        if (MapUtils.isEmpty(bookIdMapping)) {
            return Lists.newArrayList();
        }
        List<BookVO> bookVOS = Lists.newArrayListWithCapacity(bookIdMapping.size());

        Map<Long, List<String>> bookId2AuthorNamesMapping = getAuthorNamesMap(bookIdMapping);

        Map<Long, ContentProvider> bookId2ProviderMapping = getProviderNameMap(bookIdMapping);

        Iterator<Map.Entry<Long, Book>> iterator = bookIdMapping.entrySet().iterator();
        while (iterator.hasNext()) {
            BookVO bookVO = new BookVO();
            Map.Entry<Long, Book> entry = iterator.next();
            setBookVOProperties(bookVO, entry);
            setAuthorNames(bookId2AuthorNamesMapping, bookVO, entry);
            setProviderName(bookId2ProviderMapping, bookVO, entry);
            bookVOS.add(bookVO);
        }
        return bookVOS;
    }

    private void setBookVOProperties(BookVO bookVO, Map.Entry<Long, Book> entry) {
        try {
            BeanUtils.copyProperties(bookVO, entry.getValue());
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.warn("convert occur exception:{}", e);
        }
    }

    private void setProviderName(Map<Long, ContentProvider> bookId2ProviderMapping, BookVO bookVO, Map.Entry<Long, Book> entry) {
        if (bookId2ProviderMapping.get(entry.getKey()) != null) {
            bookVO.setProviderName(bookId2ProviderMapping.get(entry.getKey()).getName());
        }
    }

    private void setAuthorNames(Map<Long, List<String>> bookId2AuthorNamesMapping, BookVO bookVO, Map.Entry<Long, Book> entry) {
        if (CollectionUtils.isNotEmpty(bookId2AuthorNamesMapping.get(entry.getKey()))) {
            bookVO.setAuthorNames(Joiner.on(",").join(bookId2AuthorNamesMapping.get(entry.getKey())));
        }
    }

    /**
     * 获取每个书籍的提供商名称
     *
     * @param bookIdMapping
     * @return
     */
    private Map<Long, ContentProvider> getProviderNameMap(Map<Long, Book> bookIdMapping) {
        HashSet<Long> providerIdSet = Sets.newHashSetWithExpectedSize(bookIdMapping.size());
        Iterator<Map.Entry<Long, Book>> iterator = bookIdMapping.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, Book> entry = iterator.next();
            providerIdSet.add(entry.getValue().getProviderId());
        }
        return providerMapper.queryProvidersByIds(providerIdSet);
    }

    /**
     * 获取每个书籍的作者信息
     *
     * @param bookIdMapping
     * @return
     */
    private Map<Long, List<String>> getAuthorNamesMap(Map<Long, Book> bookIdMapping) {
        Map<Long, List<String>> bookId2AuthorNamesMap = Maps.newHashMapWithExpectedSize(bookIdMapping.size());
        HashSet<Long> authorIdSet = Sets.newHashSetWithExpectedSize(bookIdMapping.size());
        Iterator<Map.Entry<Long, Book>> iterator = bookIdMapping.entrySet().iterator();
        Map<Long, List<Long>> bookId2AuthorIdsMapping = Maps.newHashMapWithExpectedSize(bookIdMapping.size());
        while (iterator.hasNext()) {
            Map.Entry<Long, Book> entry = iterator.next();
            if (null == entry.getValue().getAuthorIds()) {
                continue;
            }
            List<Long> authorIds = transfer(Splitter.on(",").splitToList(entry.getValue().getAuthorIds()));
            authorIdSet.addAll(authorIds);
            bookId2AuthorIdsMapping.put(entry.getKey(), authorIds);
        }
        if(CollectionUtils.isNotEmpty(authorIdSet)){
            Map<Long, Author> authorIdMapping = authorMapper.queryAuthorsByIds(authorIdSet);
            if (MapUtils.isNotEmpty(authorIdMapping)) {
                Iterator<Map.Entry<Long, List<Long>>> it = bookId2AuthorIdsMapping.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<Long, List<Long>> bookId2AuthorIdsEntry = it.next();
                    bookId2AuthorNamesMap.put(bookId2AuthorIdsEntry.getKey(), getAuthorNames(bookId2AuthorIdsEntry.getValue(), authorIdMapping));
                }
            }
        }
        return bookId2AuthorNamesMap;
    }

    private List<String> getAuthorNames(List<Long> authorIds, Map<Long, Author> authorIdMapping) {
        if (CollectionUtils.isEmpty(authorIds)) {
            return Lists.newArrayListWithCapacity(0);
        }
        List<String> authorNames = Lists.newArrayListWithCapacity(authorIds.size());
        for (Long authorId : authorIds) {
            authorNames.add(authorIdMapping.get(authorId).getName());
        }
        return authorNames;
    }

    private List<Long> transfer(List<String> authorIdStrList) {
        if (CollectionUtils.isEmpty(authorIdStrList)) {
            return Lists.newArrayListWithCapacity(0);
        }
        List<Long> authorIds = Lists.newArrayListWithCapacity(authorIdStrList.size());
        for (String authorIdStr : authorIdStrList) {
            authorIds.add(Long.parseLong(authorIdStr));
        }
        return authorIds;
    }

}
