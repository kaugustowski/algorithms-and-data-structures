package datastructures;

import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

public class BinarySearchTree<T extends Comparable<T>> implements Iterable<T> {

    private Node rootNode;

    private int numberOfNodes;

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return numberOfNodes;
    }

    public boolean add(T newElem) {
        if (newElem == null)
            return false;
        rootNode = addNode(rootNode, newElem);
        return true;
    }

    private Node addNode(Node node, T elem) {

        if (node == null) {
            node = new Node(elem, null, null);
            numberOfNodes++;
        } else {
            if (elem.compareTo(node.data) < 0)
                node.leftChild = addNode(node.leftChild, elem);
            else if (elem.compareTo(node.data) > 0)
                node.rightChild = addNode(node.rightChild, elem);
        }

        return node;
    }

    public boolean contains(T elem) {
        return contains(rootNode, elem);
    }

    private boolean contains(Node node, T elem) {

        if (node == null)
            return false;

        int comparison = elem.compareTo(node.data);

        if (comparison < 0)
            return contains(node.leftChild, elem);
        else if (comparison > 0)
            return contains(node.rightChild, elem);
        else
            return true;
    }

    public boolean remove(T elem) {
        if (contains(elem)) {
            rootNode = remove(rootNode, elem);
            return true;
        }
        return false;
    }

    private Node remove(Node node, T elem) {

        int comparison = elem.compareTo(node.data);

        if (comparison < 0)
            node.leftChild = remove(node.leftChild, elem);
        else if (comparison > 0)
            node.rightChild = remove(node.rightChild, elem);
        else {
            if (node.leftChild == null) {
                return node.rightChild;
            } else if (node.rightChild == null) {
                return node.leftChild;
            }
            //swap with min node in right subtree (alternatively with max node in left subtree)
            else {
                Node tempNode = findMin(node.rightChild);

                node.data = tempNode.data;

                node.rightChild = remove(node.rightChild, tempNode.data);
            }
        }

        return node;
    }

    public Node findMin() {
        return findMin(rootNode);
    }

    private Node findMin(Node node) {
        while (node.leftChild != null)
            node = node.leftChild;
        return node;
    }

    public Node findMax() {
        return findMax(rootNode);
    }

    private Node findMax(Node node) {
        while (node.rightChild != null)
            node = node.rightChild;
        return node;
    }


    Stream<Node> inOrderStream() {
        return rootNode.inOrder();
    }

    @Override
    public Iterator<T> iterator() {
        return inOrderStream().map(Node::getData).iterator();
    }

    class Node {
        T data;
        Node leftChild;
        Node rightChild;

        public Node(T data) {
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;
        }

        public Node(T data, Node leftChild, Node rightChild) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public T getData() {
            return data;
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

        public Stream<Node> preOrder() {
            return Stream.concat(
                    Stream.of(this),
                    Stream.concat(
                            this.getLeftChild().map(Node::inOrder).orElse(Stream.empty()),
                            this.getRightChild().map(Node::inOrder).orElse(Stream.empty()))
            );
        }

        public Stream<Node> postOrder() {
            return Stream.concat(
                    this.getLeftChild().map(Node::inOrder).orElse(Stream.empty()),
                    Stream.concat(
                            this.getRightChild().map(Node::inOrder).orElse(Stream.empty()),
                            Stream.of(this))
            );
        }
    }

}
