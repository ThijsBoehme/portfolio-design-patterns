package behavioural_patterns.iterator

import java.util.function.Consumer

class Node<T>(
    val value: T,
    val left: Node<T>?,
    val right: Node<T>?,
) {
    var parent: Node<T>? = null

    constructor(value: T): this(value, null, null)

    init {
        left?.parent = this
        right?.parent = this
    }
}

class InOrderIterator<T>(private val root: Node<T>): Iterator<T> {
    private var current: Node<T> = root
    private var yieldedStart = false

    init {
        while (current.left != null) {
            current = current.left!!
        }
    }

    private fun hasRightmostParent(node: Node<T>): Boolean {
        return if (node.parent == null) false
        else {
            (node == node.parent!!.left) || hasRightmostParent(node.parent!!)
        }
    }

    override fun hasNext(): Boolean {
        return current.left != null || current.right != null || hasRightmostParent(current)
    }

    override fun next(): T {
        if (!yieldedStart) {
            yieldedStart = true
            return current.value
        }

        return if (current.right != null) {
            current = current.right!!
            while (current.left != null)
                current = current.left!!
            current.value
        } else {
            var parent = current.parent
            while (parent != null && current == parent.right) {
                current = parent
                parent = parent.parent
            }
            current = parent!!
            current.value
        }
    }
}

class BinaryTree<T>(
    private val root: Node<T>
): Iterable<T> {

    override fun iterator(): Iterator<T> {
        return InOrderIterator(root)
    }

    override fun forEach(action: Consumer<in T>?) {
        for (item in this) {
            action?.accept(item)
        }
    }
}

fun main() {
    //    1
    //   / \
    //  2   3

    // In-order: 2-1-3

    val root = Node(1, Node(2), Node(3))
    val iterator = InOrderIterator(root)

    while (iterator.hasNext()) {
        print("${iterator.next()}, ")
    }
    println()

    val binaryTree = BinaryTree(root)
    for (n in binaryTree) {
        print("$n, ")
    }
    println()
}
