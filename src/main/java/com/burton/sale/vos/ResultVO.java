package com.burton.sale.vos;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Burton
 * @title: ResultVO
 * @projectName sale
 * @description: HTTP请求返回的最外层对象
 * 返回码+描述+多个类目+类目下商品
 * @date 2019/5/27 21:14
 */
@Getter
@Setter
public class ResultVO<T> {
    /**
     * 返回码
     */
    private Integer responseCode;
    /**
     * 返回信息
     */
    private String responseMsg;
    /**
     * 返回主体数据
     */
    private T data;
}
