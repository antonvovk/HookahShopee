package com.wolf.hookahshopee.mapper;

import java.util.List;

public interface IMapper<S, T> {

    T toDto(S source);

    List<T> toDto(List<S> sourceList);
}
