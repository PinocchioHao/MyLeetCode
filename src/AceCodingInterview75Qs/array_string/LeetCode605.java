package AceCodingInterview75Qs.array_string;

/*
 *
 *
 *
605. Can Place Flowers
Easy

You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.

Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, return true if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule and false otherwise.



Example 1:

Input: flowerbed = [1,0,0,0,1], n = 1
Output: true
Example 2:

Input: flowerbed = [1,0,0,0,1], n = 2
Output: false


Constraints:

1 <= flowerbed.length <= 2 * 104
flowerbed[i] is 0 or 1.
There are no two adjacent flowers in flowerbed.
0 <= n <= flowerbed.length

 *
 *
 */

public class LeetCode605 {


    public static void main(String[] args) {
        int arr[] = {0, 1, 0};
        System.out.println(canPlaceFlowers(arr, 1));
    }

    // Greedy: find the max number of flowers that can be planted, and compare it with n
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int max = 0;
        // 讨论只有一个元素的情况
        if (len == 1 && flowerbed[0] == 0) {
            max++;
        } else {
            // 讨论从开头到倒数第二个元素的情况
            for (int i = 0; i < flowerbed.length - 1; i++) {
                if (i == 0 && flowerbed[i] == 0 && flowerbed[i + 1] == 0) {
                    flowerbed[i] = 1;
                    max++;
                }
                if (i == 0) {
                    continue;
                }
                if (flowerbed[i] == 0 && flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
                    flowerbed[i] = 1;
                    max++;
                }
            }
            // 讨论结尾可能能再种一个花的情况
            if (flowerbed[len - 1] == 0 && flowerbed[len - 2] == 0) {
                max++;
            }
        }
        // 最后比较能种的花的数量和需要种的花的数量
        return max >= n;
    }

    // promoted version - more concise and easier to read, but same time complexity O(n) and space complexity O(1)
    public boolean canPlaceFlowers1(int[] flowerbed, int n) {
        int max = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            // 条件 1: 当前位置必须是 0
            if (flowerbed[i] == 0) {

                // 条件 2: 检查左边。如果是索引 0，或者左边是 0，都算通过
                boolean leftEmpty = (i == 0) || (flowerbed[i - 1] == 0);

                // 条件 3: 检查右边。如果是最后一个索引，或者右边是 0，都算通过
                boolean rightEmpty = (i == flowerbed.length - 1) || (flowerbed[i + 1] == 0);

                if (leftEmpty && rightEmpty) {
                    // 种花！
                    flowerbed[i] = 1;
                    max++;

                    // 提前退出：如果已经种够了，直接返回 true，省点时间
                    if (max >= n) {
                        return true;
                    }
                }
            }
        }
        // 最后检查种下的总数是否达到 n
        // 注意：哪怕 n 是 0，也应该返回 true，所以用 >=
        return max >= n;
    }




}