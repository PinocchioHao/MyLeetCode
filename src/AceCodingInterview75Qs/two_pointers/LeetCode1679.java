package AceCodingInterview75Qs.two_pointers;

/*
 *
 *
 *

1679. Max Number of K-Sum Pairs

Medium

You are given an integer array nums and an integer k.

In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.

Return the maximum number of operations you can perform on the array.



Example 1:

Input: nums = [1,2,3,4], k = 5
Output: 2
Explanation: Starting with nums = [1,2,3,4]:
- Remove numbers 1 and 4, then nums = [2,3]
- Remove numbers 2 and 3, then nums = []
There are no more pairs that sum up to 5, hence a total of 2 operations.
Example 2:

Input: nums = [3,1,3,4,3], k = 6
Output: 1
Explanation: Starting with nums = [3,1,3,4,3]:
- Remove the first two 3's, then nums = [1,4,3]
There are no more pairs that sum up to 6, hence a total of 1 operation.


Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 109
1 <= k <= 109

 *
 *
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeetCode1679 {


    public static void main(String[] args) {
        int arr[] = {5,0,2,3,1,4};
        System.out.println(maxOperations(arr, 5));
        System.out.println(maxOperations1(arr, 5));
    }

    // 双指针，先排序，然后判断相加之和，如果等于k，则同时移动，如果小于k，则移动左指针往右找大的，如果大于k，则右指针往左找小的
    // O(nlogn) time complexity, O(1) space complexity
    static int maxOperations(int[] nums, int k) {
        if (nums.length < 2) {
            return 0;
        }
        int count = 0;
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == k) {
                count++;
                left++;
                right--;
            }else if (sum < k) {
                left++;
            } else {
                right--;
            }
        }
        return count;
    }



    // HashMap，遍历数组，计算目标值 target = k - num，
    // 如果在 HashMap 中找到 target 且其出现次数大于 0，则说明找到了一个满足条件的数对，计数器 count 加 1，并将 target 的出现次数减 1；
    // 否则，将当前数字 num 的出现次数加 1
    // O(n) time complexity, O(n) space complexity
    static int maxOperations1(int[] nums, int k) {
        // HashMap记录每个数字出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int num : nums) {
            // 先计算目标数，并查HashMap，找到了count++，不必要再把num加入HashMap了，继续下一个数的遍历
            int target = k - num;
            if (map.get(target) != null && map.get(target) > 0) {
                count ++;
                map.put(target, map.get(target) - 1);
            } else {
                // 没有找到目标数，则把当前数加入HashMap，记录出现次数
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

        }
        return count;


    }



    }