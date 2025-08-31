package pattern.iteration;

/*
 *
 *
258. Add Digits

Given an integer num, repeatedly add all its digits until the result has only one digit, and return it.

Example 1:

Input: num = 38
Output: 2
Explanation: The process is
38 --> 3 + 8 --> 11
11 --> 1 + 1 --> 2
Since 2 has only one digit, return it.
Example 2:

Input: num = 0
Output: 0

Constraints:

0 <= num <= 231 - 1

Follow up: Could you do it without any loop/recursion in O(1) runtime?
 *
 *
 *
 */

public class LeetCode258 {


    public static void main(String[] args) {

        System.out.println(addDigits2(56));
    }

    /**
     * Transfer the num to string, then add all digits
     *
     * @param num
     * @return
     */
    public static int addDigits(int num) {
        String numStr = String.valueOf(num);
        while (numStr.length() > 1) {
            int sum = 0;
            for (int i = 0; i < numStr.length(); i++) {
                // Can also use   int digitNum = Character.getNumericValue(numStr.charAt(i));
                int digitNum = numStr.charAt(i) - '0'; // 这里如果用Integer.valueOf(str.charAt(i)+"")开销会很大
                sum += digitNum;
            }
            numStr = String.valueOf(sum);
        }
        return Integer.parseInt(numStr);
    }

    /**
     * 每执行一次里层while，就将当前数的所有位加起来，并且更新当前数为各位相加后的数 -- 里层执行相加传入的位数
     * 然后再到外层判断，如果当前数仍为两位数以上，则继续进入内层的各位相加循环  -- 外层则相加新数的位数，注意里层结果计算完就要将计算结果传入外层
     * @param num
     * @return
     */
    public static int addDigits1(int num) {
        // Continue the process until the number is a single digit
        while (num >= 10) {
            int sum = 0;
            // Add all digits of the current number
            while (num != 0) {
                sum += num % 10; // Add the last digit to sum
                num = num / 10;  // Remove the last digit
            }
            // Update num to be the sum of its digits -- which is import！！
            num = sum;
        }
        return num; // Return the single digit result

    }


    /**
     * Recursive realization
     * @param num
     * @return
     */
    public static int addDigits2(int num) {
        // Base case: if the number is a single digit, return it
        if (num < 10) {
            return num;
        }else {
            // Recursive case: sum the last digit (num % 10) with the result of addDigits2 called on the rest of the digits (num / 10)
            return addDigits2(num%10 + addDigits2(num/10));
        }
    }



}