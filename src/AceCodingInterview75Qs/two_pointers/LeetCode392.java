package AceCodingInterview75Qs.two_pointers;

/*
 *
 *
 *

392. Is Subsequence

Easy

Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).



Example 1:

Input: s = "abc", t = "ahbgdc"
Output: true
Example 2:

Input: s = "axc", t = "ahbgdc"
Output: false


Constraints:

0 <= s.length <= 100
0 <= t.length <= 104
s and t consist only of lowercase English letters.


Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 109, and you want to check one by one to see if t has its subsequence. In this scenario, how would you change your code?


 *
 *
 */

public class LeetCode392 {


    public static void main(String[] args) {
        int arr[] = {0, 1, 0, 3, 12};
        isSubsequence("abc", "ahbgdc");
    }

    // 双指针：一个指针 i 用于遍历字符串 s，另一个指针 j 用于遍历字符串 t
    // 只需遍历一次， O(n) time complexity, O(1) space complexity
    public static boolean isSubsequence(String s, String t) {
        if (s.length() == 0) {
            return true;
        }
        if (s.length() > t.length()) {
            return false;
        }
        int i = 0;
        int j = 0;
        while (i < s.length() && j < t.length()) {
            // s的当前字符与t的当前字符相同，则同时移动两个指针；否则只移动t的指针，继续比较s的当前字符和t的下一个字符
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
            // 找到s的所有字符都在t中按顺序出现过，直接返回 true
            if (i == s.length()) {
                return true;
            }
        }
        // 遍历完后如果s的指针没有移动到s的末尾，说明s的某些字符没有在t中按顺序出现过，返回 false
        return false;

    }

    // Java API - 底层优化好，效率更高
    // O(n) time complexity, O(1) space complexity
    public static boolean isSubsequence1(String s, String t) {
        int index = -1;
        for (char c : s.toCharArray()) {
            // 注意这里要从上一个找到的字符位置之后开始找下一个字符，如果找不到，直接返回 false
            index = t.indexOf(c, index + 1);
            if (index == -1) {
                return false;
            }
        }
        return true;
    }
}