package edu.isu.cs.cs3308.traversals;

import edu.isu.cs.cs3308.structures.Node;

public class BreadthFirstTraversal<E> extends AbstractTraversal<E> {

    @Override
    public Iterable<Node<E>> traverse() {
        return traverseFrom(tree.root());
    }

    @Override
    public Iterable<Node<E>> traverseFrom(Node<E> node) {
        return null;

    }
}
