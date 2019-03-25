public class Tester 
{
    public static void main(String[] args)
    {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add("j", 12);
        bst.add("f", 19);
        bst.add("m", 32);
        bst.add("g", 22);
        bst.add("k", 45);
        bst.add("m", 1);

        System.out.println(bst.rootParent.right.key);
        System.out.println(bst.rootParent.right.left.key);
        System.out.println(bst.rootParent.right.right.key);
        //System.out.println(bst.rootParent.right.left.left.key);
        System.out.println(bst.rootParent.right.left.right.key);
        System.out.println(bst.rootParent.right.right.left.key);
        //System.out.println(bst.rootParent.right.right.right.key);

        System.out.println("-----");
        System.out.println(bst.lookup("m"));
    }
}