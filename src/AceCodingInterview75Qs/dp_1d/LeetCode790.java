package AceCodingInterview75Qs.dp_1d;

/*
 *
 *
 *
790. Domino and Tromino Tiling
Medium
Topics
premium lock icon
Companies
You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.


Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it modulo 109 + 7.

In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.



Example 1:


Input: n = 3
Output: 5
Explanation: The five different ways are shown above.
Example 2:

Input: n = 1
Output: 1


Constraints:

1 <= n <= 1000
 *
 *
 */

public class LeetCode790 {


    public static void main(String[] args) {

        int[] arr1 = {1};
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};

        LeetCode790 example = new LeetCode790();

        System.out.println(example.numTilings(5));
    }


    // 【状态机 DP】
    // 核心思想：把二维平面的拼图问题，每列的情况可以分为铺满和凸起两个状态，降维成一维的“边缘切线”状态转换。
    public int numTilings(int n) {
        // 基础边界：一列只有 1 种（竖放），两列有 2 种（两竖、两横）
        if (n == 1) return 1;
        if (n == 2) return 2;

        int MOD = 1000000007;
        // dp[i]：严丝合缝拼满前 i 列的方法数（边缘平齐）
        long[] dp = new long[n];
        // pa[i]：拼到第 i 列时，边缘多出一块的方法数（单侧凸起，统一按上凸处理，下凸对称）
        long[] pa = new long[n];

        dp[0] = 1;
        dp[1] = 2;
        pa[0] = 0;
        pa[1] = 1;

        for (int i = 2; i < n; i++) {
            // dp[i] 的来源：
            // 1. 前一列平齐 (dp[i-1])，补 1 个竖多米诺
            // 2. 前两列平齐 (dp[i-2])，补 2 个横多米诺
            // 3. 前一列不平齐 (pa[i-1])，补 1 个 L 形。因为上下凸起是对称的，所以 * 2
            dp[i] = (dp[i-1] + dp[i-2] + 2 * pa[i-1]) % MOD;

            // pa[i] (假设上凸) 的来源：
            // 1. 前一列下凸 (pa[i-1])，下半部分补 1 个横多米诺，延续上凸状态（上凸下凸等效，所以可以这样讨论）
            // 2. 前两列平齐 (dp[i-2])，补 1 个 L 形，强行造出一个上凸
            pa[i] = (pa[i-1] + dp[i-2]) % MOD;
        }
        return (int) dp[n-1];
    }


}