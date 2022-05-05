package com.ezreal.squirrel.demo.config;

import com.ezreal.squirrel.demo.dao.CouponDao;
import com.ezreal.squirrel.demo.entity.CouponContext;
import com.ezreal.squirrel.demo.entity.CouponDO;
import com.ezreal.squirrel.demo.eunms.Events;
import com.ezreal.squirrel.demo.eunms.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.squirrelframework.foundation.fsm.*;


@Component
public class CouponStatemachineFactory {

    @Autowired
    private CouponDao couponDao;
//    @Autowired
//    private CouponListen couponListen;
    @Autowired
    private FrozenCondition frozenCondition;
//    @Autowired
//    private ExternalModule externalModule;

    public  CouponStatemachine buildStateMachine(String couponCode) {
        StateMachineBuilder<CouponStatemachine, States, Events, CouponContext> builder = StateMachineBuilderFactory.create(CouponStatemachine.class, States.class, Events.class, CouponContext.class);
        builder.externalTransition().from(States.UN_USED).to(States.FROZEN).on(Events.FROZENING).when(frozenCondition).callMethod("frozen");
        builder.externalTransition().from(States.FROZEN).to(States.USED).on(Events.USEING).callMethod("use");
        builder.externalTransition().from(States.USED).to(States.END).on(Events.END).callMethod("end");
        builder.externalTransition().from(States.USED).to(States.UN_USED).on(Events.ROLLBACK).callMethod("rollback");

        CouponDO couponDO = couponDao.selectById(couponCode);
        CouponStatemachine couponStatemachine = builder.newStateMachine(States.valueOf(couponDO.getCouponStatus()));
//        couponStatemachine.addStateMachineListener(couponListen);
//        couponStatemachine.addDeclarativeListener(new ExternalModule(couponDao));
        return couponStatemachine;

    }




}
