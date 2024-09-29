package iteration;

import java.util.*;
import java.util.stream.Collectors;

/**
 128. Longest Consecutive Sequence
 Medium

 Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

 You must write an algorithm that runs in O(n) time.


 Example 1:

 Input: nums = [100,4,200,1,3,2]
 Output: 4
 Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 Example 2:

 Input: nums = [0,3,7,2,5,8,4,6,0,1]
 Output: 9


 Constraints:

 0 <= nums.length <= 105
 -109 <= nums[i] <= 109
 */

public class LeetCode128 {
    public static void main(String[] args) {

        int[] a = {1,53,3,6,9};
        Set<Integer> set = Arrays.stream(a).boxed().collect(Collectors.toSet());

        System.out.println(longestConsecutive(a));
    }

    /**
     * 先将数组转化为set，再遍历set(开销O(n))，从set中取数判断是否连续(开销只要O(1))
     * 遍历集合要注意是否当前元素是某一连续队列起始，是就继续找它的后续节点，不是则直接进入下一次循环
     * Convert the array to a set, then iterate through the set (cost O(n)), checking for consecutive numbers from the set (cost O(1)).
     * When traversing the set, check if the current element is the start of a consecutive sequence; if so, continue to find its subsequent elements, otherwise move to the next iteration.
     * @param nums
     * @return
     */
    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        // 数组转为集合
        // Convert the array to a set
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());

        int longest = 0;
        // 只会遍历一遍set，时间复杂度O(n)，for里面套while实则只是线性遍历
        // We only iterate through the set once, with a time complexity of O(n); the nested while loop is essentially linear traversal.
        for(Integer num : set) {
            // set不包含num-1的元素，说明num可以作为新的起点；如果不加这个条件，则时间复杂度为O(n平方)
            // If the set does not contain num - 1, it indicates that num can be a new starting point; without this condition, the time complexity would be O(n^2).
            if (!set.contains(num - 1)) {
                // 若是开始节点
                // If it's a starting node
                int curNum = num;
                int curLen = 1;
                // 遍历整段连续数组，注意判定条件！
                // Traverse the entire segment of consecutive numbers; pay attention to the condition!
                while (set.contains(curNum + 1)) {
                    curNum++;
                    curLen++;
                }
                longest = Math.max(longest, curLen);
            }
        }
        return longest;
    }




}
