public class Tester 
{
    public static void main(String[] args)
    {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add("d", 12);
        bst.add("f", 19);
        bst.add("m", 32);
        bst.add("g", 22);
        bst.add("k", 45);
        bst.add("a", 1);
        bst.add("e", 12);
        bst.add("h", 19);
        bst.add("c", 32);
        bst.add("i", 22);
        bst.add("b", 45);
        bst.add("n", 1);
        bst.add("j", 12);
        bst.add("n", 19);
        bst.add("l", 32);
        bst.add("k", 22);
        bst.add("f", 45);
        bst.add("e", 1);

        bst.inOrderTraverse();
    }
}