package datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

class BinarySearchTreeTest {

    private BinarySearchTree<Integer> tree;

    @BeforeEach
    public void init() {
        tree = new BinarySearchTree<>();
    }

    @Test
    public void shouldSuccessfullyInsertFirstNode() {
        assertThat(tree.add(1)).isEqualTo(true);
    }

    @Test
    public void shouldNotInsertNull() {
        assertThat(tree.add(null)).isEqualTo(false);
    }

    @Test
    public void shouldReturnInOrderElementsArray() {
        tree.add(4);
        tree.add(2);
        tree.add(7);
        tree.add(1);
        tree.add(-9);
        tree.add(15);
        tree.add(8);
        tree.add(5);

        int[] expected = {-9, 1, 2, 4, 5, 7, 8, 15};

        int[] actual = new int[expected.length];
        Iterator<Integer> treeIterator = tree.iterator();

        for (int i = 0; i < expected.length; i++) {
            actual[i] = treeIterator.next();
        }

        assertThat(actual).isEqualTo(expected);
    }


    @Test
    public void shouldFindMinValue() {
        tree.add(4);
        tree.add(2);
        tree.add(7);
        tree.add(1);
        tree.add(-9);
        tree.add(15);
        tree.add(8);
        tree.add(5);

        int expected = -9;

        assertThat(tree.findMin().getData()).isEqualTo(expected);
    }

    @Test
    public void shouldFindMaxValue() {
        tree.add(4);
        tree.add(2);
        tree.add(7);
        tree.add(1);
        tree.add(-9);
        tree.add(15);
        tree.add(8);
        tree.add(5);

        int expected = 15;

        assertThat(tree.findMax().getData()).isEqualTo(expected);
    }

    @Test
    public void shouldReturnSizeOfTheTree() {
        tree.add(4);
        tree.add(2);
        tree.add(7);
        tree.add(1);
        tree.add(-9);
        tree.add(15);
        tree.add(8);
        tree.add(5);

        assertThat(tree.size()).isEqualTo(8);
    }

    @Test
    public void shouldReturnTrueForEmptyTree() {
        assertThat(tree.isEmpty()).isTrue();
    }

    @Test
    public void shouldReturnFalseForNotEmptyTree() {
        tree.add(4);
        tree.add(2);
        tree.add(7);
        tree.add(1);
        tree.add(-9);
        tree.add(15);
        tree.add(8);
        tree.add(5);

        assertThat(tree.isEmpty()).isFalse();
    }


}