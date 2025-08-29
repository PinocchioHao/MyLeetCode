package iteration;

/*
 * 
 * 
13. Roman to Integer
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer.

Example 1:

Input: s = "III"
Output: 3
Explanation: III = 3.
Example 2:

Input: s = "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.
Example 3:

Input: s = "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

Constraints:

1 <= s.length <= 15
s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
It is guaranteed that s is a valid roman numeral in the range [1, 3999].
 * 
 */

import java.util.HashMap;
import java.util.Map;

public class LeetCode13 {

    public static void main(String[] args) {

//        String a = "abcdee";
//		System.out.println(a.substring(1, 3));
//		System.out.println(a.substring(2));

        System.out.println(romanToInt3("IV"));
    }

    /**
     * My solution
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        Map<String, Integer> romSymbolValueMap = new HashMap<>();
        romSymbolValueMap.put("I", 1);
        romSymbolValueMap.put("V", 5);
        romSymbolValueMap.put("X", 10);
        romSymbolValueMap.put("L", 50);
        romSymbolValueMap.put("C", 100);
        romSymbolValueMap.put("D", 500);
        romSymbolValueMap.put("M", 1000);
        romSymbolValueMap.put("IV", 4);
        romSymbolValueMap.put("IX", 9);
        romSymbolValueMap.put("XL", 40);
        romSymbolValueMap.put("XC", 90);
        romSymbolValueMap.put("CD", 400);
        romSymbolValueMap.put("CM", 900);
        int romNum = 0;
        String calcSymbol = s;
        while (calcSymbol.length() > 0) {
            String temp;
            if (calcSymbol.length() >= 2) {
                temp = calcSymbol.substring(0, 2);
                if (romSymbolValueMap.containsKey(temp)) {
                    romNum += romSymbolValueMap.get(temp);
                    calcSymbol = calcSymbol.substring(2);
                } else {
                    temp = calcSymbol.substring(0, 1);
                    romNum += romSymbolValueMap.get(temp);
                    calcSymbol = calcSymbol.substring(1);
                }
            }
			else {
				temp = calcSymbol.substring(0, 1);
				romNum += romSymbolValueMap.get(temp);
				calcSymbol = calcSymbol.substring(1);
			}

        }
		return romNum;
    }

    /**
     * Regard IV,IX,XL,etc as individual items
     * Use String.replace()
     * Then add them all
     * @param s
     * @return
     */
    public int romanToInt1(String s) {
        s = s.replace("IV","a");
        s = s.replace("IX","b");
        s = s.replace("XL","c");
        s = s.replace("XC","d");
        s = s.replace("CD","e");
        s = s.replace("CM","f");

        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res += getValue(s.charAt(i));
        }
        return res;
    }

    public int getValue(char c) {
        switch(c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            case 'a': return 4;
            case 'b': return 9;
            case 'c': return 40;
            case 'd': return 90;
            case 'e': return 400;
            case 'f': return 900;
        }
        return 0;
    }





    /**
     * Scan every character of the string
     * If the former is less than the scanned character now, then subtract
     * @param s
     * @return
     */
    public int romanToInt2(String s) {
        Map<Character, Integer> romanToInt = new HashMap<>();
        romanToInt.put('I', 1);
        romanToInt.put('V', 5);
        romanToInt.put('X', 10);
        romanToInt.put('L', 50);
        romanToInt.put('C', 100);
        romanToInt.put('D', 500);
        romanToInt.put('M', 1000);

        int total = 0;
        int prevValue = 0;

        for (char c : s.toCharArray()) {
            int currentValue = romanToInt.get(c);
            if (currentValue > prevValue) {
                // substact 2*prevValue, because it was added before
                total += currentValue - 2 * prevValue;
            } else {
                total += currentValue;
            }
            prevValue = currentValue;
        }

        return total;

    }

    /**
     * while循环直接遍历累加 - 注意步进不规律择选while循环
     * @param s
     * @return
     */
    public static int romanToInt3(String s) {
        int rlt = 0;
        int i = 0;
        while (i < s.length()) {
            // 先判断前两位，如果前两位是以下特殊情况则累加后跳过后面所有步骤，防止这一轮再加一次，以及防止之后逻辑数组越界
            if(i<s.length() - 1){
                String sub = s.substring(i, i + 2);
                switch (sub) {
                    case "IV": rlt+=4; i+=2; continue;
                    case "IX": rlt+=9; i+=2; continue;
                    case "XL": rlt+=40; i+=2; continue;
                    case "XC": rlt+=90; i+=2; continue;
                    case "CD": rlt+=400; i+=2; continue;
                    case "CM": rlt+=900; i+=2; continue;
                }
            }
            // 不是连着两位的特殊情况，则可以直接累加
            char c = s.charAt(i);
            switch (c) {
                case 'I': rlt+=1; i++; break;
                case 'V': rlt+=5; i++; break;
                case 'X': rlt+=10; i++; break;
                case 'L': rlt+=50; i++; break;
                case 'C': rlt+=100; i++; break;
                case 'D': rlt+=500; i++; break;
                case 'M': rlt+=1000; i++; break;
            }
        }
        return rlt;
    }
    

}


