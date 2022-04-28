package com.ezreal.demo.config;

import com.ezreal.demo.eunms.Events;
import com.ezreal.demo.eunms.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CouponLister extends StateMachineListenerAdapter<States, Events> {

    @Override
    public void stateChanged(State<States, Events> from, State<States, Events> to) {
        log.info("----stateChanged----");
    }

    @Override
    public void stateEntered(State<States, Events> state) {
        log.info("----stateEntered----");
    }

    @Override
    public void stateExited(State<States, Events> state) {
        log.info("----stateExited----");
    }

    @Override
    public void eventNotAccepted(Message<Events> event) {
        log.info("----eventNotAccepted----");
    }

    @Override
    public void transition(Transition<States, Events> transition) {
        log.info("----transition----");
    }

    @Override
    public void transitionStarted(Transition<States, Events> transition) {
        log.info("----transitionStarted----");
    }

    @Override
    public void transitionEnded(Transition<States, Events> transition) {
        log.info("----transitionEnded----");
    }

    @Override
    public void stateMachineStarted(StateMachine<States, Events> stateMachine) {
        log.info("----stateMachineStarted----");
    }

    @Override
    public void stateMachineStopped(StateMachine<States, Events> stateMachine) {
        log.info("----stateMachineStopped----");
    }

    @Override
    public void stateMachineError(StateMachine<States, Events> stateMachine, Exception exception) {
        log.info("----stateMachineError----");
    }

    @Override
    public void extendedStateChanged(Object key, Object value) {
        log.info("----extendedStateChanged----");
    }

    @Override
    public void stateContext(StateContext<States, Events> stateContext) {
        log.info("----stateContext----");
    }
}
