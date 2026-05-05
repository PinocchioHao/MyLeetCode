package AceCodingInterview75Qs.sliding_window;

/*
 *
 *
 *

1456. Maximum Number of Vowels in a Substring of Given Length

Medium

Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.

Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.



Example 1:

Input: s = "abciiidef", k = 3
Output: 3
Explanation: The substring "iii" contains 3 vowel letters.
Example 2:

Input: s = "aeiou", k = 2
Output: 2
Explanation: Any substring of length 2 contains 2 vowels.
Example 3:

Input: s = "leetcode", k = 3
Output: 2
Explanation: "lee", "eet" and "ode" contain 2 vowels.


Constraints:

1 <= s.length <= 105
s consists of lowercase English letters.
1 <= k <= s.length


 *
 *
 */

public class LeetCode1456 {


    public static void main(String[] args) {
        int arr[] = {0, 4, 0, 3, 2};
        int arr2[] = {1, 12, -5, -6, 50, 3};
        System.out.println(maxVowels("abciiidef", 3));
    }


    // 固定滑窗
    public static int maxVowels(String s, int k) {
        int windowVowelNums = 0;
        char[] charArray = s.toCharArray();
        for (int i = 0; i < k; i++) {
            if (isVowel(charArray[i])) {
                windowVowelNums++;
            }
        }
        int maxVowelNums = windowVowelNums;
        for (int i = k; i < charArray.length; i++) {
            // 右移窗口时检查左右边界元素，左边元音减少，右边元音增加
            if (isVowel(charArray[i - k])) windowVowelNums--;
            if (isVowel(charArray[i])) windowVowelNums++;
            maxVowelNums = Math.max(maxVowelNums, windowVowelNums);
        }
        return maxVowelNums;
    }

    // 判断是否为元音字母，语法越简单效率越高
    public static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    // 前缀和思想 记录前i个元素中元音的个数，prefixSum[i] - prefixSum[i-k] 就是长度为k的滑窗子串中元音的个数
    public static int maxVowels1(String s, int k) {
        // 前i个字符中元音和的个数，prefixSum[0] = 0
        int[] prefixSum = new int[s.length() + 1];
        char[] charArray = s.toCharArray();
        int maxVowelNums = 0;
        for (int i = 1; i < prefixSum.length; i++) {
            // 当前元素是元音则前缀和加1，否则不变
            if (isVowel(charArray[i - 1])) {
                prefixSum[i] = prefixSum[i - 1] + 1;
            } else {
                prefixSum[i] = prefixSum[i - 1];
            }
            // 遍历到大于等于k的位置时，计算当前长度为k的子串中元音字母的个数，并更新最大值
            if (i >= k) {
                maxVowelNums = Math.max(maxVowelNums, prefixSum[i] - prefixSum[i - k]);
            }
        }
        return maxVowelNums;
    }


}