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
        this.attachElement(this.rootParent.right, toAdd);
        this.size++;
        
    }

    /**
     * Removes a node by its key
     * @return removedNode
     * @param 
     */
    public Integer remove(String key) 
    {
        //if there are no nodes present
        if(this.size == 0)
        {
            return null;
        }

        //if such a node does not exist
        if(this.lookUpElement(this.rootParent.right, key) == null)
        {
            return null;
        }

        //if the only node is the root and the keys are the same
        if(this.size == 1 && this.rootParent.right.key.equals(key)) 
        {
            this.rootParent.right = null;
            this.size--;
        }

        //otherwise
        //find the node itself
        Node toRemove = this.lookUpElement(this.rootParent.right, key);
        Integer toRemoveValue = this.lookup(key);


        // 1) if it is a leaf - no children
        if(toRemove.left == null && toRemove.right == null)
        {
            //if this leaf is its parent's left child
            if(toRemove.parent.left.key.equals(key))
            {
                toRemove.parent.left = null;
            }

            //otherwise
            else
            {
                toRemove.parent.right = null;
            }
            this.size--;
        }

        // 2) if it has both children
        else if(toRemove.left != null && toRemove.right != null)
        {
            String newKey;
            Integer newValue;
            //the right node has no left child
            if(toRemove.right.left == null)
            {
                //sttoring the successor's pair data
                newKey = toRemove.right.key;
                newValue = toRemove.right.value;
                //removing the successor
                this.remove(newKey);
            }

            //otherwise
            else
            {
                newKey = this.findSuccessor(toRemove.right).key;
                newValue = this.findSuccessor(toRemove.right).value;
                this.remove(newKey);
            }

            //assigning stored values to our "toRemove"
            toRemove.key = newKey;
            toRemove.value = newValue;

            /**
             * Note: do not decrement this.size in this case
             * as it is done when remove is self-called
             */
        }

        // 3)has only one child
        //      -only left child
        else if(toRemove.left != null)
        {
            toRemove.left.parent = toRemove.parent;
            //if I am the left child of my parent
            if (toRemove.parent.left.key.equals(key))
            {
                toRemove.parent.left = toRemove.left;
            }
            else
            {
                toRemove.parent.right = toRemove.left;
            }
            this.size--;
        }

        //      -only right child
        else if(toRemove.right != null)
        {
            toRemove.right.parent = toRemove.parent;
            //if I am the left child of my parent
            if (toRemove.parent.left.key.equals(key))
            {
                toRemove.parent.left = toRemove.right;
            }
            else
            {
                toRemove.parent.right = toRemove.right;
            }
            this.size--;
        }

        //return the value of the removed node
        return toRemoveValue;
    }

    /**
     * Goes through the tree
     * and return a node by its key
     * @return lookedUpNode
     * @param
     */
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
        if(lookUpElement(this.rootParent.right, key) == null)
        {
            return null;
        }

        //otherwise return the value of the looked up key
        return lookUpElement(this.rootParent.right, key).value;
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
     * @return Node by its key
     * @param
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
     /**
      * finds the leftmost node 
      * in the right subtree of a node
      */
    private Node findSuccessor(Node startNode) {
        if(startNode.left == null)
        {
            return startNode;
        }

        return findSuccessor(startNode.left);
    }
}