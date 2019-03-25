public class Node 
{
    //the key of the node
    public String key;

    //the value of the node
    public Integer value;

    //the parent of the node
    public Node parent;

    //the left child
    public Node left;

    //the right child
    public Node right;

    public Node(String _key, Integer _value) 
    {
        this.key = _key;
        this.value = _value;
    }

}