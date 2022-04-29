package com.ezreal.squirrel.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("coupon")
@Data
public class CouponDO implements Serializable {

    @TableId
    private Long id;

    private String couponStatus;

    private String name;

    @Override
    public String toString() {
        return "CouponDO{" +
                "id=" + id +
                ", couponStatus='" + couponStatus + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
