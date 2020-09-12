package algorithms;

import java.util.Arrays;

public class MergeSort {


    //O(n*log(n)) complexity
    public static int[] mergeSort(int[] inputArray) {

        int n = inputArray.length;
        if (n == 1)
            return inputArray;

        int[] leftArray = mergeSort(Arrays.copyOfRange(inputArray, 0, n / 2));
        int[] rightArray = mergeSort(Arrays.copyOfRange(inputArray, n / 2, n));

        return merge(leftArray, rightArray);
    }

    private static int[] merge(int[] array1, int[] array2) {

        int mergedArrayLength = array1.length + array2.length;
        int[] mergedArray = new int[mergedArrayLength];
        int j = 0;
        int k = 0;

        for (int i = 0; i < mergedArrayLength; i++) {
            if (j >= array1.length)
                mergedArray[i] = array2[k++];
            else if (k >= array2.length)
                mergedArray[i] = array1[j++];
            else {
                if (array1[j] < array2[k])
                    mergedArray[i] = array1[j++];
                else
                    mergedArray[i] = array2[k++];
            }
        }
        return mergedArray;
    }


    public static void main(String[] args) {
        int[] testArray = {5, 6, 15, 14, 21, 2, 8, 11};

        testArray = MergeSort.mergeSort(testArray);

        System.out.println(Arrays.toString(testArray));
    }
}
