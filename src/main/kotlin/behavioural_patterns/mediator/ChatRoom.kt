package behavioural_patterns.mediator

class Person(
    val name: String
) {
    lateinit var room: ChatRoom
    private val chatLog = ArrayList<String>()

    fun receive(sender: String, message: String) {
        val output = "$sender: $message"
        println("[$name's chat session] $output")
        chatLog.add(output)
    }

    fun say(message: String) {
        room.broadCast(name, message)
    }

    fun privateMessage(receiver: String, message: String) {
        room.message(name, receiver, message)
    }
}

class ChatRoom {
    private val people = ArrayList<Person>()

    fun join(person: Person) {
        val joinMessage = "${person.name} joins the room"
        broadCast("room", joinMessage)

        person.room = this
        people.add(person)
    }

    fun broadCast(source: String, message: String) {
        for (person in people) {
            if (person.name != source) {
                person.receive(source, message)
            }
        }
    }

    fun message(source: String, destination: String, message: String) {
        people.stream()
            .filter { it.name == destination }
            .findFirst()
            .ifPresent { it.receive(source, message) }
    }
}

fun main() {
    val room = ChatRoom()
    val john = Person("John")
    val jane = Person("Jane")

    room.join(john)
    room.join(jane)

    john.say("Hi room!")
    jane.say("Oh, hey John!")

    val simon = Person("Simon")
    room.join(simon)
    simon.say("Hi everyone!")

    jane.privateMessage("Simon", "Glad you could join us!")
}
