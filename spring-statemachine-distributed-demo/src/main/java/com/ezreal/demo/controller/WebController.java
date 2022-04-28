package com.ezreal.demo.controller;


import com.ezreal.demo.eunms.Events;
import com.ezreal.demo.eunms.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @Autowired
    private StateMachine<States, Events> stateMachine;

    @Autowired
    private StateMachinePersister<States, Events, String> stateMachinePersister;



    @GetMapping("/")
    public String helloWorld() {
        return "hello World!";
    }

    @GetMapping("/change")
    public String change(String id, String change) {

        try {
            StateMachine<States, Events> statesEventsStateMachine = resetStateMachineFromStore(id);

            switch (change){
                case "1":
                    //业务代码
                    feedMachine(id, Events.FROZENING);
                    break;
                case "2":
                    feedMachine(id, Events.USEING);
                    break;
                case "3":
                    feedMachine(id, Events.END);
                    break;
                case "4":
                    feedMachine(id, Events.ROLLBACK);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "ok";

    }

    private StateMachine<States, Events> resetStateMachineFromStore(String coupon) throws Exception {
        return stateMachinePersister.restore(stateMachine,  coupon);
    }

    private void feedMachine(String user, Events id) throws Exception {
        stateMachine.sendEvent(id);
        stateMachinePersister.persist(stateMachine,  user);
    }
}
