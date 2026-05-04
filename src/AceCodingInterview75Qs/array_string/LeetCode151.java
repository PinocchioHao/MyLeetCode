package AceCodingInterview75Qs.array_string;

/*
 *
 *
 *

151. Reverse Words in a String

Medium

Given an input string s, reverse the order of the words.

A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.

Return a string of the words in reverse order concatenated by a single space.

Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.



Example 1:

Input: s = "the sky is blue"
Output: "blue is sky the"
Example 2:

Input: s = "  hello world  "
Output: "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.
Example 3:

Input: s = "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.


Constraints:

1 <= s.length <= 104
s contains English letters (upper-case and lower-case), digits, and spaces ' '.
There is at least one word in s.


Follow-up: If the string data type is mutable in your language, can you solve it in-place with O(1) extra space?



 *
 *
 */

import java.util.*;

public class LeetCode151 {


    public static void main(String[] args) {
        int arr[] = {0, 1, 0};
        System.out.println(reverseWords("a good   example"));
        System.out.println(reverseWords1("a good   example"));
        System.out.println(reverseWords2("a good   example"));
    }

    // Java Api
    public static String reverseWords(String s) {
        List<String> list = new ArrayList();
        // split方法的参数是一个正则表达式，\\s+表示匹配一个或多个空格，这样可以处理多个连续的空格情况。
        list.addAll(Arrays.asList(s.split("\\s+")));
        Collections.reverse(list);
        return String.join(" ", list).trim();
    }


    // 双指针找单词 + 双端队列排序
    // double pointer to find words + deque to reverse order
    public static String reverseWords1(String s) {
        char[] arr = s.toCharArray();
        int left = 0;
        int right = 0;
        Deque<String> deque = new ArrayDeque<>();
        while (left < arr.length && right < arr.length){
            // 跳过空格，找到单词的起始位置 skip spaces to find the start of a word
            while (left < arr.length && arr[left] == ' '){
                left++;
            }
            // 跳过单词，找到单词的结束位置 skip the word to find the end of a word
            right = left;
            while (right < arr.length && arr[right] != ' '){
                right++;
            }
            // 将单词添加到双端队列的头部 add the word to the front of the deque
            deque.addFirst(new String(arr, left, right - left));
            left = right;
        }
        return String.join(" ", deque).trim();
    }


    // 双指针从右往左找单词 + StringBuilder拼接就相当于反转了
    public static String reverseWords2(String s) {
        int j = s.length() - 1;
        int i = j;
        StringBuilder res = new StringBuilder();

        while (i >= 0) {
            // 指针 i 往左走，直到遇到第一个空格（找单词左边界）
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }

            // 3. 此时 i+1 到 j 之间就是一个完整的单词
            // substring 是左闭右开，所以用 (i+1, j+1)
            res.append(s.substring(i + 1, j + 1)).append(" ");

            // 4. 跳过单词之间的所有多余空格
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }

            // 5. 让 j 追上 i，准备锁定下一个单词的末尾
            j = i;
        }

        // 最后 trim 一下，去掉末尾多出的那个空格
        return res.toString().trim();
    }





}