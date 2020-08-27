package behavioural_patterns.memento;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Token {
    public int value = 0;

    public Token(int value) {
        this.value = value;
    }
}

class Memento2 {
    public List<Token> tokens = new ArrayList<>();
}

class TokenMachine {
    public List<Token> tokens = new ArrayList<>();

    public Memento2 addToken(int value) {
        return addToken(new Token(value));
    }

    public Memento2 addToken(Token token) {
        tokens.add(token);
        Memento2 memento = new Memento2();
        memento.tokens = tokens.stream()
                .map(t -> new Token(t.value))
                .collect(Collectors.toList());
        return memento;
    }

    public void revert(Memento2 m) {
        tokens = m.tokens.stream()
                .map(token -> new Token(token.value))
                .collect(Collectors.toList());
    }
}
