package creational_patterns.builder;

import java.util.HashMap;
import java.util.Map;

class CodeBuilder {
    private final String className;
    private final Map<String, String> fields = new HashMap<>();
    private final String indent = "  ";
    private final String newLine = System.lineSeparator();

    public CodeBuilder(String className) {
        this.className = className;
    }

    public CodeBuilder addField(String name, String type) {
        fields.put(name, type);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("public class ").append(className).append(newLine);
        builder.append("{").append(newLine);

        fields.forEach((name, type) -> builder.append(indent).append("public ").append(type).append(" ").append(name).append(";").append(newLine));

        builder.append("}");

        return builder.toString();
    }
}
