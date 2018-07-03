package com.zlikun.jee.sort;

import java.util.Arrays;

/**
 * 希尔排序
 *
 * @author zlikun
 * @date 2018/7/3 16:44
 */
public class Shell {

    public static void main(String[] args) {

        // 排序前的数组
        int[] array = {27, 12, 36, 12, 24, 68, 59, 91, 45};

        // 打印排序前的数组
        // [27, 12, 36, 12, 24, 68, 59, 91, 45]
        System.out.println(Arrays.toString(array));

        // 执行排序
        sort(array, 5);
        // [27, 12, 36, 12, 24, 68, 59, 91, 45]
        System.out.println(Arrays.toString(array));

        sort(array, 3);
        // [12, 12, 36, 27, 24, 45, 59, 91, 68]
        System.out.println(Arrays.toString(array));

        sort(array, 1);

        // 打印排序后的结果
        // [12, 12, 24, 27, 36, 45, 59, 68, 91]
        System.out.println(Arrays.toString(array));

        // 上述过程为分解过程，通过循环将基合并
        array = new int[]{27, 12, 36, 12, 24, 68, 59, 91, 45};
        sort(array);

        // [12, 12, 24, 27, 36, 45, 59, 68, 91]
        System.out.println(Arrays.toString(array));

    }

    /**
     * 希尔排序，这里采用二分法拆解的数列做为分组因子
     *
     * @param array
     */
    private static final void sort(int[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            sort(array, gap);
        }
    }

    /**
     * 改造插入排序，使其步长为传入值(默认为：1)，改造后的函数代表希尔排序中的一趟
     *
     * @param array
     * @param gap
     */
    private static final void sort(int[] array, int gap) {

        for (int i = gap; i < array.length; i++) {

            // 取第i个元素作为哨兵，其前面的元素则为有序列表
            // 当i == 1的时候，前面只有一个元素，所以一定是有序列表
            int sentinel = array[i];

            // 遍历有序列表，将哨兵与列表中的元素比较，
            // 当遇到比哨兵大的元素时，将该元素后的所有元素向后移动一次
            // 将将哨兵插入到移动元素后空出来的位置上
            // 因为需要向后移动元素，所以遍历时，需要倒序遍历
            for (int j = i - gap; j >= 0; j -= gap) {

                if (array[j] > sentinel) {
                    array[j + gap] = array[j];
                    array[j] = sentinel;
                } else {
                    // 因为前面是有序列表，所以如果遇到一个元素小于哨兵时，其前面的所有元素都小于哨兵，就没有必要再遍历下去了
                    break;
                }

            }

        }

    }

}
