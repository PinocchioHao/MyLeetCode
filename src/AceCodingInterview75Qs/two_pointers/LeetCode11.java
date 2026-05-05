package AceCodingInterview75Qs.two_pointers;

/*
 *
 *
 *

11. Container With Most Water

Medium

You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

Notice that you may not slant the container.



Example 1:


Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
Example 2:

Input: height = [1,1]
Output: 1


Constraints:

n == height.length
2 <= n <= 105
0 <= height[i] <= 104

 *
 *
 */

public class LeetCode11 {


    public static void main(String[] args) {
        int arr[] = {1, 2, 4, 3};
        System.out.println(maxArea(arr));
        System.out.println(maxArea1(arr));
    }

    // Brute force solution, O(n^2) time complexity, O(1) space complexity
    public static int maxArea(int[] height) {
        int maxWater = 0;
        int len = height.length;
        for(int i = 0; i < len - 1; i++) {
            for(int j = i + 1; j < len; j++){
                int w = j - i;
                int h = Math.min(height[i], height[j]);
                maxWater = Math.max(maxWater, w * h);
            }
        }
        return maxWater;
    }

    // Double pointer & Greedy solution, O(n) time complexity, O(1) space complexity
    // 贪心策略：由于宽度在缩减，而面积受限于矮边，所以要想增加容器的容量，必须增加最矮边的高度，才有可能使得面积增加
    public static int maxArea1(int[] height) {
        int maxWater = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
            int w = r - l;
            int h = Math.min(height[l], height[r]);
            maxWater = Math.max(maxWater, w*h);
            if (height[l] < height[r]){
                l++;
            } else {
                r--;
            }
        }
        return maxWater;
    }


}