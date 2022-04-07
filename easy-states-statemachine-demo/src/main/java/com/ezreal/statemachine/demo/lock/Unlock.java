package com.ezreal.statemachine.demo.lock;

import com.ezreal.statemachine.demo.events.CoinEvent;
import org.jeasy.states.api.EventHandler;

import java.util.Date;

public class Unlock implements EventHandler<CoinEvent> {

    @Override
    public void handleEvent(CoinEvent event) throws Exception {
        System.out.println("Notified about event '" + event.getName() + "' triggered at " + new Date(event.getTimestamp()));
        System.out.println("Unlocking turnstile..");
    }
}
