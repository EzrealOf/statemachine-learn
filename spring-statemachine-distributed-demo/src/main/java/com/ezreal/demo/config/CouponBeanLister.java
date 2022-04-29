//package com.ezreal.demo.config;
//
//import com.ezreal.demo.eunms.Events;
//import com.ezreal.demo.eunms.States;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.statemachine.StateContext;
//import org.springframework.statemachine.annotation.OnEventNotAccepted;
//import org.springframework.statemachine.annotation.OnStateMachineError;
//import org.springframework.statemachine.annotation.OnTransition;
//import org.springframework.statemachine.annotation.WithStateMachine;
//
//@Slf4j
//@WithStateMachine(id = "COUPON")
//public class CouponBeanLister {
//
//
//    @OnTransition
//    public void anyTransition(StateContext<States, Events> stateContext) {
//
//        log.info("anyTransition:{}", stateContext.toString());
//
//    }
//
//    @OnEventNotAccepted
//    public void eventNotAccepted(StateContext<States, Events> context) {
//        log.warn("MainStateMachine当前事件和状态不匹配，当前事件：{}, 当前状态：{}", context.getEvent(), context.getSource().getId());
//        context.getStateMachine().setStateMachineError(new RuntimeException("123"));
//    }
//
//    @OnStateMachineError
//    public void OnStateMachineError(StateContext<States, Events> context, Exception e) {
//        log.info("MainStateMachine OnStateMachineError-->");
//        // 将异常塞进当前的SM实例，用于外面业务逻辑提取处理
//        context.getStateMachine().getExtendedState().getVariables().put(Exception.class, e);
//    }
//
//
//
//}
