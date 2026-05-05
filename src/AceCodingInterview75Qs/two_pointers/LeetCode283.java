package AceCodingInterview75Qs.two_pointers;

/*
 *
 *
 *

283. Move Zeroes

Easy

Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Note that you must do this in-place without making a copy of the array.



Example 1:

Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
Example 2:

Input: nums = [0]
Output: [0]


Constraints:

1 <= nums.length <= 104
-231 <= nums[i] <= 231 - 1


Follow up: Could you minimize the total number of operations done?


 *
 *
 */

import java.util.*;

public class LeetCode283 {


    public static void main(String[] args) {
        int arr[] = {0, 1, 0, 3, 12};
        moveZeroes(arr);
    }

    // 双指针：一个指针 read 用于遍历数组，另一个指针 write 用于记录下一个非零元素应该写入的位置
    public static void moveZeroes(int[] nums) {
        int len = nums.length;
        int read = 0;
        int write = 0;
        while (read < len) {
            // 读指针不为零时，交换读指针和写指针的元素，并将写指针向右移动一位
            if (nums[read] != 0) {
                int temp = nums[write];
                nums[write] = nums[read];
                nums[read] = temp;
                write++;
                read++;
            }
            // 读指针为零时，继续向右移动读指针，直到找到下一个非零元素
            if (read < len && nums[read] == 0) {
                read++;
            }
        }
    }

    // 逻辑合并版本，更清爽
    public static void moveZeroes1(int[] nums) {
        int write = 0;
        for (int read = 0; read < nums.length; read++) {
            if (nums[read] != 0) {
                if (read != write) {
                    int temp = nums[write];
                    nums[write] = nums[read];
                    nums[read] = temp;
                }
                write++;
            }
        }
    }

    // 覆盖法：先遍历数组，将所有非零元素按照顺序覆盖到数组的前面，记录写指针的位置，最后将写指针之后的元素全部填充为零
    // 减少了交换的次数，优化了性能
    public static void moveZeroes2(int[] nums) {
        int write = 0;
        for (int read = 0; read < nums.length; read++) {
            if (nums[read] != 0) {
                nums[write] = nums[read];
                write++;
            }
        }
        for (int i = write; i < nums.length; i++) {
            nums[i] = 0;
        }

    }


}