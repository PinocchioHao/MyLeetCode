package AceCodingInterview75Qs.stack;

/*
 *
 *
 *

394. Decode String

Medium

Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].

The test cases are generated so that the length of the output will never exceed 105.



Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"
Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"


Constraints:

1 <= s.length <= 30
s consists of lowercase English letters, digits, and square brackets '[]'.
s is guaranteed to be a valid input.
All the integers in s are in the range [1, 300].

 *
 *
 */


import java.util.ArrayDeque;
import java.util.Deque;

public class LeetCode394 {


    public static void main(String[] args) {
        int[] arr = {10, 2, -5};
        System.out.println(decodeString("2[abc]3[cd]ef"));
    }


    // 双栈，一个记录重复次数，一个记录子串，并且要注意方向
    // 遇到数字记录重复次数，注意这里可能多个位数来表示大数字
    // 遇到'['开始存档，当前记录的子串和重复次数入栈，并重置状态便于下一次入栈
    // 遇到']'开始读档并出栈进行拼接操作
    // 否则按照正常顺序记录当前子串
    public static String decodeString(String s) {
        Deque<Integer> countDeque = new ArrayDeque<>();
        Deque<StringBuilder> subStrDeque = new ArrayDeque<>();
        StringBuilder currStr = new StringBuilder();
        // 计数
        int k = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                // 记录数字
                k = k * 10 + (c - '0');
            } else if (c == '[') {
                // 遇到'['则开始“存档”
                // count入栈并重置
                countDeque.addLast(k);
                k = 0;
                // 将已记录的子串入栈
                subStrDeque.addLast(currStr);
                currStr = new StringBuilder();
            } else if (c==']') {
                // 遇到']'出栈并进行拼接操作
                // 当前子串，出栈后需要进行重复拼接操作
                StringBuilder repeatStr = currStr;
                // 先前的子串，注意需要拼接在重复子串前面
                currStr = subStrDeque.removeLast();
                int repeatTimes = countDeque.removeLast();
                while (repeatTimes > 0) {
                    currStr.append(repeatStr);
                    repeatTimes--;
                }
            } else {
                // 否则正常顺序记录当前的子串
                currStr.append(c);
            }

        }

        return currStr.toString();
    }


}
