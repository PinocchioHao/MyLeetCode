package AceCodingInterview75Qs.binary_search;

/*
 *
 *
 *
162. Find Peak Element
Medium

A peak element is an element that is strictly greater than its neighbors.

Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.

You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.

You must write an algorithm that runs in O(log n) time.



Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 5
Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.


Constraints:

1 <= nums.length <= 1000
-231 <= nums[i] <= 231 - 1
nums[i] != nums[i + 1] for all valid i.
 *
 *
 */

import java.util.Arrays;

public class LeetCode162 {

    public static void main(String[] args) {

        int[] arr1 = {1, 2, 3, 4, 5, 6, 7};
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};

        LeetCode162 example = new LeetCode162();

        int rlt = example.findPeakElement(arr1);
        System.out.println(rlt);
    }


    // 常规暴力O(n)解法
    public int findPeakElement(int[] nums) {
        // 讨论边界
        if (nums.length == 1) return 0;
        if (nums[0] > nums[1]) return 0;
        if (nums[nums.length - 1] > nums[nums.length - 2]) return nums.length - 1;
        // 3个以上元素时讨论中间的情况
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return -1;
    }


    // 二分 - 由于nums[-1] = nums[n] = -∞，并且不存在相等相邻元素，所以遇到爬坡的，那一定能遇到山顶，往爬坡的一方收缩
    // 如果nums[i]>nums[i+1]，则往自己及左半边找，如果nums[i]<nums[i+1]，则往i+1及其右半边找
    public int findPeakElement1(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int res = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // 【核心逻辑】：只要是上坡，mid 必不是峰顶，直接往右走
            // 注意加一个 mid < nums.length - 1 防越界
            if (mid < nums.length - 1 && nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                // 否则（下坡，或者走到了最右边悬崖），mid 有可能是峰顶！
                res = mid;         // 记下候选人
                right = mid - 1;   // 往左逼近
            }
        }

        return res;
    }


}