package edu.isu.cs.cs3308.traversals;

import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.traversals.commands.TraversalCommand;
import edu.isu.cs.cs3308.structures.Tree;

public class AbstractTraversal<E> implements  TreeTraversal<E> {

    public Tree<E> tree;

    public TraversalCommand command = null;

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

    public AbstractTraversal(Tree<E> tree) {
        if (tree == null) {
            throw new IllegalArgumentException("Tree is null");
        }
        this.tree = tree;
    }
}
