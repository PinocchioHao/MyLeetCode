package pattern.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 56. Merge Intervals
 Medium

 Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.


 Example 1:

 Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 Output: [[1,6],[8,10],[15,18]]
 Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 Example 2:

 Input: intervals = [[1,4],[4,5]]
 Output: [[1,5]]
 Explanation: Intervals [1,4] and [4,5] are considered overlapping.


 Constraints:

 1 <= intervals.length <= 104
 intervals[i].length == 2
 0 <= starti <= endi <= 104
 */

public class LeetCode56 {
    public static void main(String[] args) {

        int[][] a = {{1, 3}, {6, 9}};
        int[] b = {9, 15};
        int[][] c = merge(a);
        int[][] d = merge1(a);

//        int[] prevInterval = {1, 3};
//
//        List<int[]> list = new ArrayList<>();
//        list.add(prevInterval);
//        list.add(prevInterval);
//
//        prevInterval[1] = 6;
//
//        list.add(prevInterval);
//        prevInterval[1] = 8;


        System.out.println();
    }


    /**
     * 先按照左值排序，再遍历区间，判断是否存在重合，存在则更新区间，不存在则添加区间进结果集
     * First, sort the intervals by the left value, then iterate through the intervals.
     * If an overlap is found, merge the intervals; otherwise, add the interval to the result set.
     *
     * @param intervals 需要合并的区间数组
     *                  The array of intervals to be merged.
     * @return 合并后的区间数组
     * The array of merged intervals.
     */
    public static int[][] merge(int[][] intervals) {
        // 边界条件，输入为空则返回空数组
        // Return an empty array if the input is empty.
        if (intervals.length == 0) return new int[0][0];

        List<int[]> res = new ArrayList<>();

        // 按照区间左值排序
        // Sort intervals based on the left value in ascending order.
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // 上一个合法区间，作用为前值游标，如果遇到重叠情况需要扩展
        // Previous valid interval, used as a cursor to expand in case of overlap.
        int[] prevInterval = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            // 存在重叠，进行区间扩展，注意边界情况[1,4],[4,5]
            // If overlap exists, expand the interval, taking into account edge cases like [1,4] and [4,5].
            if (intervals[i][0] <= prevInterval[1]) {
                // 只用更新右值为最大，左值由于已经排序了就不用更新了
                // Only update the right value to the maximum, as the left value is already sorted.
                prevInterval[1] = Math.max(intervals[i][1], prevInterval[1]);
            } else {
                // 不存在重叠则将上一合法区间加入结果集
                // If no overlap exists, add the previous valid interval to the result set.

                // 由于将prevInterval添加进结果集后立马就把它指向了新的intervals[i]的地址，
                // 所以可以直接添加prevInterval进数组，比取当前值创建新数组效率更高
                // Add prevInterval directly to the list; more efficient than creating a new array.

                // 但是还是更推荐以prevInterval的值新建数组添加进结果集，
                // 因为这样更加保险，直接添加引用进去并且引用可能被修改风险比较大
                // However, it's safer to create a new array using prevInterval's values to avoid risks of reference modification.
                res.add(prevInterval);
                prevInterval = intervals[i]; // 更新当前区间为新的游标
                // Update prevInterval to the current interval as the new cursor.
            }
        }
        // 最后一次比较结果没添加至结果集，手动添加
        // Add the last remaining interval to the result set manually.
        res.add(prevInterval);

        // 将结果列表转换为二维数组返回
        // Convert the result list to a 2D array and return.
        return res.toArray(new int[res.size()][]);
    }



    /**
     * while 循环解法 - 优化重叠区间处理
     * While loop solution - optimized for merging overlapping intervals.
     *
     * @param intervals 需要合并的区间数组
     *                  The array of intervals to be merged.
     * @return 合并后的区间数组
     *         The array of merged intervals.
     */
    public static int[][] merge1(int[][] intervals) {
        if (intervals.length == 0) return new int[0][0]; // 边界条件，输入为空则返回空数组
        // 排序
        // Sort intervals based on the left value in ascending order.
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> res = new ArrayList<>();
        int i = 0;

        while (i < intervals.length) {
            // 直接使用intervals[i]作为当前区间
            int[] currentInterval = intervals[i];
            // 与下一区间比较，如果存在重叠区间，持续合并
            // If the left value of the next interval is less than or equal to the right value of the current interval, merge them.
            while (i < intervals.length - 1 && intervals[i + 1][0] <= currentInterval[1]) {
                // 进行区间合并
                // Merge intervals by updating the right value of the current interval.
                currentInterval[1] = Math.max(currentInterval[1], intervals[i + 1][1]);
                i++;
            }

            // 将合并后的区间加入结果集
            // Add the merged interval to the result set.
            res.add(currentInterval);
            i++; // 更新 i 指向下一个区间
        }

        return res.toArray(new int[res.size()][]);
    }



}