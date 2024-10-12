package recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17. Letter Combinations of a Phone Number
 * 
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 * 
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * 
 * 
 * Example 1:
 * 
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * Example 2:
 * 
 * Input: digits = ""
 * Output: []
 * Example 3:
 * 
 * Input: digits = "2"
 * Output: ["a","b","c"]
 */

public class LeetCode17 {
    public static void main(String[] args) {

        System.out.println(letterCombinations("23"));
    }


    public static List<String> letterCombinations(String digits) {

        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        List<String> digitsStrList = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            digitsStrList.add(map.get(digits.charAt(i)));
        }

        List<String> res = new ArrayList<>();
        return doRecorsion(digitsStrList, res);
    }


    // 先求出前n-1个号码字符的结果，然后再根据n-1个号码字符的结果，组合上第n个号码字符
    private static List<String> doRecorsion(List<String> strsList, List<String> rlt) {
        if (strsList.size() == 1) {
            for (int i = 0; i < strsList.get(0).length(); i++) {
                rlt.add(strsList.get(0).charAt(i) + "");
            }
            return rlt;
        }
        // 每一层递归，就将号码字符集分为前n-1个组合上第n个
        String str = strsList.remove(strsList.size() - 1);
        //递 - 到最下一层处理最基本情况
        rlt = doRecorsion(strsList, rlt);
        //归 - 往上每层就将前n-1的结果拼接上第n个
        rlt = appendStrs(rlt, str);
        return rlt;
    }

    // 将前n-1个号码字符结果集组合上第n个号码字符
    private static List<String> appendStrs(List<String> strsList, String toAppendStr) {
        // 注意不能在原来数组上操作，新建一个数组来返回结果
        List<String> temp = new ArrayList<>();
        for (String str : strsList) {
            for (int i = 0; i < toAppendStr.length(); i++) {
                temp.add(str + toAppendStr.charAt(i));
            }
        }
        return temp;
    }


}
