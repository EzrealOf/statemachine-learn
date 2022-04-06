package com.ezreal.statemachine.demo;

import com.ezreal.statemachine.demo.enums.States;
import com.ezreal.statemachine.demo.enums.Triggers;
import com.github.oxo42.stateless4j.StateMachine;
import com.github.oxo42.stateless4j.StateMachineConfig;

public class QuickStartSample {


    public static void main(String[] args) {
        test();
    }

    public static void test() {

        StateMachineConfig<States, Triggers> phoneCallConfig = new StateMachineConfig<>();

        phoneCallConfig.configure(States.OffHook)
                .permit(Triggers.CallDialed, States.Ringing);

        phoneCallConfig.configure(States.Ringing)
                .permit(Triggers.HungUp, States.OffHook)
                .permit(Triggers.CallConnected, States.Connected);

        // this example uses Java 8 method references
        // a Java 7 example is provided in /examples
        phoneCallConfig.configure(States.Connected)
                .onEntry(() -> startCallTimer())
                .onExit(() -> stopCallTimer())
                .permit(Triggers.LeftMessage, States.OffHook)
                .permit(Triggers.HungUp, States.OffHook)
                .permit(Triggers.PlacedOnHold, States.OnHold);


        StateMachine<States, Triggers> phoneCall =
                new StateMachine<>(States.OffHook, phoneCallConfig);

        phoneCall.fire(Triggers.CallDialed);

    }

    public static void startCallTimer() {
        System.out.println("startCallTimer process");
    }

    public static void stopCallTimer() {
        System.out.println("stopCallTimer process");

    }

}
