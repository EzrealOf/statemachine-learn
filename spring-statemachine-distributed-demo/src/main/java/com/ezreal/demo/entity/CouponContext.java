package com.ezreal.demo.entity;

import lombok.Data;

@Data
public class CouponContext {


    private String couponCode;

    private CouponDO couponDO;

    private Object request;

    @Override
    public String toString() {
        return "CouponContext{" +
                "couponCode='" + couponCode + '\'' +
                ", couponDO=" + couponDO +
                ", request=" + request +
                '}';
    }
}
