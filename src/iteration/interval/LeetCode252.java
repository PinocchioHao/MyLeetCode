package iteration.interval;

import java.util.Arrays;

/**
 252 Meeting Rooms
 Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.


 Example 1:

 Input: intervals = [[0,30],[5,10],[15,20]]
 Output: false
 Example 2:

 Input: intervals = [[7,10],[2,4]]
 Output: true


 Constraints:

 0 <= intervals.length <= 104
 intervals[i].length == 2
 0 <= starti < endi <= 106

 */

public class LeetCode252 {
    public static void main(String[] args) {
        int[][] intervals = {{0,30},{5,10},{15,20}};

        int[][] a = {{1,3},{6,9}};
        System.out.println(canAttendMeetings(intervals));
        System.out.println(canAttendMeetings(a));
    }

    /**
     * 先区间按照左值排序，然后判断是否有重合区间（排序后若区间存在重合，则当前left值小于上一right值）
     * First, sort the intervals by the left value (meeting start time), then check for overlapping intervals.
     * If there is an overlap after sorting, the current left value will be less than the previous right value.
     * @param intervals 会议时间区间数组
     *                  The array of meeting time intervals.
     * @return 是否可以参加所有会议
     *         Returns true if the person can attend all meetings, otherwise false.
     */
    public static boolean canAttendMeetings(int[][] intervals) {
        // 按照区间left(会议开始时间)升序排列
        // Sort the intervals in ascending order based on the left value (meeting start time).
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        for (int i = 1; i < intervals.length; i++) {
            // 当前left值在上一区间right左侧，则重叠(如果当前会议在上一个会议结束前就开始了，则表示会议重叠)
            // If the current left value is less than the previous right value, then there is an overlap.
            // This means the current meeting starts before the previous meeting ends.
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false; // 发现重叠
                // Return false if an overlap is found.
            }
        }
        return true; // 无重叠
        // Return true if no overlaps are found.
    }

}
