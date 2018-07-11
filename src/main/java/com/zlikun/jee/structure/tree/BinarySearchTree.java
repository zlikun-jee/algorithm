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

    }

}
