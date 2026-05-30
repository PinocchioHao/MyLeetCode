package AceCodingInterview75Qs.dp_2d;

/*
 *
 *
 *
1143. Longest Common Subsequence
Medium

Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.



Example 1:

Input: text1 = "abcde", text2 = "ace"
Output: 3
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.


Constraints:

1 <= text1.length, text2.length <= 1000
text1 and text2 consist of only lowercase English characters.
 *
 *
 */

public class LeetCode1143 {


    public static void main(String[] args) {

        int[] arr1 = {1};
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};

        LeetCode1143 example = new LeetCode1143();

        System.out.println(example.longestCommonSubsequence("abcde","ace"));
    }

    // dp动态规划
    // dp数组可以考虑到所有子串可能存在的最大子序列的情况，而像双指针等做法不具有”记忆性“
    // 把两个text转为 (m+1) x (n+1) 矩阵，其中第0行和第0列用0垫底，表示任意位置跟空串的最大子序列长度为0，最终结果就是dp[m][n]
    // dp[i][j]表示text1的i-1位置和text2第j-1位置的字符串的最大公共子序列长度
    // 状态转移方程： 如果当前位置的字符相等，则子串最大子序列长度+1，而dp[i-1][j-1]恰好就代表两个子串在前一个位置的最长子序列 dp[i][j] = dp[i-1][j-1] + 1;
    // 如果不等则dp[i][j]取前一个位置的最大子序列长度可以是text1对text2子串，也可以是text2对text1子串，可以取上方或左方的最大值
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        // 留出 0 行 0 列作为垫底空串。Java 默认初始化为 0，无需额外赋值
        int[][] dp = new int[m+1][n+1];
        // 声明时默认就是0，可不做特殊处理
//        for (int i = 0; i <= m; i++) {
//            dp[i][0] = 0;
//        }
//        for (int i = 0; i <= n; i++) {
//            dp[0][i] = 0;
//        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)){
                    // 字符相等：找到公共字符，长度 = 左上角剔除掉这两个字符的长度 + 1
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    // 字符不等：不可能同时作为公共子序列的尾巴，尝试各自退一步，取两者中的最大值
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[m][n];
    }




}