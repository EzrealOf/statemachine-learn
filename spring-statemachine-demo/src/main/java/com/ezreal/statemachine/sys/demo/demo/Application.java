package com.ezreal.statemachine.sys.demo.demo;

import com.ezreal.statemachine.sys.demo.demo.enums.Events;
import com.ezreal.statemachine.sys.demo.demo.enums.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
public class Application  {
    @Autowired
    private StateMachine<States, Events> stateMachine;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public void run(String... args) throws Exception {
        stateMachine.sendEvent(Events.E1);
        stateMachine.sendEvent(Events.E2);
    }
}
