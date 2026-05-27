package AceCodingInterview75Qs.binary_search;

/*
 *
 *
 *
2300. Successful Pairs of Spells and Potions

Medium

You are given two positive integer arrays spells and potions, of length n and m respectively, where spells[i] represents the strength of the ith spell and potions[j] represents the strength of the jth potion.

You are also given an integer success. A spell and potion pair is considered successful if the product of their strengths is at least success.

Return an integer array pairs of length n where pairs[i] is the number of potions that will form a successful pair with the ith spell.



Example 1:

Input: spells = [5,1,3], potions = [1,2,3,4,5], success = 7
Output: [4,0,3]
Explanation:
- 0th spell: 5 * [1,2,3,4,5] = [5,10,15,20,25]. 4 pairs are successful.
- 1st spell: 1 * [1,2,3,4,5] = [1,2,3,4,5]. 0 pairs are successful.
- 2nd spell: 3 * [1,2,3,4,5] = [3,6,9,12,15]. 3 pairs are successful.
Thus, [4,0,3] is returned.
Example 2:

Input: spells = [3,1,2], potions = [8,5,8], success = 16
Output: [2,0,2]
Explanation:
- 0th spell: 3 * [8,5,8] = [24,15,24]. 2 pairs are successful.
- 1st spell: 1 * [8,5,8] = [8,5,8]. 0 pairs are successful.
- 2nd spell: 2 * [8,5,8] = [16,10,16]. 2 pairs are successful.
Thus, [2,0,2] is returned.


Constraints:

n == spells.length
m == potions.length
1 <= n, m <= 105
1 <= spells[i], potions[i] <= 105
1 <= success <= 1010
 *
 *
 */

import java.util.Arrays;

public class LeetCode2300 {

    public static void main(String[] args) {

        int[] arr1 = {1,2,3,4,5,6,7};
        int[] arr2 = {1,2,3,4,5,6,7};

        LeetCode2300 example = new LeetCode2300();
        int[] rlt = example.successfulPairs(arr1, arr2, 25);
        System.out.println(Arrays.toString(rlt));
    }


    // 思路：排序后二分查找，找到第一个乘积大于等于success的下标
    // 但是我的方法有点问题，long prev = spell * potions[mid - 1];
    // 这里不仅有越界的风险，并且在二分查找中找第一个符合xxx条件的元素也不应该是加限定条件break，
    // 常规解法是使用二分查找找边界，让它自己跑完，最后指针停留的位置即边界
//    public int[] successfulPairs(int[] spells, int[] potions, long success) {
//        int[] res = new int[spells.length];
//        Arrays.sort(potions);
//
//        for (int i = 0; i < spells.length; i++){
//            int spell = spells[i];
//            int left = 0;
//            int right = potions.length - 1;
//            while (left < right) {
//                int mid = left + (right - left) / 2;
//                long prd = spell * potions[mid];
//
//                if (prd < success) {
//                    left = mid + 1;
//                } else {
//                    // 找到第一个大于等于successful的位置，记录长度并跳出
//                    long prev = spell * potions[mid - 1];
//                    if (mid ==0 || prev < success){
//                        System.out.println("prev Num: " + spell * potions[mid - 1]);
//                        res[i] = (potions.length - mid);
//                        break;
//                    } else {
//                        right = mid;
//                    }
//                }
//            }
//        }
//        return res;
//    }




    // 思路：排序后找到第一个乘积大于等于successful的位置，记录长度
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int[] res = new int[spells.length];
        Arrays.sort(potions);

        for (int i = 0; i < spells.length; i++){
            int spell = spells[i];
            int left = 0;
            int right = potions.length;
            while (left < right) {
                int mid = left + (right - left) / 2;
                // 注意这里必须手动转一次，直接long prd 不行，后面还是int计算
                long prd = (long)spell * potions[mid];
                if (prd < success) {
                    left = mid + 1;
                } else {
                    right = mid;
                }

            }
            res[i] = (potions.length - right);
        }
        return res;
    }

}