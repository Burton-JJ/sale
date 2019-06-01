package com.burton.sale.commons;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Burton
 * @location：com.burton.sale.commons.PageRequest
 * @title: PageRequest
 * @projectName sale
 * @description: 分页请求对象
 * @date 2019/5/30 14:32
 */
@Getter
@Setter
public class PageRequest {

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 每页大小
     */
    private Integer pageSize;

    public PageRequest(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
