package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.structures.impl.BinarySearchTree;
import edu.isu.cs.cs3308.structures.impl.LinkedBinaryTree;
import edu.isu.cs.cs3308.traversals.InOrderTraversal;
import edu.isu.cs.cs3308.traversals.PreOrderTraversal;

/**
 * A very simple classification tree example using a BinaryTree and console
 * input.
 *
 * @author Isaac Griffith
 */
public class ClassificationTree {

    private LinkedBinaryTree<Datum> tree;

    /**
     * Constructs a new Animal tree class which manages an underlying animal
     * tree
     */
    public ClassificationTree() {
        tree = new LinkedBinaryTree<>();
        load();
    }

    /**
     * Main method which controls the identification and tree management loop.
     */
    public void identify() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Saves a tree to a file.
     */
    public void save() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Loads a tree from the given file, if an exception occurs during file
     * operations, a hardcoded basic tree will be loaded instead.
     */
    public void load() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
