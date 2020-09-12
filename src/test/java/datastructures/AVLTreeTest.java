package datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AVLTreeTest {

    private AVLTree<Integer> tree;

    @BeforeEach
    public void init() {
        tree = new AVLTree<Integer>();
    }

    @Test
    public void shouldSuccessfullyInsertFirstNode() {
        assertThat(tree.insert(1)).isEqualTo(true);
    }

    @Test
    public void shouldNotInsertNull() {
        assertThat(tree.insert(null)).isEqualTo(false);
    }

    @Test
    public void testAlreadyBalancedCase() {
        tree.insert(2);
        tree.insert(1);
        tree.insert(3);

        assertThat(tree.getRootNode().getData()).isEqualTo(2);
        assertThat(tree.getRootNode().getLeftChild().get().getData()).isEqualTo(1);
        assertThat(tree.getRootNode().getRightChild().get().getData()).isEqualTo(3);

        assertThat(tree.getRootNode().getLeftChild().get().getLeftChild().isEmpty()).isTrue();
        assertThat(tree.getRootNode().getLeftChild().get().getRightChild().isEmpty()).isTrue();
        assertThat(tree.getRootNode().getRightChild().get().getLeftChild().isEmpty()).isTrue();
        assertThat(tree.getRootNode().getRightChild().get().getRightChild().isEmpty()).isTrue();
    }

    @Test
    public void testLeftBalance() {
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);

        assertThat(tree.getRootNode().getData()).isEqualTo(2);
        assertThat(tree.getRootNode().getLeftChild().get().getData()).isEqualTo(1);
        assertThat(tree.getRootNode().getRightChild().get().getData()).isEqualTo(3);

        assertThat(tree.getRootNode().getLeftChild().get().getLeftChild().isEmpty()).isTrue();
        assertThat(tree.getRootNode().getLeftChild().get().getRightChild().isEmpty()).isTrue();
        assertThat(tree.getRootNode().getRightChild().get().getLeftChild().isEmpty()).isTrue();
        assertThat(tree.getRootNode().getRightChild().get().getRightChild().isEmpty()).isTrue();
    }

    @Test
    public void testRightBalance() {
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);

        assertThat(tree.getRootNode().getData()).isEqualTo(2);
        assertThat(tree.getRootNode().getLeftChild().get().getData()).isEqualTo(1);
        assertThat(tree.getRootNode().getRightChild().get().getData()).isEqualTo(3);

        assertThat(tree.getRootNode().getLeftChild().get().getLeftChild().isEmpty()).isTrue();
        assertThat(tree.getRootNode().getLeftChild().get().getRightChild().isEmpty()).isTrue();
        assertThat(tree.getRootNode().getRightChild().get().getLeftChild().isEmpty()).isTrue();
        assertThat(tree.getRootNode().getRightChild().get().getRightChild().isEmpty()).isTrue();
    }

    @Test
    public void testLeftRightBalance() {
        tree.insert(3);
        tree.insert(1);
        tree.insert(2);

        assertThat(tree.getRootNode().getData()).isEqualTo(2);
        assertThat(tree.getRootNode().getLeftChild().get().getData()).isEqualTo(1);
        assertThat(tree.getRootNode().getRightChild().get().getData()).isEqualTo(3);

        assertThat(tree.getRootNode().getLeftChild().get().getLeftChild().isEmpty()).isTrue();
        assertThat(tree.getRootNode().getLeftChild().get().getRightChild().isEmpty()).isTrue();
        assertThat(tree.getRootNode().getRightChild().get().getLeftChild().isEmpty()).isTrue();
        assertThat(tree.getRootNode().getRightChild().get().getRightChild().isEmpty()).isTrue();
    }

    @Test
    public void testRightLeftBalance() {
        tree.insert(1);
        tree.insert(3);
        tree.insert(2);

        assertThat(tree.getRootNode().getData()).isEqualTo(2);
        assertThat(tree.getRootNode().getLeftChild().get().getData()).isEqualTo(1);
        assertThat(tree.getRootNode().getRightChild().get().getData()).isEqualTo(3);

        assertThat(tree.getRootNode().getLeftChild().get().getLeftChild().isEmpty()).isTrue();
        assertThat(tree.getRootNode().getLeftChild().get().getRightChild().isEmpty()).isTrue();
        assertThat(tree.getRootNode().getRightChild().get().getLeftChild().isEmpty()).isTrue();
        assertThat(tree.getRootNode().getRightChild().get().getRightChild().isEmpty()).isTrue();
    }

    @Test
    public void testInOrder() {
        tree.insert(1);
        tree.insert(3);
        tree.insert(2);
        tree.insert(5);
        tree.insert(7);
        tree.insert(12);
        tree.insert(8);

        int[] expected = {1, 2, 3, 5, 7, 8, 12};

        List<Integer> expectedList = new ArrayList<>();

        tree.forEach(expectedList::add);

        int[] actual = expectedList.stream().mapToInt(item -> item).toArray();

        assertThat(actual).isEqualTo(expected);
    }


}