package com.ezreal.demo.config;

import com.ezreal.demo.dao.CouponDao;
import com.ezreal.demo.entity.CouponContext;
import com.ezreal.demo.entity.CouponDO;
import com.ezreal.demo.eunms.Events;
import com.ezreal.demo.eunms.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class CouponStateMachinePersist implements StateMachinePersist<States, Events, CouponContext> {
    @Autowired
    private CouponDao couponDao;


    @Override
    public void write(StateMachineContext<States, Events> context, CouponContext contextObj) throws Exception {


        CouponDO couponDO = couponDao.selectById(contextObj.getCouponCode());
        couponDO.setCouponStatus(context.getState().name());
        couponDao.updateById(couponDO);
        log.info("------CouponStateMachinePersist#write----{}",contextObj.toString());
    }

    @Override
    public StateMachineContext<States, Events> read(CouponContext contextObj) throws Exception {

        CouponDO couponDO = couponDao.selectById(contextObj.getCouponCode());
        contextObj.setCouponDO(couponDO);

        Map<String, Object> eventHeaders = new HashMap<>();
        eventHeaders.put("coupon", contextObj.getCouponDO());
        eventHeaders.put("request", contextObj.getRequest());
        log.info("------CouponStateMachinePersist#read----{}",contextObj.toString());
        return new DefaultStateMachineContext<States, Events>(States.valueOf(couponDO.getCouponStatus()), null, eventHeaders, null, null, contextObj.getCouponCode());

    }
}
