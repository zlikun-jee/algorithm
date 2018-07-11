package com.zlikun.jee.structure.tree;

import lombok.Data;

import java.util.Stack;

/**
 * 二叉树，这里主要研究二叉树的构成及遍历方法<br>
 * https://www.jianshu.com/p/0190985635eb<br>
 *
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2018/7/11 20:08
 */
public class BinaryTree<T extends Comparable<T>> {

    /**
     * 节点类
     *
     * @param <T>
     */
    @Data
    private static class TreeNode<T extends Comparable<T>> {

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

        BinaryTree<Integer> bt = new BinaryTree();

        // 前序遍历
        System.out.println("\n前序遍历");
        // 4	2	1	7	5	9
        bt.preOrder(root);

        // 中序遍历
        System.out.println("\n中序遍历");
        // 1	2	4	5	7	9
        bt.inOrder(root);

        // 后序遍历
        System.out.println("\n后序遍历");
        // 1	2	5	9	7	4
        bt.postOrder(root);

        // 下面的一些命题出自：https://www.jianshu.com/p/0190985635eb

        // 求二叉树的最大深度(高度)：3
        System.out.printf("\n二叉树的最大深度：%d%n", bt.maxDepth(root));

        // 求二叉树中节点总数：6
        System.out.printf("\n二叉树的节点总数：%d%n", bt.count(root));

        // 求二叉树中叶子节点数
        System.out.printf("\n二叉树的叶子节点总数：%d%n", bt.countLeaf(root));

        // 求二叉树中最大值：9
        System.out.printf("\n二叉树的最大值：%d%n", bt.maxValue(root));

        // 求二叉树中第n层(小于等于树高)节点数

        // 判断二叉树是否平衡树

        // 判断二叉树是否完全二叉树

        // 判断两个二叉树是否完全相同

        // 判断两个二叉树是否互为镜像

        // 翻转二叉树 or 镜像二叉树

        // 求两个二叉树的最低公共祖先节点

        // 在二叉树中插入节点
        // 在值为7的节点位置插入值为6的节点，原来的节点作为插入节点的右子节点
        root.setRight(new TreeNode<>(6, null, root.getRight()));
        // 使用中序遍历确认是否正确插入(由于是手动插入，未重排序，所以实际并不是有序的，这里只是为了验证插入正确)
        // 1	2	4	6	5	7	9
        // bt.inOrder(root);

        // 打印二叉树中节点值之和等于给定值的所有路径

        // 二叉树的搜索区间

        // 二叉树的层次遍历

        // 二叉树内两个节点的最长距离

        // 计算 1 ... n 为节点的二叉树有多少种

        // 判断二叉树是否合法的二叉搜索树(BST)

        System.out.println();
    }

    /** 叶子总数 */
    private int leafs = 0;

    /**
     * 统计二叉树叶子节点总数
     *
     * @param node
     * @return
     */
    private int countLeaf(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        countLeaf(node.getLeft());
        countLeaf(node.getRight());
        if (node.getLeft() == null && node.getRight() == null) {
            leafs ++;
        }
        return leafs;
    }

    /**
     * 统计二叉树节点总数
     *
     * @param node
     * @return
     */
    private int count(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        int left = count(node.getLeft());
        int right = count(node.getRight());
        return left + right + 1;
    }

    /**
     * 前序遍历：DLR
     *
     * @param node
     */
    private void preOrder(TreeNode<T> node) {
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
     */
    private void inOrder(TreeNode<T> node) {
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
     */
    private void postOrder(TreeNode<T> node) {
        if (node == null) {
            return;
        }
        postOrder(node.getLeft());
        postOrder(node.getRight());
        System.out.print(node.getData() + "\t");
    }

    /**
     * 求给定节点的最大深度
     *
     * @param node
     * @return
     */
    private int maxDepth(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = maxDepth(node.getLeft());
        int rightDepth = maxDepth(node.getRight());
        // 取左子节点深度与右子节点深度间中的一个
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 查找二叉树最大值，下面演示逻辑为二叉搜索树的最大值(最右节点值)<br>
     * 怎样在变通二叉树中查找最大值呢(遍历二叉树，比较每个节点值，需要外部变量参与)
     *
     * @param node
     * @return
     */
    private T maxValue(TreeNode<T> node) {

        TreeNode<T> cursor = node;

        while (cursor != null && cursor.getRight() != null) {
            cursor = cursor.getRight();
        }

        return cursor.getData();
    }

}
