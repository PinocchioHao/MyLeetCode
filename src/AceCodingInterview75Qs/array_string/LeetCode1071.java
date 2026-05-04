package AceCodingInterview75Qs.array_string;

/*
 *
 *
 *
1071. Greatest Common Divisor of Strings

Easy

For two strings s and t, we say "t divides s" if and only if s = t + t + t + ... + t + t (i.e., t is concatenated with itself one or more times).

Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.



Example 1:

Input: str1 = "ABCABC", str2 = "ABC"

Output: "ABC"

Example 2:

Input: str1 = "ABABAB", str2 = "ABAB"

Output: "AB"

Example 3:

Input: str1 = "LEET", str2 = "CODE"

Output: ""

Example 4:

Input: str1 = "AAAAAB", str2 = "AAA"

Output: ""



Constraints:

1 <= str1.length, str2.length <= 1000
str1 and str2 consist of English uppercase letters.
 *
 *
 */

public class LeetCode1071 {


    public static void main(String[] args) {
        System.out.println(gcdOfStrings("abababab", "ababab"));
        System.out.println(gcdOfStrings1("abababab", "ababab"));
        System.out.println(gcdOfStrings2("abcabc", "abc"));
    }


    // brute force - expense O(n^2) time complexity, O(n) space complexity -- N squared
    // start with the shorter string and check if it divides both strings,
    // if not, remove the last character and check again until we find a common divisor or exhaust all possibilities
    public static String gcdOfStrings(String str1, String str2) {
        String rlt = str1.length() > str2.length() ? str2 : str1;
        while (rlt.length() > 0) {
            if (judgeDivisor(str1, rlt) && judgeDivisor(str2, rlt)) {
                return rlt;
            }
            rlt = rlt.substring(0, rlt.length() - 1);
        }
        return "";
    }

    public static boolean judgeDivisor(String target, String sub) {
        if (target.length() % sub.length() != 0) return false;
        int times = target.length() / sub.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(sub);
        }
        return sb.toString().equals(target);
    }

    // optimized brute force solution
    public static String gcdOfStrings1(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        for (int i = Math.min(len1, len2); i > 0; i--) {
            if (len1 % i == 0 && len2 % i == 0) {
                String rlt = str1.substring(0, i);
                if (judgeDivisor(str1, rlt) && judgeDivisor(str2, rlt)) {
                    return rlt;
                }
            }
        }
        return "";
    }


    // optimal solution - O(n) time complexity, O(1) space complexity
    // gcd algorithm
    public static String gcdOfStrings2(String str1, String str2) {
        // If the connected string of str1 and str2 in both orders is not the same, then there is no common divisor string,
        // return an empty string.
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        // Calculate the greatest common divisor of the lengths of str1 and str2,
        // which will be the length of the largest common divisor string.
        int gcdLength = gcd(str1.length(), str2.length());

        return str1.substring(0, gcdLength);
    }

    // 计算两个整数的最大公约数 - Calculate the greatest common divisor of two integers
    // 核心思想就是把原来的除数给 a，把余数给 b，直到余数为 0
    // 例如：a = 48, b = 18
    // 1. 48 % 18 = 12, a = 18, b = 12
    // 2. 18 % 12 = 6, a = 12, b = 6
    // 3. 12 % 6 = 0, a = 6, b = 0
    // 当 b 变成 0 时，当前的 a 就是最后一个能整除的数，即最大公约数
    public static int gcd(int a, int b) {
        // 迭代写法：
        // 只要余数 b 不为 0，就一直算下去
        while (b != 0) {
            int remainder = a % b; // 1. 先求出余数
            a = b;                 // 2. 把除数挪给 a，作为下一轮的被除数
            b = remainder;         // 3. 把余数挪给 b，作为下一轮的除数
        }
        // 除到最后，当 b 变成 0 时，当前的 a 就是最后一个能整除的数，即最大公约数
        return a;

        // 递归写法：
//        if (b == 0) return a;
//        return gcd(b, a % b);

    }


}


