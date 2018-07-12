package com.zlikun.jee.structure.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 基于List实现一个二叉堆(完全二叉树的特例)
 *
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2018/7/12 13:12
 */
public class BinaryHeap<T extends Comparable<T>> {

    private int current;
    private List<T> list = new ArrayList<>();

    public BinaryHeap() {
        // 计数从1开始，所以0位置留一个占位
        this.list.add(null);
    }

    public void buildHeap(List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.current = list.size();
        // 增加一个占位
        this.list = new ArrayList<>();
        this.list.add(null);
        this.list.addAll(list);
        // 循环下沉顶元素
        int index = list.size() / 2;
        while (index > 0) {
            down(index);
            index--;
        }

    }

    public void insert(T data) {
        this.list.add(data);
        this.current++;
        this.up(this.current);
    }

    private void up(int index) {
        while (index / 2 > 0) {
            if (this.list.get(index).compareTo(this.list.get(index / 2)) > 0) {
                T t = this.list.get(index);
                this.list.set(index, this.list.get(index / 2));
                this.list.set(index / 2, t);
            }
            index /= 2;
        }
    }

    public T findMin() {
        if (this.isEmpty()) {
            return null;
        }
        return this.list.get(1);
    }

    public T delMin() {
        T data = this.list.get(1);
        this.list.set(1, this.list.get(current));
        this.list.remove(this.current);
        this.current--;
        this.down(1);
        return data;
    }

    private void down(int index) {
        while (index * 2 <= current) {
            int mc = this.minChild(index);
            if (this.list.get(index).compareTo(this.list.get(mc)) > 0) {
                T t = this.list.get(index);
                this.list.set(index, this.list.get(mc));
                this.list.set(mc, t);
            }
            index = mc;
        }
    }

    private int minChild(int index) {
        if (index * 2 + 1 > this.current) {
            return index * 2;
        } else {
            if (this.list.get(index * 2).compareTo(this.list.get(index * 2 + 1)) < 0) {
                return index * 2;
            } else {
                return index * 2 + 1;
            }
        }
    }


    public boolean isEmpty() {
        return this.current == 0;
    }

    public int size() {
        return this.current;
    }

    public static void main(String[] args) {

        BinaryHeap<Integer> heap = new BinaryHeap<>();
        // true
        System.out.println(heap.isEmpty());
        // 0
        System.out.println(heap.size());
        heap.insert(4);
        heap.insert(2);
        heap.insert(7);
        heap.insert(5);
        // 7
        System.out.println(heap.findMin());
        // 7
        System.out.println(heap.delMin());
        // 2
        System.out.println(heap.delMin());
        // 4
        System.out.println(heap.findMin());
        // 4
        System.out.println(heap.delMin());
        // 5
        System.out.println(heap.delMin());
        // true
        System.out.println(heap.isEmpty());

        heap.buildHeap(Arrays.asList(4, 1, 7, 3, 9, 5, 2, 6, 8));

        // 1
        // 2
        // 3
        // 4
        // 5
        // 6
        // 7
        // 8
        // 9
        while (!heap.isEmpty()) {
            System.out.println(heap.delMin());
        }

    }

}
