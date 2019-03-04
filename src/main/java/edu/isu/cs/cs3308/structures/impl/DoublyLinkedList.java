package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.DoubleNode;
import edu.isu.cs.cs3308.structures.DoubleNode;
import edu.isu.cs.cs3308.structures.List;

public class DoublyLinkedList<E> implements List<E> {

    protected DoubleNode<E> head;

    protected DoubleNode<E> tail;

    protected int size = 0;

    /**
     * @return first element in the list or null if the list is empty.
     */
    @Override
    public E first() {

        if(head == null)
        {
            return null;
        }

        return head.getData();
    }

    /**
     * @return last element in the list or null if the list is empty.
     */
    @Override
    public E last() {

        if(size > 0)
        {
            return tail.getData();
        }
        else
        {
            return null;
        }
    }

    /**
     * Adds the provided element to the end of the list, only if the element is
     * not null.
     *
     * @param element Element to be added to the end of the list.
     */
    @Override
    public void addLast(E element) {

        if(size > 0)
        {
            if(element == null)
            {
                // Do nothing as the element is null.
            }
            else
            {
                DoubleNode<E> newDoubleNode = new DoubleNode<>(element);
                tail.setNext(newDoubleNode);
                newDoubleNode.setPrevious(tail);

                tail = newDoubleNode;

                size++;
            }
        }
        else
        {
            addFirst(element);
        }

    }

    /**
     * Adds the provided element to the front of the list, only if the element
     * is not null.
     *
     * @param element Element to be added to the front of the list.
     */
    @Override
    public void addFirst(E element) {

        if(element == null)
        {
            //Do nothing
            return;
        }
        else
        {
            DoubleNode<E> newDoubleNode = new DoubleNode<>(element);

            if(size > 0)
            {
                newDoubleNode.setPrevious(null);
                newDoubleNode.setNext(head);

                //Set previous of old head to new DoubleNode.
                head.setPrevious(newDoubleNode);
            }

            //Checks if the addFirst was the first element ot be added. If so, set tail.
            if(size == 0)
            {
                tail = newDoubleNode;
            }

            head = newDoubleNode;
            size++;

        }

    }

    /**
     * Removes the element at the front of the list.
     *
     * @return Element at the front of the list, or null if the list is empty.
     */
    @Override
    public E removeFirst() {

        if(size > 0)
        {
            // Created tempDoubleNode variable to store new head temporarily.
            DoubleNode<E> tempDoubleNode;


            // the new head is in the tempDoubleNode variable.
            if(size > 1)
            {
                tempDoubleNode = head.getNext();

                //Sets tempDoubleNodes previous to old heads previous.
                tempDoubleNode.setPrevious(head.getPrevious());
            }
            else
            {
                tempDoubleNode = head;
            }



            // Sets next and previous of current head to null which should leave for garbage collection.
            head.setNext(null);
            head.setPrevious(null);

            //Creates temp DoubleNode to store the data from the DoubleNode we are removing.
            DoubleNode<E> originalDoubleNode;
            originalDoubleNode = head;


            // Sets new head to the tempDoubleNode;
            head = tempDoubleNode;

            size--;

            return originalDoubleNode.getData();
        }

        return null;
    }

    /**
     * Removes the element at the end of the list.
     *
     * @return Element at the end of the list, or null if the list is empty.
     */
    @Override
    public E removeLast() {

        //Check list size to ensure the list is not empty.
        if(size > 0)
        {
            // Created tempDoubleNode variable to store new head temporarily.
            DoubleNode<E> tempDoubleNode;

            // set tempDoubleNode to head.
            tempDoubleNode = head;

            //Iterate to the end of the list.
            while(tempDoubleNode.getNext() != null)
            {
                tempDoubleNode = tempDoubleNode.getNext();
            }



            //Iterate size down
            size--;

            //Return tails data.
            return tail.getData();
        }
        else
        {
            return null;
        }
    }

    /**
     * Inserts the given element into the list at the provided index. The
     * element will not be inserted if either the element provided is null or if
     * the index provided is less than 0. If the index is greater than or equal
     * to the current size of the list, the element will be added to the end of
     * the list.
     *
     * @param element Element to be added (as long as it is not null).
     * @param index Index in the list where the element is to be inserted.
     */
    @Override
    public void insert(E element, int index) {

        //If the element is null or the index is less than 0,
        if(element == null || index < 0)
        {
            // Do nothing.
        }
        else
        {
            // If index is greater or equal to in size then do the end instead.
            if(index >= size)
            {
                addLast(element);
            }
            else
            {
                //Create new tempDoubleNode.
                DoubleNode<E> tempDoubleNode;

                // index being 0,1,2,3, set tempDoubleNode to the head.
                tempDoubleNode = head;

                // Get index to the right place.
                for(int i = 0; i < index - 1; i++)
                {
                    tempDoubleNode = tempDoubleNode.getNext();
                }

                // Create newDoubleNode with element parameter.
                DoubleNode<E> newDoubleNode = new DoubleNode<>(element);

                //Set the next for the new DoubleNode equal to the tempDoubleNodes next.
                newDoubleNode.setNext(tempDoubleNode.getNext());
                newDoubleNode.setPrevious(tempDoubleNode);

                //Set tempDoubleNodes next to the new DoubleNode to complete insert.
                tempDoubleNode.setNext(newDoubleNode);

                //Iterate size
                size++;
            }

        }
    }

    /**
     * Removes the element at the given index and returns the value.
     *
     * @param index Index of the element to remove
     * @return The value of the element at the given index, or null if the index
     * is greater than or equal to the size of the list or less than 0.
     */
    @Override
    public E remove(int index) {

        if(index >= size || index < 0)
        {
            return null;
        }
        else
        {

            DoubleNode<E> tempDoubleNode;

            tempDoubleNode = head;

            for(int i = 0; i < index -1; i++)
            {
                tempDoubleNode = tempDoubleNode.getNext();
            }

            DoubleNode<E> tempDoubleNodeTwo;

            tempDoubleNodeTwo = tempDoubleNode.getNext();

            tempDoubleNode.setNext(tempDoubleNodeTwo.getNext());

            tempDoubleNodeTwo.setNext(null);
            tempDoubleNodeTwo.setPrevious(null);

            size--;

            return tempDoubleNodeTwo.getData();

        }
    }

    /**
     * Retrieves the value at the specified index. Will return null if the index
     * provided is less than 0 or greater than or equal to the current size of
     * the list.
     *
     * @param index Index of the value to be retrieved.
     * @return Element at the given index, or null if the index is less than 0
     * or greater than or equal to the list size.
     */
    @Override
    public E get(int index) {

        if(index < 0 || index >= size)
        {
            return null;
        }
        else
        {
            DoubleNode<E> tempDoubleNode;

            // index being 0,1,2,3
            tempDoubleNode = head;

            for(int i = 0; i < index; i++)
            {
                tempDoubleNode = tempDoubleNode.getNext();
            }

            return tempDoubleNode.getData();
        }

    }

    /**
     * @return The current size of the list. Note that 0 is returned for an
     * empty list.
     */
    @Override
    public int size() {

        if(size < 0)
        {
            size = 0;
        }

        return size;
    }

    /**
     * @return true if there are no items currently stored in the list, false
     * otherwise.
     */
    @Override
    public boolean isEmpty() {

        if(size() < 1)
            return true;
        else
        {
            return false;
        }
    }

    /**
     * Prints the contents of the list each item on its own line
     */
    @Override
    public void printList() {

        DoubleNode<E> tempDoubleNode;

        tempDoubleNode = head;

        //Iterates through the linked list and prints the data.
        for(int i = 0; i < size; i++)
        {
            System.out.println(tempDoubleNode.getData());

            tempDoubleNode = tempDoubleNode.getNext();
        }

    }
}
