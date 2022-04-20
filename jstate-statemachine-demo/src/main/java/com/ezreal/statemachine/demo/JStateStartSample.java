package com.ezreal.statemachine.demo;


import com.ezreal.statemachine.demo.enums.State;
import unquietcode.tools.esm.EnumStateMachine;

public class JStateStartSample {


    public static void main(String[] args) {
        EnumStateMachine<State> esm = new EnumStateMachine<>(State.Ready);
        esm.addTransitions(State.Ready, State.Running, State.Finished);
        esm.addTransitions(State.Running, State.Paused, State.Stopping);
        esm.addTransitions(State.Paused, State.Running, State.Stopping);
        esm.addTransitions(State.Stopping, State.Stopped);
        esm.addTransitions(State.Stopped, State.Finished);
        esm.addTransitions(State.Finished, State.Ready, null);

        esm.transition(State.Running);

    }

}
