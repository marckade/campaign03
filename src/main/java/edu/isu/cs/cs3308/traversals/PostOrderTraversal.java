package edu.isu.cs.cs3308.traversals;

import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.structures.Tree;
import edu.isu.cs.cs3308.structures.impl.LinkedBinaryTree;

import java.util.List;

public class PostOrderTraversal<E> extends DepthFirstTraversal<E> {


    public PostOrderTraversal(Tree<E> tree) {
        super(tree);
    }

    @Override
    public void subtree(Node<E> p, List<Node<E>> snapshot)
    {
        if (snapshot == null)
        {
            throw new IllegalArgumentException("List is null");
        }
        LinkedBinaryTree.BinaryTreeNode<E> treeNode = (LinkedBinaryTree.BinaryTreeNode<E>)tree.validate(p);

        if (treeNode.getLeft() != null)
        {
            subtree(treeNode.getLeft(), snapshot);
        }
        if (treeNode.getRight() != null)
        {
            subtree(treeNode.getRight(), snapshot);
        }

        snapshot.add(treeNode);

        if (command != null)
        {
            command.execute(tree, treeNode);
        }
    }
}

