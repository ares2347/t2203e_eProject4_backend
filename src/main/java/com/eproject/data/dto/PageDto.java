package com.eproject.data.dto;

import java.util.ArrayList;
import java.util.Collection;

public class PageDto<T> {
    public PageDto(Collection<T> data, int page, int size, int totalPage, int total) {
        this.data = data;
        this.page = page;
        this.size = size;
        this.totalPage = totalPage;
        this.total = total;
    }
    public static PageDto empty(){
        return new PageDto(new ArrayList(), 0, 0, 0, 0);
    }
    public Collection<T> data;
    public int page;
    public int size;
    public int totalPage;
    public int total;
}
