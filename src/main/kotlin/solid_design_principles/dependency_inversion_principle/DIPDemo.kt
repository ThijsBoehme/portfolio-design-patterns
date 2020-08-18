package solid_design_principles.dependency_inversion_principle

// A. High-level modules should not depend on low-level modules.
// Both should depend on abstractions.

// B. Abstractions should not depend on details.
// Details should depend on abstractions.

fun main() {
    val parent = Person("John")
    val child1 = Person("Chris")
    val child2 = Person("Matt")

    val relationships = Relationships()
    relationships.addParentAndChild(parent, child1)
    relationships.addParentAndChild(parent, child2)

    val research = Research(relationships)
}
