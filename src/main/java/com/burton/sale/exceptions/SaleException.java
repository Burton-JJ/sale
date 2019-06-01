package com.burton.sale.exceptions;

import com.burton.sale.enums.ResultEnum;

/**
 * @author Burton
 * @location：com.burton.sale.exceptions.SaleException
 * @title: SaleException
 * @projectName sale
 * @description:异常
 * @date 2019/5/29 13:48
 */
public class SaleException extends RuntimeException{

    private String code;

    public SaleException(ResultEnum resultEnum) {
        //RuntimeException中有message属性
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SaleException(ResultEnum resultEnum,String errorMsg) {
        super(errorMsg);
        this.code = resultEnum.getCode();
    }
}
