package com.ezreal.demo.guard;

import com.ezreal.demo.entity.CouponContext;
import com.ezreal.demo.eunms.Events;
import com.ezreal.demo.eunms.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageHeaders;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.support.DefaultStateContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Slf4j
@Component
public class CouponGuard implements Guard<States, Events> {
    @Override
    public boolean evaluate(StateContext<States, Events> context) {
        log.info("------CouponGuard start-------{}", context.toString());
        CouponContext c = (CouponContext) context.getMessageHeaders().get("context");
        if (c.getCouponDO().getName().equals("pzj")) {
            return true;
        } else if (c.getCouponDO().getName().equals("hjm")){
//            context.setMessag()
//            context.getStateMachine().setStateMachineError(new RuntimeException("123"));
//            throw new RuntimeException("123");
            RuntimeException exception = new RuntimeException("123");
//            context.getMessageHeaders().put("exception", exception);
            context =  new DefaultStateContext<States, Events>( context.getStage(), context.getMessage(), context.getMessageHeaders(), context.getExtendedState(), context.getTransition(), context.getStateMachine(), context.getSource(), context.getTarget(), exception);
            log.info("------CouponGuard end-------{}", context.toString());

        }
        return false;
    }
}
