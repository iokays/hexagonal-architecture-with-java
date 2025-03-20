package com.iokays.dispatch.core.adapter.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.function.Function;

public class Pages {

    public static <T, R> Page<R> toNewPage(Pageable pageable, Page<T> page, Function<? super T, R> mapper) {
        return new PageImpl<>(CollectionUtils.emptyIfNull(page.getContent()).stream().map(mapper).toList(), pageable, page.getTotalElements());
    }
}
