public class HybridQsort3Median {

    public static void main(String[] args) {
        Integer[] arr = {22, 5, 4, 1, 13, 18, 54, 83, 11, 7, 2, 25, 34, 2};
        quicksort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static <T extends Comparable<? super T>> void quicksort(T[] a) {
        quicksort(a, 0, a.length - 1);
    }

    public static <T extends Comparable<? super T>> void quicksort(T[] a, int li, int re) {
        if (re-li <= 2) {
            insertionSort(a, li, re);
        } else if (re > li) {
            int i = partition(a, li, re);

            quicksort(a, li, i - 1);
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
            for (int i = li; i <= re; i++) {
                T v = a[i];
                int j = i - 1;
                while (j >= li && a[j].compareTo(v) > 0) {
                    a[j + 1] = a[j];
                    j--;
                }
                a[j + 1] = v;
            }

        }
    }


    private static <T extends Comparable<? super T>> int partition(T[] a, int li, int re) {
        Integer[] arr2 = {(Integer) a[li], (Integer) a[(li + re) / 2], (Integer) a[re]};
        insertionSort(arr2, 0, 2);
        a[li] = (T)arr2[0];
        a[(li + re) / 2] = (T)arr2[1];
        a[re] = (T)arr2[2];
        T v = a[(li + re) / 2];
        int i = li - 1;
        int j = re;
        while (true) {
            do i++; while (a[i].compareTo(v) < 0);
            do j--; while (j >= li && a[j].compareTo(v) >=  0);
            if (i >= j)
                break;
            swap(a, i, j);
        }

        int z;
        for (z = li; z < re; z++) {
            if (a[z] == v) {
                break;
            }
        }
        swap(a, z, i);
        return i;

    }


}
