package com.ezreal.satemachine.turnstile.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class TurnstileApplication implements CommandLineRunner {


    @Autowired
    private StateMachine<TurnstileConfig.States, TurnstileConfig.Events> stateMachine;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TurnstileApplication.class, args);


    }

    public void run(String... args) throws Exception {
        System.out.println(stateMachine.getState().getId());
        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(TurnstileConfig.Events.PUSH).build()));
        stateMachine.sendEvent(TurnstileConfig.Events.COIN);
        stateMachine.sendEvent(TurnstileConfig.Events.PUSH);
        stateMachine.sendEvent(TurnstileConfig.Events.PUSH);

    }

}
