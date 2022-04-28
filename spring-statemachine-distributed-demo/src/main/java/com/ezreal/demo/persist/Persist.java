//package com.ezreal.demo.persist;
//
//import com.ezreal.demo.dao.CouponDao;
//import com.ezreal.demo.entity.CouponDO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.statemachine.StateMachine;
//import org.springframework.statemachine.state.State;
//import org.springframework.statemachine.transition.Transition;
//import org.springframework.stereotype.Component;
//
////@Component
//public class Persist {
//
//    private final PersistStateMachineHandler handler;
//
//    @Autowired
//    private CouponDao couponDao;
//
//    private final PersistStateMachineHandler.PersistStateChangeListener listener = new LocalPersistStateChangeListener();
//
//    public Persist(PersistStateMachineHandler handler) {
//        this.handler = handler;
//        this.handler.addPersistStateChangeListener(listener);
//    }
//
//    /**
//     *
//     * @param id
//     * @param event  FROZENING  USEING  END
//     */
//    public void change(Long id, String event) {
//
//        CouponDO couponDO = couponDao.selectById(id);
//        handler.handleEventWithState(MessageBuilder.withPayload(event).setHeader("couponId", id).build(), couponDO.getCouponStatus());
//    }
//
//    private class LocalPersistStateChangeListener implements PersistStateMachineHandler.PersistStateChangeListener {
//
//        @Override
//        public void onPersist(State<String, String> state, Message<String> message,
//                              Transition<String, String> transition, StateMachine<String, String> stateMachine) {
//            if (message != null && message.getHeaders().containsKey("coupon")) {
//                Integer couponId = message.getHeaders().get("couponId", Integer.class);
//                CouponDO couponDO = couponDao.selectById(couponId);
//                couponDO.setCouponStatus(state.getId());
//                couponDao.updateById(couponDO);
//            }
//        }
//    }
//
//
//}
