public class Node<E> {
    private Node<E> leftChild;
    private Node<E> rightChild;
    private Node<E> parent;
    private E element;

    public Node(E element, Node<E> lc, Node<E> rc, Node<E> parent) {
        this.element = element;
        this.leftChild = lc;
        this.rightChild = rc;
        this.parent = parent;
    }

    public E getElement() { return element; }
    public Node<E> getLeftChild() { return leftChild; }
    public Node<E> getRightChild() { return rightChild; }
    public Node<E> getParent() { return this.parent; }

    public void setLeftChild(Node<E> lc) { this.leftChild = lc; }
    public void setRightChild(Node<E> rc) { this.rightChild = rc; }
    public void setParent(Node<E> p) { this.parent = p; }

    public String toString()
    {
        return element.toString();
    }
}
