package behavioural_patterns.state

import java.io.BufferedReader
import java.io.InputStreamReader

enum class PhoneState {
    OFF_HOOK, // Starting
    ON_HOOK, // Terminal
    CONNECTING,
    CONNECTED,
    ON_HOLD
}

enum class Trigger {
    CALL_DIALED,
    HUNG_UP,
    CALL_CONNECTED,
    PLACED_ON_HOLD,
    TAKEN_OFF_HOLD,
    LEFT_MESSAGE,
    STOP_USING_PHONE
}

fun main() {
    val rules = HashMap<PhoneState, List<Pair<Trigger, PhoneState>>>()
    rules[PhoneState.OFF_HOOK] = listOf(Pair(Trigger.CALL_DIALED, PhoneState.CONNECTING), Pair(Trigger.STOP_USING_PHONE, PhoneState.ON_HOOK))
    rules[PhoneState.CONNECTING] = listOf(Pair(Trigger.HUNG_UP, PhoneState.OFF_HOOK), Pair(Trigger.CALL_CONNECTED, PhoneState.CONNECTED))
    rules[PhoneState.CONNECTED] = listOf(Pair(Trigger.LEFT_MESSAGE, PhoneState.OFF_HOOK), Pair(Trigger.HUNG_UP, PhoneState.OFF_HOOK), Pair(Trigger.PLACED_ON_HOLD, PhoneState.ON_HOLD))
    rules[PhoneState.ON_HOLD] = listOf(Pair(Trigger.TAKEN_OFF_HOLD, PhoneState.CONNECTED), Pair(Trigger.HUNG_UP, PhoneState.OFF_HOOK))

    var currentState = PhoneState.OFF_HOOK
    var exitState = PhoneState.ON_HOOK

    val console = BufferedReader(InputStreamReader(System.`in`))

    while (true) {
        println("The phone is currently $currentState")
        println("Select a trigger:")

        for (i in rules[currentState]!!.indices) {
            val trigger = rules[currentState]!![i].first
            println("$i. $trigger")
        }

        var parseOK = false
        var choice = 0
        do {
            println("Please enter your choice:")
            try {
                choice = readLine()!!.toInt()
                parseOK = choice >= 0 && choice < rules[currentState]!!.size
            } catch (exception: Exception) {
                parseOK = false
            }
        } while (!parseOK)

        currentState = rules[currentState]!![choice].second

        if (currentState == exitState) break
    }

    println("And we are done!")
}
