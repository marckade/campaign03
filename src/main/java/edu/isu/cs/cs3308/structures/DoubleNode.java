package edu.isu.cs.cs3308.structures;

public class DoubleNode<E> {

    private DoubleNode<E> next;

    private E data;

    public DoubleNode<E> getPrevious() {
        return previous;
    }

    public void setPrevious(DoubleNode<E> previous) {
        this.previous = previous;
    }

    private DoubleNode<E> previous;

    public DoubleNode(E data) {
        this.data = data;
    }

    public DoubleNode<E> getNext() {
        return next;
    }

    public void setNext(DoubleNode<E> next) {
        this.next = next;
    }

    public E getData() {
        return data;
    }

    // Basic constructor for node including data.
    public void setData(E data) {
        this.data = data;
    }
}