package com.zlikun.jee.sort;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author zlikun
 * @date 2018/7/3 16:53
 */
public class Quick {

    public static void main(String[] args) {

        // 排序前的数组
        int[] array = {27, 12, 36, 12, 24, 68, 59, 91, 45};

        // 打印排序前的数组
        // [27, 12, 36, 12, 24, 68, 59, 91, 45]
        System.out.println(Arrays.toString(array));

        // 执行排序
        sort(array);

        // 打印排序后的结果
        // [12, 12, 24, 27, 36, 45, 59, 68, 91]
        System.out.println(Arrays.toString(array));

    }

    private static final void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private static final void sort(int[] array, int left, int right) {

        if (left >= right) {
            return;
        }

        // 选择一个支点，用于作为中心点，取左边第一个元素(左边的空位)
        int pivot = array[left];
        // low 指向了这个空位
        int low = left;
        int high = right;

        while (low < high) {

            // 从右向左遍历，当元素比支点大时，持续遍历，直到找到比支点小的元素
            while (low < high && pivot <= array[high]) {
                high--;
            }

            // 此时 high 指向的元素比支点小，应交换到左边的空位上，交换后 high 指向的位置将成为空位
            array[low] = array[high];

            // 从左向右遍历，当元素比支点小时，持续遍历，直到找到比支点大的元素
            while (low < high && pivot >= array[low]) {
                low++;
            }

            // 此时 low 指向的元素比支点大，应交换到右边的空位上，交换后 low 指向的位置将成为空位
            array[high] = array[low];

        }

        // 此时 low == high，该点为支点元素的位置
        array[low] = pivot;

        // 采用递归方式，将支点左边、右边的列表分别执行同样操作
        sort(array, left, low - 1);
        sort(array, low + 1, right);

    }


}
