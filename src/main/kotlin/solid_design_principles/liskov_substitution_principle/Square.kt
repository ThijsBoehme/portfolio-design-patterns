package solid_design_principles.liskov_substitution_principle

class Square(): Rectangle() {
    override var width: Int
        get() = super.width
        set(value) {
            super.width = value
            super.height = value
        }
    override var height: Int
        get() = super.height
        set(value) {
            super.width = value
            super.height = value
        }

    constructor(size: Int): this() {
        width = size
        height = size
    }
}
