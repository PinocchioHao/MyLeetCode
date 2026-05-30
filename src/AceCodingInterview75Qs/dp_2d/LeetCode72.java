package AceCodingInterview75Qs.dp_2d;

/*
 *
 *
 *
72. Edit Distance
Medium

Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character


Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation:
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')


Constraints:

0 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.
 *
 *
 */

public class LeetCode72 {


    public static void main(String[] args) {

        int[] arr1 = {1};
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};

        LeetCode72 example = new LeetCode72();

        System.out.println(example.minDistance("abcde","ace"));
    }


    // 【经典字符串二维 DP】
    // 物理意义：dp[i][j] 表示将 word1 的前 i 个字符转换成 word2 的前 j 个字符所需的最少步数
    // 注意删除操作和增加操作其实有等效作用，word1中增加一个元素，可以看成word2中删除了一个元素（因为word1新增的元素跟word2的下一个元素相等抵消）
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // 考虑到空串所以多开一行一列，用于表示与“空串”比较的状态
        int[][] dp = new int[m+1][n+1];

        // 初始化第一列：word1 转成空串，只能不断删除
        for (int i = 0; i <= m; i++) dp[i][0] = i;
        // 初始化第一行：空串转成 word2，只能不断插入
        for (int j = 0; j <= n; j++) dp[0][j] = j;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    // 字符相等：直接继承左上角状态，不需要做任何操作
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    // 字符不等：在增、删、改中取最小代价
                    // 1. 删除：拿 word1 删掉一个字符后的状态匹配 word2
                    int del = dp[i-1][j] + 1;
                    // 2. 插入：拿 word1 匹配 word2 缺一个字符的状态（等效于 word2 删除）
                    int add = dp[i][j-1] + 1;
                    // 3. 替换：双方都干掉当前字符，去匹配剩下的
                    int rep = dp[i-1][j-1] + 1;

                    dp[i][j] = Math.min(del, Math.min(add, rep));
                }
            }
        }
        return dp[m][n];
    }


}