package aufgabe1;

public abstract class AbstractSet implements Set {
      
    @Override
    /**
     * Pr&uuml;ft, ob alle Element aus s in dieser Menge vorkommen.
     * @param s Menge
     * @return true, falls alle Element aus s in dieser Menge vorkommen, sonst false. 
     */
    public boolean containsAll(Set s) {
        for(int i = 0; i < s.size(); i++) {
            if(!(this.contains(s.get(i)))) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    /**
     * F&uuml;gt alle Element aus s zu dieser Menge dazu.
     * @param s Menge.
     */
    public void addAll(Set s) {
        for(int i = 0; i < s.size(); i++) {
            this.add(s.get(i));
        }
    }
    
    @Override
    /**
     * Entfernt alle Element aus s aus dieser Menge.
     * @param s Menge
     */
    public void removeAll(Set s) {
        if(this.equals(s)) {
            this.clear();
        } else {
            for(int i = 0; i < s.size(); i++) {
                this.remove(s.get(i));
            }
        }
    }
}
