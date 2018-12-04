package aufgabe2;

public class LinkedListSet extends AbstractSet {
    
    public LinkedListSet() {clear();}
    
    public final void clear() {
        head = new Node(0,null);
        size = 0;
    }
    
    static private class Node {
        Node next;
        int data;
        Node (int x, Node p) {
            data = x;
            next = p;
        }
    }
    private Node head;
    private int size;
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean add(int x) {
        if (!contains(x)) {
            Node p = head;
            while(p.next != null && p.next.data < x) {
                p = p.next;
                }
            p.next = new Node(x,p.next);
            size++;
            return true;
        }
        return false;
    }
    
    public boolean remove(int x) {
        if (contains(x)) {
            Node p = head.next;
            for (int i = 0; i < size; i++) {
                if (p.next.data != x) {
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
    public boolean contains(int x) {
        Node p;
        for (p = head.next; p != null; p = p.next) {
            if (p.data == x)
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
    public int get(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= size)
            throw new IndexOutOfBoundsException();
        Node p = head.next;
        for (int q = 0; q < i; q++)
            p = p.next;
        return p.data;
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        for (Node p = head.next; p != null; p = p.next) {
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
