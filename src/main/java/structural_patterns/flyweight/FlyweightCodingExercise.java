package structural_patterns.flyweight;

import java.util.ArrayList;
import java.util.List;

class Sentence {
    private String plainText;
    private List<WordToken> tokens;

    public Sentence(String plainText) {
        this.plainText = plainText;
        tokens = new ArrayList<>(plainText.split(" ").length);
        for (int i = 0; i < plainText.split(" ").length; i++) {
            tokens.add(i, new WordToken());
        }
    }

    public WordToken getWord(int index) {
        return tokens.get(index);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String[] words = plainText.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (getWord(i).capitalize) builder.append(words[i].toUpperCase());
            else builder.append(words[i]);
            builder.append(" ");
        }
        return builder.toString().trim();
    }

    class WordToken {
        public boolean capitalize;
    }
}
