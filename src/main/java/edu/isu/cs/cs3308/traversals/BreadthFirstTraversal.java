package edu.isu.cs.cs3308.traversals;

import edu.isu.cs.cs3308.structures.BinaryTree;
import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.structures.Queue;
import edu.isu.cs.cs3308.structures.Tree;
import edu.isu.cs.cs3308.structures.impl.LinkedBinaryTree;
import edu.isu.cs.cs3308.structures.impl.LinkedQueue;

import java.util.LinkedList;

public class BreadthFirstTraversal<E> extends AbstractTraversal<E> {

    public BreadthFirstTraversal(Tree<E> tree) {
        super(tree);
    }

    @Override
    public Iterable<Node<E>> traverse() {
        if(tree.root() == null)
        {
            return null;
        }
        else
        {
            return traverseFrom(tree.root());
        }
    }

    @Override
    public Iterable<Node<E>> traverseFrom(Node<E> node) {

        tree.validate(node);

        LinkedQueue<LinkedBinaryTree.BinaryTreeNode<E>> nodeQueue = new LinkedQueue<>();
        LinkedList<Node<E>> nodeList = new LinkedList<>();

        nodeQueue.offer((LinkedBinaryTree.BinaryTreeNode<E>) node);

        while(!nodeQueue.isEmpty())
        {
            LinkedBinaryTree.BinaryTreeNode<E> temp = nodeQueue.poll();
            nodeList.addLast(temp);

            command.execute(tree,temp);

            if(temp.getLeft() != null)
            {
                nodeQueue.offer(temp.getLeft());
            }
            if(temp.getRight() != null)
            {
                nodeQueue.offer(temp.getRight());
            }
        }

        return nodeList;

    }
}
