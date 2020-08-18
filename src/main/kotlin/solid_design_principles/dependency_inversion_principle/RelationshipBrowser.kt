package solid_design_principles.dependency_inversion_principle

interface RelationshipBrowser {
    fun findAllChildrenOf(name: String): List<Person>
}
