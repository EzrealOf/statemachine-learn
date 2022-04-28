package com.ezreal.demo.persist;

import com.ezreal.demo.dao.CouponDao;
import com.ezreal.demo.entity.CouponDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

public class LocalPersistStateChangeListener implements PersistStateMachineHandler.PersistStateChangeListener {

    @Autowired
    private CouponDao couponDao;

    @Override
    public void onPersist(State<String, String> state, Message<String> message, Transition<String, String> transition, StateMachine<String, String> stateMachine) {
        CouponDO coupon = new CouponDO();
        this.couponDao.updateById(coupon);
    }
}
