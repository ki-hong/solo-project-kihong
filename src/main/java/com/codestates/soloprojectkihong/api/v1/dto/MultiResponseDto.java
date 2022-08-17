package com.codestates.soloprojectkihong.api.v1.dto;

import java.util.List;

public class MultiResponseDto<T> {

    private List<T> data;

    public MultiResponseDto(List<T> data) {
        this.data = data;
    }
}
