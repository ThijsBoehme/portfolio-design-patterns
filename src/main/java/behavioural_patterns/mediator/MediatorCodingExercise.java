package behavioural_patterns.mediator;

import java.util.ArrayList;
import java.util.List;

class Participant {
    int value = 0;
    private final Mediator room;

    public Participant(Mediator mediator) {
        this.room = mediator;
        room.add(this);
    }

    public void say(int n) {
        room.broadCast(this, n);
    }

    public void hear(int n) {
        value += n;
    }
}

class Mediator {
    private final List<Participant> people = new ArrayList<>();

    public void add(Participant participant) {
        people.add(participant);
    }

    public void broadCast(Participant participant, int n) {
        for (Participant person: people) {
            if (!person.equals(participant)) {
                person.hear(n);
            }
        }
    }
}
