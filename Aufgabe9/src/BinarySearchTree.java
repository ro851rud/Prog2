// O. Bittel
// 9.3.2018

//package aufgabenblatt9;

import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class BinarySearchTree<T> implements Iterable<T> {

    @Override
    public Iterator<T> iterator() {
        List<T> list = new LinkedList();
        addListR(list, root);
        for(T x : list){
            System.out.println(x);
        }
        return list.iterator();
    }

    private void addListR(List list, Node p) {
        if(p != null) {
            addListR(list, p.left);
            list.add(p.data);
            addListR(list, p.right);
        }
    }

    static private class Node <T extends Comparable<? super T>> {
        T data;
        Node left;
        Node right;
        Node(T x) {
            data = x;
            left = null;
            right = null;
        }
    }

    private Node root = null;
    private int size = 0;



    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("BinarySearchTree: ");
        appendR(s,root);
        s.append(" size = ").append(size);
        return s.toString();
    }

    private static void appendR(StringBuilder s, Node p) {
        if (p != null) {
            appendR(s,p.left);
            s.append(p.data).append(", ");
            appendR(s,p.right);
        }
    }

    public <T extends Comparable<? super T>> boolean  contains(T x) {
        return containsR(x,root);
    }

    private <T extends Comparable<? super T>> boolean containsR(T x, Node p) {
	if (p == null)
            return false;
	else if (x.compareTo((T)p.data) < 0)
            return containsR(x,p.left);
	else if (x.compareTo((T)p.data) > 0)
            return containsR(x,p.right);
	else
            return true;
    }

    public <T extends Comparable<? super T>> void insert(T x) {
        root = insertR(x, root);
    }

    private <T extends Comparable<? super T>> Node insertR(T x, Node p) {
        if (p == null) {
            size++;
            return new Node(x);
        }
        if (x.compareTo((T)p.data) < 0) {
            p.left = insertR(x, p.left);
        } else if (x.compareTo((T)p.data) > 0){
            p.right = insertR(x, p.right);
        }
        // im else-Fall ist nicht zu tun; keine doppelten Werte
        return p;
    }

    public <T extends Comparable<? super T>> void remove(T x) {
        root = removeR(x,root);
    }

    private <T extends Comparable<? super T>> Node removeR(T x, Node p) {
        if (p == null)
            return null;
        if (x.compareTo((T)p.data) < 0)
            p.left = removeR(x,p.left);
	    else if (x.compareTo((T)p.data) > 0)
            p.right = removeR(x,p.right);
        else {
            // Knoten loeschen:
            if (p.left == null || p.right == null) {
                // One or no child can be deleted directly:
                size--;
                p = (p.left != null) ? p.left : p.right;
            }
            else {
                // Two children
                p.data = getMin(p.right);
                p.right = removeR(p.data,p.right);
            }
        }
	return p;
    }

    private <T extends Comparable<? super T>> T getMin(Node p) {
        assert (p != null);
        while(p.left != null)
            p = p.left;
        return (T) p.data;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public static void main (String[] args){
        BinarySearchTree t = new BinarySearchTree();
        BinarySearchTree t2 = new BinarySearchTree();
        BinarySearchTree t3 = new BinarySearchTree();
        int[] a = {5, 3, 1, 4, 8, 6, 2, 7};
        for (int x : a){
            t.insert(x);
        }
        t.prettyPrint();
        System.out.println("Baumtiefe: " + t.height());

        String [] p = {"Hallo", "Tschuess", "moin"};
        for (String g : p){
            t3.insert(g);
        }
        t3.prettyPrint();

        int[] b = {7, 2, 8, 1, 4, 3, 6};
        for (int y : b){
            t2.insert(y);
        }
        t2.iterator();
        //Elemente werden in absteigender Reihenfolge sortiet
        while (t2.getSize() > 0){
            System.out.printf("Entferntes Element:");
            System.out.println(t2.removeMax());
        }


    }

    public void prettyPrint() {
        prettyPrintR(0, this.root);
    }

    private static void prettyPrintR(int tiefe, Node p) {

        for (int i = 0; i < tiefe - 1; i++){
            System.out.print("\t");
        }
        if (tiefe != 0) {                               //weiter Elemente
            System.out.println("|__" + p.data);
        } else {                                        //Startelement
            System.out.println(p.data);
        }
        ++tiefe;
        if((p.left != null) && (p.right != null)) {     //beides vorhanden
            prettyPrintR(tiefe, p.left);
            prettyPrintR(tiefe, p.right);
        } else if(p.left != null) {                     //nur kleineres vorhanden
            prettyPrintR(tiefe, p.left);
            for (int i = 0; i < tiefe - 1; i++) {
                System.out.print("\t");
            }
            System.out.print("|__#\n");
        } else if(p.right != null) {                    //nur groeseres vorhanden
            for (int i = 0; i < tiefe - 1; i++) {
                System.out.print("\t");
            }
            System.out.print("|__#\n");
            prettyPrintR(tiefe, p.right);
        }

    }

    public int height(){
        return heightR(this.root, 0);
    }
    public int heightR(Node p, int tiefe) {

        if (p.left != null && p.right == null){             //nur links
            tiefe++;
            int i = heightR(p.left, tiefe);
            if (i > tiefe){
                tiefe = i;
            }
        } else if (p.right != null && p.left == null) {     //nur rechts
            tiefe++;
            int i = heightR(p.right, tiefe);
            if (i > tiefe) {
                tiefe = i;
            }
        } else if (p.right != null && p.left != null) {     //beides vorhanden
            tiefe++;
            int l = heightR(p.left, tiefe);
            int r = heightR(p.right, tiefe);
            if (l > tiefe || r > tiefe) {
                tiefe = (l > r) ? l : r;
            }
        }
        return tiefe;

    }

    public int removeMax() {
        int entferntes = removeMaxR(root);
        remove(entferntes);
        return entferntes;
    }

    public int removeMaxI(Node p) {
        while (p.right != null) {
            p = p.right;
        }
        int groeste = (Integer) p.data;
        remove(groeste);
        return groeste;
    }
    public int removeMaxR(Node p) {
        int groeste = 0;
        if (p.right != null){
            groeste = removeMaxR(p.right);
        } else if (p.left != null) {
            groeste = removeMaxR(p.left);
        }
        return  p.data.compareTo(groeste)> 0 ? (Integer) p.data : groeste;
    }

    /*public static void main(String[] args) {

        BinarySearchTree t = new BinarySearchTree();
        t.insert(10);
        t.insert(5);
        t.insert(15);
        t.insert(3);
        t.insert(8);
        t.insert(7);
        t.insert(15);
        t.insert(20);
        t.insert(17);
        t.prettyPrint(t);

    }*/
}
