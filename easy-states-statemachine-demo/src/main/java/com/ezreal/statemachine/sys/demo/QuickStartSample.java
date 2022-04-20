package com.ezreal.statemachine.sys.demo;

import com.ezreal.statemachine.sys.demo.events.CoinEvent;
import com.ezreal.statemachine.sys.demo.events.PushEvent;
import com.ezreal.statemachine.sys.demo.lock.Lock;
import com.ezreal.statemachine.sys.demo.lock.Unlock;
import org.jeasy.states.api.FiniteStateMachine;
import org.jeasy.states.api.FiniteStateMachineException;
import org.jeasy.states.api.State;
import org.jeasy.states.api.Transition;
import org.jeasy.states.core.FiniteStateMachineBuilder;
import org.jeasy.states.core.TransitionBuilder;

import java.util.HashSet;
import java.util.Scanner;
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
    public static void main(String[] args) throws FiniteStateMachineException {
        //1. define states
        State locked = new State("locked");
        State unlocked = new State("unlocked");

        Set<State> states = new HashSet<>();
        states.add(locked);
        states.add(unlocked);

        //2. Then, define events
        //class PushEvent extends AbstractEvent { }
        //class CoinEvent extends AbstractEvent { }

        //3. transitions
        Transition lockedCoin = new TransitionBuilder()
                .name("lockedCoin")
                .sourceState(locked)
                .eventType(CoinEvent.class)
                .eventHandler(new Unlock())
                .targetState(unlocked)
                .build();


        Transition lockedPush = new TransitionBuilder()
                .name("lockedPush")
                .sourceState(locked)
                .eventType(PushEvent.class)
                .targetState(locked)
                .build();

        Transition unlockCoin = new TransitionBuilder()
                .name("unlockCoin")
                .sourceState(unlocked)
                .eventType(CoinEvent.class)
                .targetState(unlocked)
                .build();

        Transition unlockPush = new TransitionBuilder()
                .name("unlockPush")
                .sourceState(unlocked)
                .eventType(PushEvent.class)
                .eventHandler(new Lock())
                .targetState(locked)
                .build();

        FiniteStateMachine turnstileStateMachine = new FiniteStateMachineBuilder(states, locked)
                .registerTransition(lockedCoin)
                .registerTransition(lockedPush)
                .registerTransition(unlockCoin)
                .registerTransition(unlockPush)
                .build();



        /*
         * Fire some events and print FSM state
         */
        System.out.println("Turnstile initial state : " + turnstileStateMachine.getCurrentState().getName());

        Scanner scanner = new Scanner(System.in);
        System.out.println("Which event do you want to fire?");
        System.out.println("1. Push [p]");
        System.out.println("2. Coin [c]");
        System.out.println("Press [q] to quit tutorial.");
        while (true) {
            String input = scanner.nextLine();
            if (input.trim().equalsIgnoreCase("p")) {
                System.out.println("input = " + input.trim());
                System.out.println("Firing push event..");
                turnstileStateMachine.fire(new PushEvent());
                System.out.println("Turnstile state : " + turnstileStateMachine.getCurrentState().getName());
            }
            if (input.trim().equalsIgnoreCase("c")) {
                System.out.println("input = " + input.trim());
                System.out.println("Firing coin event..");
                turnstileStateMachine.fire(new CoinEvent());
                System.out.println("Turnstile state : " + turnstileStateMachine.getCurrentState().getName());
            }
            if (input.trim().equalsIgnoreCase("q")) {
                System.out.println("input = " + input.trim());
                System.out.println("Bye!");
                System.exit(0);
            }

        }

    }

}
