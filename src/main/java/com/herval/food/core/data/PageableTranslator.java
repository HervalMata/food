package com.herval.food.core.data;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;
import java.util.stream.Collectors;

/*
 * Criado Por Herval Mata em 18/12/2019
 */
public class PageableTranslator {

    public static Pageable translate(Pageable pageable, Map<String, String> fieldsMapping) {
        var orders = pageable.getSort().stream()
                .filter(order -> fieldsMapping.containsKey(order.getProperty()))
                .map(order -> new Sort(order.getDirection(),
                        fieldsMapping.get(order.getProperty())))
                .collect(Collectors.toList());
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by((Sort.Order) orders));

    }
}