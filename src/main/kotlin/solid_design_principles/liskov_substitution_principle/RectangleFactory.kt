package solid_design_principles.liskov_substitution_principle

class RectangleFactory {
    companion object {
        fun newRectangle(width: Int, height: Int): Rectangle {
            return Rectangle(width, height)
        }

        fun newSquare(side: Int): Rectangle {
            return Rectangle(side, side)
        }
    }
}
