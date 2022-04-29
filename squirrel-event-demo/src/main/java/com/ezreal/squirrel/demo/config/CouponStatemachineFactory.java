package com.ezreal.squirrel.demo.config;

import com.ezreal.squirrel.demo.dao.CouponDao;
import com.ezreal.squirrel.demo.entity.CouponDO;
import com.ezreal.squirrel.demo.eunms.Events;
import com.ezreal.squirrel.demo.eunms.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.squirrelframework.foundation.fsm.StateMachine;
import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.UntypedStateMachine;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;


@Component
public class CouponStatemachineFactory {

    @Autowired
    private CouponDao couponDao;

    public  StateMachine buildStateMachine(String couponCode) {
        UntypedStateMachineBuilder builder = StateMachineBuilderFactory.create(CouponStatemachine.class);
        builder.externalTransition().from(States.UN_USED).to(States.FROZEN).on(Events.FROZENING).callMethod("frozen");
        builder.externalTransition().from(States.FROZEN).to(States.USED).on(Events.USEING).callMethod("use");
        builder.externalTransition().from(States.USED).to(States.END).on(Events.END).callMethod("end");
        builder.externalTransition().from(States.USED).to(States.UN_USED).on(Events.ROLLBACK).callMethod("rollback");

        CouponDO couponDO = couponDao.selectById(couponCode);
        UntypedStateMachine untypedStateMachine = builder.newStateMachine(States.valueOf(couponDO.getCouponStatus()));
        return untypedStateMachine;

    }


}
