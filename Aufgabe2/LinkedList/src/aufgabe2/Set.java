package aufgabe2;

/**
 *
 * @author oliverbittel
 */
public interface Set {

    /**
     * Liefert die Anzahl der Elemente dieser Menge zur&uuml;ck
     * @return Anzahl der Elemente.
     */
    int size();

    /**
     * Pr&uuml;ft, ob diese Menge leer ist.
     * @return true, falls diese Menge leer ist, sonst false.
     */
    boolean isEmpty();

    /**
     * F&uuml;gt das Element x zu dieser Menge dazu, 
     * falls es noch nicht in der Menge enthalten ist.
     * @param x Element
     * @return true, falls x dazugef&uuml;gt wurde, sonst false.
     */
    boolean add(int x);

    /**
     * Entfernt das Element x aus dieser Menge, falls es vorhanden ist.
     * @param x Element
     * @return true, falls x entfernt wurde, sonst false.
     */
    boolean remove(int x);

    /**
     * Pr&uuml;ft, ob das Element in dieser Menge vorkommt.
     * @param x Element
     * @return true, falls x vorkommt, sonst false.
     */
    boolean contains(int x);

    /**
     * Liefert das Element an der Position i zur&uuml;ck.
     * @param i Position 
     * @return Element an der Position i.
     * @throws IndexOutOfBoundsException falls die Position i &lt; 0 oder &gt;= size() 
     */
    int get(int i) throws IndexOutOfBoundsException;

    /**
     * Pr&uuml;ft, ob alle Element aus s in dieser Menge vorkommen.
     * @param s Menge
     * @return true, falls alle Element aus s in dieser Menge vorkommen, sonst false. 
     */
    boolean containsAll(Set s);

    /**
     * F&uuml;gt alle Element aus s zu dieser Menge dazu.
     * @param s Menge.
     */
    void addAll(Set s);

    /**
     * Entfernt alle Element aus s aus dieser Menge.
     * @param s Menge
     */
    void removeAll(Set s);
    
    /**
     * Löscht alle Elemente aus dieser Menge.
     */
    void clear();
}
