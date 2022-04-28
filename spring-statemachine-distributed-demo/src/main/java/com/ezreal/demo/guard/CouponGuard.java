package com.ezreal.demo.guard;

import com.ezreal.demo.entity.CouponContext;
import com.ezreal.demo.eunms.Events;
import com.ezreal.demo.eunms.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CouponGuard implements Guard<States, Events> {
    @Override
    public boolean evaluate(StateContext<States, Events> context) {
        log.info("------CouponGuard start-------{}",context.toString());
        CouponContext c = (CouponContext) context.getMessageHeaders().get("context");
        if (c.getCouponDO().getName().equals("pzj")){
            return true;
        }
        return false;
    }
}
