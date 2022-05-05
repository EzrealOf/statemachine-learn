package com.ezreal.squirrel.demo.config;

import com.ezreal.squirrel.demo.entity.CouponContext;
import org.springframework.stereotype.Component;
import org.squirrelframework.foundation.fsm.Condition;


@Component
public class FrozenCondition implements Condition<CouponContext> {

    @Override
    public boolean isSatisfied(CouponContext context) {
        if (context.getCouponDO().getName().equals("hjm")) {
//            throw new RuntimeException("123");
            return true;
        }
        return true;
    }

    @Override
    public String name() {
        return "hjm";
    }
}
