public class Main {
    public static void main(String[] args) {
        var tree = new BinarySearchTree<Integer>();
        tree.add(8);
        tree.add(4);
        tree.add(15);
        tree.add(3);
        tree.add(7);
        tree.add(12);
        tree.add(23);


        tree.inOrderStream()
                .map(BinarySearchTree.Node::getData)
                .forEach(System.out::println);
        System.out.println();

        tree.remove(15);


        tree.inOrderStream()
                .map(BinarySearchTree.Node::getData)
                .forEach(System.out::println);
    }
}
