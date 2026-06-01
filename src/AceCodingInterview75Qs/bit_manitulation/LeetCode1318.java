package AceCodingInterview75Qs.bit_manitulation;

/*
 *
 *
 *
1318. Minimum Flips to Make a OR b Equal to c
Medium

Given 3 positives numbers a, b and c. Return the minimum flips required in some bits of a and b to make ( a OR b == c ). (bitwise OR operation).
Flip operation consists of change any single bit 1 to 0 or change the bit 0 to 1 in their binary representation.



Example 1:

Input: a = 2, b = 6, c = 5
Output: 3
Explanation: After flips a = 1 , b = 4 , c = 5 such that (a OR b == c)
Example 2:

Input: a = 4, b = 2, c = 7
Output: 1
Example 3:

Input: a = 1, b = 2, c = 3
Output: 0


Constraints:

1 <= a <= 10^9
1 <= b <= 10^9
1 <= c <= 10^9
 *
 *
 */

public class LeetCode1318 {


    public static void main(String[] args) {

        int[] arr1 = {1};
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};

        LeetCode1318 example = new LeetCode1318();

        System.out.println(example.minFlips(2, 6, 5));
    }


    // ==========================================
    // 位运算：逐位对比法（Bit by Bit）
    // 核心思想：每次抽取出 a, b, c 的最低位，结合或运算a||b==c的条件讨论进行翻转代价计算，然后整体右移
    // ==========================================
    public int minFlips(int a, int b, int c) {
        int flipCnt = 0;

        // 只要这三个数还有一个没变成 0（没移尽），就继续比较
        while (a > 0 || b > 0 || c > 0) {
            // 【核心操作】：通过 & 1 抽取各自当前的最低位
            int bitA = a & 1;
            int bitB = b & 1;
            int bitC = c & 1;

            // 根据或运算 (OR) 的规则进行分类讨论：
            if (bitC == 1) {
                // 目标是 1：a 和 b 只要有一个是 1 就行。
                // 只有当两个都是 0 的时候，我们才需要强行翻转其中一个。
                if (bitA == 0 && bitB == 0) {
                    flipCnt++;
                }
            } else {
                // 目标是 0：a 和 b 必须双双为 0！
                // 【代码优化】：有几个 1，就得付出几次翻转的代价，直接把相加即可！
                // 比如 bitA=1, bitB=1，得翻 2 次；只有一个为 1，翻 1 次；都是 0，翻 0 次。
                flipCnt += (bitA + bitB);
            }

            // 比较完当前最低位后，三个数字集体向右平移一位，把刚才比较过的位丢掉
            // 注意：0 >> 1 依然是 0，所以不怕越界
            a >>= 1; // a = a>>1;
            b >>= 1;
            c >>= 1;
        }

        return flipCnt;
    }

}