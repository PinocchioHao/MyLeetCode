package AceCodingInterview75Qs.dp_2d;

/*
 *
 *
 *
62. Unique Paths
Medium
Topics
premium lock icon
Companies
There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 109.



Example 1:


Input: m = 3, n = 7
Output: 28
Example 2:

Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down


Constraints:

1 <= m, n <= 100
 *
 *
 */

public class LeetCode62 {


    public static void main(String[] args) {

        int[] arr1 = {1};
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};

        LeetCode62 example = new LeetCode62();

        System.out.println(example.uniquePaths(3,7));
    }


    // 【基础二维 DP】
    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) return 1;

        // dp[i][j] 表示从起点走到坐标 (i, j) 的路径总数
        int[][] dp = new int[m][n];

        // 边界初始化：贴着上边缘和左边缘走，永远只有 1 条直路
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int i = 0; i < n; i++) dp[0][i] = 1;

        // 状态转移：当前格子的路径数 = 从上面走下来的路径数 + 从左边走过来的路径数
        // dp[i][j]来源于dp[i-1][j]或者dp[i][j-1]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

}