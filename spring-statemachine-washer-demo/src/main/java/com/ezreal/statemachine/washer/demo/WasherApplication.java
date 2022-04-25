package com.ezreal.statemachine.washer.demo;

import com.ezreal.statemachine.washer.demo.config.Events;
import com.ezreal.statemachine.washer.demo.config.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import reactor.core.publisher.Mono;


@SpringBootApplication
public class WasherApplication implements CommandLineRunner {
    @Autowired
    private StateMachine<States, Events> machine;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WasherApplication.class, args);
    }


    public void run(String... args) throws Exception {
        machine.sendEvent(Mono.just(MessageBuilder.withPayload(Events.STOP).build()));
    }
}
