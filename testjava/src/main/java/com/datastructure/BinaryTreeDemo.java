package com.datastructure;
//https://www.geeksforgeeks.org/write-an-efficient-c-function-to-convert-a-tree-into-its-mirror-tree/

public class BinaryTreeDemo {

    public static void main(String[] arg ) {

        /* creating a binary tree and entering the nodes */
        BinaryTree bt = new BinaryTree();
        bt.root = new Node(1);

        bt.root.left = new Node(2);
        bt.root.right = new Node(3);

        bt.root.left.left = new Node(4);
        bt.root.left.right = new Node(5);

        /* print inorder traversal of the input tree */
        System.out.println("In Order traversal of input tree :");
        bt.inOrder();
        System.out.println(" ");

        // convert tree to its mirror
        bt.mirror();
        /* print inorder traversal of the minor tree */
        System.out.println("Inorder traversal of binary tree is : ");
        bt.inOrder();
    }
}

class BinaryTree{
    Node root;

    void mirror() {
        root = mirror(root);
    }


    private Node mirror(Node root) {
        if (root == null )
            return root;

        // do the subtrees
        Node left = mirror(root.left);
        Node right = mirror(root.right);

        root.left = right;
        root.right = left;

        return root;
    }

    void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node root) {
        if (root == null)
            return;

        inOrder(root.left);
        System.out.print(root.data+" ");
        inOrder(root.right);
    }

}
class Node{
    int data;
    Node left,right;
    public Node(int item) {
        data = item;
        left = right = null;
    }
}