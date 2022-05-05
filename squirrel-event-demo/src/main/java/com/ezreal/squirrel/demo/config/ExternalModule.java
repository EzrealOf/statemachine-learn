package com.ezreal.squirrel.demo.config;

import com.ezreal.squirrel.demo.dao.CouponDao;
import com.ezreal.squirrel.demo.entity.CouponContext;
import com.ezreal.squirrel.demo.entity.CouponDO;
import com.ezreal.squirrel.demo.eunms.Events;
import com.ezreal.squirrel.demo.eunms.States;
import lombok.extern.slf4j.Slf4j;
import org.squirrelframework.foundation.exception.TransitionException;
import org.squirrelframework.foundation.fsm.Action;
import org.squirrelframework.foundation.fsm.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;

@Slf4j
public class ExternalModule {

    private CouponDao couponDao;

    public ExternalModule(CouponDao couponDao){
        this.couponDao = couponDao;

    }

    @OnTransitionEnd
    @ListenerOrder(10) // Since 0.3.1 ListenerOrder can be used to insure listener invoked orderly
    public void transitionEnd() {
        // method annotated with TransitionEnd will be invoked when transition end...
        // the method must be public and return nothing

        log.info("-----transitionEnd-----");
    }

    @OnTransitionBegin
    public void transitionBegin(Events event) {
        // method annotated with TransitionBegin will be invoked when transition begin...
        log.info("-----transitionBegin-----:{}", event);
    }

    // 'event'(E), 'from'(S), 'to'(S), 'context'(C) and 'stateMachine'(T) can be used in MVEL scripts
//    @OnTransitionBegin(when="event.name().equals(\"toB\")")
    public void transitionBeginConditional() {
        // method will be invoked when transition begin while transition caused by event "toB"
        log.info("-----transitionBeginConditional-----");
    }

    @OnTransitionComplete
    public void transitionComplete(States from, States to, Events event, CouponContext context) throws InvocationTargetException {
        // method annotated with TransitionComplete will be invoked when transition complete...
        log.info("-------transitionComplete-------from:{},to:{},event:{},context:{}", from, to, event, context);
        CouponDO couponDO = couponDao.selectById(context.getCouponCode());
        couponDO.setCouponStatus(to.name());
        couponDao.updateById(couponDO);
        throw new InvocationTargetException(null);
    }

    @OnTransitionDecline
    public void transitionDeclined(States from, Events event, CouponContext context) throws RuntimeException {
        // method annotated with TransitionDecline will be invoked when transition declined...
        log.info("-------transitionDeclined-------from:{},event:{},context:{}", from, event, context);
//        throw new RuntimeException("transitionDeclined"+from);
    }

//        @OnBeforeActionExecuted
    public void onBeforeActionExecuted(States sourceState, States targetState,
                                       Object event, Object context, Action<?, ?, ?, ?> action) {
        // method annotated with OnAfterActionExecuted will be invoked before action invoked
//        log.info("-------onBeforeActionExecuted-------sourceState:{},event:{},context:{}", from, event, context);

    }

    //    @OnAfterActionExecuted
    public void onAfterActionExecuted(Object sourceState, Object targetState,
                                      Object event, Object context, int[] mOfN, Action<?, ?, ?, ?> action) {
        // method annotated with OnAfterActionExecuted will be invoked after action invoked
    }

    //    @OnActionExecException
    public void onActionExecException(Action<?, ?, ?, ?> action, TransitionException e) {
        // method annotated with OnActionExecException will be invoked when action thrown exception
    }
}
