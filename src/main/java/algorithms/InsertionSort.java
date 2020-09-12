package algorithms;

import java.util.Arrays;

public class InsertionSort {

    private static int[] insertionSort(int[] inputArray) {

        for (int sortingKey = 1; sortingKey < inputArray.length; sortingKey++) {
            for (int j = sortingKey; j > 0 && inputArray[j] < inputArray[j - 1]; j--) {
                swap(inputArray, j - 1, j);
            }
        }
        return inputArray;
    }

    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void main(String[] args) {
        int[] testArray = {15, 28, 17, 2, 7, 4, 1};
        int[] testArray2 = {12, 21, 17, 3, 2, 6, 1};

        InsertionSort sorter = new InsertionSort();

        sorter.sort(testArray);

        InsertionSort.insertionSort(testArray2);

        System.out.println(Arrays.toString(testArray));

        System.out.println(Arrays.toString(testArray2));

    }

    //O(n^2) complexity
    public void sort(int[] array) {
        InsertionSort.insertionSort(array);
    }
}


