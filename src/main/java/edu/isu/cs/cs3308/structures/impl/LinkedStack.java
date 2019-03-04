package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.Stack;

public class LinkedStack<E> implements Stack<E> {

    private DoublyLinkedList<E> stack = new DoublyLinkedList<>();

    /**
     * Adds the provided item to the top of the stack. Note that if the item is
     * null, nothing occurs.
     *
     * @param element Element added to the top of the stack, unless this item is
     * null.
     */
    @Override
    public void push(E element) {

        stack.addFirst(element);

    }

    /**
     * Returns the value of the top item in the stack, without removing it. If
     * the stack is empty then null is returned.
     *
     * @return The value of the item at the top of the stack, or null if the
     * stack is empty.
     */
    @Override
    public E peek() {

        //Calls first method inside doubly linked list. It already checks for
        //null variables there.
        return stack.first();
    }

    /**
     * Removes the top item from the stack and returns it's value. If the stack
     * is currently empty, null is returned.
     *
     * @return The value of the top item in the stack, or null if the stack is
     * empty.
     */
    @Override
    public E pop() {

        return stack.removeFirst();
    }

    /**
     * @return The current number of items in this stack.
     */
    @Override
    public int size() {

        //Calls size method inside double linked list.
        return stack.size();
    }

    /**
     * A test to determine if this Stack is currently empty.
     *
     * @return True if this stack is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {

        //Calls isEmpty method inside double linked list.
        return stack.isEmpty();
    }

    /**
     * Tranfers the contents of this stack into the provided stack. The contents
     * of this stack are to found in reverse order at the top of the provided
     * stack. This stack should be empty once the transfer is completed. Note
     * that if the provided stack is null, nothing is to happen.
     *
     * @param to The new stack onto which the reversed order of contents from
     * this stack are to be transferred to the top of, unless the provided stack
     * is null.
     */
    @Override
    public void transfer(Stack<E> to) {

        if(to == null && size() > 0)
        {
            //Nothing happens
        }
        else
        {
            while(stack.size() > 0)
            {
                E tempVariable = this.pop();
                to.push(tempVariable);
            }
        }
    }

    /**
     * Reverses the contents of this stack.
     */
    @Override
    public void reverse() {

        //Creates two temp stacks to transfer between in order to reverse.
        LinkedStack tempOne = new LinkedStack<E>();
        LinkedStack tempTwo = new LinkedStack<E>();


        this.transfer(tempOne);
        tempOne.transfer(tempTwo);
        tempTwo.transfer(this);


    }

    /**
     * Merges the contents of the provided stack onto the bottom of this stack.
     * The order of both stacks must be preserved in the order of this stack
     * after the method call. Furthermore, the provided stack must still contain
     * its original contents in their original order after the method is
     * complete. If the provided stack is null, no changes should occur.
     *
     * @param other Stack whose contents are to be merged onto the bottom of
     * this stack.
     */
    @Override
    public void merge(Stack<E> other) {

        //Checks if the other stack is null before merging.
        if(other == null)
        {
            //Do nothing
        }
        else
        {
            //Creates two temp stacks to transfer between in order to reverse.
            LinkedStack<E> tempOne = new LinkedStack<E>();
            LinkedStack<E> tempTwo = new LinkedStack<E>();

            //Transfers the stacks to reverse order originally. Prevents later problems.
            this.transfer(tempOne);
            other.transfer(tempTwo);

            //While the second temp stack is greater than 0
            while(tempTwo.size() > 0)
            {
                // Bring the first variable from tempTwo and store in a temp.
                E tempElement = tempTwo.pop();

                // Push it onto the other stack.
                other.push(tempElement);
                //push it onto the current stack.
                this.push(tempElement);
            }

            //Transfer the tempOne to this stack.
            tempOne.transfer(this);


        }
    }

    /**
     * Prints the contents of the stack starting at top, one item per line. Note
     * this method should not empty the contents of the stack.
     */
    @Override
    public void printStack() {

        //Calls printList method from double linked list.
        stack.printList();

    }
}