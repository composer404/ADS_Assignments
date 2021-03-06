package tree;

import java.util.ArrayList;

public class BinarySearchTree extends BinaryTree {

    public BinarySearchTree(BinaryTreeNode root) {
        super(root);
    }


    public void insert(int element) {
        insertRecursive(getRoot(), new BinaryTreeNode(element));
    }

    public void removeElement(int element) {
         removeElement(getRoot(), element);
    }

    public int findMin() {
        BinaryTreeNode node = getRoot();
        if (node == null) {
            return -1;
        }

        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        return node.getElement();
    }

    public int findMax() {
        BinaryTreeNode node = getRoot();
        if (node == null) {
            return -1;
        }

        while (node.getRightChild() != null) {
            node = node.getRightChild();
        }
        return node.getElement();
    }


    public void rebalance() {
        int end = inOrder().size() - 1;
        setRoot(rebalanceRecursive(inOrder(), 0, end));
    }

    private BinaryTreeNode rebalanceRecursive(ArrayList<Integer> inOrder, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        BinaryTreeNode node = new BinaryTreeNode(inOrder.get(mid));

        node.addLeftChild(rebalanceRecursive(inOrder, start, mid - 1));

        node.addRightChild(rebalanceRecursive(inOrder, mid + 1, end));
        return node;
    }


    private void insertRecursive(BinaryTreeNode node, BinaryTreeNode newNode) {
        if (node == null) {
            setRoot(newNode);
            return;
        }

        if (node.getElement() < newNode.getElement()) {
            if (node.getRightChild() == null) {
                node.addRightChild(newNode);
                return;
            }
            insertRecursive(node.getRightChild(), newNode);
        }

        if (node.getElement() > newNode.getElement()) {
            if (node.getLeftChild() == null) {
                node.addLeftChild(newNode);
                return;
            }
            insertRecursive(node.getLeftChild(), newNode);
        }
    }

    private BinaryTreeNode removeElement(BinaryTreeNode root, int element) { ;
        if(root == null)
            return null;
        if(element < root.getElement()) {
            root.addLeftChild(removeElement(root.getLeftChild(), element));
        }
        else if(element > root.getElement()) {
            root.addRightChild(removeElement(root.getRightChild(), element));
        }
        else
        {
            if(root.getLeftChild() == null && root.getRightChild() == null) {
                root = null;
            }
            else if(root.getLeftChild() != null && root.getRightChild() != null)
            {
                int successor = findMinForRemove(root.getRightChild());
                root.setElement(successor);

                root.addRightChild(removeElement(root.getRightChild(), successor));
            }
            else if(root.getLeftChild() != null && root.getRightChild() == null)
                root = root.getLeftChild();
            else if(root.getRightChild() != null && root.getLeftChild() == null)
                root = root.getRightChild();
        }
        return root;
    }

    private int findMinForRemove(BinaryTreeNode node){
        if (node == null)
            return Integer.MAX_VALUE;

        int r = node.getElement();
        int lr = findMinForRemove(node.getLeftChild());
        int rr = findMinForRemove(node.getRightChild());

        if (lr < r)
            r = lr;
        if (rr < r)
            r = rr;
        return r;
    }
}



















