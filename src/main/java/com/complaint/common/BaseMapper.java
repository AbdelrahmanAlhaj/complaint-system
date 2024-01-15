package com.complaint.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @param <S> Source parameter to convert from
 * @param <T> Target parameter to convert to
 * @author Abdelrhaman Alhaj
 */
public interface BaseMapper<S, T> {

    T mapToDto(S source);

    S mapToEntity(T source);

    List<T> mapListToDto(List<S> sourceList);

    List<S> mapListToEntity(List<T> sourceList);

    default Page<T> mapPageToDto(Page<S> productPage, Pageable pageable) {
        List<T> productList = this.mapListToDto(productPage.getContent());
        return new PageImpl<>(productList, pageable, productPage.getTotalElements());
    }

}
