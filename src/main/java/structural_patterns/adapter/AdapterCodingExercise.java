package structural_patterns.adapter;

class Square {
    public int side;

    public Square(int side) {
        this.side = side;
    }
}

interface Rectangle {
    int getWidth();
    int getHeight();

    default int getArea() {
        return getWidth() * getHeight();
    }
}

class SquareToRectangleAdapter implements Rectangle {
    public int side;

    public SquareToRectangleAdapter(Square square) {
        this.side = square.side;
    }

    @Override
    public int getWidth() {
        return side;
    }

    @Override
    public int getHeight() {
        return side;
    }
}
