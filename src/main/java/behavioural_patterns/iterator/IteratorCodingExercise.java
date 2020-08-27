package behavioural_patterns.iterator;

import java.util.Iterator;

class Node2<T> {
    public T value;
    public Node2<T> left, right, parent;

    public Node2(T value) {
        this.value = value;
    }

    public Node2(T value, Node2<T> left, Node2<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;

        left.parent = right.parent = this;
    }

    public Iterator<Node2<T>> preOrder() {
        return new PreOrderIterator(this);
    }
}

class PreOrderIterator<T> implements Iterator<Node2<T>> {
    private Node2<T> current;

    public PreOrderIterator(Node2<T> root) {
        this.current = root;
    }

    private Node2<T> getParentRightChild(Node2<T> node) {
        if (node.parent == null)
            return null;
        else {
            if (node.parent.right != null && node.parent.right != node) {
                return node.parent.right;
            } else {
                return getParentRightChild(node.parent);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return this.current != null;
    }

    @Override
    public Node2<T> next() {

        Node2<T> next = current;

        if (current.left != null) {
            current = current.left;
        } else if (current.right != null) {
            current = current.right;
        } else {
            Node2<T> nextNode = getParentRightChild(current);
            current = nextNode;
        }
        return next;
    }
}
