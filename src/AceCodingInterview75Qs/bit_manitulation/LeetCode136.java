package AceCodingInterview75Qs.bit_manitulation;

/*
 *
 *
 *
136. Single Number
Easy

Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.

You must implement a solution with a linear runtime complexity and use only constant extra space.


Example 1:

Input: nums = [2,2,1]

Output: 1

Example 2:

Input: nums = [4,1,2,1,2]

Output: 4

Example 3:

Input: nums = [1]

Output: 1

Constraints:

1 <= nums.length <= 3 * 104
-3 * 104 <= nums[i] <= 3 * 104
Each element in the array appears twice except for one element which appears only once.
 *
 *
 */

import java.util.HashSet;
import java.util.Set;

public class LeetCode136 {


    public static void main(String[] args) {

        int[] arr1 = {1,2,3,2,1};
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};

        LeetCode136 example = new LeetCode136();

        System.out.println(example.singleNumber(arr1));
    }


    // 位运算：利用异或的性质，a^a = 0, a^0=a, a^b^c = a^c^b，把每一位都异或之后，剩下的那个就是只出现1次的数
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            // 利用异或性质：
            // 1. a ^ a = 0 (自己和自己异或，互相湮灭)
            // 2. a ^ 0 = a (和 0 异或不改变原值)
            // 3. 满足交换律，顺序打乱也无所谓。最后剩下的绝对是那个单身狗！
            res ^= num;
        }
        return res;
    }

    // 其它思路可以计数，排序，Set等。。。但是都做不到常数空间线性时间
    public int singleNumber1(int[] nums) {
        // 使用set，如果set中没有则加入，有则删除，最后留的一个元素就是出现一次的
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            if (!set.contains(num)){
                set.add(num);
            } else {
                set.remove(num);
            }
        }

//        int res = -1;
//        for (int num : set){
//            res = num;
//        }
//        return res;

        // 【优化写法】：此时 set 中必定只有一个元素，直接拿出来即可
        return set.iterator().next();

    }


}