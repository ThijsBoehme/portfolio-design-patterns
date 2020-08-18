package solid_design_principles.dependency_inversion_principle

import java.util.stream.Collectors

class Relationships: RelationshipBrowser { // Low-level
    val relations = ArrayList<Triple<Person, Relationship, Person>>()

    fun addParentAndChild(parent: Person, child: Person) {
        relations.add(Triple(parent, Relationship.PARENT, child))
        relations.add(Triple(child, Relationship.CHILD, parent))
    }

    override fun findAllChildrenOf(name: String): List<Person> {
        return relations.stream()
            .filter { t -> t.first.name == name && t.second == Relationship.PARENT }
            .map { t -> t.third }
            .collect(Collectors.toList())
    }
}
