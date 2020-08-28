package behavioural_patterns.state

import org.springframework.statemachine.StateMachine
import org.springframework.statemachine.config.StateMachineBuilder
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import java.util.stream.Collectors

enum class States {
    OFF_HOOK, // Starting
    ON_HOOK, // Terminal
    CONNECTING,
    CONNECTED,
    ON_HOLD
}

enum class Events {
    CALL_DIALED,
    HUNG_UP,
    CALL_CONNECTED,
    PLACED_ON_HOLD,
    TAKEN_OFF_HOLD,
    LEFT_MESSAGE,
    STOP_USING_PHONE
}

fun buildMachine(): StateMachine<States, Events> {
    val builder = StateMachineBuilder.builder<States, Events>()

    builder.configureStates()
        .withStates()
        .initial(States.OFF_HOOK)
        .states(EnumSet.allOf(States::class.java))

    builder.configureTransitions()
        .withExternal()
        .source(States.OFF_HOOK).event(Events.CALL_DIALED).target(States.CONNECTING)
        .and()
        .withExternal()
        .source(States.OFF_HOOK).event(Events.STOP_USING_PHONE).target(States.ON_HOOK)
        .and()
        .withExternal()
        .source(States.CONNECTING).event(Events.HUNG_UP).target(States.OFF_HOOK)
        .and()
        .withExternal()
        .source(States.CONNECTING).event(Events.CALL_CONNECTED).target(States.CONNECTED)
        .and()
        .withExternal()
        .source(States.CONNECTED).event(Events.LEFT_MESSAGE).target(States.OFF_HOOK)
        .and()
        .withExternal()
        .source(States.CONNECTED).event(Events.HUNG_UP).target(States.OFF_HOOK)
        .and()
        .withExternal()
        .source(States.CONNECTED).event(Events.PLACED_ON_HOLD).target(States.ON_HOLD)
        .and()
        .withExternal()
        .source(States.ON_HOLD).event(Events.TAKEN_OFF_HOLD).target(States.CONNECTED)
        .and()
        .withExternal()
        .source(States.ON_HOLD).event(Events.HUNG_UP).target(States.OFF_HOOK)

    return builder.build()
}

fun main() {
    val machine = buildMachine()
    machine.start()

    val exitState = States.ON_HOOK

    val console = BufferedReader(InputStreamReader(System.`in`))

    while (true) {
        val state = machine.state

        println("The phone is currently ${state.id}")
        println("Select a trigger:")

        val transitions = machine.transitions
            .stream()
            .filter { it.source == state }
            .collect(Collectors.toList())

        for (i in transitions.indices) {
            val trigger = transitions[i].trigger.event
            println("$i. $trigger")
        }

        var parseOK: Boolean
        var choice = 0
        do {
            println("Please enter your choice:")
            try {
                choice = readLine()!!.toInt()
                parseOK = choice >= 0 && choice < transitions.size
            } catch (exception: Exception) {
                parseOK = false
            }
        } while (!parseOK)

        machine.sendEvent(transitions[choice].trigger.event)

        if (machine.state.id == exitState) break
    }

    println("And we are done!")
}
