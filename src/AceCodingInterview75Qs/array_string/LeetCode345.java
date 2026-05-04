package AceCodingInterview75Qs.array_string;

/*
 *
 *
 *
345. Reverse Vowels of a String
Easy

Given a string s, reverse only all the vowels in the string and return it.

The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.



Example 1:

Input: s = "IceCreAm"

Output: "AceCreIm"

Explanation:

The vowels in s are ['I', 'e', 'e', 'A']. On reversing the vowels, s becomes "AceCreIm".

Example 2:

Input: s = "leetcode"

Output: "leotcede"



Constraints:

1 <= s.length <= 3 * 105
s consist of printable ASCII characters.

 *
 *
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LeetCode345 {


    public static void main(String[] args) {
        int arr[] = {0, 1, 0};
        System.out.println(reverseVowels("IceCreAm"));
        System.out.println(reverseVowels1("IceCreAm"));
    }

    // 遍历数组后，记录元素后创建反转后的字符串
    public static String reverseVowels(String s) {
        // 元音字符串，可以利用indexOf方法判断是否是元音，如果不是元音，返回-1
        String vowels = "aeiouAEIOU";
        char[] arr = s.toCharArray();
        // 2. 用一个布尔数组记录哪些位置是元音，相当于“打地基”
        boolean[] isVowel = new boolean[arr.length];
        // 3. 栈：利用它先进后出的特性。比如存入 'e', 'o'，弹出来就是 'o', 'e'，自动完成了反转。
        Stack<Character> stack = new Stack<>();

        // 第一遍遍历：收集物资
        for (int i = 0; i < arr.length; i++) {
            if (vowels.indexOf(arr[i]) != -1) { // 利用indexOf方法判断是否是元音，如果不是元音，返回-1
                isVowel[i] = true; // 标记此处需要“填空”
                stack.push(arr[i]); // 把元音关进栈里
            }
        }

        // 第二遍遍历：按标记填回
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (isVowel[i]) {
                // 如果是元音位置，从栈顶拿一个出来（拿到的正好是最后存进去的）
                sb.append(stack.pop());
            } else {
                // 如果不是元音，保持原样
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }


    // 双指针：从两端向中间靠拢，遇到元音就交换
    // double pointer: from both ends to the middle, swap when encounter a vowel
    public static String reverseVowels1(String s) {
        int l = 0;
        int r = s.length() - 1;
        String vowels = "aeiouAEIOU";
        char[] arr = s.toCharArray();
        while (l < r) {
            // 左右都是元音
//            if (vowels.indexOf(arr[l])!= -1 && vowels.indexOf(arr[r]) != -1){
//                char c = arr[l];
//                arr[l] = arr[r];
//                arr[r] = c;
//                l++;
//                r--;
//            }
            // 不是元音，继续往下走
//            if (vowels.indexOf(arr[l])== -1) l++;
//            if (vowels.indexOf(arr[r])== -1) r--;

            // 更高效版：先找到左边的元音，再找到右边的元音，最后交换
            while (l < r && vowels.indexOf(arr[l]) == -1) l++;
            while (l < r && vowels.indexOf(arr[r]) == -1) r--;
            // 经过while步骤，已找到原因，交换
            char c = arr[l];
            arr[l] = arr[r];
            arr[r] = c;
            l++;
            r--;
        }
        return new String(arr);
    }
}