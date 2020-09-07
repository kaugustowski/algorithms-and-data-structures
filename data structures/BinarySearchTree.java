import java.util.Iterator;

public class BinarySearchTree <T extends Comparable<T>> {

    private Node rootNode;

    private int numberOfNodes;

    private class Node {
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
    }

    public boolean isEmpty(){
        return size()==0;
    }

    public int size(){
        return numberOfNodes;
    }

    public boolean add(T newElem){

        rootNode=addNode(rootNode, newElem);
        return true;
    }

    private Node addNode(Node node, T elem){

        if(node == null){
            node = new Node(elem, null,null);
            numberOfNodes++;
        }

        else {
            if(elem.compareTo(node.data)<0)
                node.leftChild= addNode(node.leftChild,elem);
            else
                node.rightChild= addNode(node.rightChild,elem);
        }

        return node;
    }

    public boolean contains(T elem){
        return contains(rootNode,elem);
    }

    private boolean contains(Node node, T elem){

        if(node==null)
            return false;

        int comparison = elem.compareTo(node.data);

        if (comparison<0)
            return contains(node.leftChild,elem);
        else if (comparison>0)
            return contains(node.rightChild,elem);
        else
            return true;

    }

    public boolean remove(T elem){

        if(contains(elem)){
            rootNode=remove(rootNode,elem);
            return true;
        }
        return false;
    }

    private Node remove(Node node, T elem){

        int comparison = elem.compareTo(node.data);

        if(comparison<0)
            node.leftChild=remove(node.leftChild, elem);
        else if(comparison>0)
            node.rightChild=remove(node.rightChild, elem);
        else{
            if (node.leftChild==null){
                return node.rightChild;
//                node=node.rightChild;
//                return node;
            }
            else if (node.rightChild==null){
                return node.leftChild;
//                node = node.leftChild;
//                return node;
            }
            //swap with min node in right subtree (alternatively with max node in left subtree)
            else {
                Node tempNode = findMin(node.leftChild);

                node.data = tempNode.data;

                node.rightChild = remove(node.rightChild, tempNode.data);
            }
        }

        return node;
    }

    public Node findMin(Node node){
        while (node.leftChild!=null)
            node = node.leftChild;
        return node;
    }

    public Node findMax(Node node){
        while (node.rightChild!=null)
            node = node.rightChild;
        return node;
    }

    public Node nextLarger(T elem){
        return null;

    }


    //TODO
    public Iterator<T> inOrder(){

        int nodeCount=0;

        return new Iterator<T>() {

            Node currentNode=rootNode;

            @Override
            public boolean hasNext() {
                return (nodeCount<numberOfNodes);
            }

            @Override
            public T next() {

                Node nextNode;

                while (currentNode.leftChild!=null)
                if (currentNode.leftChild!=null)
                    nextNode=currentNode.leftChild;


                return currentNode.data;
            }
        }
    }

}
