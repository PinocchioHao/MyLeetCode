package math;

import java.util.Arrays;

/**
 453. Minimum Moves to Equal Array Elements
 Medium

 Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.

 In one move, you can increment n - 1 elements of the array by 1.

 Example 1:

 Input: nums = [1,2,3]
 Output: 3
 Explanation: Only three moves are needed (remember each move increments two elements):
 [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 Example 2:

 Input: nums = [1,1,1]
 Output: 0

 Constraints:

 n == nums.length
 1 <= nums.length <= 105
 -109 <= nums[i] <= 109
 The answer is guaranteed to fit in a 32-bit integer.
 */
public class LeetCode453 {

    public static void main(String[] args) {

        int[] nums = {1,1,1000000000};


        System.out.println(minMoves(nums));

    }
    /**
     * 每次操作n-1个元素+1，那么等效于每次只操作1个元素-1
     * 因此可以看作是数组中所有数于最小数之间的差值之和
     * Each operation increases n-1 elements by 1, which is equivalent to decreasing
     * one element by 1. Therefore, it can be viewed as the sum of the differences
     * between all numbers in the array and the minimum number.
     *
     * @param nums 输入的整数数组
     * @return 使所有元素相等所需的最小操作次数
     *         the minimum number of moves required to make all elements equal
     */
    public static int minMoves(int[] nums) {
        int minValue = Arrays.stream(nums).min().getAsInt();
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i] - minValue;
        }
        return count;
    }


}
