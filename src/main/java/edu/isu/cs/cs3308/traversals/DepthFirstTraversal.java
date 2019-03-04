package edu.isu.cs.cs3308.traversals;

import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.structures.Tree;

import java.util.LinkedList;
import java.util.List;

public class DepthFirstTraversal<E> extends AbstractTraversal<E>{


    public DepthFirstTraversal(Tree<E> tree) {
        super(tree);
    }

    @Override
    public Iterable<Node<E>> traverse() {
        return traverseFrom(tree.root());
    }

    @Override
    public Iterable<Node<E>> traverseFrom(Node<E> node) {
        return subTreeTraverse(node);
    }

    public Iterable<Node<E>> subTreeTraverse(Node<E> current)
    {
        LinkedList<Node<E>> list = new LinkedList<>();

        if(!tree.isEmpty())
        {
            subtree(current, list);
        }

        return list;
    }

    public void subtree(Node<E> p, List<Node<E>> snapshot)
    {
        //Placeholder
    }

}
