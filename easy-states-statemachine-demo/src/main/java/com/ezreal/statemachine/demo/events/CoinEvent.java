package com.ezreal.statemachine.demo.events;

import org.jeasy.states.api.AbstractEvent;

public class CoinEvent extends AbstractEvent {
    public CoinEvent() {
        super("CoinEvent");
    }

    protected CoinEvent(String name) {
        super(name);
    }
}
