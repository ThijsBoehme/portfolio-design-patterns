package structural_patterns.bridge;

abstract class ExerciseShape {
    private final ExerciseRenderer renderer;

    public ExerciseShape(ExerciseRenderer renderer) {
        this.renderer = renderer;
    }

    public abstract String getName();

    @Override
    public String toString() {
        return String.format("Drawing %s as %s", getName(), renderer.whatToRenderAs());
    }
}

class Triangle extends ExerciseShape {
    public Triangle(ExerciseRenderer renderer) {
        super(renderer);
    }

    @Override
    public String getName() {
        return "Triangle";
    }
}

class Square extends ExerciseShape {
    public Square(ExerciseRenderer renderer) {
        super(renderer);
    }

    @Override
    public String getName() {
        return "Square";
    }
}

interface ExerciseRenderer {
    String whatToRenderAs();
}

class ExerciseVectorRenderer implements ExerciseRenderer {
    @Override
    public String whatToRenderAs() {
        return "lines";
    }
}

class ExerciseRasterRenderer implements ExerciseRenderer {
    @Override
    public String whatToRenderAs() {
        return "pixels";
    }
}
