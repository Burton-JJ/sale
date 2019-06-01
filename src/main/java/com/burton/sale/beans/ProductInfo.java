package com.burton.sale.beans;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@Setter
@Getter
public class ProductInfo {
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    private Integer productStatus;

    public ProductInfo() {
    }

    public ProductInfo(String productId, String productName, BigDecimal productPrice, Integer productStock, String productDescription, String productIcon,
                       Integer categoryType, Date createTime, Date updateTime, Integer productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productDescription = productDescription;
        this.productIcon = productIcon;
        this.categoryType = categoryType;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.productStatus = productStatus;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productStock=" + productStock +
                ", productDescription='" + productDescription + '\'' +
                ", productIcon='" + productIcon + '\'' +
                ", categoryType=" + categoryType +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", productStatus=" + productStatus +
                '}';
    }
}