package AceCodingInterview75Qs.sliding_window;

/*
 *
 *
 *

1493. Longest Subarray of 1's After Deleting One Element

Medium

Given a binary array nums, you should delete one element from it.

Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.



Example 1:

Input: nums = [1,1,0,1]
Output: 3
Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
Example 2:

Input: nums = [0,1,1,1,0,1,1,0,1]
Output: 5
Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
Example 3:

Input: nums = [1,1,1]
Output: 2
Explanation: You must delete one element.


Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.


 *
 *
 */

public class LeetCode1493 {


    public static void main(String[] args) {
        int arr[] = {1,1,1,0,0,0,1,1,1,1,0};
        int arr2[] = {1, 12, -5, -6, 50, 3};
        System.out.println(longestSubarray(arr));
    }

    // 可变滑窗，用类似于1004题的思路(k=1)，窗口内0的数量不能超过1，超过了就移动左指针直到窗口内0的数量不超过1为止。
    public static int longestSubarray(int[] nums) {
        int left = 0;
        int right = 0;
        int maxLen = 0;
        int zeroNums = 0;
        while (right < nums.length) {
            if (nums[right] == 0) {
                zeroNums++;
            }
            while (zeroNums > 1) {
                if (nums[left] == 0) zeroNums--;
                left++;
            }
            // 更新最大长度，注意题目要求删除一个元素，所以窗口内的元素数量要减去1，即right - left
            maxLen = Math.max(maxLen, right - left);
            right++;
        }
        return maxLen;
    }


    // 动态规划，p1记录全为1的子数组长度，遇到0时重置为0（表示移除这个0，从这一点再开始计数），p2记录只有一个0的子数组长度，遇到0时重置为p1
    // 这样能保证p2每次的长度为只含有1个0的子串，当再次遇到0时p2更新为p1，即前一个0之后的全1子串长度，再继续遍历
    // 需要注意特殊情况时是全为1的子串，由于必须要移除一个元素，所以p2的长度需要-1
    public static int longestSubarray1(int[] nums) {
        // 记录全为1的子数组长度，遇到0时重置为0
        int p1 = 0;
        // 记录只有一个0的子数组长度，遇到0时重置为p1
        int p2 = 0;
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                p1++;
                p2++;
            } else {
                // 遇到0则假定移除0重新计数，p2从原来p1位置开始计数，p1归0重新计数
                p2 = p1;
                p1 = 0;
            }
            // 更新最大长度，maxLen记录p2的值
            maxLen = Math.max(maxLen, p2);
        }
        // 需要额外考虑全为1的情况，题目要求删除一个元素，所以如果全为1，则需要减去1
        return maxLen == nums.length ? maxLen - 1 : maxLen;
    }


}