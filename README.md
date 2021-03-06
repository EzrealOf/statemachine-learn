# statemachine-learn
状态机学习
# 前言

The concept of a state machine is most likely older than any reader of this reference documentation and definitely older than the Java language itself.
Description of finite automata dates back to 1943 when gentlemen Warren McCulloch and Walter Pitts wrote a paper about it. Later George H. Mealy presented a state machine concept (known as a “Mealy Machine”) in 1955.

A year later, in 1956, Edward F. Moore presented another paper, in which he described what is known as a “Moore Machine”. If you have ever read anything about state machines, the names, Mealy and Moore, should have popped up at some point.

状态机的概念很可能比本参考文档的任何读者都要老，而且肯定比Java语言本身还要老。对有限自动机的描述可以追溯到1943年，当时沃伦·麦卡洛克(Warren McCulloch)和沃尔特·皮茨(Walter Pitts)先生写了一篇关于它的论文。后来乔治·H·米利在1955年提出了状态机的概念(称为“米利机”)。一年后，也就是1956年，爱德华·F·摩尔发表了另一篇论文，在论文中他描述了被称为“摩尔机”的东西。如果你曾经读过关于状态机的任何东西，那么米利和摩尔这两个名字应该会在某个时候出现。

# 什么是状态机
概念
有限状态机(FSM)或有限状态自动机(FSA，复数：自动机)、有限自动机或简称状态机是计算的数学模型。它是一台抽象的机器，在任何给定的时间都可以处于有限数量的状态之一。
FSM可以根据某些输入从一种状态转换到另一种状态；从一种状态到另一种状态的变化称为转换。FSM由其状态、初始状态和触发每个转换的输入的列表定义。

1. 状态
2. 初始状态
3. 触发每个转换的输入的列表定义

e.g.  投币式旋转门
用于控制通往地铁和游乐园游乐设施的旋转门是一个大门，它有三个与腰部齐高的旋转臂，一个横跨入口。最初，手臂是锁着的，挡住了入口，防止顾客通过。将一枚硬币或代币放在旋转栅门上的狭缝中，就可以解锁手臂，允许一个顾客推过去。在顾客通过后，手臂再次锁定，直到另一枚硬币被插入。
对应状态图如下
![旋转门状态变化](https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Turnstile_state_machine_colored.svg/660px-Turnstile_state_machine_colored.svg.png)
状态:  锁定 解锁
初始状态 : 锁定

动作: 投币 推动
行为: 锁定 解锁

触发每个转换的输入的列表定义:  锁定投币  锁定推动 解锁投币 解锁推动






# 相应的框架
### 



## easy states  (starts 187)
### 描述

简单并且易上手的状态机模型

简单的状态机模型API提供了状态机的核心概念

- 状态：机器可以处于给定时间点的一种特定状态
- 事件：表示可能触发操作并更改计算机状态的事件
- 过度: 表示发生事件时计算机的两个状态之间的转换
- 有限状态机: 有限状态机的核心抽象

### 特点

- 简单易上手

### 优势
- 上手简单
- 代码容易

### 劣势

- 只是状态机的基本概念对于复杂的场景支持力度不够

### demo

投币式旋转门（QuickStartSample）

## stateless4j (starts 721)
### 描述
轻量级状态机


### 特点
- 基于参数的状态跳转

- 提供状态的进入和退出扩展点

- 初始化状态流转时，提供了保护措施。

- 支持子状态机

  

### 劣势

- 对于未定义的状态流转会抛出异常
- 不允许相同触发器执行多个行为。



### demo

- 基于参数的状态跳转（DynamicTriggerTests）

- 父子状态下前置扩展点的进入条件（InitialStateEntryTest）

- 自我状态的自旋不会触发 进入和退出 但是在初始化时会调用进入方法 （InternalTransitionActionTests#ExitAndEntryAreNotPerformed）

- 初始化状态流转时，提供了保护措施。不允许的状态流转会抛出 IllegalStateException 异常 （InternalTransitionActionTests#ActionWithNegativeGuardIsNotPerformed）

  






## squirrel (starts 1.9k)
squirrel提供了简单易用，类型安全和高扩展的状态机

### 特点

- 提供了 状态进入和退出的扩展点

- 根据参数调用不同事件进行流转

- 提供根据状态机转变的方法，并且提供自定义接口方法

- 提供声明试的状态机定义

- 提供触发器对于状态的流转

- 支持状态机的嵌套（提供了父级状态和子状态的概念）（提供了是否进入子状态的注释）

- 支持定义开始/中断/结束事件

- 提供了过渡的三种定义（内连，本地，扩展）

- 提供事件的监听

- 提供异步注释来进行异步任务

  

### 劣势

- 定义的一些状态机中的调用方法是用的是方法名称。会增大代码阅读的难度，不利于维护

- 定义的方法需要按照命名规则来定义

  ```
   transitFrom[fromStateName]To[toStateName]On[eventName]When[conditionName]  
   transitFrom[fromStateName]To[toStateName]On[eventName]  
   transitFromAnyTo[toStateName]On[eventName]  
   transitFrom[fromStateName]ToAnyOn[eventName]  
   transitFrom[fromStateName]To[toStateName]          
   on[eventName] 
  ```

  

### demo

- 状态机的流转（QuickStartSample）
- 基于参数的状态跳转（DecisionStateSampleTest）
- 父子状态机（ParallelStateMachineTest）
- 异常处理（StateMachineExceptionTest）
- 监听器 （UntypedStateMachineTest）（StateMachineContextTest）
- 触发器 

## spring-statemachine

描述

Spring应用程序中使用传统状态机概念的框架

特点

- 用于简单用例的易于使用的平面(单级)状态机。
- 分层状态机结构，简化复杂的状态配置。
- 状态机区域，以提供更复杂的状态配置。
- 触发器、转换、保护和操作的使用。
- 类型安全配置适配器。
- 状态机事件监听器。
- 将Bean与状态机相关联的Spring IoC集成。

优势

- 



劣势

- 状态机是提供的单例



### demo

投币式旋转门（TurnstileApplication）

洗衣机（Washer）





## JSate (starts 91)

### 描述

一种核心Java工具，它使用枚举、字符串或任何您想要表示各种状态的东西来提供状态机语义。

### 特点







### 相关demo
spring-statemachine
squirrel
stateless4j
easy-states
JState




# 参考文档
- Java状态机调研报告: https://juejin.cn/post/6844904170852450318
- uml2.4 状态机图解: https://www.uml-diagrams.org/state-machine-diagrams.html
- 有限状态机 wiki百科:  https://en.wikipedia.org/wiki/Finite-state_machine
- easy-states 地址: https://github.com/j-easy/easy-states
- stateless4j 地址: https://github.com/stateless4j/stateless4j
- jSate 地址: https://github.com/UnquietCode/JState
- squirrel 地址: http://hekailiang.github.io/squirrel
- spring-statemachine 地址: https://spring.io/projects/spring-statemachine

