package edu.isu.cs.cs3308.traversals;

import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.structures.Tree;
import edu.isu.cs.cs3308.structures.impl.LinkedBinaryTree;

import java.util.List;

public class InOrderTraversal<E> extends DepthFirstTraversal<E> {

    public InOrderTraversal(Tree<E> tree) {
        super(tree);
    }

    @Override
    public void subtree(Node<E> p, List<Node<E>> snapshot) {

        if (snapshot == null)
        {
            throw new IllegalArgumentException("List is null");
        }

        LinkedBinaryTree.BinaryTreeNode<E> tempNode = (LinkedBinaryTree.BinaryTreeNode<E>)tree.validate(p);

        if (tempNode.getLeft() != null)
        {
            subtree(tempNode.getLeft(), snapshot);
        }

        snapshot.add(tempNode);
        if (command != null)
        {
            command.execute(tree, tempNode);
        }

        if (tempNode.getRight() != null)
        {
            subtree(tempNode.getRight(), snapshot);
        }
    }
}
