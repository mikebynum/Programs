// Name         : Mike Bynum
// Class        : CSCI 1620-301
// Program #    : 8
// Due Date     : 10 Aug 2010
//
// Honor Pledege:   On my honor as a student of the University of Nebraska at
//                  Omaha, I have neither given nor received unauthorized help
//                  on this homework assignment.
//
// NAME:    Mike Bynum
// NUID:    343
// EMAIL:   mbynum@unomaha.edu
//
// Partners:    NONE
//
// Description: This program will have a user enter information and perform
//              various operations using a tree class.
class mbynum_TreeNode <T extends Comparable <T>>
{
    // package access members
    mbynum_TreeNode<T> leftNode; // left node
    T data; // node value
    mbynum_TreeNode<T> rightNode; // right node

    //---------------------------------------------------------
    //Method Name           :mbynum_TreeNode
    //Parameters            :item, a T object
    //Return Values         :none
    //Partners              :none
    //Description           :constructor initializes data and makes this
    //                       a leaf node
    public mbynum_TreeNode(T item)
    {
    data = item;
    leftNode = rightNode = null; // node has no children
    }
    // This is the only method defined for the TreeNode – this is different
    // from the text book implemention. Our insert() method is defined
    // as part of the Tree class.
}// end TreeNode class

public class mbynum_Tree <T extends Comparable<T>>
{
    private mbynum_TreeNode<T> root;

    //Method Name           :mbynum_Tree
    //Parameters            :none
    //Return Values         :none
    //Partners              :none
    //Description           :constructor that initializes variable root to null
    public mbynum_Tree()
    {
        root = null;
    }//end constructor

    //Method Name           :insertItem
    //Parameters            :item, a T object
    //Return Values         :none
    //Partners              :none
    //Description           :insert a new node in the binary search tree
    public void insertItem(T item)
    {
        insert(root, item);
    }//end insertItem

    //Method Name           :insert
    //Parameters            :item, a T object; tree, an object of
    //                       mbynum_TreeNode
    //Return Values         :none
    //Partners              :none
    //Description           :insert a new node in the binary search tree
    private void insert(mbynum_TreeNode<T> tree, T item)
    {
        if (root == null)
            root = new mbynum_TreeNode<T>(item);
        else if (item.compareTo(tree.data) < 0)
        {
            if (tree.leftNode == null)
                tree.leftNode = new mbynum_TreeNode<T>(item);
            else // continue searching left subtree
                insert(tree.leftNode, item);
        }
        else if (item.compareTo(tree.data) > 0)
        {
            if (tree.rightNode == null)
                tree.rightNode = new mbynum_TreeNode<T>(item);
            else
                insert(tree.rightNode, item);
        }
    }//end insert

    //Method Name           :countNodes
    //Parameters            :tree, a mbynum_TreeNode object
    //Return Values         :the number of nodes, an integer
    //Partners              :none
    //Description           :Recursive utility method to count the nodes in the
    //                       tree
    private int countNodes(mbynum_TreeNode<T> tree)
    {
        if (tree == null)
            return 0;
        else
            return countNodes(tree.leftNode) +
        countNodes(tree.rightNode) + 1;
    }//end countNodes

    //Method Name           :lengthIs
    //Parameters            :none
    //Return Values         :number of nodes, an integer
    //Partners              :none
    //Description           :Method to determine length of the tree. Calls
    //                       recursive method countNodes() to count the nodes in
    //                       the tree.
    public int lengthIs()
    {
        return countNodes(root);
    }//end lengthIs

    //Method Name           :inOrderHelper
    //Parameters            :node, a treeNode object
    //Return Values         :none
    //Partners              :none
    //Description           :recursive method to perfor inorder traversal
    private void inOrderHelper(mbynum_TreeNode<T> node)
    {
        if (node == null)
            return;

        inOrderHelper(node.leftNode); // traverse left
        // subtree
        System.out.printf("%s ", node.data); // output node
        // data
        inOrderHelper(node.rightNode); // traverse
        // right subtree
    }//end inOrderHelper

    //Method Name           :inOrderTraversal
    //Parameters            :none
    //Return Values         :none
    //Partners              :none
    //Description           :begin inorder traversal
    // begin inorder traversal
    public void inOrderTraversal()
    {
        if (root != null)
            inOrderHelper(root);
        else
            System.out.println("\nTree is Empty\n");
    }//end inOrderTraversal

    //Method Name           :postOrderHelper
    //Parameters            :node, a treeNode object
    //Return Values         :none
    //Partners              :none
    //Description           :recursive method to perfor postorder traversal
    private void postOrderHelper(mbynum_TreeNode<T> node)
    {
        if (node == null)
            return;

        postOrderHelper(node.leftNode); // traverse left
        // subtree
        postOrderHelper(node.rightNode); // traverse right
        // subtree
        System.out.printf("%s ", node.data); // output node
        // data
    }//end postOrderHelper

    //Method Name           :postOrderTraversal
    //Parameters            :none
    //Return Values         :none
    //Partners              :none
    //Description           :begin postOrderTraversal
    public void postOrderTraversal()
    {
        if (root != null)
            postOrderHelper(root);
        else
            System.out.println("\nTree is Empty\n");
    }//end postOrderTraversal

    //Method Name           :preOrderHelper
    //Parameters            :node, a treeNode object
    //Return Values         :none
    //Partners              :none
    //Description           :recursive method to perform preorder traversal
    private void preOrderHelper( mbynum_TreeNode<T> node )
    {
        if (node == null)
            return;

        System.out.printf("%s ", node.data); // output node
        // data
        preOrderHelper(node.leftNode); // traverse left
        // subtree
        preOrderHelper(node.rightNode); // traverse right
        // subtree
    }//end preOrderHelper

    //Method Name           :preOrderTraversal
    //Parameters            :none
    //Return Values         :none
    //Partners              :none
    //Description           :begin preorder traversal
    public void preOrderTraversal()
    {
        if (root != null)
            preOrderHelper(root);
        else
            System.out.println("\nTree is Empty\n");
    }//end preOrderTraversal

    //Method Name           :isEmpty
    //Parameters            :none
    //Return Values         :empty, a boolean
    //Partners              :none
    //Description           :returns boolean T/F whether list is empty
    public boolean isEmpty()
    {
        boolean empty=false;

        if(root!=null)
        {
            return empty;
        }
        else
            return !empty;
    }//end isEmpty

    //Method Name           :clear
    //Parameters            :none
    //Return Values         :none
    //Partners              :none
    //Description           :clears list
    public void clear()
    {
        root=null;
    }//end clear

    //Method Name           :findItem
    //Parameters            :none
    //Return Values         :boolean whether item was found
    //Partners              :none
    //Description           :calls private recursive method find() to search
    //                       for an item in the tree
    public boolean findItem(T item)
    {
        return find(root, item);
    }

    //Method Name           :find
    //Parameters            :item, a T object; tree, a treeNode object
    //Return Values         :a boolean whether item was found
    //Partners              :none
    //Description           :recursively searches tree for item
    private boolean find(mbynum_TreeNode<T> tree, T item )
    {
        boolean found = false;

        if (tree == null)
            found = false; // item is not
            // found.
        else if (item.compareTo(tree.data) < 0)
            found = find(tree.leftNode, item); // Search left
            // subtree.
        else if (item.compareTo(tree.data) > 0)
            found = find(tree.rightNode, item); // Search right
            // subtree.
        else
            found = true;
        return found;
    }//end find

    //Method Name           :deleteItem
    //Parameters            :item, a T object
    //Return Values         :none
    //Partners              :none
    //Description           :calls upon deleteNode to delete a paticular node
    public void deleteItem(T item)
    {
        root = deleteNode(root, item);
    }//end deleteItem

    //Method Name           :deleteNode
    //Parameters            :item, a T object; tree, a treeNode object
    //Return Values         :a treeNode object, the item deletede
    //Partners              :none
    //Description           :Method to delete node in tree
    private mbynum_TreeNode<T> deleteNode(mbynum_TreeNode<T> tree, T item)
    {
        if (tree != null)
        {
            // if item < tree.data - search in left subtree
            if (item.compareTo(tree.data) < 0)
                tree.leftNode = deleteNode(tree.leftNode, item);
            // else if item > tree.data: search in right subtree
            else if (item.compareTo(tree.data) > 0)
                tree.rightNode = deleteNode(tree.rightNode, item);
            // else if item found - remove the value from the tree
            else if (item.compareTo(tree.data) == 0)
            {
                // cases 1 and 2: Left child is (or both children are) null
                // is null. Set tree = right subtree (promote right subtree)
                if (tree.leftNode == null)
                {
                    tree = tree.rightNode;
                }
                // case 3: Right child is null
                // set tree = left subtree (promote left subtree)
                else if (tree.rightNode == null)
                {
                    tree = tree.leftNode;
                }
                // case 4: both children are non-null
                else
                {
                    // find the logical predecessor's value from the left
                    // tree by moving left once, then right until no right
                    // child exists - this is the predecessor's value
                    mbynum_TreeNode<T> tempNode = tree.leftNode;
                    while (tempNode.rightNode != null)
                        tempNode = tempNode.rightNode;
                    // replace value to be removed with logical predecessor
                        tree.data= tempNode.data;
                    // use recursion to call deleteNode() to remove the
                    // promoted value's node
                    // remove predecessor's node from left subtree and
                    // reassign tree.leftNode to new subtree
                        tree.leftNode = deleteNode(tree.leftNode, tempNode.data);
                }//end else
            }//end else if
        }//end if
    // Return tree reference (if the item was not found then the effect
    // will be to do thing.
        return tree;
    }//end deleteNode

    //Method Name           :treeFormatTraversal
    //Parameters            :indentSpaces, an integer
    //Return Values         :none
    //Partners              :none
    //Description           :calls upon treeFormatHelper to print list
    public void treeFormatTraversal(Integer indentSpaces)
    {
        treeFormatHelper(root, indentSpaces >=0 ? indentSpaces : 0);
    }//end treeFormatTraversal

    //Method Name           :treeFormatHelper
    //Parameters            :tree, a treeNode object; indentSpaces, an integer
    //Return Values         :none
    //Partners              :none
    //Description           :prints the tree in a specified format
    private void treeFormatHelper(mbynum_TreeNode<T> tree, Integer indentSpaces)
    {
        // recursively print right branch, then left
      if ( tree != null )
      {
         treeFormatHelper( tree.rightNode, indentSpaces + 5 );

         for ( int k = 0; k < indentSpaces; k++ )
         {
            System.out.print( " " );
         }

         System.out.println( tree.data );
         treeFormatHelper( tree.leftNode, indentSpaces + 5 );
      } // end if
   } // end treeFormatHelper
}//end class
