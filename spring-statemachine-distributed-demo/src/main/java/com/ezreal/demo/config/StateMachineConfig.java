package com.ezreal.demo.config;

import com.ezreal.demo.eunms.Events;
import com.ezreal.demo.eunms.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;

import java.util.EnumSet;

@Slf4j
@Configuration
public class StateMachineConfig {

    @Autowired
    CouponStateMachinePersist couponStateMachinePersist;


    @Bean(name = "stateMachineTarget")
    @Scope(scopeName = "prototype")
    public StateMachine<States, Events> stateMachineTarget() throws Exception {
        StateMachineBuilder.Builder<States, Events> builder = StateMachineBuilder.<States, Events>builder();

        builder.configureConfiguration()
                .withConfiguration()
                .autoStartup(true);

        builder.configureStates()
                .withStates()
                .initial(States.UN_USED)
                .states(EnumSet.allOf(States.class));

        builder.configureTransitions()
                .withExternal()
                .source(States.UN_USED).target(States.FROZEN)
                .action(pageviewAction())
                .event(Events.FROZENING)
                .and()
                .withExternal()
                .source(States.FROZEN).target(States.USED)
                .action(pageviewAction())
                .event(Events.USEING)
                .and()
                .withExternal()
                .source(States.USED).target(States.END)
                .action(pageviewAction())
                .event(Events.END)
                .and()
                .withExternal()
                .source(States.USED).target(States.UN_USED)
                .action(pageviewAction())
                .event(Events.ROLLBACK);

        return builder.build();
    }

    @Bean
    public Action<States, Events> pageviewAction() {
        return new Action<States, Events>() {

            @Override
            public void execute(StateContext<States, Events> context) {
               log.info("-----------context"+context.getSources().toString() + ":"+context.getTarget().toString());
            }
        };
    }

    @Bean
    public StateMachinePersister<States, Events, String> stateMachineRuntimePersister() {
        return new DefaultStateMachinePersister<States, Events, String>(couponStateMachinePersist);
    }

}
