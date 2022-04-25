//package com.ezreal.satemachine.turnstile.demo;
//
//
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.shell.core.annotation.CliCommand;
//import org.springframework.shell.core.annotation.CliOption;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//@Component
//public class StateMachineCommands extends AbstractStateMachineCommands<TurnstileConfig.States, TurnstileConfig.Events> {
//
//    @CliCommand(value = "sm event", help = "Sends an event to a state machine")
//    public String event(@CliOption(key = {"", "event"}, mandatory = true, help = "The event") final TurnstileConfig.Events event) {
//        getStateMachine()
//                .sendEvent(Mono.just(MessageBuilder
//                        .withPayload(event).build()))
//                .subscribe();
//        return "Event " + event + " send";
//    }
//}
