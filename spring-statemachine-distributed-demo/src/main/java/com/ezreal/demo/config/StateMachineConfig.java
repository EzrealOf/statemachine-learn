package com.ezreal.demo.config;

import com.ezreal.demo.action.CouponAction;
import com.ezreal.demo.entity.CouponContext;
import com.ezreal.demo.eunms.Events;
import com.ezreal.demo.eunms.States;
import com.ezreal.demo.guard.CouponGuard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccess;
import org.springframework.statemachine.access.StateMachineFunction;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptor;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;

import java.util.EnumSet;

@Slf4j
@Configuration
public class StateMachineConfig {

    @Autowired
    CouponStateMachinePersist couponStateMachinePersist;

    @Autowired
    CouponAction couponAction;

    @Autowired
    CouponGuard couponGuard;
    @Autowired
    private CouponLister couponLister;


    @Bean(name = "stateMachineTarget")
    @Scope(scopeName = "prototype")
    public StateMachine<States, Events> stateMachineTarget() throws Exception {
        StateMachineBuilder.Builder<States, Events> builder = StateMachineBuilder.<States, Events>builder();

        builder.configureConfiguration()
                .withConfiguration()
                .machineId("COUPON")
                .autoStartup(true);

        builder.configureStates()
                .withStates()
                .initial(States.UN_USED)
                .states(EnumSet.allOf(States.class));

        builder.configureTransitions()
                .withExternal()
                .source(States.UN_USED).target(States.FROZEN)
                .guard(couponGuard)
                .action(couponAction)
                .event(Events.FROZENING)


                .and()
                .withExternal()
                .source(States.FROZEN).target(States.USED)
                .guard(couponGuard)
                .action(pageviewAction())
                .event(Events.USEING)


                .and()
                .withExternal()
                .source(States.USED).target(States.END)
                .guard(couponGuard)
                .action(pageviewAction())
                .event(Events.END)


                .and()
                .withExternal()
                .source(States.USED).target(States.UN_USED)
                .guard(couponGuard)
                .action(pageviewAction())
                .event(Events.ROLLBACK);
                // TODO: 2022/4/28 对于选择的 并不是事件触发的 那其实可以通过参数来调用不同的事件来触发

//                .and()
//                .withChoice()
//                .source(States.UN_USED)
//                .first(States.FROZEN, couponGuard, couponAction)
//                .then(States.END,couponGuard);


        StateMachine<States, Events> build = builder.build();
        build.addStateListener(couponLister);
        /*build.getStateMachineAccessor().doWithAllRegions(new StateMachineFunction<StateMachineAccess<States, Events>>() {
            @Override
            public void apply(StateMachineAccess<States, Events> function) {
                function.addStateMachineInterceptor(
                        new StateMachineInterceptor<States, Events>() {
                            @Override
                            public Message<Events> preEvent(Message<Events> message, StateMachine<States, Events> stateMachine) {
                                return null;
                            }

                            @Override
                            public void preStateChange(State<States, Events> state, Message<Events> message, Transition<States, Events> transition, StateMachine<States, Events> stateMachine, StateMachine<States, Events> rootStateMachine) {

                            }

                            @Override
                            public void postStateChange(State<States, Events> state, Message<Events> message, Transition<States, Events> transition, StateMachine<States, Events> stateMachine, StateMachine<States, Events> rootStateMachine) {

                            }

                            @Override
                            public StateContext<States, Events> preTransition(StateContext<States, Events> stateContext) {
                                return null;
                            }

                            @Override
                            public StateContext<States, Events> postTransition(StateContext<States, Events> stateContext) {
                                return null;
                            }

                            @Override
                            public Exception stateMachineError(StateMachine<States, Events> stateMachine, Exception exception) {
                                log.info("123");
                                return exception;
                            }
                        }
                );
            }
        });*/
        return build;
    }

    @Bean
    public Action<States, Events> pageviewAction() {
        return new Action<States, Events>() {

            @Override
            public void execute(StateContext<States, Events> context) {
                log.info("pageviewAction start");
            }
        };
    }

    @Bean
    public StateMachinePersister<States, Events, CouponContext> stateMachineRuntimePersister() {
        return new DefaultStateMachinePersister<States, Events, CouponContext>(couponStateMachinePersist);
    }

}
