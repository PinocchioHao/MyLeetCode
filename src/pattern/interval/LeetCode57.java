package pattern.interval;

import java.util.ArrayList;
import java.util.List;

/**
 57. Insert Interval
 Medium
 Topics
 Companies
 Hint
 You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.

 Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

 Return intervals after the insertion.

 Note that you don't need to modify intervals in-place. You can make a new array and return it.



 Example 1:

 Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 Output: [[1,5],[6,9]]
 Example 2:

 Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 Output: [[1,2],[3,10],[12,16]]
 Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].


 Constraints:

 0 <= intervals.length <= 104
 intervals[i].length == 2
 0 <= starti <= endi <= 105
 intervals is sorted by starti in ascending order.
 newInterval.length == 2
 0 <= start <= end <= 105
 */

public class LeetCode57 {
    public static void main(String[] args) {

        int[][] a = {{1,3},{6,9}};
        int[] b = {9,15};
        int[][] c = insert(a,b);
        System.out.println();
    }

    /**
     * 这个题迭代更好解，分三段构造：1. 在新区间左侧intervals[i].right < newInterval.left
     * 2. 与新区间有重叠部分  newInterval.left <= intervals[i].left <= newInterval.right这种场景，
     *    以及左右包括在新区间，右侧超过新区间这三种场景
     *    注意此处这个条件已经涵盖了 <= intervals[i].right
     * 3. 新区间右侧部分 intervals[i].left > newInterval.right
     *
     * Iteration is better for solving this problem. It can be divided into three phases:
     * 1. Intervals strictly to the left of the new interval: intervals[i].right < newInterval.left
     * 2. Intervals that overlap with the new interval, covering cases such as:
     *    newInterval.left <= intervals[i].left <= newInterval.right, intervals[i] is fully within
     *    or partially overlaps the new interval on the right.
     *    Note that the condition also covers <= intervals[i].right.
     * 3. Intervals strictly to the right of the new interval: intervals[i].left > newInterval.right.
     *
     * @param intervals   给定的区间数组
     *                   The array of existing intervals.
     * @param newInterval 新插入的区间
     *                   The new interval to insert.
     * @return 合并后的区间
     *         The merged intervals after insertion.
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        // 由于需要合并操作，数组元素个数不定，所以先转为list
        // Since the number of elements is uncertain due to the merge operation, convert the array to a list.
        List<int[]> res = new ArrayList<>();
        int i = 0;

        // 1. 在新区间左侧的区间 intervals[i].right < newInterval.left
        // 1. Add intervals strictly to the left of the new interval (intervals[i].right < newInterval.left).
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i]); // 添加当前区间到结果集
            // Add current interval to result if it's completely to the left.
            i++;
        }

        // 2. 处理与新区间有重叠部分的区间
        // 2. Handle intervals that overlap with the new interval.
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            // 操纵新区间的范围，使得新区间永远保持左右最大范围
            // Adjust the bounds of the new interval to encompass the overlapping intervals.
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]); // 向左延展新区间
            // Extend newInterval to the left if necessary.
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]); // 向右延展新区间
            // Extend newInterval to the right if necessary.
            i++;
        }
        // 将合并后的新区间（左右最大范围）加入结果集
        // Add the merged interval (now adjusted to encompass all overlaps) to the result list.
        res.add(newInterval);

        // 3. 把剩下的右侧部分区间加入结果集
        // 3. Add remaining intervals that are strictly to the right of the new interval.
        while (i < intervals.length) {
            res.add(intervals[i]); // 这些区间已经在右侧，不需要进一步操作
            // These intervals are already to the right, no need for further adjustment.
            i++;
        }

        // 把结果列表转换为数组并返回
        // Convert the result list back into an array and return it.
        return res.toArray(new int[res.size()][]);
    }


}
