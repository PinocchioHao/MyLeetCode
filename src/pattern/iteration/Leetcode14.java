package pattern.iteration;

import java.util.Arrays;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串""。
 *
 * 示例1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 *
 * @author Pinocchao
 */


public class Leetcode14 {

    public static void main(String[] args) {

//		Scanner scanner = new Scanner(System.in);
//		
//		while (scanner.hasNext()) {
//			System.out.println(scanner.next());
//		}
//		

        String[] strs = {"ccir", "c"};
//		System.out.println("a".substring(0,1));

        System.out.println(longestCommonPrefix(strs));

        System.out.println(longestCommonPrefix2(strs));
        System.out.println(longestCommonPrefix3(strs));
        System.out.println(longestCommonPrefix4(strs));


    }


    /**
     * From front to rear, append character
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix2(String[] strs) {
        int minLength = Integer.MAX_VALUE;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            minLength = Math.min(minLength, strs[i].length());
        }
        for (int i = 0; i < minLength; i++) {
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].charAt(i) != strs[0].charAt(i)) {
                    return sb.toString();
                }
                // Traverse to the last element, which means all the prefix char is equal in this round, append char
                if (j == strs.length - 1) {
                    sb.append(strs[j].charAt(i));
                }
            }
        }

        return sb.toString();
    }


    /**
     * From rear to front, substring
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        int allStrLen = strs.length;
        String shortestString = strs[0];
        int endIdx = 0;
        // find the shortest string
        for (int i = 1; i < allStrLen; i++) {
            if (strs[i].length() < shortestString.length()) {
                shortestString = strs[i];
            }
        }

        // compare from rear to front, substring the longest prefix
        for (int i = shortestString.length(); i > 0; i--) {
            for (int j = 0; j < allStrLen; j++) {
                if (!shortestString.substring(0, i).equals(strs[j].substring(0, i))) {
                    break;
                }
                //End flag -- all strings in the array is conpared, and find the longest prefix, return
                if (j == allStrLen - 1) {
                    endIdx = i;
                    return shortestString.substring(0, endIdx);
                }
            }
        }
        //None of them match, return an empty string
        return "";

    }

    /**
     * 简化寻找minLength操作，遍历两者之间较短的一方，整体时间复杂度相差不大
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix3(String[] strs) {
        int endIdx = 0;
        for (int i = 0; i < strs[0].length(); i++) {
            for (int j = 0; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != strs[0].charAt(i)) {
                    return strs[j].substring(0, endIdx);
                }
                if (j == strs.length - 1) {
                    endIdx = i + 1;
                }
            }

        }
        return strs[0].substring(0, endIdx);
    }


    /**
     * 字符串数组排序后只需比较首尾字符串，找最长子序列
     * @param strs
     * @return
     */
    public static String longestCommonPrefix4(String[] strs) {
        Arrays.sort(strs);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        String first = strs[0];
        String last = strs[strs.length - 1];
        while (i < first.length() && i < last.length() && first.charAt(i) == last.charAt(i)) {
            sb.append(first.charAt(i));
            i++;
        }

        return sb.toString();
    }


}