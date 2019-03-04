package edu.isu.cs.cs3308.traversals;

import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.structures.Queue;
import edu.isu.cs.cs3308.structures.Tree;
import edu.isu.cs.cs3308.structures.impl.LinkedQueue;

import java.util.LinkedList;

public class BreadthFirstTraversal<E> extends AbstractTraversal<E> {

    public BreadthFirstTraversal(Tree<E> tree) {
        super(tree);
    }

    @Override
    public Iterable<Node<E>> traverse() {
        return traverseFrom(tree.root());
    }

    @Override
    public Iterable<Node<E>> traverseFrom(Node<E> node) {



        return null;
    }
}
