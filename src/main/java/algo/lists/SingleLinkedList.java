package algo.lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Single linked list
 */
public class SingleLinkedList<E> implements MyList<E> {

    private Node<E> first;

    private int size;

    public SingleLinkedList(E...items) {
        for (E item: items) {
            add(item);
        }
    }

    /**
     * Insert item to the head - O(1)
     */
    public void addFirst(E item) {
        Node<E> node = new Node<>(item);
        if (first == null) {
            first = node;
        } else {
            first = node;
        }
        size++;
    }

    /**
     * Add item to the tail of the list - O(n)
     */
    public void add(E item) {
        if (first == null) {
            addFirst(item);
        } else {
            Node<E> curr = first;
            Node<E> prev = first;
            while (curr != null) {
                prev = curr;
                curr = curr.next;
            }
            final Node<E> l = new Node<>(item, null);
            prev.next = l;
            size++;
        }
    }

    public E search(E key) {
        Node<E> node = searchRecursively(key, first);
        return node != null ? node.item : null;
    }

    public E getFirst() {
        return first != null ? first.item : null;
    }

    public void remove(E item) {

    }

    public int size() {
        return size;
    }

    /**
     * Removing first element
     */
    public void removeFirst() {
        final Node<E> f = first;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null;
        first = next;
        size--;
    }

    public Iterator<E> iterator() {
        return new IteratorImpl(first);
    }

    Node<E> searchRecursively(E item, Node<E> node) {
        if (node == null)
            return null;
        return node.item.equals(item)
                ? node: searchRecursively(item, node.next);
    }

    private class IteratorImpl implements Iterator<E> {
        private Node<E> next;
        private Node<E> lastReturned;

        public IteratorImpl(Node<E> next) {
            this.next = next;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public E next() {
            if (hasNext()) {
                lastReturned = next;
                next = next.next;
                return lastReturned.item;
            }
            throw new NoSuchElementException();
        }
    }

    static final class Node<E> {
        E item;

        Node<E> next;

        public Node(E item) {
            this.item = item;
        }

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }
}
