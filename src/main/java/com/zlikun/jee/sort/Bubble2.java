package com.zlikun.jee.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author zlikun
 * @date 2018/7/24 08:44
 */
public class Bubble2 {

    public static void main(String[] args) {

        // 排序前的数组
        int[] array = {27, 12, 36, 12, 24, 45, 68, 59, 91};

        // 打印排序前的数组
        // [27, 12, 36, 12, 24, 68, 59, 91, 45]
        System.out.println(Arrays.toString(array));

        int times = 0;
        for (int i = 0; i < array.length - 1; i++) {

            for (int j = 0; j < array.length - i - 1; j++) {
                times++;
                if (array[j] > array[j + 1]) {
                    int t = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = t;
                }

            }
            System.out.println(String.format("第 %d 轮排序后，序列为：%s", i + 1, Arrays.toString(array)));

        }

        // 长度为 9 的序列，冒泡排序一共比较了 36 次，时间复杂度：O(N ^ 2)
        // 每轮比较次数：8 + 7 + 6 + 5 + 4 + 3 + 2 + 1 = 36
        // 但通过对每轮排序结果的观察，发现实际在第3轮时，整体已经有序，后面几轮都是多余的
        System.out.println(String.format("长度为 %d 的序列，冒泡排序一共比较了 %d 次", array.length, times));

        // 打印排序后的结果
        // [12, 12, 24, 27, 36, 45, 59, 68, 91]
        System.out.println(Arrays.toString(array));

    }

}
