package com.wolf.hookahshopee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageDTO<T> {

    private List<T> items;

    private Long totalElements;
}
