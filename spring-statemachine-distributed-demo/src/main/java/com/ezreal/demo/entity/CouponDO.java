package com.ezreal.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("coupon")
@Data
public class CouponDO {

    @TableId
    private Long id;

    private String couponStatus;

    private String name;

}
