package com.ezreal.squirrel.demo.config;

import com.ezreal.squirrel.demo.dao.CouponDao;
import com.ezreal.squirrel.demo.entity.CouponContext;
import com.ezreal.squirrel.demo.entity.CouponDO;
import com.ezreal.squirrel.demo.eunms.Events;
import com.ezreal.squirrel.demo.eunms.States;
import com.ezreal.squirrel.demo.util.ContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.squirrelframework.foundation.fsm.annotation.StateMachineParameters;
import org.squirrelframework.foundation.fsm.impl.AbstractStateMachine;
import org.squirrelframework.foundation.fsm.impl.AbstractUntypedStateMachine;

import java.lang.reflect.InvocationTargetException;


@Slf4j
@StateMachineParameters(stateType = States.class, eventType = Events.class, contextType = CouponContext.class)
public class CouponStatemachine extends AbstractStateMachine<CouponStatemachine, States ,Events, CouponContext> implements ApplicationContextAware {


    private static ApplicationContext applicationContext;




    public void frozen(States from, States to, Events event, CouponContext context) {
        CouponDao couponDao = ContextUtil.getBean(CouponDao.class);

        CouponDO couponDO = context.getCouponDO();
        couponDO.setName("lan");
        couponDao.updateById(couponDO);
        log.info("frozen");
    }

    public void use(States from, States to, Events event, CouponContext context) {
        log.info("use");
    }

    public void end(States from, States to, Events event, CouponContext context) {
        log.info("end");
    }

    public void rollback(States from, States to, Events event, CouponContext context) {
        log.info("rollback");
    }

    @Override
    protected void afterTransitionCausedException(States fromState, States toState, Events event, CouponContext context) {
        if(getLastException().getTargetException()!=null) {
            log.error("Transition caused exception", getLastException().getTargetException());
            throw new RuntimeException(getLastException().getMessage());
        }
    }

    @Override
    protected void afterTransitionDeclined(States fromState, Events event, CouponContext context) {
        StringBuilder sb = new StringBuilder();
        sb.append("不被允许的状态流转").append(fromState.name()).append("在事件").append(event.name());
        throw new RuntimeException(sb.toString());
    }

    @Override
    protected void afterTransitionCompleted(States fromState, States toState, Events event, CouponContext context) {

        log.info("-------afterTransitionCompleted-------from:{},to:{},event:{},context:{}", fromState, toState, event, context);

        CouponDao couponDao = ContextUtil.getBean(CouponDao.class);

        CouponDO couponDO = couponDao.selectById(context.getCouponCode());
        couponDO.setCouponStatus(toState.name());
        couponDao.updateById(couponDO);

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
