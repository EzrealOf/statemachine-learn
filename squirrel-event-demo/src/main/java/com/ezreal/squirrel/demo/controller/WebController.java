package com.ezreal.squirrel.demo.controller;


import com.ezreal.squirrel.demo.config.CouponStatemachineFactory;
import com.ezreal.squirrel.demo.dao.CouponDao;
import com.ezreal.squirrel.demo.entity.CouponContext;
import com.ezreal.squirrel.demo.entity.CouponDO;
import com.ezreal.squirrel.demo.eunms.Events;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.squirrelframework.foundation.fsm.StateMachine;

@Slf4j
@RestController
public class WebController {

    @Autowired
    private CouponStatemachineFactory couponStatemachineFactory;

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Autowired
    private CouponDao couponDao;


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


    private void feedMachine(CouponContext context, Events id) throws Exception {
        StateMachine stateMachine = couponStatemachineFactory.buildStateMachine(context.getCouponCode());

        CouponDO couponDO = couponDao.selectById(context.getCouponCode());
        context.setCouponDO(couponDO);
            stateMachine.fire(id, context);

        Object currentState = stateMachine.getCurrentState();
        log.info("--------currentState:{}", currentState);


    }
}
