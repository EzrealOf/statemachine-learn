package com.ezreal.statemachine.demo;

import com.ezreal.statemachine.demo.events.CoinEvent;
import com.ezreal.statemachine.demo.events.PushEvent;
import com.ezreal.statemachine.demo.lock.Lock;
import com.ezreal.statemachine.demo.lock.Unlock;
import org.jeasy.states.api.FiniteStateMachine;
import org.jeasy.states.api.State;
import org.jeasy.states.api.Transition;
import org.jeasy.states.core.FiniteStateMachineBuilder;
import org.jeasy.states.core.TransitionBuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * A turnstile has two states: Locked and Unlocked.
 * There are two inputs that affect its state: putting a coin in the slot (coin) and pushing the arm (push).
 * In the locked state, pushing on the arm has no effect;
 * no matter how many times the input push is given it stays in the locked state.
 * Putting a coin in, that is giving the machine a coin input, shifts the state from Locked to Unlocked.
 * In the unlocked state, putting additional coins in has no effect;
 * that is, giving additional coin inputs does not change the state.
 *
 *
 */
public class QuickStartSample {

    /**
     * For this example, we define:
     *
     * two states: Locked and Unlocked
     * two events: PushEvent and CoinEvent
     * two actions: Lock and Unlock
     * and four transitions: pushLocked, unlock, coinUnlocked and lock
     * @param args
     */
    public static void main(String[] args) {
        //1. define states
        State locked = new State("locked");
        State unlocked = new State("unlocked");

        Set<State> states = new HashSet<>();
        states.add(locked);
        states.add(unlocked);

        //2. Then, define events
        //class PushEvent extends AbstractEvent { }
        //class CoinEvent extends AbstractEvent { }

        //3.
        Transition unlock = new TransitionBuilder()
                .name("unlock")
                .sourceState(locked)
                .eventType(CoinEvent.class)
                .eventHandler(new Unlock())
                .targetState(unlocked)
                .build();

        Transition pushLocked = new TransitionBuilder()
                .name("pushLocked")
                .sourceState(locked)
                .eventType(PushEvent.class)
                .targetState(locked)
                .build();

        Transition lock = new TransitionBuilder()
                .name("lock")
                .sourceState(unlocked)
                .eventType(PushEvent.class)
                .eventHandler(new Lock())
                .targetState(locked)
                .build();

        Transition coinUnlocked = new TransitionBuilder()
                .name("coinUnlocked")
                .sourceState(unlocked)
                .eventType(CoinEvent.class)
                .targetState(unlocked)
                .build();

        FiniteStateMachine turnstileStateMachine = new FiniteStateMachineBuilder(states, locked)
                .registerTransition(lock)
                .registerTransition(pushLocked)
                .registerTransition(unlock)
                .registerTransition(coinUnlocked)
                .build();

    }

}
