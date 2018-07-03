package com.zlikun.jee.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author zlikun
 * @date 2018/7/3 16:19
 */
public class Bubble {

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

        // 外层循环控制循环趟数，为：n - 1 次
        for (int i = 0; i < array.length - 1; i++) {

            // 内层循环用于使每趟列表中最大的元素交换至列表最后一个元素上(每趟循环比上次循环少一个元素)，
            // 直到只剩下一个元素，整个列表即为有序
            for (int j = 0; j < array.length - i - 1; j++) {

                // 当前一个元素比后一个元素大时，即发生交换(大的被交换到后面，直到本次序列最后一个)
                if (array[j] > array[j + 1]) {
                    int t = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = t;
                }

            }

        }

    }

}
