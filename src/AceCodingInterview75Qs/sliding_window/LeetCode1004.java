package AceCodingInterview75Qs.sliding_window;

/*
 *
 *
 *

1004. Max Consecutive Ones III

Medium

Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.



Example 1:

Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
Example 2:

Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.


Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
0 <= k <= nums.length


 *
 *
 */

public class LeetCode1004 {


    public static void main(String[] args) {
        int arr[] = {1,1,1,0,0,0,1,1,1,1,0};
        int arr2[] = {1, 12, -5, -6, 50, 3};
        System.out.println(longestOnes(arr, 2));
    }

    // 可变滑窗，k相当于多给了k个0的额度，窗口内0的数量不能超过k，超过了就移动左指针直到窗口内0的数量不超过k为止。
    // 移动方式为右指针每次向右移动一位，如果遇到0则增加0的数量，如果窗口内0的数量超过k，则移动左指针直到窗口内0的数量不超过k为止。
    // 每次移动右指针后，更新最大长度。
    // 时间复杂度O(n)，空间复杂度O(1)
    public static int longestOnes(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int zeroCount = 0;
        int maxLength = 0;
        while (right < nums.length) {
            // 每一步向右扩张检查该位置是否为0，记录0的个数，最多多走k步，如果遇到0则增加0的数量
            if (nums[right] == 0) {
                zeroCount++;
            }
            // 如果多走了k步，即0的个数大于k，则窗口左边开始缩减，缩减过程中如果遇到0则zeroCount--，则相当于又能往右多走
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            // 更新最大长度
            maxLength = Math.max(maxLength, right - left + 1);

            // 操作完毕，右指针每次向右移动一位
            right++;
        }
        return maxLength;
    }


}