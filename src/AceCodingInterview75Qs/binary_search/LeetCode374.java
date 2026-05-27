package AceCodingInterview75Qs.binary_search;

/*
 *
 *
 *
374. Guess Number Higher or Lower
Easy

We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked (the number I picked stays the same throughout the game).

Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.

You call a pre-defined API int guess(int num), which returns three possible results:

-1: Your guess is higher than the number I picked (i.e. num > pick).
1: Your guess is lower than the number I picked (i.e. num < pick).
0: your guess is equal to the number I picked (i.e. num == pick).
Return the number that I picked.



Example 1:

Input: n = 10, pick = 6
Output: 6
Example 2:

Input: n = 1, pick = 1
Output: 1
Example 3:

Input: n = 2, pick = 1
Output: 1


Constraints:

1 <= n <= 231 - 1
1 <= pick <= n
 *
 *
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class LeetCode374 {

    public static void main(String[] args) {

        int[] arr1 = {1};
        int[] arr2 = {2,1,3,4};

        LeetCode374 example = new LeetCode374();

//        System.out.println(example.guessNumber(334));

        System.out.println(example.binarySearch(arr1,1));


    }

    // 二分查找，注意控制边界
    public int guessNumber(int n) {
        int left = 1;
        int right = n;
        while(left < right){
            int mid =  left + (right - left) / 2;
            int rlt = guess(mid);
            if(rlt == 1){
                left = mid + 1;
            } else if (rlt == -1){
                right = mid - 1;
            } else{
                return mid;
            }
        }
        return left;
    }

    public int guess(int num){
        int n = 3;
        if(num == n){
            return 0;
        } else if (num > n) {
            return 1;
        } else {
            return -1;
        }
    }


    // 普通二分查找，找到索引
    public int binarySearch(int[] nums, int target){
        // 搜索区间：[left, right] （左闭右闭）
        int left = 0;
        int right = nums.length - 1; // 因为是闭区间，right 必须是真实的最后一个元素索引

        while (left <= right) { // 关键：必须是 <=。因为 left == right 时，那个唯一的元素还没被检查！
            //这个mid的位置是偏左的，当数组长度为奇数时候，mid为正中间，当数组长度为偶数时候，mid是靠近左边
            // 不能直接用(left + right)/2，怕越界
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid; // 找到了，直接收工
            } else if (nums[mid] < target) {
                left = mid + 1; // mid 太小了，且 mid 绝不是我们要找的，直接抛弃
            } else {
                right = mid - 1; // mid 太大了，且 mid 绝不是我们要找的，直接抛弃
            }
        }
        return -1; // 找遍了也没有

    }




}