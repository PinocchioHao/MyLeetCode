package AceCodingInterview75Qs.array_string;

/*
 *
 *
 *

238. Product of Array Except Self

Medium

Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.



Example 1:

Input: nums = [1,2,3,4]
Output: [24,12,8,6]
Example 2:

Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]


Constraints:

2 <= nums.length <= 105
-30 <= nums[i] <= 30
The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.


Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)


 *
 *
 */

import java.util.*;

public class LeetCode238 {


    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4};
        System.out.println(productExceptSelf(arr));
    }


    // 先遍历两次数组，记录每个元素左边的乘积和右边的乘积，最后再遍历一次数组，rlt[i] = leftProd[i - 1] * rightProd[i + 1] 计算每个元素的结果
    // O(n) time complexity, O(n) space complexity
    public static int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int [] leftProd = new int[len];
        leftProd[0] = nums[0];
        int [] rightProd = new int[len];
        rightProd[len - 1] = nums[len - 1];
        for (int i = 1; i < len; i++) {
            leftProd[i] = leftProd[i - 1] * nums[i];
        }
        for (int i = len - 2; i >= 0; i--) {
            rightProd[i] = rightProd[i + 1] * nums[i];
        }

        // calculate the result for each element
        int [] rlt = new int[len];
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                rlt[i] = rightProd[i + 1];
            } else if (i == len - 1) {
                rlt[i] = leftProd[i - 1];
            } else {
                rlt[i] = leftProd[i - 1] * rightProd[i + 1];
            }
        }
        return rlt;
    }


    // 优化版本：在计算右侧积的同时直接乘入结果数组，节省了一个数组的空间 O(n) time complexity, O(1) space complexity
    public static int[] productExceptSelf1(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        // 第一步：计算左侧积
        // res[i] 表示 nums[i] 左侧所有元素的乘积
        res[0] = 1; // 0 左边没有元素，初始为 1
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        // 第二步：计算右侧积并直接乘入 res
        // right 变量代表当前元素右侧所有元素的乘积
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            // 此时 res[i] 是左侧积，乘上此时的 right（右侧积）
            res[i] = res[i] * right;
            // 更新 right，为左边那个元素准备右侧积
            right = right * nums[i];
        }
        return res;
    }


}