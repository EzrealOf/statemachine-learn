//package com.ezreal.demo.config;
//
//import com.ezreal.demo.dao.CouponDao;
//import com.ezreal.demo.persist.Persist;
//import com.ezreal.demo.persist.PersistStateMachineHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.statemachine.StateMachine;
//
//@Configuration
//public class PersistHandlerConfig {
//
//    @Autowired
//    private StateMachine<String, String> stateMachine;
//
//    @Autowired
//    private CouponDao couponDao;
//
//    @Bean
//    public Persist persist() {
//        return new Persist(persistStateMachineHandler());
//    }
//
//    @Bean
//    public PersistStateMachineHandler persistStateMachineHandler() {
//        return new PersistStateMachineHandler(stateMachine);
//    }
//
//
//}
