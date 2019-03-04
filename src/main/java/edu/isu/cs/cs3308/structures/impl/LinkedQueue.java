package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.Queue;

public class LinkedQueue<E> implements Queue<E> {


    private DoublyLinkedList<E> linkedQueue= new DoublyLinkedList<>();


    /**
     * @return The number of elements in the queue
     */
    @Override
    public int size() {

        return linkedQueue.size();
    }

    /**
     * @return tests whether the queue is empty.
     */
    @Override
    public boolean isEmpty() {

        return linkedQueue.isEmpty();
    }

    /**
     * Inserts an element at the end of the queue.
     *
     * @param element Element to be inserted.
     */
    @Override
    public void offer(E element) {

        // Checks if the element is null, if its not, add to bottom of list.
        if(element != null)
        {
            linkedQueue.addLast(element);
        }
    }

    /**
     * @return The value first element of the queue (with out removing it), or
     * null if empty.
     */
    @Override
    public E peek() {

        // Check size of the linked que first.
        if(linkedQueue.size() > 0)
        {
            return linkedQueue.first();
        }
        else
        {
            return null;
        }

    }

    /**
     * @return The value of the first element of the queue (and removes it), or
     * null if empty.
     */
    @Override
    public E poll() {

        // Check is the size is less than 1 or empty
        if(linkedQueue.size() < 1)
        {
            return null;
        }
        else
        {
            return linkedQueue.removeFirst();
        }
    }

    /**
     * Prints the contents of the queue starting at top, one item per line. Note
     * this method should not empty the contents of the queue.
     */
    @Override
    public void printQueue() {

        // Call printlist from doubly linked list class.
        linkedQueue.printList();

    }

    /**
     * Tranfers the contents of this queue into the provided queue. The contents
     * of this queue are to found in reverse order at the top of the provided
     * queue. This queue should be empty once the transfer is completed. Note
     * that if the provided queue is null, nothing is to happen.
     *
     * @param into The new queue onto which the reversed order of contents from
     * this queue are to be transferred to the top of, unless the provided queue
     * is null.
     */
    @Override
    public void transfer(Queue<E> into) {

        if(this.size() < 1 || into == null)
        {

        }
        else
        {
            //Create a new linkedStack that will be a temp base for reversing.
            LinkedStack<E> tempStack = new LinkedStack<>();

            while(this.size() > 0)
            {
                E pushed = this.poll();

                tempStack.push(pushed);
            }

            while(tempStack.size() > 0)
            {
                into.offer(tempStack.pop());
            }

        }






    }

    /**
     * Reverses the contents of this queue.
     */
    @Override
    public void reverse() {

        if(this.size() > 9)
        {
            //Create a new linkedStack that will be a temp base for reversing.
            LinkedStack<E> tempStack = new LinkedStack<>();

            while(this.size() > 0)
            {
                E pushed = this.poll();

                tempStack.push(pushed);
            }

            while(tempStack.size() > 0)
            {
                this.offer(tempStack.pop());
            }

        }

    }

    /**
     * Merges the contents of the provided queue onto the bottom of this queue.
     * The order of both queues must be preserved in the order of this queue
     * after the method call. Furthermore, the provided queue must still contain
     * its original contents in their original order after the method is
     * complete. If the provided queue is null, no changes should occur.
     *
     * @param from Queue whose contents are to be merged onto the bottom of
     * this queue.
     */
    @Override
    public void merge(Queue<E> from) {

        if(from == null)
        {

        }
        else
        {
            if(from.size() > 0)
            {
                int storedSize = from.size();

                E element;

                for(int i = 0; i < storedSize; i++)
                {
                    element = from.poll();

                    from.offer(element);
                    this.offer(element);
                }
            }
        }


    }
}
