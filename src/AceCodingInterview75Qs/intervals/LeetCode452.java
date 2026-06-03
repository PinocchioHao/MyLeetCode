package AceCodingInterview75Qs.intervals;

/*
 *
 *
 *
452. Minimum Number of Arrows to Burst Balloons
Medium
There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches between xstart and xend. You do not know the exact y-coordinates of the balloons.

Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis. A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.

Given the array points, return the minimum number of arrows that must be shot to burst all balloons.



Example 1:

Input: points = [[10,16],[2,8],[1,6],[7,12]]
Output: 2
Explanation: The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
- Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
Example 2:

Input: points = [[1,2],[3,4],[5,6],[7,8]]
Output: 4
Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.
Example 3:

Input: points = [[1,2],[2,3],[3,4],[4,5]]
Output: 2
Explanation: The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
- Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].


Constraints:

1 <= points.length <= 105
points[i].length == 2
-231 <= xstart < xend <= 231 - 1

 *
 *
 */

import java.util.Arrays;

public class LeetCode452 {

    public static void main(String[] args) {

        int[][] arr = {{1,2}, {1,3},{3,4},{1,3}};
        int[][] arr1 = {{1,2}, {1,2},{1,2}};

        LeetCode452 example = new LeetCode452();

        System.out.println(example.findMinArrowShots(arr1));
    }




    // ==========================================
    // 贪心算法：按右边界排序
    // 物理意义：我们要用最少的箭射爆气球。对于当前气球，箭射在它的“最右侧边缘”收益最高，
    // 因为这样最有可能顺带射穿后面的气球。
    // ==========================================
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        if (points.length == 1) return 1;

        // 1. 按气球的右边界从小到大排序
        // 防溢出写法：使用 Integer.compare
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int cnt = 1; // 至少需要 1 根箭
        // 2. 抛出第一根箭，射在第一个气球的最右侧边缘（安全锚点）
        int flag = points[0][1];

        for (int i = 1; i < points.length; i++) {
            int[] curr = points[i];

            // 3. 判断是否超出射程
            // 注意：题目说明挨着的边界 (curr[0] == flag) 也可以一箭射爆！
            // 所以只有当新气球的左边界【严格大于】当前箭的位置时，才需要射出新的一箭
            if (curr[0] > flag) {
                cnt++;             // 射出一根新箭
                flag = curr[1];    // 把新箭的位置更新在这个新气球的最右侧
            }
        }
        return cnt;
    }


}