package iteration;

import java.util.ArrayList;
import java.util.List;

/**
 228. Summary Ranges
 Easy
 Topics
 Companies
 You are given a sorted unique integer array nums.

 A range [a,b] is the set of all integers from a to b (inclusive).

 Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.

 Each range [a,b] in the list should be output as:

 "a->b" if a != b
 "a" if a == b


 Example 1:

 Input: nums = [0,1,2,4,5,7]
 Output: ["0->2","4->5","7"]
 Explanation: The ranges are:
 [0,2] --> "0->2"
 [4,5] --> "4->5"
 [7,7] --> "7"
 Example 2:

 Input: nums = [0,2,3,4,6,8,9]
 Output: ["0","2->4","6","8->9"]
 Explanation: The ranges are:
 [0,0] --> "0"
 [2,4] --> "2->4"
 [6,6] --> "6"
 [8,9] --> "8->9"


 Constraints:

 0 <= nums.length <= 20
 -231 <= nums[i] <= 231 - 1
 All the values of nums are unique.
 nums is sorted in ascending order.
 */

public class LeetCode228 {
    public static void main(String[] args) {

        int[] a = {1,2,3,6,7};
        System.out.println(summaryRanges(a));
        System.out.println(summaryRanges1(a));
    }


    /**
     * 初版 - 优化逻辑见函数 summaryRanges1
     * First version - See summaryRanges1 for optimized logic
     *
     * @param nums 输入的整型数组
     *             input integer array
     * @return 包含连续区间的字符串列表
     *         list of strings representing the summary of consecutive ranges
     */
    public static List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        // 如果数组为空或长度为0，返回空结果集
        // If the array is null or has a length of 0, return an empty result
        if (nums == null || nums.length == 0) return res;

        // 记录连续区间的起始位置
        // Variable to track the start of a consecutive range
        int start = 0;

        // 遍历数组，使用 i 控制循环直到 nums.length+1
        // Iterate through the array, with i controlling the loop until nums.length + 1
        for (int i = 1; i < nums.length + 1; i++) {
            // 当遍历到数组最后一个元素时处理剩下的区间
            // Handle the remaining range when reaching the last element of the array
            if (i == nums.length) {
                // 如果只有一个元素，则单独输出
                // If the range contains only one element, add it as a single value
                if (start == i - 1) {
                    res.add(nums[start] + "");
                }
                // 否则输出连续区间
                // Otherwise, output the consecutive range in the format "start->i-1"
                else {
                    res.add(nums[start] + "->" + nums[i - 1]);
                }
                // 更新起始位置
                // Update the starting position for the next range
                start = i;
            } else {
                // 连续则继续走
                // If consecutive, continue
                if (nums[i] - nums[i - 1] == 1) {
                    continue;
                }
                // 不连续则输出区间
                // If not consecutive, output the range
                else {
                    // 如果是单个元素
                    // If the range contains only one element
                    if (start == i - 1) {
                        res.add(nums[start] + "");
                    }
                    // 否则输出连续区间
                    // Otherwise, output the consecutive range in the format "start->i-1"
                    else {
                        res.add(nums[start] + "->" + nums[i - 1]);
                    }
                    // 更新起始位置
                    // Update the starting position for the next range
                    start = i;
                }
            }
        }
        return res;
    }


    /**
     * 遍历数组，记录连续开始的位置，比较前后元素是否连续，不连续时记录连续区间
     * Iterate through the array, track the start of consecutive sequences, compare consecutive elements,
     * and record the range when the sequence is interrupted.
     *
     * @param nums the input array of integers
     * @return a list of summary ranges in string format
     */
    public static List<String> summaryRanges1(int[] nums) {
        List<String> res = new ArrayList<>();
        // 如果数组为空或长度为0，返回空结果集
        // If the array is null or has a length of 0, return an empty result.
        if (nums == null || nums.length == 0) return res;

        // 连续区间起始位置
        // Variable to track the start of a consecutive range
        int start = 0;

        // 因为是跟前一个元素比较，所以从1遍历到 <=nums.length
        // Iterate from 1 to nums.length because we are comparing with the previous element
        for (int i = 1; i <= nums.length; i++) {
            // 当前值与前一值比较，如果连续则不进行操作；如果不连续则构造结果集；如果遍历到末端也需要构造结果集
            // Compare current value with the previous one; if consecutive, continue. If not, add the range to the result.
            // Also, handle the case when reaching the end of the array.
            if (i == nums.length || nums[i] - nums[i - 1] != 1) {
                // 连续区间为单个元素则输出单个元素
                // If the consecutive range has only one element, add it as a single number.
                if (start == i - 1) {
                    res.add(String.valueOf(nums[start]));
                }
                // 否则输出从start到i-1这段连续区间
                // Otherwise, add the range from start to i-1 in the format "start->i-1".
                else {
                    res.add(nums[start] + "->" + nums[i - 1]);
                }
                // 记录连续起始位置为当前下标
                // Update the start of the new range to the current index
                start = i;
            }
        }
        return res;
    }



}
