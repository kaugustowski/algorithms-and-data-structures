package algorithms;

public class QuickSort {

    public static void quicksort(int[] ar) {
        if (ar == null) return;
        quicksort(ar, 0, ar.length - 1);
    }

    // Hoare partition
    private static void quicksort(int[] ar, int lo, int hi) {
        if (lo < hi) {
            int splitIndex = partition(ar, lo, hi);
            quicksort(ar, lo, splitIndex);
            quicksort(ar, splitIndex + 1, hi);
        }
    }

    private static int partition(int[] ar, int lo, int hi) {
        int pivot = ar[lo];
        int i = lo - 1, j = hi + 1;
        while (true) {
            do {
                i++;
            } while (ar[i] < pivot);
            do {
                j--;
            } while (ar[j] > pivot);
            if (i < j)
                swap(ar, i, j);
            else return j;
        }
    }

    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public void sort(int[] array) {
        QuickSort.quicksort(array);
    }
}
