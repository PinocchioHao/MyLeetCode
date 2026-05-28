package AceCodingInterview75Qs.dp_1d;

/*
 *
 *
 *
1137. N-th Tribonacci Number
Easy

The Tribonacci sequence Tn is defined as follows:

T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.

Given n, return the value of Tn.

Example 1:

Input: n = 4
Output: 4
Explanation:
T_3 = 0 + 1 + 1 = 2
T_4 = 1 + 1 + 2 = 4
Example 2:

Input: n = 25
Output: 1389537


Constraints:

0 <= n <= 37
The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.
 *
 *
 */

import java.util.ArrayList;
import java.util.List;

public class LeetCode1137 {


    public static void main(String[] args) {

        int[] arr1 = {3, 6, 7, 11};
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};

        LeetCode1137 example = new LeetCode1137();

        System.out.println(example.tribonacci(1));
        System.out.println(example.tribonacci1(1));
    }

    // ==========================================
    // 暴力递归解法（会超时 TLE）
    // 原因：存在大量重叠子问题，比如计算 T(5) 需要 T(4), T(3), T(2)，
    // 而计算 T(4) 又要重新算一遍 T(3), T(2)... 呈指数级爆炸
    // ==========================================
    public int tribonacci(int n) {
        if(n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        return tribonacci(n-3) + tribonacci(n-1) + tribonacci(n-2);
    }

    // ==========================================
    // 一维 DP 解法：记忆化递推（空间换时间）
    // 把每次算出来的结果用数组存起来，下次直接用 $O(N)$ 时间复杂度
    // ==========================================
    public int tribonacci1(int n) {
        // 先做边界特判，防止后续初始化 prev[1] 或 prev[2] 时发生数组越界
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;

        // 1. 状态定义：prev[i] 记录第 i 个泰波那契数的值
        int[] prev = new int[n + 1];

        // 2. 初始化基石
        prev[0] = 0;
        prev[1] = 1;
        prev[2] = 1;

        // 3. 遍历顺序：从前往后推导
        for (int i = 3; i <= n; i++) {
            // 4. 状态转移方程：当前数等于前三个数之和
            prev[i] = prev[i-1] + prev[i-2] + prev[i-3];
        }

        // 5. 返回目标值
        return prev[n];
    }

}