package AceCodingInterview75Qs.dp_1d;

/*
 *
 *
 *
746. Min Cost Climbing Stairs
Easy

You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.

You can either start from the step with index 0, or the step with index 1.

Return the minimum cost to reach the top of the floor.


Example 1:

Input: cost = [10,15,20]
Output: 15
Explanation: You will start at index 1.
- Pay 15 and climb two steps to reach the top.
The total cost is 15.
Example 2:

Input: cost = [1,100,1,1,1,100,1,1,100,1]
Output: 6
Explanation: You will start at index 0.
- Pay 1 and climb two steps to reach index 2.
- Pay 1 and climb two steps to reach index 4.
- Pay 1 and climb two steps to reach index 6.
- Pay 1 and climb one step to reach index 7.
- Pay 1 and climb two steps to reach index 9.
- Pay 1 and climb one step to reach the top.
The total cost is 6.


Constraints:

2 <= cost.length <= 1000
0 <= cost[i] <= 999
 *
 *
 */

import java.util.Arrays;

public class LeetCode746 {


    public static void main(String[] args) {

        int[] arr1 = {10, 15, 20};
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};

        LeetCode746 example = new LeetCode746();

        System.out.println(example.minCostClimbingStairs(arr1));
    }


    // ==========================================
    // 动态规划五部曲：
    // 1. 定义：dp[i] 表示到达第 i 级台阶所需要的【最小】花费
    // 2. 递推：dp[i] = Math.min(走一步跨过来, 走两步跨过来)
    // 3. 初始化：题目说可以从 0 或 1 开始，所以到达 0 和 1 的代价是 0
    // ==========================================
    public int minCostClimbingStairs(int[] cost) {
        // dp 数组长度设为 cost.length + 1
        // 因为楼梯顶端其实是在最后一个台阶的再上面一格
        int[] dp = new int[cost.length + 1];

        // 初始化基石：直接站上去不需要花费
        dp[0] = 0;
        dp[1] = 0;

        // 从第 2 级台阶开始推导，直到爬到楼顶 (cost.length)
        for (int i = 2; i < dp.length; i++) {
            // 核心状态转移：
            // 选择一：从前一级 (i-1) 跨一步上来，要花掉前一级的门票钱 cost[i-1]
            // 选择二：从前两级 (i-2) 跨两步上来，要花掉前两级的门票钱 cost[i-2]
            // 取两者中的最小值！
            dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2]);
        }

        // dp 数组的最后一个元素就是登顶的最小总代价
        return dp[dp.length - 1];

        // 空间压缩版
//        // x 代表 dp[i-2]（跳到前两阶的最小总花费），初始化也就是 dp[0] = 0
//        int x = 0;
//        // y 代表 dp[i-1]（跳到前一阶的最小总花费），初始化也就是 dp[1] = 0
//        int y = 0;
//        int ans = 0;
//        for (int i = 2; i <= cost.length; i++) {
//            // 1. 根据前两个状态，算出到达当前台阶 i 的最小花费
//            ans = Math.min(x + cost[i - 2], y + cost[i - 1]);
//            // 2. 核心：窗口整体向右滑动一格，准备迎接下一轮循环
//            x = y;      // 昨天的 dp[i-1] 变成了明天的 dp[i-2]
//            y = ans;    // 今天刚算出的 dp[i] 变成了明天的 dp[i-1]
//        }
//        return ans;

    }

}