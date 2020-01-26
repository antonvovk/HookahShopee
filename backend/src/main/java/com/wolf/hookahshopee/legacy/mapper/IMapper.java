package com.wolf.hookahshopee.legacy.mapper;

import java.util.List;

public interface IMapper<S, T> {

    T toDto(S source);

    S fromDto(T source);

    List<T> toDto(List<S> sourceList);

    List<S> fromDto(List<T> sourceList);
}
