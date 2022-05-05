package com.ezreal.squirrel.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CouponContext  implements Serializable {


    private String couponCode;

    private CouponDO couponDO;

    private Object request;

    public CouponContext() {
    }

    public CouponContext(String couponCode, CouponDO couponDO, Object request) {
        this.couponCode = couponCode;
        this.couponDO = couponDO;
        this.request = request;
    }

    @Override
    public String toString() {
        return "CouponContext{" +
                "couponCode='" + couponCode + '\'' +
                ", couponDO=" + couponDO +
                ", request=" + request +
                '}';
    }
}
