package datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author bin2.zhao (D52B48 in ZhangMen)
 * @since 2021/11/1 13:00
 */
public class Solution15 {

    /*
    【15-1】双指针
        -> 单数组前后指针
        -> 快慢指针
        -> 区间指针
        -> 双数组的两个指针

    题型：
    （a）求数对
    （b）特殊排序
        1. 快速排序：partition()函数
        2. 选择排序：已排序，未排序区间
        3. 插入排序：已排序，未排序区间
     */

    /**
     *【15-1-1】翻转字符串
     * 『力扣 - 344』
     */
    // 见【1-4】


    /**
     *【15-1-2】数对和
     *『面试题 16.24.』
     * 解法一：暴力穷举
     * 时间复杂度：o(n^2)
     */

    // 能否用回溯？因为也数据组合的问题

    /**
     *【15-1-2】数对和
     *『面试题 16.24.』
     * 解法二：双指针
     * 时间复杂度：复杂度在排序上o(n log n) + 后面求解实际上相当于循环一遍o(n)
     */
    public List<List<Integer>> pairSums(int[] nums, int target) {
        // 先排序是关键！
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        int head = 0;
        int tail = nums.length - 1;
        while (head < tail) {
            if (nums[head] + nums[tail] == target) {
                List<Integer> subResult = new ArrayList<>();
                subResult.add(nums[head]);
                subResult.add(nums[tail]);
                result.add(subResult);
                head++;
                tail--;
            } else if (nums[head] + nums[tail] > target) {
                tail--;
            } else {
                head++;
            }
        }
        return result;
    }


    /**
     *【15-1-3】两数之和
     *『力扣-1』
     * 解法一：Hash
     * 解法二：暴力枚举（穷举）
     */
    // 见【1-3】

    /**
     *【15-1-3】两数之和
     *『力扣-1』
     * 解法三：双指针
     */
//    public int[] twoSum(int[] nums, int target) {
//        Arrays.sort(nums);
//        int i = 0;
//        int j = nums.length - 1;
//        while (i < j) {
//            if (nums[i] + nums[j] == target) {
//                return new int[]{i, j};
//            } else if (nums[i] + nums[j] > target) {
//                j--;
//            } else {
//                i++;
//            }
//        }
//        return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
//    }


    /**
     *【15-1-4】三数之和
     *『力扣-15』
     * 解法一：hash
     */
    // 见【8-2】

    /**
     *【15-1-4】三数之和
     *『力扣-15』
     * 解法二：双指针
     */


    /**
     *【15-1-5】调整数组顺序使奇数位于偶数前面
     *『剑指Offer 21.』
     * 解法一：利用选择排序思想
     * 解法二：双指针 + 冒泡思想
     * 解法三：辅助数组 + 双指针
     */
    // 见【6-6】


    /**
     *【15-1-6】颜色分类
     *『力扣-75』
     * 解法一：直接用排序函数
     * 解法二：借助辅助数组
     * 解法三：双指针
     */
    // 见【6-7】


    /**
     *【15-1-7】移动零（已排序、未排序指针）
     *『力扣-283』
     * 解法一：辅助数组
     */
    public int[] moveZeroes(int[] nums) {
        int[] result = new int[nums.length];
        int iR = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0)
                result[iR++] = nums[i];
        }
        for (; iR < nums.length; iR++) result[iR] = 0;
        return result;
    }


    /**
     *【15-1-7】移动零（已排序、未排序指针）
     *『力扣-283』
     * 解法二：双指针（模仿快排思想）
     */
    public void moveZeroes2(int[] nums) {
        /*
        [0,p]       非0
        [p+1, q-1]    0
        [q, n-1]  未处理
         */
        int n = nums.length;
        int p = -1;
        int q = 0;
        while (q < n) {
            if (nums[q] == 0) {
                q++;
                continue;
            }
            if (nums[q] != 0) {
                this.swap(nums, p + 1, q);
                q++;
                p++;
            }
        }
    }
    private void swap(int[] x, int i, int j) {
        int temp = x[i];
        x[i] = x[j];
        x[j] = temp;
    }


    /**
     *【15-1-8】最小差（类似合并两个有序数组）
     *『面试题 16.06』
     * 解法一：暴力枚举
     */
    public int smallestDifference(int[] a, int[] b) {
        long minD = Long.MAX_VALUE;
        for (int ai = 0; ai < a.length; ai++) {
            for (int bi = 0; bi < b.length; bi++) {
                long diff = Math.abs((long)(a[ai] - b[bi]));
                if (diff < minD) minD = diff;
            }
        }
        return (int)minD;
    }


    /**
     *【15-1-8】最小差（类似合并两个有序数组）
     *『面试题 16.06』
     * 解法二：双指针
     */
    public int smallestDifference2(int[] a, int[] b) {
        /*
        注意：int类型越界
        [-2147483648,1]     [2147483647,0]
        minD = 1
         */
        long minD = Long.MAX_VALUE;
        /*
        1, 2,  3,  11,  15
                   ai
        8, 19, 23, 127, 235
        bi
         */
        // 仍然需要先排序
        Arrays.sort(a);
        Arrays.sort(b);
        int ai = 0;
        int bi = 0;
        while (ai < a.length && bi < b.length) {
            minD = Math.min(minD, Math.abs((long)(a[ai] - b[bi])));
            if (a[ai] < b[bi])
                ai++;
            else
                bi++;
        }
        return (int)minD;
    }


    /**
     *【15-1-9】单词距离（类似合并两个有序数组）
     *『面试题 17.11』
     * 解法一：暴力枚举
       无法通过AC，时间复杂度太高了o(n^2)，超时！
     */
    public int findClosest(String[] words, String word1, String word2) {
        int minIndex = Integer.MAX_VALUE;
        for (int i1 = 0; i1 < words.length; i1++) {
            for (int i2 = 0; i2 < words.length; i2++) {
                if (words[i1].equals(word1) && words[i2].equals(word2))
                    minIndex = Math.min(minIndex, Math.abs(i1 - i2));
            }
        }
        return minIndex;
    }

    /**
     *【15-1-9】单词距离（类似合并两个有序数组）
     *『面试题 17.11』
     * 解法二：双指针（快慢针）
     */
    public int findClosest2(String[] words, String word1, String word2) {
        int minIndex = words.length + 1;
        int slow = 0;
        int fast = 0;
        return minIndex;
    }




    /*
    【15-2】滑动窗口
     */


    /**
     *【15-2-1】和为s的连续正数序列
     *『剑指Offer 57-II』
     */
    // 解法一：完美答案，效率最高（只需要遍历一半），滑动窗口
    public int[][] findContinuousSequence(int target) {

        /*
        sum
            1   2   3   4   5   6   7   8   9
        3   r   l
        6   r   -   l
        10  r   -   -   l
      Y 9       r   -   l
        7           r   l
        12          r   -   l
      Y 9               r   l
        5                   rl  END
         */

        List<int[]> result = new ArrayList<>();

        int r = 1;      // 滑动窗口左边界
        int l = 2;      // 滑动窗口右边界
        int sum = 3;    // 滑动窗口中数字的和

        while (r <= target / 2) {
            if (sum < target) {
                l++;
                sum += l;
            } else if (sum > target) {
                sum -= r;
                r++;
            } else {
                int[] arr = new int[l - r + 1];
                for (int i = r; i <= l; i++) {
                    arr[i - r] = i;
                }
                result.add(arr);
                sum -= r;
                r++;
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    // 解法二：思路清晰，但是需要全部遍历
    public int[][] findContinuousSequence2(int target) {

        List<int[]> result = new ArrayList<>();

        int r = 1;
        int l = 2;
        int sum = 3;

        // 循环结束条件保证不会有一个值的情况，并且也缩短了循环次数
        while (l <= target) {
            if (sum < target) {
                l++;
                sum += l;
            } else if (sum > target) {
                sum -= r;
                r++;
            } else {
                if (r == l) break;      // 防止指针指向target的时候
                int[] arr = new int[l - r + 1];
                for (int i = r; i <= l; i++) {
                    arr[i - r] = i;
                }
                result.add(arr);
                sum -= r;
                r++;
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    // 解法三：暴力枚举
    public int[][] findContinuousSequence3(int target) {
        List<int[]> result = new ArrayList<>();
        int r = 1;
        int l = 2;
        while (r <= target - 1) {
            l = r + 1;
            int sum = r + l;
            while (l <= target) {
                if (sum < target) {
                    l++;
                    sum += l;
                } else if (sum > target) {
                    r++;
                    break;
                } else {
                    int[] subResult = new int[l - r + 1];
                    for (int i = 0; i < subResult.length; i++) {
                        subResult[i] = r + i;
                    }
                    result.add(subResult);
                     r++;
                     break;
                }
            }
        }
        return result.toArray(new int[result.size()][]);
    }


    /**
     *【15-2-2】最长不含重复字符的子字符串
     *『剑指Offer 48.』
     */
    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;

        int head = 0;
        int tail = 0;
        HashSet pools = new HashSet();
        while (tail < s.length()) {
            char x = s.charAt(tail);
            if (!pools.contains(x)) {
                pools.add(x);
                maxLen = Math.max(maxLen, tail - head + 1);
                tail++;
                continue;
            }
            while (pools.contains(x)) {
                pools.remove(s.charAt(head));
                head++;
            }
        }

        return maxLen;
    }


    /**
     *【15-2-3】找到字符串中所有字母的异位词
     *『力扣-438』
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        int sn = s.length();
        int pn = p.length();
        if (sn < pn) return result;

        int[] sCnt = new int[26];
        int[] pCnt = new int[26];
        for (int i = 0; i < pn; i++) {
            pCnt[p.charAt(i) - 'a']++;
        }

        int r = 0;
        int l = 0;
        while (r < sn) {
            int currR = s.charAt(r) - 'a';
            sCnt[currR]++;
            while (sCnt[currR] > pCnt[currR]) {
                int currL = s.charAt(l) - 'a';
                sCnt[currL]--;
                l++;
            }
            if (r - l + 1 == pn)
                result.add(l);
            r++;
        }

        return result;
    }


    /**
     *【15-2-4】最小覆盖子串
     *『力扣-76』
     */
//    public String minWindow(String s, String t) {
//
//    }



    /*
    【15-3】前后缀统计
     */


    /**
     *【15-3-1】最大子序和
     *『力扣-53』
     */


    /**
     *【15-3-2】买卖股票的最佳时期
     *『力扣-121』
     */


    /**
     *【15-3-3】除自身以外数组的乘积
     *『力扣-238』
     */


    /**
     *【15-3-4】翻转数位
     *『面试题 05.03.』
     */


    /**
     *【15-3-5】接雨水
     *『力扣-42』
     */




    /*
    【15-4】位运算
     */

    /**
     *【15-4-1】位1的个数
     *『力扣-191』
     */


    /**
     *【15-4-2】汉明距离
     *『力扣-461』
     */


    /**
     *【15-4-3】整数转换
     *『面试题 05.06』
     */


    /**
     *【15-4-4】配对交换
     *『面试题 05.07』
     */


    /**
     *【15-4-5】插入
     *『面试题 05.01.』
     */


    /**
     *【15-4-6】消失的数字
     *『面试题 17.14.』
     */


    /**
     *【15-4-7】数组中数字出现的次数
     *『剑指Offer 56-I』
     */


    /**
     *【15-4-8】数组中数字出现的次数II
     *『剑指Offer 56-II』
     */


    /**
     *【15-4-9】交换数字
     *『面试题 16.01.』
     */


    /**
     *【15-4-10】2的幂
     *『力扣-231』
     */


}
