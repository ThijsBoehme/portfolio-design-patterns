package behavioural_patterns.state

open class State {
    open fun on(switch: LightSwitch): Unit = println("Light is already on")
    open fun off(switch: LightSwitch): Unit = println("Light is already off")
}

class OnState: State() {
    init {
        println("Light turned on")
    }

    override fun off(switch: LightSwitch) {
        println("Switching light off...")
        switch.state = OffState()
    }
}

class OffState: State() {
    init {
        println("Light turned off")
    }

    override fun on(switch: LightSwitch) {
        println("Switching light on...")
        switch.state = OnState()
    }
}

class LightSwitch {
    var state: State = OffState()

    fun on(): Unit = state.on(this)
    fun off(): Unit = state.off(this)
}

fun main() {
    val switch = LightSwitch()
    switch.on()
    switch.off()
    switch.off()
}
