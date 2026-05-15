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

    // 需要由内到外解析字符串，首先想到栈
    // 双栈，一个记录重复次数，一个记录子串，并且要注意方向
    // 遇到数字记录重复次数，注意这里可能多个位数来表示大数字
    // 遇到'['开始存档，将重复次数，以及遇到[之前的前缀子串入栈，并重置状态便于下一次入栈
    // 遇到']'开始读档并出栈进行拼接操作
    // 否则按照正常顺序记录当前子串
    public static String decodeString(String s) {
        // 存储每层嵌套重复次数 k
        Deque<Integer> repeatCountDeque = new ArrayDeque<>();
        // 存储进入嵌套前的已有字符串前缀
        Deque<StringBuilder> outerPrefixStack = new ArrayDeque<>();
        // 当前正在构造的字符串内容
        StringBuilder currStr = new StringBuilder();
        // 计数
        int k = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                // 处理多位数情况，将字符数字转换为整型并累加
                k = k * 10 + (c - '0');
            } else if (c == '[') {
                // 进入嵌套层级，遇到'['则开始“存档”，将之前已记录的字符串前缀入栈，以及将重复次数也入栈
                // 1. 将当前累加的倍数 k 压入栈中供后续出栈使用
                repeatCountDeque.addLast(k);
                // 2. 将当前层级已构造好的前缀字符串压入栈中进行现场保护
                outerPrefixStack.addLast(currStr);

                // 3. 重置状态：清除倍数计数器，并初始化新的 StringBuilder
                // 以便开始独立记录当前方括号内的字符串内容
                k = 0;
                currStr = new StringBuilder();
            } else if (c == ']') {
                // --- 嵌套层级处理结束，进行回溯合并 ---

                // 1. 获取当前层级构造完成的待重复字符串内容
                StringBuilder contentToRepeat = currStr;
                // 2. 开始拼接，获取上一层级（外部）暂存的前缀字符串
                // 此时 currentContent 指向弹出的前缀，作为合并的基础
                currStr = outerPrefixStack.removeLast();
                // 3. 获取该层级对应的重复倍数
                int times = repeatCountDeque.removeLast();
                // 4. 将当前层内容重复指定次数，追加到前缀字符串末尾
                for (int i = 0; i < times; i++){
                    currStr.append(contentToRepeat);
                }
                // 合并完成后，currentContent 自动成为包含当前层处理结果的最新前缀
            } else {
                // 处理普通字母字符，直接追加至当前层级的 StringBuilder
                currStr.append(c);
            }
        }

        return currStr.toString();
    }


}
