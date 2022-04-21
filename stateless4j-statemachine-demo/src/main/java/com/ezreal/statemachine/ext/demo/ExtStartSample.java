package com.ezreal.statemachine.ext.demo;

import com.ezreal.statemachine.ext.demo.enums.TurnstileStates;
import com.ezreal.statemachine.ext.demo.enums.TurnstileTriggers;
import com.ezreal.statemachine.ext.demo.events.TurnstileEvents;
import com.github.oxo42.stateless4j.StateMachine;
import com.github.oxo42.stateless4j.StateMachineConfig;
import com.github.oxo42.stateless4j.delegates.Action;
import com.github.oxo42.stateless4j.delegates.Action1;
import com.github.oxo42.stateless4j.delegates.Action2;
import com.github.oxo42.stateless4j.transitions.Transition;

import javax.swing.*;
import java.util.Objects;
import java.util.Scanner;

public class ExtStartSample {

    public static void main(String[] args) {
        StateMachineConfig<TurnstileStates, TurnstileTriggers> TurnstileConfig = new StateMachineConfig<>();

        TurnstileConfig.configure(TurnstileStates.Locked)
                .permit(TurnstileTriggers.LockCoin, TurnstileStates.UNLOCKED)
//                .ignore(TurnstileTriggers.LockPush)
                .onEntry(TurnstileEvents.coinEvent());
//                .onEntryFrom(TurnstileTriggers.LockCoin, new Action() {
//                    @Override
//                    public void doIt() {
//                        System.out.println("email");
//                    }
//                });

//        TurnstileConfig.configure(TurnstileStates.Locked)
//                .ignore(TurnstileTriggers.LockPush);


        TurnstileConfig.configure(TurnstileStates.UNLOCKED)
                .permit(TurnstileTriggers.UnlockPush, TurnstileStates.Locked)
                .onExit(TurnstileEvents.pushEvent());

        TurnstileConfig.configure(TurnstileStates.UNLOCKED)
                .ignore(TurnstileTriggers.UnlockCoin);

        StateMachine<TurnstileStates, TurnstileTriggers> stateMachine =
                new StateMachine<>(TurnstileStates.Locked, TurnstileConfig);



        /*
         * Fire some events and print FSM state
         */
        System.out.println("Turnstile initial state : " + stateMachine.getState().name());

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
                stateMachine.fire(TurnstileTriggers.UnlockPush);
                System.out.println("Turnstile state : " + stateMachine.getState().toString());
            }
            if (input.trim().equalsIgnoreCase("c")) {
                System.out.println("input = " + input.trim());
                System.out.println("Firing coin event..");
                stateMachine.fire(TurnstileTriggers.LockCoin);
                System.out.println("Turnstile state : " + stateMachine.getState().toString());
            }
            if (input.trim().equalsIgnoreCase("q")) {
                System.out.println("input = " + input.trim());
                System.out.println("Bye!");
                System.exit(0);
            }

        }





    }
}
