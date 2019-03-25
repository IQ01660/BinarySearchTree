/**
 * @author Ikram Gabiyev
 * Notes:
 * 1) Remember throughout the code rootParent.right
 * is the ROOT of the tree (rootParent is its parent )
 */

public class BinarySearchTree 
{
    //the node referencing the root
    public Node rootParent;
    //the number of elements in tree
    private int size;

    //constructor
    public BinarySearchTree() 
    {
        this.rootParent = new Node(null, null);
        this.size = 0;
    }

    /**
     * adds the <key,value> to tree
     * if such a pair exists
     * it is replaced
     */
    public void add(String key, Integer value) 
    {
        //create the Node 
        Node toAdd = new Node(key, value);

        //if the size of the tree is 0
        if (this.size == 0) 
        {
            //the element is added to right
            //the rootParent will have just one child
            //so this is a random choice
            this.rootParent.right = toAdd;
            toAdd.parent = rootParent;
            this.size++;
            return;
        }

        //if such a pair exists already
        if (this.lookUpElement(this.rootParent.right, key) != null)
        {
            this.lookUpElement(this.rootParent.right, key).value = value;
        }

        //go through tree and attach
        //the elements to its newfound parent
        //rootParent
        this.attachElement(rootParent.right, toAdd);
        this.size++;
        
    }

    public Integer remove(String key) 
    {
        return null;
    }

    public Integer lookup(String key) 
    {
        //if the size of the tree is 0
        if(this.size == 0) 
        {
            return null;
        }

        //if size is 1
        if(this.size == 1) 
        {
            return this.rootParent.right.value;
        }

        //return null if no such key exists
        if(lookUpElement(rootParent.right, key) == null)
        {
            return null;
        }

        //otherwise return the value of the looked up key
        return lookUpElement(rootParent.right, key).value;
    }

    public void inOrderTraverse() {

    }


    /**
     * Helper recursive method for ADDING
     * elements to the tree
     */
    private void attachElement(Node currentNode, Node toAdd)
    {
        //if the toAdd is less than current Node
        if( currentNode.key.compareTo(toAdd.key) > 0 )
        {
            //stop if the currentNode's left child is null
            if(currentNode.left == null)
            {
                //attach the toAdd to as its left child
                currentNode.left = toAdd;
                toAdd.parent = currentNode;
                return;
            }

            //otherwise jump onto the left child
            this.attachElement(currentNode.left, toAdd);


        }
        //if the toAdd is larger than current Node
        if( currentNode.key.compareTo(toAdd.key) < 0 )
        {
            //stop if the currentNode's right child is null
            if(currentNode.right == null)
            {
                //attach the toAdd to as its right child
                currentNode.right = toAdd;
                toAdd.parent = currentNode;
                return;
            }

            //otherwise jump onto right child
            this.attachElement(currentNode.right, toAdd);
        }
    }

    /**
     * a helper method for LOOKUP
     * recursively walks through tree 
     * until finding the necessary element
     */

     private Node lookUpElement(Node currentNode, String givenKey)
     {

        //stop if you are at the right node
        if (currentNode.key.equals(givenKey)) 
        {
            return currentNode;
        }

        //check whether given key is smaller than the currentNode key
        else if ( currentNode.key.compareTo(givenKey) > 0 )
        {
            //stop if the left child of current node is null
            if(currentNode.left == null)
            {
                return null;
            }

            //jump onto the node on the left
            return this.lookUpElement(currentNode.left, givenKey);
        }

        //check whether given key is larger than the currentNode key
        else 
        {
            //stop if the right child of current node is null
            if(currentNode.right == null)
            {
                return null;
            }

            //jump onto the node on the right
            return this.lookUpElement(currentNode.right, givenKey);
        }
     }
}