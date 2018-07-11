package com.zlikun.jee.structure.tree;

import lombok.Data;

import java.util.*;
import java.util.function.Consumer;

/**
 * 二叉树搜索树，本例二叉树遍历方式为中根序遍历(LDR)，即：左子节点 < 根节点 < 右子节点<br>
 * 实现 java.lang.Iterable 接口，使二叉树可遍历，按中序遍历
 *
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2018/7/11 17:01
 */
public class BinarySearchTree<T extends Comparable<T>> implements Iterable<T> {

    /**
     * 节点类
     *
     * @param <T>
     */
    @Data
    private class Node<T> {

        private T data;
        private Node left;
        private Node right;

        public Node(T data) {
            this.data = data;
        }

    }

    /**
     * 根节点
     */
    Node<T> root;

    /**
     * 插入子节点
     *
     * @param data
     */
    public void insert(T data) {
        if (data == null) {
            return;
        }
        Node<T> node = new Node<>(data);
        if (root == null) {
            root = node;
        } else {
            insertNode(root, node);
        }
    }

    /**
     * 因为是排序二叉树，所以在插入新节点时，要比较插入节点值大小，
     *
     * @param node
     * @param newNode
     */
    private void insertNode(Node<T> node, Node<T> newNode) {
        if (newNode == null || newNode.getData() == null) {
            return;
        }
        if (newNode.getData().compareTo(node.getData()) < 0) {
            // 如果新节点值小于原节点值，需要将新值插入到旧节点的左子节点位上
            if (node.getLeft() == null) {
                // 如果旧节点左子节点不存在，直接将新节点作为其左子节点
                node.setLeft(newNode);
            } else {
                // 如果旧节点左子节点存在，则递归调用插入节点方法，向下搜索并插入节点
                insertNode(node.getLeft(), newNode);
            }
        } else {
            // 如果新节点值大于等于原节点值，需要将新值插入到旧节点的右子节点上
            if (node.getRight() == null) {
                node.setRight(newNode);
            } else {
                insertNode(node.getRight(), newNode);
            }
        }

    }

    /**
     * 删除指定值节点，删除节点时，存在三种情况：
     * 1. 没有左子树(只有右子树)：
     * 2. 没有右子树(只有左子树)：直接使指定节点的父节点指向指定节点的左子树即可
     * 3. 左右子树都有：
     *
     * @param data
     */
    public void remove(T data) {
        this.removeNode(this.root, data);
    }

    private Node<T> removeNode(Node<T> node, T data) {
        if (node == null) {
            return null;
        }
        if (data.compareTo(node.getData()) < 0) {
            // 如果删除节点值小于给定节点，那么其位于给定节点的左子树上
            node.setLeft(removeNode(node.getLeft(), data));
            return node;
        } else if (data.compareTo(node.getData()) > 0) {
            // 如果删除节点值大于给定节点，那么其位于给定节点的右子树上
            node.setRight(removeNode(node.getRight(), data));
            return node;
        } else {
            // 如果删除节点的值等于给定节点，那么删除节点即为给定节点
            if (node.getLeft() == null && node.getRight() == null) {
                node = null;
            } else if (node.getLeft() == null) {
                // 只有右子树
                node = node.getRight();
            } else if (node.getRight() == null) {
                // 只有左子树
                node = node.getLeft();
            } else {
                // 左右子树都有，找到该节的右子树里最小节点(最左子节点)，将其值代替删除节点，将最小节点删除即可
                Node<T> min = findMinNode(node.getRight());
                node.setData(min.getData());
                node.setRight(removeNode(node.getRight(), min.getData()));
            }
            return node;

        }
    }

    /**
     * 查找给定节点的最小子节点(最左子节点)
     *
     * @param node
     * @return
     */
    private Node<T> findMinNode(Node<T> node) {
        if (node == null) {
            return null;
        }
        Node<T> cursor = node;
        while (cursor.getLeft() != null) {
            cursor = cursor.getLeft();
        }
        return cursor;
    }

    /**
     * @return
     * @see AbstractList#iterator()
     */
    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            private Stack<Node<T>> stack = new Stack<>();
            private Node<T> cursor = root;

            @Override
            public boolean hasNext() {
                return !stack.isEmpty() || cursor != null;
            }

            @Override
            public T next() {

                while (cursor != null) {
                    stack.push(cursor);
                    cursor = cursor.getLeft();
                }

                if (stack.isEmpty()) {
                    return null;
                }

                Node<T> node = stack.pop();

                cursor = node.getRight();

                return node.getData();
            }
        };
    }

    /**
     * @param action
     * @see AbstractList#forEach(Consumer)
     */
    @Override
    public void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }

    /**
     * @return
     * @see AbstractList#spliterator()
     */
    @Override
    public Spliterator<T> spliterator() {
        return null;
    }

    public static void main(String[] args) {

        // 无序列表
        List<Integer> list = Arrays.asList(4, 1, 7, 3, 9, 5, 2, 6, 8);

        // 使用二叉树搜索树排序
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        // 遍历列表，并将列表数据全部插入二叉搜索树中
        list.forEach(tree::insert);

        // 按中根序(LDR)遍历二叉树
        for (Integer x : tree) {
            System.out.println(x);
        }

        System.out.println("---------------");

        // 删除一个节点
        tree.remove(3);
        tree.remove(5);
        tree.remove(7);
        // 遍历二叉树
        tree.forEach(System.out::println);

    }

}
