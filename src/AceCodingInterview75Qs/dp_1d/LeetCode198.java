package AceCodingInterview75Qs.dp_1d;

/*
 *
 *
 *
198. House Robber
Medium

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.


Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.


Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 400
 *
 *
 */

public class LeetCode198 {


    public static void main(String[] args) {

        int[] arr1 = {1};
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};

        LeetCode198 example = new LeetCode198();

        System.out.println(example.rob(arr1));
    }


    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        // dp[i]表示打劫到这一家的时候能获取的钱财最大值
        // 或者可以用dp[i]表示打劫完这一家能获得的最大钱财数，长度为nums.length+1，初始化dp[0]=0, dp[1]=nums[0], dp[i] = Math.max(nums[i - 1] + dp[i-2], dp[i-1]); 就能包含所有情况
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        // 注意dp[1]选二者之间最有钱的打劫，如果dp[1]搞错了会导致后续状态转移方程都错
        dp[1] = Math.max(nums[0], nums[1]);
        // 每遍历到一个位置，可以选择打劫和不打劫这一家
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }

        return dp[dp.length - 1];
    }


    // 优化数组，复杂度从O(n)降为两个变量
    public int rob1(int[] nums) {
        if (nums.length == 1) return nums[0];

        // prev2 相当于 dp[i-2]
        int prev2 = nums[0];
        // prev1 相当于 dp[i-1]
        int prev1 = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            // curr 相当于当前的 dp[i]
            int curr = Math.max(nums[i] + prev2, prev1);

            // 【滚动更新】：所有状态整体往前挪一步，迎接下一次循环
            prev2 = prev1; // 旧的 prev1 变成了下一次的 prev2
            prev1 = curr;  // 当前算出的结果变成了下一次的 prev1
        }

        // 循环结束后，prev1 停在最后一步，也就是最终答案
        return prev1;
    }




}