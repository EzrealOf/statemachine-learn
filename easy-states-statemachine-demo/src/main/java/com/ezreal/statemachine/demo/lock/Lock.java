package com.ezreal.statemachine.demo.lock;

import com.ezreal.statemachine.demo.events.PushEvent;
import org.jeasy.states.api.EventHandler;

import java.util.Date;

public class Lock implements EventHandler<PushEvent> {

    @Override
    public void handleEvent(PushEvent event) throws Exception {
        System.out.println("Notified about event '" + event.getName() + "' triggered at " + new Date(event.getTimestamp()));
        System.out.println("Locking turnstile..");
    }
}
