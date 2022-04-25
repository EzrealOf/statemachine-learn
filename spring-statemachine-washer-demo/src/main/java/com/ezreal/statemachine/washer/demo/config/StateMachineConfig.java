package com.ezreal.statemachine.washer.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.config.configurers.StateConfigurer;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {


    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
        states
                .withStates()
                .initial(States.RUNNING)
                .state(States.POWEROFF)
                .end(States.END)
                .and()
                .withStates()
                .parent(States.RUNNING)
                .initial(States.WASHING)
                .state(States.RINSING)
                .state(States.DRYING)
                .history(States.HISTORY, StateConfigurer.History.SHALLOW);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(States.WASHING).target(States.RINSING)
                .event(Events.RINSE)
                .and()
                .withExternal()
                .source(States.RINSING).target(States.DRYING)
                .event(Events.DRY)
                .and()
                .withExternal()
                .source(States.RUNNING).target(States.POWEROFF)
                .event(Events.CUTPOWER)
                .and()
                .withExternal()
                .source(States.POWEROFF).target(States.HISTORY)
                .event(Events.RESTOREPOWER)
                .and()
                .withExternal()
                .source(States.RUNNING).target(States.END)
                .event(Events.STOP);
    }

}





