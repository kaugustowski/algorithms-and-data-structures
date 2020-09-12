package algorithms;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MergeSortTest {


    @Test
    void shouldReturnSortedArray() {
        int[] testArray = {6, 2, 3, 4, 12, 11, 19, 31, 15};

        testArray = MergeSort.mergeSort(testArray);

        int[] sortedArray = {2, 3, 4, 6, 11, 12, 15, 19, 31};

        assertThat(testArray).isEqualTo(sortedArray);
    }
}