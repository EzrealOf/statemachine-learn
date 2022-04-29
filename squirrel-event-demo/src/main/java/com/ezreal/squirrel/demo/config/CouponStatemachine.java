package com.ezreal.squirrel.demo.config;

import com.ezreal.squirrel.demo.entity.CouponContext;
import com.ezreal.squirrel.demo.eunms.Events;
import org.squirrelframework.foundation.fsm.UntypedStateMachine;
import org.squirrelframework.foundation.fsm.annotation.StateMachineParameters;
import org.squirrelframework.foundation.fsm.annotation.Transit;
import org.squirrelframework.foundation.fsm.annotation.Transitions;
import org.squirrelframework.foundation.fsm.impl.AbstractStateMachine;
import org.squirrelframework.foundation.fsm.impl.AbstractUntypedStateMachine;


@StateMachineParameters(stateType = String.class, eventType = Events.class, contextType = CouponContext.class)
public class CouponStatemachine extends AbstractUntypedStateMachine {



}
