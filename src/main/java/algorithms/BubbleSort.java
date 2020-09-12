package algorithms;

public class BubbleSort {

    public void sort(int[] inputArray) {
        BubbleSort.bubbleSort(inputArray);
    }

    //O(n^2) complexity
    private static void bubbleSort(int[] array) {
        if (array == null)
            return;

        boolean isSorted = false;

        while (!isSorted) {
            isSorted = true;
            for (int i = 1; i < array.length; i++) {
                if (array[i] < array[i - 1]) {
                    swap(array, i - 1, i);
                    isSorted = false;
                }
            }
        }
    }

    private static void swap(int[] array, int prevIndex, int index) {
        int temp = array[prevIndex];
        array[prevIndex]=array[index];
        array[index]=temp;
    }

    public static void main(String[] args) {
        //#1
        int[] array = {2,1,4,18,13,12,10,-2};
        System.out.println("Array before sorting: " + java.util.Arrays.toString(array));
        BubbleSort sorter = new BubbleSort();
        sorter.sort(array);
        System.out.println("Array after sorting: " + java.util.Arrays.toString(array));

        //#2
        int[] array2 = {2,5,14,4,6,13,12,-2};
        System.out.println("Array 2 before sorting: " + java.util.Arrays.toString(array2));
        BubbleSort.bubbleSort(array2);
        System.out.println("Array 2 after sorting: " + java.util.Arrays.toString(array2));
    }
}
