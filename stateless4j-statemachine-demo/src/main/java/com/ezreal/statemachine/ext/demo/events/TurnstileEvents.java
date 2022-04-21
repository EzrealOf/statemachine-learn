package com.ezreal.statemachine.ext.demo.events;

import com.github.oxo42.stateless4j.delegates.Action;

import java.util.Date;

public class TurnstileEvents {

    public static Action coinEvent() {

        Action action = new Action() {
            @Override
            public void doIt() {
                System.out.println("Event" +
                        "{name='" + "coinEvent" + '\'' +
                        ", timestamp=" + new Date() +
                        '}');
            }
        };
        return action;
    }

    public static Action pushEvent() {

        Action action = new Action() {
            @Override
            public void doIt() {
                System.out.println("Event" +
                        "{name='" + "pushEvent" + '\'' +
                        ", timestamp=" + new Date() +
                        '}');
            }
        };

        return action;
    }
}
