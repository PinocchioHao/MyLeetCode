package AceCodingInterview75Qs.hash_map_set;

/*
 *
 *
 *

1657. Determine if Two Strings Are Close

Medium

Two strings are considered close if you can attain one from the other using the following operations:

Operation 1: Swap any two existing characters.
For example, abcde -> aecdb
Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
You can use the operations on either string as many times as necessary.

Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.



Example 1:

Input: word1 = "abc", word2 = "bca"
Output: true
Explanation: You can attain word2 from word1 in 2 operations.
Apply Operation 1: "abc" -> "acb"
Apply Operation 1: "acb" -> "bca"
Example 2:

Input: word1 = "a", word2 = "aa"
Output: false
Explanation: It is impossible to attain word2 from word1, or vice versa, in any number of operations.
Example 3:

Input: word1 = "cabbba", word2 = "abbccc"
Output: true
Explanation: You can attain word2 from word1 in 3 operations.
Apply Operation 1: "cabbba" -> "caabbb"
Apply Operation 2: "caabbb" -> "baaccc"
Apply Operation 2: "baaccc" -> "abbccc"


Constraints:

1 <= word1.length, word2.length <= 105
word1 and word2 contain only lowercase English letters.

 *
 *
 */


import java.util.*;

public class LeetCode1657 {


    public static void main(String[] args) {
        int arr[] = {2, 1, -1};
        int arr2[] = {1, 12, -5, -6, 50, 3};
        System.out.println(closeStrings("abc", "bcaa"));
    }

    // 两个字符串的长度必须相同，字符集必须相同，字符出现的频率集合必须相同（不要求字符和频率一一对应）。
    public static boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) return false;
        HashMap<Character, Integer> map1 = new HashMap();
        for (int i = 0; i < word1.length(); i++) {
            char c1 = word1.charAt(i);
            map1.put(c1, map1.getOrDefault(c1, 0) + 1);
        }
        HashMap<Character, Integer> map2 = new HashMap();
        for (int j = 0; j < word2.length(); j++) {
            char c2 = word2.charAt(j);
            if (!map1.containsKey(c2)) return false;
            map2.put(c2, map2.getOrDefault(c2, 0) + 1);
        }
        Object[] countArr1 = map1.values().toArray();
        Object[] countArr2 = map2.values().toArray();

        Arrays.sort(countArr1);
        Arrays.sort(countArr2);
        return Arrays.equals(countArr1, countArr2);
    }

    // HashMap优化版
    public static boolean closeStrings1(String word1, String word2) {
        if (word1.length() != word2.length()) return false;
        HashMap<Character, Integer> map1 = new HashMap();
        HashMap<Character, Integer> map2 = new HashMap();
//        char[] chars1 = word1.toCharArray();
//        char[] chars2 = word2.toCharArray();
//        for (int i = 0; i < chars1.length; i++) {
//            char c1 = chars1[i];
//            map1.put(c1, map1.getOrDefault(c1, 0) + 1);
//        }
//        for (int j = 0; j < chars2.length; j++) {
//            char c2 = chars2[j];
//            if (!map1.containsKey(c2)) return false;
//            map2.put(c2, map2.getOrDefault(c2, 0) + 1);
//        }

        // merge填充map，操作更优雅
        for (char c : word1.toCharArray()) map1.merge(c, 1, Integer::sum);
        for (char c : word2.toCharArray()) map2.merge(c, 1, Integer::sum);

        // 比较字符集是否一致
        if(!map1.keySet().equals(map2.keySet())) return false;

        // 出现频率转为int[]并排序
        int[] countArr1 = map1.values().stream().mapToInt(i -> i).sorted().toArray();
        int[] countArr2 = map2.values().stream().mapToInt(i -> i).sorted().toArray();

        // 出现频率对比
        return Arrays.equals(countArr1, countArr2);
    }

    // 优化版
    // 数据较大，但是只包含26个小写字母，可以直接用int[26]数组来代替hashmap，下标即第几个字母，这样效率更高
    public static boolean closeStrings2(String word1, String word2) {
        if (word1.length() != word2.length()) return false;
        int[] count1 = new int[26];
        int[] count2 = new int[26];
        // 数组第几个下标就代表第几个字母，计数
        for (char c : word1.toCharArray()) count1[c - 'a']++;
        for (char c : word2.toCharArray()) count2[c - 'a']++;

        // 检查字符集是否相同
        for (int i = 0; i < 26; i++){
            // 如果某一个字符在其中一方存在另一方不存在
//            if ((count1[i] == 0 && count2[i] != 0) || (count1[i] == 0 && count2[i] != 0)) {
            if ((count1[i] == 0) != (count2[i] == 0)) {
                return false;
            }
        }

        // 排序比较频率
        Arrays.sort(count1);
        Arrays.sort(count2);

        return Arrays.equals(count1, count2);

    }

}