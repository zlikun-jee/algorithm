package com.zlikun.jee.structure.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 基于数组实现一个二叉堆(完全二叉树的特例)
 *
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2018/7/12 13:40
 */
public class BinaryHeap2<T extends Comparable<T>> {

    private int current;
    private int capacity;
    private Comparable[] array;

    public BinaryHeap2(int capacity) {
        this.capacity = capacity;
        this.array = new Comparable[capacity + 1];
    }

    public boolean isFull() {
        return this.current == this.capacity;
    }

    public void insert(T data) {
        if (isFull()) {
            throw new RuntimeException("堆满了");
        }
        this.current++;
        this.array[this.current] = data;
        this.up(this.current);
    }

    private void up(int index) {
        while (index / 2 > 0) {
            if (this.array[index].compareTo(this.array[index / 2]) > 0) {
                Comparable<T> t = this.array[index];
                this.array[index] = this.array[index / 2];
                this.array[index / 2] = t;
            }
            index /= 2;
        }
    }

    public T findMin() {
        if (this.isEmpty()) {
            return null;
        }
        return (T) this.array[1];
    }

    public T delMin() {
        Comparable data = this.array[1];
        this.array[1] = this.array[current];
        this.array[this.current] = null;
        this.current--;
        this.down(1);
        return (T) data;
    }

    private void down(int index) {
        while (index * 2 <= current) {
            int mc = this.minChild(index);
            if (this.array[index].compareTo(this.array[mc]) > 0) {
                Comparable t = this.array[index];
                this.array[index] = this.array[mc];
                this.array[mc] = t;
            }
            index = mc;
        }
    }

    private int minChild(int index) {
        if (index * 2 + 1 > this.current) {
            return index * 2;
        } else {
            if (this.array[index * 2].compareTo(this.array[index * 2 + 1]) < 0) {
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

        BinaryHeap2<Integer> heap = new BinaryHeap2<>(10);
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

    }

}
