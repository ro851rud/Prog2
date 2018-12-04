// O. Bittel
// 27.3.2018
//package rangesearch;

public class RangeSearch {

    public static void main(String[] args) {
        Integer[] a = {0, 2, 2, 2, 2, 2, 3, 4, 5, 6, 6, 6, 6, 9, 10, 11};
        System.out.println(rangeSearch(a, 2, 6)); // 8
        System.out.println(rangeSearch(a, 1, 6)); // 8
        System.out.println(rangeSearch(a, 3, 6)); // 3

        System.out.println(rangeSearch(a, 2, 8)); // 12
        System.out.println(rangeSearch(a, 2, 9)); // 12

        System.out.println(rangeSearch(a, 0, 9)); // 13
        System.out.println(rangeSearch(a, -1, 9)); // 13

        System.out.println(rangeSearch(a, 3, 11)); // 9
        System.out.println(rangeSearch(a, 3, 15)); // 10
    }

    public static <T extends Comparable<? super T>> int rangeSearch(T[] a, T u, T v) {
            // Ihr Code:
        int li = 0;
        int re = a.length - 1;
        int foundU;
        int foundV;
        int intervall = 0;
        if (u.compareTo(v) < 0) {
            foundU = binarySearch(a, li, re, u);
            foundV = binarySearch(a, li, re, v);
            intervall = foundV - foundU;
        }
        return intervall;   // damit IDE kein Sysntaxfehler anzeigt.
    }

    private static <T extends Comparable<? super T>> int binarySearch(T[] a, int li, int re, T x) {
        // Ihr Code:
        if (re < li)
            return li;
        else {
            int m = (li + re)/2;
            if (x.compareTo(a[m]) < 0) {
                return binarySearch(a, li, m - 1, x);
            } else if (x.compareTo(a[m]) > 0) {
                return binarySearch(a, m + 1, re, x);
            } else if (x.compareTo(a[m]) != 0) {

            } else{// x == a[m]
                while(m != 0 && a[m].compareTo(a[m - 1]) == 0) {
                    m = m - 1;
                }
            }
                return m;
        }
    }
    public static <T extends Comparable<? super T>> int binSuche(T[] a, T x) {
        assert isSorted(a);
        return binSuche(a,x);
    }
    private static <T extends Comparable<? super T>> boolean isSorted(T[] a) {
        for (int i = 0; i < a.length-1; i++)
            if (a[i+1].compareTo(a[i]) < 0)
                return false;
        return true;
    }

}
