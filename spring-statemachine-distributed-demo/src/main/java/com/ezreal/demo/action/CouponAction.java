package com.ezreal.demo.action;

import com.ezreal.demo.eunms.Events;
import com.ezreal.demo.eunms.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CouponAction implements Action<States, Events> {
    @Override
    public void execute(StateContext<States, Events> context) {
        log.info("CouponAction:{}", context);
    }
}
