package datastructures;

import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

public class AVLTree<T extends Comparable<T>> implements Iterable<T> {

    private Node rootNode;

    private int nodeCount;

    public boolean insert(T elem) {
        if (elem == null)
            return false;
        rootNode = insert(rootNode, elem);
        nodeCount++;
        return true;
    }

    private Node insert(Node node, T value) {
        if (node == null)
            return new Node(value);

        if (value.compareTo(node.data) < 0)
            node.leftChild = insert(node.leftChild, value);
        else if (value.compareTo(node.data) > 0)
            node.rightChild = insert(node.rightChild, value);
        else return node;

        update(node);

        return balance(node);
    }

    public boolean remove(T value) {

        if (value == null)
            return false;

        rootNode = remove(rootNode, value);
        nodeCount--;
        return true;
    }

    private Node remove(Node node, T value) {
        if (node == null) return null;

        int comparison = value.compareTo(node.data);

        if (comparison < 0)
            node.leftChild = remove(node.leftChild, value);
        else if (comparison > 0)
            node.rightChild = remove(node.rightChild, value);
        else {
            if (node.leftChild == null) {
                return node.rightChild;
            } else if (node.rightChild == null) {
                return node.leftChild;
            } else {
                if (node.leftChild.subtreeHeight > node.rightChild.subtreeHeight) {
                    T nextValue = findMin(node.leftChild).data;
                    node.data = nextValue;

                    node.leftChild = remove(node.leftChild, nextValue);
                } else {
                    T nextValue = findMin(node.rightChild).data;
                    node.data = nextValue;

                    node.rightChild = remove(node.rightChild, nextValue);
                }
            }
        }

        update(node);

        return balance(node);
    }

    public void find() {

    }

    public Node findMin(Node node) {
        while (node.leftChild != null)
            node = node.leftChild;
        return node;
    }

    private Node balance(Node node) {

        if (node.balance == -2) {
            if (node.leftChild.balance <= 0)
                return balanceLeft(node);
            else
                return balanceLeftRight(node);
        } else if (node.balance == 2) {
            if (node.rightChild.balance >= 0)
                return balanceRight(node);
            else
                return balanceRightLeft(node);
        }

        return node;
    }

    //L
    public Node balanceLeft(Node node) {
        return rotateRight(node);
    }

    //R
    public Node balanceRight(Node node) {
        return rotateLeft(node);
    }

    //LR left rotation on right child then right rotation on node
    public Node balanceLeftRight(Node node) {
        node.leftChild = rotateLeft(node.leftChild);
        return rotateLeft(node);
    }

    //RL right rotation on left child then left rotation on node
    public Node balanceRightLeft(Node node) {
        node.rightChild = rotateRight(node.rightChild);
        return rotateRight(node);
    }

    public Node rotateLeft(Node node) {
        Node newSubtreeRoot = node.rightChild;
        node.rightChild = newSubtreeRoot.leftChild;
        newSubtreeRoot.leftChild = node;
        update(node);
        update(newSubtreeRoot);
        return newSubtreeRoot;
    }

    public Node rotateRight(Node node) {
        Node newSubtreeRoot = node.leftChild;
        node.leftChild = newSubtreeRoot.rightChild;
        newSubtreeRoot.rightChild = node;
        update(node);
        update(newSubtreeRoot);
        return newSubtreeRoot;
    }

    public int treeHeight() {
        if (rootNode != null)
            return rootNode.subtreeHeight;
        return 0;
    }

    public int size() {
        return nodeCount;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(T value) {
        return contains(rootNode, value);
    }

    private boolean contains(Node node, T value) {
        if (node == null)
            return false;

        int comparison = value.compareTo(node.data);

        if (comparison < 0)
            return contains(node.leftChild, value);
        else if (comparison > 0)
            return contains(node.rightChild, value);
        else
            return true;
    }

    private void update(Node node) {
        //update subtrees height
        int leftSubtreeHeight = (node.leftChild == null) ? -1 : node.leftChild.subtreeHeight;
        int rightSubtreeHeight = (node.rightChild == null) ? -1 : node.rightChild.subtreeHeight;

        //update node height
        node.subtreeHeight = 1 + Math.max(leftSubtreeHeight, rightSubtreeHeight);

        //update balance of subtree
        node.balance = rightSubtreeHeight - leftSubtreeHeight;
    }

    @Override
    public Iterator<T> iterator() {
        return inOrderStream().map(Node::getData).iterator();
    }

    public Stream<Node> inOrderStream() {
        return rootNode.inOrder();
    }

    public class Node {
        //-2 -> left heavy
        //-1 -> left
        //0 -> equal
        //+1 -> right
        //+2 -> right heavy
        private int balance;
        private Node leftChild, rightChild;
        private T data;
        private int subtreeHeight;

        public Node(T data) {
            this.data = data;
            this.leftChild = this.rightChild = null;
            this.balance = 0;
        }

        public T getData() {
            return this.data;
        }

        public Optional<Node> getLeftChild() {
            return Optional.ofNullable(leftChild);
        }

        public Optional<Node> getRightChild() {
            return Optional.ofNullable(rightChild);
        }

        public Stream<Node> inOrder() {
            return Stream.concat(
                    this.getLeftChild().map(Node::inOrder).orElse(Stream.empty()),
                    Stream.concat(Stream.of(this),
                            this.getRightChild().map(Node::inOrder).orElse(Stream.empty()))
            );
        }
    }
}
