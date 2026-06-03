package AceCodingInterview75Qs.intervals;

/*
 *
 *
 *
435. Non-overlapping Intervals
Medium

Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Note that intervals which only touch at a point are non-overlapping. For example, [1, 2] and [2, 3] are non-overlapping.



Example 1:

Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
Example 2:

Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
Example 3:

Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.


Constraints:

1 <= intervals.length <= 105
intervals[i].length == 2
-5 * 104 <= starti < endi <= 5 * 104

 *
 *
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class LeetCode435 {

    public static void main(String[] args) {

        int[][] arr = {{1,2}, {1,3},{3,4},{1,3}};
        int[][] arr1 = {{1,2}, {1,2},{1,2}};

        LeetCode435 example = new LeetCode435();

        System.out.println(example.eraseOverlapIntervals(arr1));
    }


    // ==========================================
    // 贪心算法：按右边界排序
    // 逻辑转换：最小移除数 = 总区间数 - 最多能保留的无重叠区间数。
    // 贪心策略：谁结束得早，就先选谁！因为结束得越早，留给后面的“空间”就越大。
    // ==========================================
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length <= 1) {
            return 0;
        }

        // 1. 按右边界从小到大排序，便于贪心挑选结束最早的区间
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int cnt = 1; // 记录能保留的无重叠区间总数，至少能保留 1 个
        // 2. 设定基准点（安全锚点）：第一个保留区间的右边界
        int flag = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            int[] curr = intervals[i];

            // 3. 判断是否重叠
            // 注意：题目说明触碰边界 [1,2] 和 [2,3] 不算重叠！
            // 所以只要新区间的左边界【大于等于】基准点，就能安全保留
            if (curr[0] >= flag) {
                cnt++;           // 成功保留该区间
                flag = curr[1];  // 更新基准点为这个新保留区间的右边界
            }
        }

        // 4. 返回需要干掉的重叠区间数
        return intervals.length - cnt;
    }


}