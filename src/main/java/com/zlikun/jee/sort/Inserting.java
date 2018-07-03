package com.zlikun.jee.sort;

import java.util.Arrays;

/**
 * 插入排序
 * @author zlikun
 * @date 2018/7/3 16:19
 */
public class Inserting {

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

        for (int i = 1; i < array.length ; i++) {

            // 取第i个元素作为哨兵，其前面的元素则为有序列表
            // 当i == 1的时候，前面只有一个元素，所以一定是有序列表
            int sentinel = array[i];

            // 遍历有序列表，将哨兵与列表中的元素比较，
            // 当遇到比哨兵大的元素时，将该元素后的所有元素向后移动一次
            // 将将哨兵插入到移动元素后空出来的位置上
            // 因为需要向后移动元素，所以遍历时，需要倒序遍历
            for (int j = i - 1; j >= 0 ; j--) {

                if (array[j] > sentinel) {
                    array[j + 1] = array[j];
                    array[j] = sentinel;
                } else {
                    // 因为前面是有序列表，所以如果遇到一个元素小于哨兵时，其前面的所有元素都小于哨兵，就没有必要再遍历下去了
                    break;
                }

            }

        }

    }


}
