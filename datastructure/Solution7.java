package datastructure;

import java.util.Arrays;

/**
 * 07-二分查找
 */
public class Solution7 {

    /*
    二分查找题型套路：
    （1）查找区间永远是闭区间：
        [head, tail]
    （2）循环条件永远是：
        while (head <= tail) {}
    （3）返回值永远是：
        return middle;
        ** 不要是head或tail **
    （4）head 和 tail 的更新永远是：
        head = middle + 1;
        tail = middle - 1;
    （5）特殊情况：
        + 对于 "head == tail" ：
          必要的时候，在while内部补充退出条件（比如查找第一个/最后一个命中的值）；
        + 对于 "非确定性查找" ：
          使用<前后探测法>，来确定探测区间；
          先处理命中情况，再处理左右半部分查找的情况；
     */


    /**
     *【7-0.0】标准二分查找
     * {LeetCode-704}
     * 解法一：循环折半
     */
    public int search(int[] nums, int target) {
        // 如果数组是未排序的，需要预处理一下。
        Arrays.sort(nums);

        int head = 0;
        int tail = nums.length - 1;
        int middle;
        while (head <= tail) {
            middle = (head + tail) / 2;
            if (nums[middle] == target)
                return middle;
            else if (nums[middle] > target)
                tail = middle - 1;
            else
                head = middle + 1;
        }
        return -1;
    }


    /**
     *【7-0.0】标准二分查找
     * {LeetCode-704}
     * 解法二：递归
     */
    public int search_2(int[] nums, int target) {
        return searchRecur(nums, 0, nums.length - 1, target);
    }
    private int searchRecur(int[] nums, int head, int tail, int target) {
        if (head > tail) return -1;
        int middle = (head + tail) / 2;
        if (nums[middle] == target)
            return middle;
        else if (nums[middle] > target)
            return searchRecur(nums, head, middle - 1, target);
        else
            return searchRecur(nums,middle + 1, tail, target);
    }




    /**
     *【7-0.1】查找第一个等于目标值的元素
     * {跟谁学21春招}
     */
    public int searchFirstTarget(int[] nums, int target) {
        int head = 0;
        int tail = nums.length - 1;
        int middle;
        while (head <= tail) {
            // 防止int越界的写法，(head + tail) / 2 有发生int越界的可能
            middle = head + (tail - head) / 2;
            if (nums[middle] == target) {
                // 前面的判断条件是防止[middle-1]<0发生数组越界
                if (middle == 0 || nums[middle - 1] != target) return middle;
                else tail = middle - 1;
            } else if (nums[middle] > target) {
                tail = middle - 1;
            } else {
                head = middle + 1;
            }
        }
        return -1;
    }



    /**
     *【7-0.2】查找最后一个等于目标值的元素
     */
    public int searchLastTarget(int[] nums, int target) {
        int head = 0;
        int tail = nums.length - 1;
        int middle;
        while (head <= tail) {
            middle = head + (tail - head) / 2;
            if (nums[middle] == target) {
                if (middle == nums.length - 1 || nums[middle + 1] != target) return middle;
                else head = middle + 1;
            } else if (nums[middle] > target) {
                tail = middle - 1;
            } else {
                head = middle + 1;
            }
        }
        return -1;
    }




    /**
     *【7-0.3】查找第一个大于等于target的元素
     */
    public int searchFirstGETarget(int[] nums , int target) {
        int head = 0;
        int tail = nums.length - 1;
        int middle;
        while (head <= tail) {
            middle = head + (tail - head) / 2;
            if (nums[middle] >= target) {
                if (middle == 0 || nums[middle - 1] < target) return middle;
                else tail = middle - 1;
            } else {
                head = middle + 1;
            }
        }
        return -1;
    }




    /**
     *【7-0.4】查找最后一个小于等于target的元素
     */
    public int searchLastLETarget(int[] nums , int target) {
        int head = 0;
        int tail = nums.length - 1;
        int middle;
        while (head <= tail) {
            middle = head + (tail - head) / 2;
            if (nums[middle] <= target) {
                if (middle == nums.length - 1 || nums[middle + 1] > target) return middle;
                else head = middle + 1;
            } else {
                tail = middle - 1;
            }
        }
        return -1;
    }



    /**
     *【7-0.5】循环有序数组中查找元素x（没有重复数据）
     */
    public int searchInCycleSortedArr(int[] cs_nums, int target) {
        int head = 0;
        int tail = cs_nums.length - 1;
        int middle;
        while (head <= tail) {
            middle = head + (tail - head) / 2;
            if (cs_nums[middle] == target) {
                return middle;
            } else if (cs_nums[head] < cs_nums[middle]) {
                if (target >= cs_nums[head] && target < cs_nums[middle]) tail = middle - 1;
                else head = middle + 1;
            } else {
                if (target > cs_nums[middle] && target < cs_nums[tail]) head = middle + 1;
                else tail = middle - 1;
            }
        }
        return -1;
    }





    /**
     *【7-2】猜数字大小
     * {LeetCode-374}
     */
    public int guessNumber(int n) {
        int num = n;
        int head = 1;
        int tail = num;
        while (true) {
            if (guess(num) == 0) {
                return num;
            }
            else if (guess(num) == 1) {
                head = num;
                num *= 2;
            }
            else {
                tail = num;
                break;
            }
        }
        while (head <= tail) {
            num = (head + tail) / 2;
            if (guess(num) == 0)
                return num;
            else if (guess(num) == 1)
                head = num + 1;
            else
                tail = num - 1;
        }
        return num;
    }
    private int guess(int num) {
        int BINGO = 1702766719;
        if (BINGO == num)
            return 0;
        else if (BINGO > num)
            return 1;
        else
            return -1;
    }

}
