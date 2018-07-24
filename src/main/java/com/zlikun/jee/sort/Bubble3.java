package com.zlikun.jee.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author zlikun
 * @date 2018/7/24 08:46
 */
public class Bubble3 {

    public static void main(String[] args) {

        // 排序前的数组
        int[] array = {27, 12, 36, 12, 24, 45, 68, 59, 91};

        // 打印排序前的数组
        // [27, 12, 36, 12, 24, 68, 59, 91, 45]
        System.out.println(Arrays.toString(array));

        int times = 0;
        for (int i = 0; i < array.length - 1; i++) {
            // 假定序列已经是有序的了，如果后面发生交换，则说明为假
            boolean flag = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                times++;
                if (array[j] > array[j + 1]) {
                    int t = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = t;
                    flag = false;
                }
            }
            System.out.println(String.format("第 %d 轮排序后，序列为：%s", i + 1, Arrays.toString(array)));
            // 如果为真，则跳出循环
            if (flag) break;
        }

        // 长度为 9 的序列，冒泡排序一共比较了 21 次
        // 每轮比较次数：8 + 7 + 6 = 21
        // 实际只排了3轮就结束了(第3轮其实是多余的，但只以比完第4轮才能知道上一轮实际已经有序)
        System.out.println(String.format("长度为 %d 的序列，冒泡排序一共比较了 %d 次", array.length, times));

        // 打印排序后的结果
        // [12, 12, 24, 27, 36, 45, 59, 68, 91]
        System.out.println(Arrays.toString(array));

    }

}
