package com.ezreal.demo.controller;


import com.ezreal.demo.entity.CouponContext;
import com.ezreal.demo.eunms.Events;
import com.ezreal.demo.eunms.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @Autowired
    private StateMachine<States, Events> stateMachine;

    @Autowired
    private StateMachinePersister<States, Events, CouponContext> stateMachinePersister;
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;


    @GetMapping("/")
    public String helloWorld() {
        return "hello World!";
    }

    @GetMapping("/change")
    public String change(String id, String change) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(def);
        CouponContext couponContext = new CouponContext();
        couponContext.setCouponCode(id);

        try {


            StateMachine<States, Events> statesEventsStateMachine = resetStateMachineFromStore(couponContext);

            switch (change) {
                case "1":
                    //业务代码
                    feedMachine(couponContext, Events.FROZENING);
                    break;
                case "2":
                    feedMachine(couponContext, Events.USEING);
                    break;
                case "3":
                    feedMachine(couponContext, Events.END);
                    break;
                case "4":
                    feedMachine(couponContext, Events.ROLLBACK);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            dataSourceTransactionManager.rollback(transaction);
        } finally {
            dataSourceTransactionManager.commit(transaction);
        }

        return "ok";

    }

    private StateMachine<States, Events> resetStateMachineFromStore(CouponContext context) throws Exception {
        return stateMachinePersister.restore(stateMachine, context);
    }

    private void feedMachine(CouponContext context, Events id) throws Exception {
        Message<Events> build = MessageBuilder.withPayload(id).setHeader("context", context).build();
        stateMachine.sendEvent(build);
        stateMachinePersister.persist(stateMachine, context);
    }
}
