package AceCodingInterview75Qs.prefix_sum;

/*
 *
 *
 *

724. Find Pivot Index

Easy

Given an array of integers nums, calculate the pivot index of this array.

The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.

If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. This also applies to the right edge of the array.

Return the leftmost pivot index. If no such index exists, return -1.



Example 1:

Input: nums = [1,7,3,6,5,6]
Output: 3
Explanation:
The pivot index is 3.
Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
Right sum = nums[4] + nums[5] = 5 + 6 = 11
Example 2:

Input: nums = [1,2,3]
Output: -1
Explanation:
There is no index that satisfies the conditions in the problem statement.
Example 3:

Input: nums = [2,1,-1]
Output: 0
Explanation:
The pivot index is 0.
Left sum = 0 (no elements to the left of index 0)
Right sum = nums[1] + nums[2] = 1 + -1 = 0


Constraints:

1 <= nums.length <= 104
-1000 <= nums[i] <= 1000


Note: This question is the same as 1991: https://leetcode.com/problems/find-the-middle-index-in-array/


 *
 *
 */

public class LeetCode724 {


    public static void main(String[] args) {
        int arr[] = {2,1,-1};
        int arr2[] = {1, 12, -5, -6, 50, 3};
        System.out.println(pivotIndex(arr));
//        System.out.println(findMaxAverage(arr2, 4));
    }

    public static int pivotIndex(int[] nums) {
        int len = nums.length;
        int[] preSum = new int[len];
        int[] sufSum = new int[len];
        preSum[0] = nums[0];
        sufSum[len - 1] = nums[len - 1];
        for (int j = len-1; j >= 0; j--){
            if (j < len - 1) sufSum[j] = sufSum[j+1] + nums[j];
            else sufSum[j] = nums[j];
        }

        for (int i = 0; i < len; i++){
            if (i > 0) preSum[i] = preSum[i-1] + nums[i];
            else preSum[i] = nums[i];
            if(sufSum[i] == preSum[i]) return i;
        }


        return -1;
    }


}