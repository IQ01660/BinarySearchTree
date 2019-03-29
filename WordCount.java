import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class WordCount
{
    public static void main(String[] args)
    {
        //declaring a BinarySearchTree
        BinarySearchTree bst = new BinarySearchTree();

        //the file to be read
        File prideJane = new File("pride.txt");
        
        try {
            //the scanner reading the file
            Scanner textScanner = new Scanner(prideJane);

            /**
             * NOTE: REGULAR EXPRESSION
             */
            textScanner.useDelimiter("(\\W*\\s+\\W*)|--");
            while(textScanner.hasNext())
            {
                //storing the next word
                String word = textScanner.next().toLowerCase();

                //if such a key is not in the tree
                if (bst.lookup(word) == null)
                {
                    //add it with value - "1"
                    bst.add(word, 1);
                }

                //if it exists in the tree
                //override its value
                else 
                {
                    Integer count = bst.lookup(word);
                    count++;
                    bst.add(word, count);
                }
            }
           
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //printing all <key, value> pairs
        bst.inOrderTraverse();
    }
}