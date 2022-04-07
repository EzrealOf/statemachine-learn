package com.ezreal.statemachine.demo.events;

import org.jeasy.states.api.AbstractEvent;

public class PushEvent extends AbstractEvent {
    public PushEvent() {
        super("PushEvent");
    }

    protected PushEvent(String name) {
        super(name);
    }

}
