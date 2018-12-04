

public class HybridesQicksort {

    public static void main(String[] args) {
        Integer[] arr = {22, 5, 4, 1, 13, 18, 54, 83, 11, 7, 2, 25, 34};
        quicksort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static <T extends Comparable<? super T>> void quicksort(T[] a) {
        quicksort(a, 0, a.length - 1);
    }

    public static <T extends Comparable<? super T>> void quicksort(T[] a, int li, int re) {
        if (a.length <= 2) {
            insertionSort(a, li, re);
        } else if (re > li) {
            int i = partition(a, li, re);

            quicksort(a, li, i -1);
            quicksort(a, i + 1, re);
        }

    }

    public static <T extends Comparable<? super T>> void swap(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static <T extends Comparable<? super T>> void insertionSort(T[] a, int li, int re) {
        if (re > li) {
            if (li != li + 1) {
                for (int i = li; i < re; i++) {
                    T v = a[i];
                    int j = i - 1;
                    while (j > li && a[j].compareTo(v) < 0) {
                        a[j + 1] = a[j];
                        j--;
                    }
                    a[j + 1] = v;
                }
            }
        }
    }




    private static <T extends Comparable<? super T>> int partition(T a[ ], int li, int re) {
        T v = a[re];
        int i = li-1;
        int j = re;
        while (true) {
            do i++; while (a[i].compareTo(v) < 0);
            do j--; while (j >= li && a[j].compareTo(v) > 0);
            if (i >= j)
                break;
            swap(a, i, j);
        }
        a[re] = a[i]; a[i] = v;
        return i;
    }

}
