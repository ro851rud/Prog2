package aufgabe2;

import java.util.Arrays;

public class ArraySet extends AbstractSet{
    int size = 0;
    int[] data = new int[32];
    
    
    public ArraySet() {}
    
    
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
    public boolean add(int x) {
        if (!(this.contains(x))) {
            if(data.length == size)
                ensureCapacity(2*size);
            data[size] = x;
            size++;
            Arrays.sort(data, 0, size);
            return true;
        } else {
            return false;
        }
    }
    
    private void ensureCapacity(int newCapacity) {
        if (newCapacity < size)
        return;
        int[] old = data;
        data = new int[newCapacity];
        System.arraycopy(old, 0, data, 0, size);
    }
    
    @Override
    /**
     * Entfernt das Element x aus dieser Menge, falls es vorhanden ist.
     * @param x Element
     * @return true, falls x entfernt wurde, sonst false.
     */
    public boolean remove(int x) {
        if (this.contains(x)) {
            int wo = 0;
            for (int i = 0; i < size; i++) {
                if (data[i] == x) {
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
    public boolean contains(int x) {
        for (int i = 0; i < size; i++) {
            if (data[i] == x) {
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
    public int get (int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= size)
            throw new IndexOutOfBoundsException();
        return data[i];
    }
    
    @Override
    /**
     * Löscht alle Elemente aus dieser Menge.
     */
    public void clear() {
        size = 0;
        data = new int[32];
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        for (int i = 0; i < size; i++) {
            s.append(data[i]).append(", ");
        }
        s.append("size = ").append(size);
        return s.toString();
    }
}
