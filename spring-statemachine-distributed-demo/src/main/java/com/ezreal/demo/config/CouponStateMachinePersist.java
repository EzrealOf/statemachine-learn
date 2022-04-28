package com.ezreal.demo.config;

import com.ezreal.demo.dao.CouponDao;
import com.ezreal.demo.entity.CouponDO;
import com.ezreal.demo.eunms.Events;
import com.ezreal.demo.eunms.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;

@Component
public class CouponStateMachinePersist implements StateMachinePersist<States, Events, String> {
    @Autowired
    private CouponDao couponDao;


    @Override
    public void write(StateMachineContext<States, Events> context, String contextObj) throws Exception {

        CouponDO couponDO = couponDao.selectById(contextObj);
        couponDO.setCouponStatus(context.getState().name());
        couponDao.updateById(couponDO);
    }

    @Override
    public StateMachineContext<States, Events> read(String contextObj) throws Exception {

        CouponDO couponDO = couponDao.selectById(contextObj);

        return new DefaultStateMachineContext<>(States.valueOf(couponDO.getCouponStatus()), null, null, null, null, contextObj);

    }
}
