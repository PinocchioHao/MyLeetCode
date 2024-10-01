package interval;

import java.util.Arrays;

/**
 * 1288. Remove Covered Intervals
 * Medium
 * Topics
 * Companies
 * Hint
 * Given an array intervals where intervals[i] = [li, ri] represent the interval [li, ri), remove all intervals that are covered by another interval in the list.
 *
 * The interval [a, b) is covered by the interval [c, d) if and only if c <= a and b <= d.
 *
 * Return the number of remaining intervals.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,4],[3,6],[2,8]]
 * Output: 2
 * Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
 * Example 2:
 *
 * Input: intervals = [[1,4],[2,3]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 1000
 * intervals[i].length == 2
 * 0 <= li < ri <= 105
 * All the given intervals are unique.
 */
public class LeetCode1288 {

    public static void main(String[] args) {
        int[][] a = {{1,2},{1,4},{6,9}};
        int[] b = {9,15};
        
        System.out.println(removeCoveredIntervals(a));
    }


    /**
     * 难点在于考虑边界值情况需要相等后降序排列
     * @param intervals
     * @return
     */
    public static int removeCoveredIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        if (intervals.length == 1) return 1;
        // 按照左值升序排列，区间值相等则按照右值降序排列；
        // 保证从左到右遍历时，最先遇到的是最大的区间
        // 如果不这样排序，则用例[[1,2],[1,4]]会输出2，原因是4大于2认为[1,4]不在[1,2]内，如果排序了之后就会先遇到[1,4]再判断[1,2]是否在它里面
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }else {
                return b[1] - a[1];
            }
        });
        // 由于已经排过序了，左边界就可以不考虑了，只要右值不被包括那一定属于不同区间
        //int left = intervals[0][0];
        int right = intervals[0][1];
        int res = 1;
        for (int i = 1; i < intervals.length; i++) {
            // 区间包含，不做操作，且由于最早遇到的
//            if (intervals[i][0] >= left && intervals[i][1] <= right) {
            if (intervals[i][1] <= right) {
                continue;
            }
            // 右值不在当前右值范围内则是不被包含的区间，记录
            // left = intervals[i][0];
            right = intervals[i][1];
            res++;
        }

        return res;
    }

}
