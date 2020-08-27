package behavioural_patterns.command;

class Command2 {
    public Action action;
    public int amount;
    public boolean success;

    public Command2(Action action, int amount) {
        this.action = action;
        this.amount = amount;
    }

    enum Action {
        DEPOSIT, WITHDRAW
    }
}

class Account {
    public int balance;

    public void process(Command2 c) {
        switch (c.action) {
            case DEPOSIT:
                balance += c.amount;
                c.success = true;
                break;
            case WITHDRAW:
                if (balance >= c.amount) {
                    balance -= c.amount;
                    c.success = true;
                } else {
                    c.success = false;
                }
                break;
        }
    }
}
