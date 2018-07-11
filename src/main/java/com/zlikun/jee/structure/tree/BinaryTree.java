package com.zlikun.jee.structure.tree;

import lombok.Data;

/**
 * 二叉树，这里主要研究二叉树的构成及遍历方法<br>
 * https://www.jianshu.com/p/0190985635eb<br>
 *
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2018/7/11 20:08
 */
public class BinaryTree {

    /**
     * 节点类
     *
     * @param <T>
     */
    @Data
    private static class TreeNode<T> {

        private T data;
        private TreeNode<T> left;
        private TreeNode<T> right;

        public TreeNode(T data) {
            this(data, null);
        }

        public TreeNode(T data, TreeNode<T> left) {
            this(data, left, null);
        }

        public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

    }

    public static void main(String[] args) {

        // 创建一个 root 节点
        TreeNode<Integer> root = new TreeNode<>(4);

        // 插入一个左子节点
        root.setLeft(new TreeNode<>(2));

        // 插入一个右子节点
        root.setRight(new TreeNode<>(7));

        // 在root的左子节点下插入一个左子节点
        root.getLeft().setLeft(new TreeNode<>(1));

        // 在root的右子节点下插入一个左子结点，一个右子节点
        root.getRight().setLeft(new TreeNode<>(5));
        root.getRight().setRight(new TreeNode<>(9));

        /* ------------------------------------------------------
        [4,
            [2,
                [1, [], []],
                []
            ],
            [7,
                [5, [], []],
                [9, [], []]
            ]
        ]
        ------------------------------------------------------ */

        // 前序遍历
        System.out.println("\n前序遍历");
        // 4	2	1	7	5	9
        preOrder(root);

        // 中序遍历
        System.out.println("\n中序遍历");
        // 1	2	4	5	7	9
        inOrder(root);

        // 后序遍历
        System.out.println("\n后序遍历");
        // 1	2	5	9	7	4
        postOrder(root);


        System.out.println();
    }

    /**
     * 前序遍历：DLR
     *
     * @param node
     * @param <T>
     */
    private static <T> void preOrder(TreeNode<T> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getData() + "\t");
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    /**
     * 中序遍历：LDR
     *
     * @param node
     * @param <T>
     */
    private static <T> void inOrder(TreeNode<T> node) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeft());
        System.out.print(node.getData() + "\t");
        inOrder(node.getRight());
    }

    /**
     * 后序遍历：LRD
     *
     * @param node
     * @param <T>
     */
    private static <T> void postOrder(TreeNode<T> node) {
        if (node == null) {
            return;
        }
        postOrder(node.getLeft());
        postOrder(node.getRight());
        System.out.print(node.getData() + "\t");
    }

}
