package edu.isu.cs.cs3308.structures.impl;

import com.google.common.collect.Iterables;
import edu.isu.cs.cs3308.structures.BinaryTree;
import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.structures.Tree;

import java.util.LinkedList;


public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> implements Tree<E> {

    protected BinaryTreeNode<E> root = null;

    private int size = 0;

    public static class BinaryTreeNode<E> implements Node<E>
    {
        private E element;
        private BinaryTreeNode<E> parent;
        private BinaryTreeNode<E> left;
        private BinaryTreeNode<E> right;

        public BinaryTreeNode(E element, BinaryTreeNode<E> parent, BinaryTreeNode<E> left, BinaryTreeNode<E> right) {
            this.element = element;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public void setParent(BinaryTreeNode<E> parent) {
            this.parent = parent;
        }

        public BinaryTreeNode<E> getLeft() {
            return left;
        }

        public void setLeft(BinaryTreeNode<E> left) {
            this.left = left;
        }

        public BinaryTreeNode<E> getRight() {
            return right;
        }

        public void setRight(BinaryTreeNode<E> right) {
            this.right = right;
        }

        /**
         * @return The element contained in this node.
         */
        @Override
        public E getElement() {
            return this.element;
        }

        /**
         * Sets the new value of this node to the provided one. Thows an
         * IllegalArgumentException if the provided value is null.
         *
         * @param element New value to be contained in this node.
         */
        @Override
        public void setElement(E element) throws IllegalArgumentException {

            if(element == null)
            {
                throw new IllegalArgumentException("Element provided was null");
            }
            else
            {
                this.element = element;
            }

        }

        /**
         * @return The parent node of this class. Can be null.
         */
        @Override
        public BinaryTreeNode<E> getParent() {
            return this.parent;
        }
    }






    // Constructor for the binary tree node.
    public BinaryTreeNode<E> createNode(E element, BinaryTreeNode<E> parent, BinaryTreeNode<E> left, BinaryTreeNode<E> right)
    {
        checkNull(element);
        return new BinaryTreeNode<>(element,parent,left,right);
    }








    /**
     * Returns the left child of the provided node.
     *
     * @param p The parent node of whom the left child is desired.
     * @return The left child of the provided node, can be null if no such child
     * exists.
     * @throws IllegalArgumentException If the provided node is invalid.
     */
    @Override
    public Node<E> left(Node<E> p) throws IllegalArgumentException {

        //Couldnt get it to work without ()
        BinaryTreeNode<E> treeNode = (BinaryTreeNode<E>)validate(p);

        return treeNode.getLeft();
    }

    /**
     * Returns the right child of the provided node.
     *
     * @param p The parent node of whom the right child is desired.
     * @return The right child of the provided node, can be null if no such
     * child exists.
     * @throws IllegalArgumentException If the provided node is invalid.
     */
    @Override
    public Node<E> right(Node<E> p) throws IllegalArgumentException {

        BinaryTreeNode<E> treeNode = (BinaryTreeNode<E>)validate(p);

        return treeNode.getRight();
    }

    /**
     * Returns the sibling node of the provided node, if such a sibling exists.
     * That is, if the right node is provided the left node will be returned
     * from the same parent.
     *
     * @param p The node of whom a sibling is requested.
     * @return The sibling of the provided node, or null if no such sibling
     * exists.
     * @throws IllegalArgumentException If the provided node is invalid.
     */
    @Override
    public Node<E> sibling(Node<E> p) throws IllegalArgumentException {

        BinaryTreeNode<E> treeNode = (BinaryTreeNode<E>)validate(p);

        //Check for parent and return null if it does not exist
        if(treeNode.getParent() == null)
        {
            return null;
        }
        else
        {
            BinaryTreeNode<E> parent = treeNode.getParent();
            BinaryTreeNode<E> tempLeft = parent.getLeft();
            BinaryTreeNode<E> tempRight = parent.getRight();

            if(treeNode == tempLeft)
            {
                return tempRight;
            }
            else
            {
                return tempLeft;
            }
        }

    }

    /**
     * Adds the provided element as a new node to the left side of the provided
     * node.
     *
     * @param p       The node to which the element is to be added as the left child.
     * @param element Element to be added
     * @return The newly created left child of the provided node
     * @throws IllegalArgumentException If the provided node is invalid, if the
     *                                  provided element is null, or if the provided node already has a left
     *                                  child.
     */
    @Override
    public Node<E> addLeft(Node<E> p, E element) throws IllegalArgumentException {

        checkNull(element);

        BinaryTreeNode<E> treeNode = (BinaryTreeNode<E>)validate(p);

        if(treeNode.getLeft() != null)
        {
            throw new IllegalArgumentException("Left already exists");
        }
        else
        {
            treeNode.setLeft(createNode(element,treeNode,null,null));
            size++;

            return treeNode.getLeft();
        }
    }

    /**
     * Adds the provided element as a new node to the right side of the provided
     * node.
     *
     * @param p       The node to which the element is to be added as the right child.
     * @param element Element to be added
     * @return The newly created right child of the provided node
     * @throws IllegalArgumentException If the provided node is invalid, if the
     *                                  provided element is null, or if the provided node already has a right
     *                                  child.
     */
    @Override
    public Node<E> addRight(Node<E> p, E element) throws IllegalArgumentException {

        checkNull(element);

        BinaryTreeNode<E> treeNode = (BinaryTreeNode<E>)validate(p);

        if(treeNode.getRight() != null)
        {
            throw new IllegalArgumentException("Right already exists");
        }
        else
        {
            treeNode.setRight(createNode(element,treeNode,null,null));
            size++;

            return treeNode.getRight();
        }
    }

    /**
     * @return The root node of this tree or null if the Tree is empty.
     */
    @Override
    public Node<E> root() {

        if(size == 0)
        {
            return null;
        }

        return root;
    }

    /**
     * Sets the tree's root node to the provided item, by creating a new node
     * (unless the given item is the same as the current root's item). Note that
     * this must also reset the size of the tree to the correct value if the
     * current node is replaced.
     *
     * @param item New item for the root node.
     * @return The new root node.
     */
    @Override
    public Node<E> setRoot(E item) {

        if(item == null)
        {
            root = null;
            size = 0;
        }
        else
        {
            if(isEmpty() || item != root().getElement())
            {
                BinaryTreeNode<E> newNode= createNode(item,null,null,null);

                root = newNode;
                size = 1;
            }
        }

        return root;
    }

    /**
     * Returns the parent node of the node provided, or null if the node is also
     * the root of the tree.
     *
     * @param p Node whose parent is being requested.
     * @return The parent of the provided node, or null if the provided node is
     * the root.
     * @throws IllegalArgumentException If the node is invalid
     */
    @Override
    public Node<E> parent(Node<E> p) throws IllegalArgumentException {

        checkValid(p);

        if(isRoot(p))
        {
            return null;
        }
        else
        {
            return p.getParent();
        }
    }

    /**
     * Returns an iterable collection of the children attached to the provided
     * node.
     *
     * @param p The node whose children are requested.
     * @return An iterable collection of the children attached to the provided
     * node.
     * @throws IllegalArgumentException If the provided node is invalid
     */
    @Override
    public Iterable<Node<E>> children(Node<E> p) throws IllegalArgumentException {

        BinaryTreeNode<E> treeNode = (BinaryTreeNode<E>)validate(p);

        LinkedList<Node<E>> listOfChildren = new LinkedList<>();

        if(treeNode.getLeft() !=null)
        {
            listOfChildren.addLast(treeNode.getLeft());
        }

        if(treeNode.getRight() != null)
        {
            listOfChildren.addLast(treeNode.getRight());
        }

        return listOfChildren;
    }

    /**
     * Returns the number of children currently attached to the provided node.
     *
     * @param p Node whose number of children is requested.
     * @return The number of children attached to the provided node.
     * @throws IllegalArgumentException If the node is invalid.
     */
    @Override
    public int numChildren(Node<E> p) throws IllegalArgumentException {

        return Iterables.size(children(p));

    }

    /**
     * Tests whether the node is an internal node or not. That is whether the
     * node has children.
     *
     * @param p The node to test.
     * @return True if the node is an internal node, false otherwise.
     * @throws IllegalArgumentException If the node is invalid.
     */
    @Override
    public boolean isInternal(Node<E> p) throws IllegalArgumentException {

        boolean hasChildren = (numChildren(p) > 0);

        return hasChildren;
    }

    /**
     * Tests whether the node is an external node of the tree. That is whether
     * the node has no children and thus is a leaf of the tree.
     *
     * @param p The node to test.
     * @return True if the node is a leaf node, false otherwise.
     * @throws IllegalArgumentException If the node is invalid
     */
    @Override
    public boolean isExternal(Node<E> p) throws IllegalArgumentException {

        return (numChildren(p) == 0);
    }

    /**
     * Tests whether this node is the root node of the tree. That is that the
     * provided node has children but not parent.
     *
     * @param p Node to test.
     * @return True if the node is the root of the tree.
     * @throws IllegalArgumentException If the provided node is invalid.
     */
    @Override
    public boolean isRoot(Node<E> p) throws IllegalArgumentException {
        checkValid(p);

        return (p == root());
    }

    /**
     * Inserts the item into the tree under the provided node. If the provided
     * node is null the item becomes the new root of the tree, beware.
     *
     * @param item Item to be inserted into the tree.
     * @param p    The parent node of the tree, if null the item becomes the new
     *             root so beaware.
     * @return True if the item was able to be inserted, false otherwise (for
     * example the item was null)
     * @throws IllegalArgumentException if the provided parent node is invalid,
     *                                  or the provided value is null.
     */
    @Override
    public Node<E> insert(E item, Node<E> p) {

        checkNull(item);

        if(isEmpty())
        {
            return setRoot(item);
        }
        else
        {
            checkValid(p);

            if(isExternal(p))
            {
                return addLeft(p,item);
            }
            else
            {
                if(left(p) == null)
                {
                    return addLeft(p,item);
                }

                if(right(p) == null)
                {
                    return addRight(p,item);
                }
            }
        }

        throw new IllegalArgumentException("Failed to insert node");
    }

    /**
     * Removes the given item from the provided parent node.
     *
     * @param item Item to be removed from the list of children of the provided
     *             node.
     * @param p    Parent node.
     * @return true if the item was removed, false otherwise.
     * @throws IllegalArgumentException If the provided parent node is not valid
     *                                  or the value is null.
     */
    @Override
    public boolean remove(E item, Node<E> p) throws IllegalArgumentException {
        if (item == null || p == null) {
            return false;
        }

        checkNull(item);
        BinaryTreeNode<E> btn = (BinaryTreeNode<E>)validate(p);

        if (isExternal(btn)) {
            return false;
        }
        else {
            if (btn.getLeft() != null && item == btn.getLeft().getElement()) {
                BinaryTreeNode<E> removeChild = btn.getLeft();
                removeChild.setParent(null);
                btn.setLeft(null);
                size--;
                return true;
            }
            else {
                BinaryTreeNode<E> removeChild = btn.getRight();
                removeChild.setParent(null);
                btn.setRight(null);
                size--;
                return true;
            }
        }
    }

    /**
     * @return The number of nodes currently in the tree.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return true if the tree contains no nodes (that is the root = null),
     * false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return (root() == null);
    }

    /**
     * Updates the value of the node to the provided value. Throws an
     * IllegalArgumentException if the value is null, the node is null, or the
     * node is not in this Tree.
     *
     * @param node    Node whose value is to be updated.
     * @param element New value for the node.
     * @throws IllegalArgumentException If the provided node is invalid, or the
     *                                  element value is null.
     */
    @Override
    public E set(Node<E> node, E element) throws IllegalArgumentException {

        checkNull(element);
        checkValid(node);

        if(!exists(node))
        {
            throw new IllegalArgumentException("Node does not exist");
        }



        return element;
    }

    /**
     * Validates that the provided node is not null, is of a subtype of Node
     * supported by the implementing tree class, and is currently in this tree.
     * If these conditions are not met then an IllegalArgumentException is
     * thrown.
     *
     * @param p The node to be validated.
     * @return A node of the expected type specific to the implementing tree.
     * @throws IllegalArgumentException Thrown if the provided node is null, not
     *                                  in the current tree, or is not of a type supported by the current tree.
     */
    @Override
    public Node<E> validate(Node<E> p) throws IllegalArgumentException {

        checkValid(p);

        if(!exists((p)))
        {
            throw new IllegalArgumentException("Node does not exist");
        }

        return p;
    }

    /**
     * Calculates the depth of the given node in the current tree.
     *
     * @param node Node whose depth is to be calculated
     * @return Depth of the node in the tree.
     * @throws IllegalArgumentException If the provided node is invalid
     */
    @Override
    public int depth(Node<E> node) throws IllegalArgumentException {
        return 0;
    }

    /**
     * Recusively calculates the size of a subtree rooted at the provided node.
     *
     * @param node Node whose subtree size is to be calculated
     * @return Size of the subtree (excluding the root)
     * @throws IllegalArgumentException If the provided node is invalid.
     */
    @Override
    public int subTreeSize(Node<E> node) throws IllegalArgumentException {
        return 0;
    }

    /**
     * Checks if the provided node is the last child of it's parent node. Note
     * that the root node always returns true.
     *
     * @param node Node to check.
     * @return True if the node is the last child of it's parent node or is the
     * root, false otherwise.
     * @throws IllegalArgumentException If the provided node is not valid.
     */
    @Override
    public boolean isLastChild(Node<E> node) throws IllegalArgumentException {
        return false;
    }




    // Custom Methods Below



    private void checkNull(E item) throws IllegalArgumentException {
        if (item == null)
        {
            throw new IllegalArgumentException("Element is null");
        }
    }

    private boolean exists(Node<E> curr) {
        if (isRoot(curr)) {
            return true;
        }
        else if (isRoot(parent(curr))) {
            return true;
        }
        else {
            return exists(parent(curr));
        }
    }


    private void checkValid(Node<E> node) throws IllegalArgumentException {
        if (node == null)
        {
            throw new IllegalArgumentException("Node is null");
        }
        if (!(node instanceof BinaryTreeNode))
        {
            throw new IllegalArgumentException("Node is not a BinaryTreeNode");
        }
    }


}
