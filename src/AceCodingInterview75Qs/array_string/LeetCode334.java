package AceCodingInterview75Qs.array_string;

/*
 *
 *
 *

334. Increasing Triplet Subsequence

Medium

Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.



Example 1:

Input: nums = [1,2,3,4,5]
Output: true
Explanation: Any triplet where i < j < k is valid.
Example 2:

Input: nums = [5,4,3,2,1]
Output: false
Explanation: No triplet exists.
Example 3:

Input: nums = [2,1,5,0,4,6]
Output: true
Explanation: One of the valid triplet is (1, 4, 5), because nums[1] == 1 < nums[4] == 4 < nums[5] == 6.


Constraints:

1 <= nums.length <= 5 * 105
-231 <= nums[i] <= 231 - 1


Follow up: Could you implement a solution that runs in O(n) time complexity and O(1) space complexity?


 *
 *
 */

import java.util.*;

public class LeetCode334 {


    public static void main(String[] args) {
        int arr[] = {5,4,6,2,7};
        System.out.println(increasingTriplet(arr));
        System.out.println(increasingTriplet1(arr));
    }

    // 遍历两次数组，第一次从左往右遍历，记录每个元素左边的最小值，
    // 第二次从右往左遍历，记录每个元素右边的最大值，并比较当前元素和左边最小值、右边最大值的关系，找到满足条件的三元组则返回 true
    // O(n) time complexity, O(n) space complexity
    public static boolean increasingTriplet(int[] nums) {
        int len = nums.length;
        if (len < 3) {
            return  false;
        }
        // leftMin[i] 记录 nums[i] 左边的最小值
        int[] leftMin = new int[len];
        leftMin[0] = nums[0];
        for (int i = 1; i < len; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], nums[i-1]);
        }
        // rightMax 记录 nums[i] 右边的最大值, 从右往左遍历数组，更新 rightMax 的值，并比较 nums[i] 和 leftMin[i]、rightMax 的关系，
        // 如果 nums[i] > leftMin[i] && nums[i] < rightMax，说明找到了一个满足条件的三元组，返回 true
        int rightMax = nums[len - 1];
        for (int i = len -2; i>=0; i--) {
            rightMax = Math.max(rightMax, nums[i + 1]);
            if (nums[i] > leftMin[i] && nums[i] < rightMax) {
                return true;
            }
        }
        // 遍历完没找到则返回false
        return false;
    }


    // 优化版本：只维护两个变量 min 和 second，分别记录当前遍历过的元素中的最小值和第二小值
    // 便利维度为当前值，若找到 min < second < nums[i]，说明找到了一个满足条件的三元组，返回 true
    // O(n) time complexity, O(1) space complexity
    public static boolean increasingTriplet1(int[] nums) {
        int len = nums.length;
        if (len < 3) {
            return  false;
        }
        // 维护两个变量 min 和 second，分别记录当前遍历过的元素中的最小值和第二小值。初始时，min 和 second 都设置为 Integer.MAX_VALUE。
        int min = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            if (nums[i] <= min) { // 找到最小值，更新 min
                min = nums[i];
            } else if (nums[i] <= second) { // 当前值大于 min 但小于 second，更新 second
                second = nums[i];
            } else {
                // 如果当前元素既大于 min 又大于 second，说明找到了一个满足条件的三元组，返回 true
                return true;
            }
        }
        // 遍历完没找到则返回false
        return false;
    }


}