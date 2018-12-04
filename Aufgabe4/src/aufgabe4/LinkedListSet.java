package aufgabe4;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListSet<T> extends AbstractSet<T> {
    
    public LinkedListSet() {clear();}

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    public final void clear() {
        head = new Node<T>(null, null);
        size = 0;
        laenge++;
    }
    
    static private class Node<T> {
        Node<T> next;
        T data;
        Node (T x, Node<T> p) {
            data = x;
            next = p;
        }
    }
    private Node<T> head;
    private int size;
    private int laenge = 0;


    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = head;
        private int  expectedMod = laenge;

        @Override
        public boolean hasNext() {
            return current.next != null;
        }
        @Override
        public T next() {
            if (expectedMod != laenge) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            current = current.next;
            return current.data;

        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean add(T x) {
        if (!contains(x)) {
            Node<T> p = head;
            while(p.next != null) {
                p = p.next;
            }
            p.next = new Node<T>(x,p.next);
            size++;
            laenge++;
            return true;
        }
        return false;
    }
    
    public boolean remove(Object x) {
        if (contains(x)) {
            Node<T> p = head.next;
            for (int i = 0; i < size; i++) {
                if (!(p.next.data.equals(x))) {
                    p = p.next;
                }
            }
            p.next = p.next.next;
            size--;
            return true;
        }
        return false;
    }
    /**
     * Pr&uuml;ft, ob das Element in dieser Menge vorkommt.
     * @param x Element
     * @return true, falls x vorkommt, sonst false.
     */
    public boolean contains(Object x) {
        Node<T> p;
        for (p = head.next; p != null; p = p.next) {
            if (p.data.equals(x))
                return true;
        }
        return false;
    }
    
    /**
     * Liefert das Element an der Position i zur&uuml;ck.
     * @param i Position 
     * @return Element an der Position i.
     * @throws IndexOutOfBoundsException falls die Position i &lt; 0 oder &gt;= size() 
     */
    public T get(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= size)
            throw new IndexOutOfBoundsException();
        Node<T> p = head.next;
        for (int q = 0; q < i; q++)
            p = p.next;
        return p.data;
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        for (Node<T> p = head.next; p != null; p = p.next) {
                s.append(p.data);
                if (p.next != null) {
                    s.append(", ");
                } else {
                    s.append(" ");
                }
        }
        s.append("size = ").append(size);
        return s.toString();
    }

}
