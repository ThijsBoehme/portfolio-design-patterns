package solid_design_principles.dependency_inversion_principle

//class Research(relationships: Relationships) { // High-level
//    init {
//        // WRONG
//        val relations = relationships.relations
//        relations.stream()
//            .filter { t -> t.first.name == "John" && t.second == Relationship.PARENT }
//            .forEach { t -> println("John has a child called ${t.third.name}") }
//    }
//}

class Research(browser: RelationshipBrowser) { // High-level
    init {
        val children = browser.findAllChildrenOf("John")
        children.forEach { println("John has a child called ${it.name}") }
    }
}
