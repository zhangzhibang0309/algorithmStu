package com.catalina.BST;

import com.catalina.BST.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.zip.CheckedOutputStream;

public class BinarySearchTree<E> implements BinaryTreeInfo {
    private int size;
    private Node<E> root;
    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return false;
    }

    public void clear() {

    }

    public void add(E element) {
        elementNotNullCheck(element);

        // 添加第一个节点
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }

        // 添加的不是第一个节点
        // 记录一下根节点
        Node<E> parent = root;
        Node<E> node = root;
        int cmp = 0;
        while (node != null) {
            parent = node;
            cmp = compare(element, node.element);
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                // 当新添加的element和旧的相等的时候
                // 但是为什么要多此一举呢 如果element是一个普通的对象 除了你比较的元素 特可能还有name等其他元素 如果直接return 那么这个新的element就被扔掉了 这样可以起到一个新对象覆盖就对象的作用
                node.element = element;
                return;
            }
        }

        // 跑完之后
        Node<E> newNode = new Node<>(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
    }

    public void remove(E element) {

    }

    public boolean contains(E element) {
        return false;
    }

    /**
     * 前序遍历
     */
// 这种写法比较死
//    public void preorderTraversal() {
//        preorderTraversal(root);
//    }
//
//    private void preorderTraversal(Node<E> node) {
//        if (node == null) return;
//
//        System.out.println(node.element);
//        preorderTraversal(node.left);
//        preorderTraversal(node.right);
//    }
    // 这种传个visitor方法过来，就可以进行任意操作
    public void preorder(Visitor<E> visitor) {
        if(visitor == null) return;
        preorder(root,visitor);
    }

    public void preorder(Node<E> node,Visitor<E> visitor) {
        if (node == null) return;

        visitor.visit(node.element);
        preorder(node.left,visitor);
        preorder(node.right,visitor);
    }

    /**
     * 中序遍历
     */
//    public void inorderTraversal() {
//        inorderTraversal(root);
//    }
//
//    private void inorderTraversal(Node<E> node) {
//        if (node == null) return;
//
//        // 中序遍历只要根节点在中间输出就可以 可以左中右 也可以右中左
//        // 所以对于二叉搜索树来说 中序遍历元素输出顺序是升序或者降序
//        inorderTraversal(node.left);
//        System.out.println(node.element);
//        inorderTraversal(node.right);
//    }

    public void inorder(Visitor<E> visitor) {
        if(visitor == null) return;

        inorder(root,visitor);
    }

    public void inorder(Node<E> node,Visitor<E> visitor) {
        if (node == null) return;

        inorder(node.left,visitor);
        visitor.visit(node.element);
        inorder(node.right,visitor);
    }


    /**
     * 后序遍历
     */
//    public void postorderTraversal() {
//        postorderTraversal(root);
//    }
//
//    private void postorderTraversal(Node<E> node) {
//        if (node == null) return;
//
//        // 左右中 右左中
//        postorderTraversal(node.left);
//        postorderTraversal(node.right);
//        System.out.println(node.element);
//    }

    public void postorder(Visitor<E> visitor) {
        if(visitor == null) return;

        postorder(root,visitor);
    }

    public void postorder(Node<E> node,Visitor<E> visitor) {
        if (node == null) return;

        postorder(node.left,visitor);
        postorder(node.right,visitor);
        visitor.visit(node.element);

    }

    /**
     * 层序遍历
     */
//    public void levelOrderTraversal() {
//        if (root == null) return;
//
//        Queue<Node<E>> queue = new LinkedList<>();
//        queue.offer(root);
//
//        while (!queue.isEmpty()) {
//            // 出队
//            Node<E> node = queue.poll();
//            System.out.println(node.element);
//
//            if (node.left != null) {
//                queue.offer(node.left);
//            }
//            if (node.right != null) {
//                queue.offer(node.right);
//            }
//        }
//
//    }

    public void levelOrder(Visitor<E> visitor) {
        if (root == null || visitor == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            // 出队
            Node<E> node = queue.poll();
            if (visitor.visit(node.element)) return;

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    // 这个内部接口 是三种遍历都要用到的
    public static interface Visitor<E> {
        boolean visit(E element);
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    public int compare(E e1, E e2) {
        if (comparator != null) return this.comparator.compare(e1, e2);

        return ((Comparable<E>) e1).compareTo(e2);
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>) node).element;
    }

    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }

}
