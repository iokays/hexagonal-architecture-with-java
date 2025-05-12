package com.iokays.flowable.core.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;

public class Pages {

    public static <T, R> Page<R> toNewPage(Pageable pageable, Page<T> page, Function<? super T, R> mapper) {
        return toNewPage(pageable, page.getTotalElements(), page.getContent(), mapper);
    }

    public static <T, R> Page<R> toNewPage(Pageable pageable, long total, List<T> list, Function<? super T, R> mapper) {
        return new PageImpl<>(CollectionUtils.emptyIfNull(list).stream().map(mapper).toList(), pageable, total);
    }
}
