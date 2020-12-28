import java.util.Scanner;

public class CircularLinkedList <E> {

    //Instance variables
    private Node<E> tail;
    private int size = 0;

    /*
        init instance variables
     */
    public CircularLinkedList()
    {
    }

    /*
        return the size of the linked list
     */
    public int size()
    {
        return size;
    }

    /*
        checks if the linked list is empty
     */
    public boolean isEmpty()
    {
        return size() == 0;
    }
    /*
        if linked list is empty return null
        if not empty return the first element
     */
    public E first()
    {
        if (isEmpty()){ return null;}
        return tail.getNext().getElement();
    }

    /*
        if linked list is empty return null
        if not empty return last element
     */
    public E last()
    {
        if (isEmpty()){ return null;}
        return tail.getElement();
    }

    /*
        move tail to the next node
     */
    public void rotate()
    {
        if (isEmpty()){ return;}
        tail = tail.getNext();
    }

    /*
        add element to the first of the linked list
        increase the size
     */
    public void addFirst(E e)
    {
        if (isEmpty()){
            Node<E> first = new Node<E>(e, null);
            tail = first;
            tail.setNext(first);
        }
        Node<E> oldFirst = tail.getNext();
        Node<E> adding = new Node<>(e, oldFirst);
        tail.setNext(adding);
        size ++;
    }

    /*
        add element to the end of the linked list
        increase size
     */
    public void addLast(E e)
    {
        if (isEmpty()){
            Node<E> last = new Node<E>(e, null);
            tail = last;
            last.setNext(last);
            size ++;
            return;
        }
        Node<E> oldTail = tail;
        Node<E> next = tail.getNext();
        Node<E> newTail = new Node<>(e, next);
        tail = newTail;
        oldTail.setNext(newTail);
        size ++;
    }

    /*
        take out the first element
        decrease the size
        return first element or null


     */
    public E removeFirst()
    {
        if(isEmpty()){return null;}
        Node<E> oldFirst = tail.getNext();
        Node<E> newFirst = oldFirst.getNext();
        tail.setNext(newFirst);
        size --;
        return oldFirst.getElement();

    }

    public String toString()
    {
        String s;
        Node<E> n;
        if ( tail == null ){
            return "null";}
        s = "[";
        n = tail.getNext();
        if (n==null)
        {
            return s+ "empty list]";
        }
        int iter =0;
        while (iter<2*size)
        {
            iter++;
            s = s+ n.getElement();
            if (iter<2*size) s = s + ", ";
            n = n.getNext();
        }
        return s+"]";
    }
    public static void main(String args[])
    {
        String[] cars = { "prius", "rav4", "subaru", "crv", "pilot"};
        CircularLinkedList<String> carslist = new CircularLinkedList<String>();
        for (String i: cars)
        {
            carslist.addLast(i);
        }
        System.out.println("linkedlist:"+ carslist);

    }
}
