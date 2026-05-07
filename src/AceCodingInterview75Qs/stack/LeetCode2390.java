package AceCodingInterview75Qs.stack;

/*
 *
 *
 *

2390. Removing Stars From a String

Medium

You are given a string s, which contains stars *.

In one operation, you can:

Choose a star in s.
Remove the closest non-star character to its left, as well as remove the star itself.
Return the string after all stars have been removed.

Note:

The input will be generated such that the operation is always possible.
It can be shown that the resulting string will always be unique.


Example 1:

Input: s = "leet**cod*e"
Output: "lecoe"
Explanation: Performing the removals from left to right:
- The closest character to the 1st star is 't' in "leet**cod*e". s becomes "lee*cod*e".
- The closest character to the 2nd star is 'e' in "lee*cod*e". s becomes "lecod*e".
- The closest character to the 3rd star is 'd' in "lecod*e". s becomes "lecoe".
There are no more stars, so we return "lecoe".
Example 2:

Input: s = "erase*****"
Output: ""
Explanation: The entire string is removed, so we return an empty string.


Constraints:

1 <= s.length <= 105
s consists of lowercase English letters and stars *.
The operation above can be performed on s.

 *
 *
 */


import java.util.*;
import java.util.stream.Collectors;

public class LeetCode2390 {


    public static void main(String[] args) {

        System.out.println(removeStars("abc**dgh"));
        System.out.println(removeStars2("abc**dgh"));
    }


    // 双端队列模拟栈，遇到*就弹出，否则压入
    // 开销有点大啊...
    public static String removeStars(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()){
            if (c == '*') {
                deque.removeLast();
            } else {
                deque.addLast(c);
            }
        }
        for (char c : deque) {
            sb.append(c);
        }
        return sb.toString();
    }

    // 使用StringBuilder模拟栈，性能迅速提升
    public static String removeStars1(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()){
            if (c == '*') {
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // 极致优化：使用char[]来模拟栈
    public static String removeStars2(String s) {
        char[] stack = new char[s.length()];
        // 模拟栈顶指针
        int top = 0;
        for (char c : s.toCharArray()){
            if (c == '*') {
                //
                if (top > 0) {
                    top--;
                }
            } else {
                stack[top] = c;
                top++;
            }
        }
        return new String(stack,0,top);
    }




}