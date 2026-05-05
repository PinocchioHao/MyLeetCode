package AceCodingInterview75Qs.sliding_window;

/*
 *
 *
 *

643. Maximum Average Subarray I

Easy

You are given an integer array nums consisting of n elements, and an integer k.

Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value. Any answer with a calculation error less than 10-5 will be accepted.



Example 1:

Input: nums = [1,12,-5,-6,50,3], k = 4
Output: 12.75000
Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
Example 2:

Input: nums = [5], k = 1
Output: 5.00000


Constraints:

n == nums.length
1 <= k <= n <= 105
-104 <= nums[i] <= 104


 *
 *
 */

public class LeetCode643 {


    public static void main(String[] args) {
        int arr[] = {0, 4, 0, 3, 2};
        int arr2[] = {1, 12, -5, -6, 50, 3};
        System.out.println(findMaxAverage(arr, 1));
//        System.out.println(findMaxAverage(arr2, 4));
    }

    // 固定滑窗：先计算前k个元素的和作为初始值，然后每次将窗口向右移动一位，更新窗口内的元素和，并比较更新最大和。最后返回最大和除以k的平均值。
    public static double findMaxAverage(int[] nums, int k) {
        if (nums.length == 1) {
            return nums[0];
        }
        // 滑窗内元素和
        int windowSum = 0;
        // 初始窗口内元素和
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }
        // 最大窗口内元素和
        int maxSum = windowSum;

        // while 写法
//        int left = 0;
//        int right = left + k;
//        while(right < nums.length){
//            windowSum = windowSum - nums[left] + nums[right];
//            maxSum = Math.max(maxSum, windowSum);
//            left++;
//            right++;
//        }

        // for循环更简洁直观
        for (int i = k; i < nums.length; i++) {
            windowSum = windowSum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, windowSum);
        }

        return (double) maxSum / k;

    }


    // 前缀和：计算前缀和数组 prefixSum，其中 prefixSum[i] 表示 nums 数组中前 i 个元素的和。
    // 然后遍历 prefixSum 数组，计算每个长度为 k 的子数组的和，并更新最大和。最后返回最大和除以 k 的平均值。
    public static double findMaxAverage1(int[] nums, int k) {
        int[] prefixSum = new int[nums.length + 1];
        int maxSum = Integer.MIN_VALUE;
        for (int i = 1; i < prefixSum.length; i ++) {
            prefixSum[i] = prefixSum[i-1] + nums[i-1];
            // 遍历到大于等于k的位置时，计算当前长度为k的子数组的和，并更新最大值
            if (i >= k) {
                // prefixSum[i] - prefixSum[i-k] 就是长度为k的子数组的和
                maxSum = Math.max(maxSum, prefixSum[i] - prefixSum[i - k]);
            }

        }
        return (double) maxSum / k;
    }


}