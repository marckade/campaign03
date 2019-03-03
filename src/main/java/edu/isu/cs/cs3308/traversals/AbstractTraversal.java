package edu.isu.cs.cs3308.traversals;

import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.traversals.commands.TraversalCommand;

public class AbstractTraversal<E> implements  TreeTraversal<E> {

    @Override
    public Iterable<Node<E>> traverse() {
        return null;
    }

    @Override
    public Iterable<Node<E>> traverseFrom(Node<E> node) {
        return null;
    }

    @Override
    public void setCommand(TraversalCommand cmd) {

    }
}
