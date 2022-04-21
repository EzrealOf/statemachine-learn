package com.ezreal.statemachine.sys.demo;

import org.junit.jupiter.api.Test;
import unquietcode.tools.esm.StateMachine;
import unquietcode.tools.esm.StringStateMachine;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class String_Test {
    @Test
    public void testStringState() {
        final AtomicReference<String> cur = new AtomicReference<String>(null);

        StateMachine<String> sm = new StringStateMachine();

        sm.addTransition(null, "hello");
        sm.addTransition("hello", "world", (from, to) -> cur.set(to));
        sm.addTransition("world", "goodbye ");
        sm.addTransition("GOODBYE", null);

        sm.transition("hello");
        assertEquals(null, cur.get());

        sm.transition("world");
        assertEquals("world", cur.get());

        sm.transition("goodbye");
    }

}
