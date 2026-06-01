package AceCodingInterview75Qs.bit_manitulation;

/*
 *
 *
 *
338. Counting Bits
Easy

Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.


Example 1:

Input: n = 2
Output: [0,1,1]
Explanation:
0 --> 0
1 --> 1
2 --> 10
Example 2:

Input: n = 5
Output: [0,1,1,2,1,2]
Explanation:
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101


Constraints:

0 <= n <= 105
 *
 *
 */

public class LeetCode338 {


    public static void main(String[] args) {

        int[] arr1 = {1};
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};

        LeetCode338 example = new LeetCode338();

        System.out.println(example.countBits(5));
    }

    // 暴力解法逐个计算
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            res[i] = count(i);
        }
        return res;
    }

    public int count(int n) {
        int cnt = 0;
//        while (n != 0) {
//            int i = n / 2;
//            int j = n % 2;
//            if (j == 1) cnt++;
//            n = i;
//        }

        while(n != 0) {
            // 【优化】：用位运算替代低效的取模和除法
            if ((n & 1) == 1) cnt++; // 等效于 n % 2 == 1
            n = n >> 1;              // 等效于 n / 2
        }

        return cnt;
    }

    // 位运算 + DP
    //数字 4 (100) 的 1 的个数，和数字 2 (10) 是一样的！（右移了一位）
    //数字 5 (101) 的 1 的个数，正好是数字 2 (10) 的个数 + 1！（右移一位后，加上末尾的那个 1）
    //也就是说，对于任意数字 i，它包含的 1 的个数，等于 i / 2 包含的 1 的个数，加上 i 本身是奇数还是偶数！
    public int[] countBits1(int n) {
        int[] res = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            // i >> 1 就是 i / 2，以前算过了，直接查表
            // i & 1 就是看末尾是不是 1，如果是的话就给它+1，如果是偶数就+0，没问题
            res[i] = res[i >> 1] + (i & 1);
        }
        return res;
    }

}