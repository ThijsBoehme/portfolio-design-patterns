package behavioural_patterns.chain_of_responsibility;

import java.util.ArrayList;
import java.util.List;

enum Statistic {
    ATTACK, DEFENSE
}

abstract class Creature3 {
    public abstract int getAttack();

    public abstract int getDefense();
}

class Goblin extends Creature3 {
    private Game2 game;

    public Goblin(Game2 game) {
        this.game = game;
    }

    protected int getBaseAttack() {
        return 1;
    }

    protected int getBaseDefense() {
        return 1;
    }

    @Override
    public int getAttack() {
        int attack = getBaseAttack();
        for (Creature3 creature: game.creatures) {
            if (creature instanceof GoblinKing && creature != this) {
                attack++;
            }
        }
        return attack;
    }

    @Override
    public int getDefense() {
        int defense = getBaseDefense();
        for (Creature3 creature: game.creatures) {
            if (creature instanceof Goblin && creature != this) {
                defense++;
            }
        }
        return defense;
    }
}

class GoblinKing extends Goblin {
    public GoblinKing(Game2 game) {
        super(game);
    }

    @Override
    protected int getBaseAttack() {
        return 3;
    }

    @Override
    protected int getBaseDefense() {
        return 3;
    }
}

class Game2 {
    public List<Creature3> creatures = new ArrayList<>();
}

class Demo {
    public static void main(String[] args) {
        Game2 game = new Game2();
        Goblin goblin = new Goblin(game);
        game.creatures.add(goblin);
        System.out.println(goblin.getAttack());
        System.out.println(goblin.getDefense());

        game.creatures.add(new Goblin(game));
        game.creatures.add(new Goblin(game));
        System.out.println(goblin.getAttack());
        System.out.println(goblin.getDefense());

        game.creatures.add(new GoblinKing(game));
        System.out.println(goblin.getAttack());
        System.out.println(goblin.getDefense());
    }
}
