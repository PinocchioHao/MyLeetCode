package AceCodingInterview75Qs.backtrack;

/*
 *
 *
 *
17. Letter Combinations of a Phone Number
Medium

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.


Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
Example 2:

Input: digits = "2"
Output: ["a","b","c"]


Constraints:

1 <= digits.length <= 4
digits[i] is a digit in the range ['2', '9'].
 *
 *
 */

import java.util.ArrayList;
import java.util.List;

public class LeetCode17 {


    // 技巧：用数组索引巧妙代替一长串 if-else 映射
    // index 0 和 1 没有字母，留空占位
    private String[] phoneMap = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};


    public static void main(String[] args) {

        int[] arr1 = {3, 6, 7, 11};
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};

        LeetCode17 example = new LeetCode17();

        System.out.println(example.letterCombinations("55"));
        System.out.println(example.letterCombinations1("55"));
    }

    // ========================================================================
    // 方法一：分治法 / 自底向上的纯递归
    // 思路：当前字符串的所有组合 = 前 (len - 1) 个字符的组合结果 ✖️ 最后一个字符的所有可能
    // ========================================================================
    public List<String> letterCombinations(String digits) {
        // LeetCode 要求的边界拦截
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        return dfs(digits);
    }

    public List<String> dfs(String digits) {
        // 递归终止条件：字符串已经被切完了，返回一个空集合作为初始的“地基”
        if (digits.length() == 0){
            return new ArrayList<>();
        }

        int len = digits.length();
        // 剥离出当前最后一个字符
        char ch = digits.charAt(len - 1);

        // 核心递归：先去求解前面部分的组合结果，等前面算完了，再和当前字符 ch 进行组合
        return combine(dfs(digits.substring(0, len - 1)), ch);
    }

    // 组合拼接函数：将一堆已有的字符串（strings）分别与字符 c 的所有映射字母进行拼接
    public List<String> combine(List<String> strings, char c){
        List<String> res = new ArrayList<>();
        String digitStr = numToStr(c);

        // 特殊情况：如果是针对第一个输入的数字，前置组合结果是空的
        // 直接把当前数字对应的字母变成单字符字符串塞进去，作为初始组合
        if (strings.size() == 0){
            for (char ch : digitStr.toCharArray()){
                StringBuilder sb = new StringBuilder();
                sb.append(ch);
                res.add(sb.toString());
            }
        } else {
            // 正常情况：双层 for 循环，笛卡尔积式拼接字符串
            for (String str : strings) {
                for (char ch : digitStr.toCharArray()){
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append(ch);
                    res.add(sb.toString());
                }
            }
        }
        return res;
    }

    // （这里为了保留你的原始逻辑留下了这个函数，实际可以直接用 phoneMap 优化）
    public String numToStr(char i){
        if (i == '2') return "abc";
        if (i == '3') return "def";
        if (i == '4') return "ghi";
        if (i == '5') return "jkl";
        if (i == '6') return "mno";
        if (i == '7') return "pqrs";
        if (i == '8') return "tuv";
        if (i == '9') return "wxyz";
        return "";
    }




    // ========================================================================
    // 方法二：标准回溯法 / 自顶向下的 DFS
    // 思路：一条道走到黑，用一个 StringBuilder 记录当前路径，凑够长度就记录，然后撤销最后一步换个字母继续
    // 优势：空间复杂度极低，不需要在每层递归都创建大量 List 和 StringBuilder 对象
    // ========================================================================
    public List<String> letterCombinations1(String digits) {
        List<String> res = new ArrayList<>();

        // 【修正 Bug】：必须在这里拦截空串，否则后续 index == digits.length() 会把 "" 塞进 res 导致报错
        if (digits == null || digits.length() == 0) {
            return res;
        }

        // 把结果集 res 和路径 path 作为参数传进去，保持函数的无状态纯洁性
        backtrack(digits, 0, new StringBuilder(), res);
        return res;
    }

    public void backtrack(String digits, int index, StringBuilder path, List<String> res){
        // 1. 终止条件：探索深度 index 达到了输入数字的长度，说明拼满了一个有效的组合
        if (index == digits.length()){
            // 把路径快照转成 String 后，抄录到最终结果集里
            res.add(path.toString());
            return;
        }

        // 2. 状态映射：拿到当前所处层级（index）对应的数字，查表得到它能代表的字母集
        char digit = digits.charAt(index);
        String letters = phoneMap[digit - '0'];

        // 3. 核心模板：做选择 -> 递归下探 -> 撤销选择
        for (char c : letters.toCharArray()){
            // 【做选择】：当前字母加入 path
            path.append(c);

            // 【递归探索】：带着拼好的字母，继续去探索下一个数字（index + 1）
            backtrack(digits, index + 1, path, res);

            // 【撤销选择】：刚才加进去的字母已经探索尽了它的所有可能，必须把它删掉（恢复现场）
            // 腾出最后一个位置，让 for 循环的下一个字符 c 可以填进来
            path.deleteCharAt(path.length() - 1);
        }
    }




}