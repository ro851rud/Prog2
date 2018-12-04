package aufgabe4;

import java.util.*;

public class ArraySet<T> extends AbstractSet<T>{
    private int size = 0;
    private T[] data;

    public ArraySet() {
        clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArraySet)) return false;
        ArraySet<?> arraySet = (ArraySet<?>) o;
        return size == arraySet.size &&
                Arrays.equals(data, arraySet.data);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    /**
     * Liefert die Anzahl der Elemente dieser Menge zur&uuml;ck
     * @return Anzahl der Elemente.
     */
    public int size() {
        return size;
    }
    
    @Override
    /**
     * Pr&uuml;ft, ob diese Menge leer ist.
     * @return true, falls diese Menge leer ist, sonst false.
     */
    public boolean isEmpty() {
        if(this.size() == 0) {
            return false;
        }
        return true;
    }
    
    @Override
    /**
     * F&uuml;gt das Element x zu dieser Menge dazu, 
     * falls es noch nicht in der Menge enthalten ist.
     * @param x Element
     * @return true, falls x dazugef&uuml;gt wurde, sonst false.
     */
    public boolean add(T x) {
        if (!(this.contains(x))) {
            if(data.length == size)
                ensureCapacity(2*size);
            data[size] = x;
            size++;
            return true;
        } else {
            return false;
        }
    }
    @SuppressWarnings("unchecked")
    private void ensureCapacity(int newCapacity) {
        if (newCapacity < size)
            return;
        T[] old = data;
        data = (T[]) new Object[newCapacity];
        System.arraycopy(old, 0, data, 0, size);
    }
    
    @Override
    /**
     * Entfernt das Element x aus dieser Menge, falls es vorhanden ist.
     * @param x Element
     * @return true, falls x entfernt wurde, sonst false.
     */
    public boolean remove(Object x) {
        if (this.contains(x)) {
            int wo = 0;
            for (int i = 0; i < size; i++) {
                if (data[i].equals(x)) {
                    wo = i;
                }
            }
            for (int i = wo; i < size-1; i++)
                data[i] = data[i+1];
            size--;
            return true;
        } 
        return false;
    }
    
    @Override
    /**
     * Pr&uuml;ft, ob das Element in dieser Menge vorkommt.
     * @param x Element
     * @return true, falls x vorkommt, sonst false.
     */
    public boolean contains(Object x) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(x)) {
                return true;
            }
        }
        return false;
        
    }
    
    @Override
    /**
     * Liefert das Element an der Position i zur&uuml;ck.
     * @param i Position 
     * @return Element an der Position i.
     * @throws IndexOutOfBoundsException falls die Position i &lt; 0 oder &gt;= size() 
     */
    public T get (int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= size)
            throw new IndexOutOfBoundsException();
        return data[i];
    }
    
    @Override
    /**
     * Löscht alle Elemente aus dieser Menge.
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        size = 0;
        data = (T[]) new Object[32];
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        for (int i = 0; i < size; i++) {
            if (data[i + 1] != null && data[i] != null) {
                s.append(data[i]).append(", ");
            } else {
                s.append(data[i]).append(" ");
            }
        }
        s.append("size = ").append(size);
        return s.toString();
    }
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {
        private int zeiger = 0;

        @Override
        public boolean hasNext() {
                return zeiger < size();
                }


        public T next() {
            if (hasNext()) {
                return data[zeiger++];
            }
            throw new NoSuchElementException();
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}
