package pattern.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 986. Interval List Intersections  区间列表的交集
 * Medium
 * 
 * You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi] and secondList[j] = [startj, endj]. Each list of intervals is pairwise disjoint and in sorted order.
 * 
 * Return the intersection of these two interval lists.
 * 
 * A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
 * 
 * The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].
 * 
 * Example 1:
 * 
 * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 * Example 2:
 * 
 * Input: firstList = [[1,3],[5,9]], secondList = []
 * Output: []
 */
public class LeetCode986 {

    public static void main(String[] args) {
        int[][] a = {{1, 3}, {6, 9}};
        int[][] b = {{2, 4}, {6, 9}};
        int[][] c = intervalIntersection(a, b);
        int[][] d = intervalIntersection1(a, b);

        System.out.println();

    }

    /**
     * 区间交叉最简单的判定：1左小于2右  2左小于1右  能涵盖区间交叉的4种情况！！！
     * firstList[ptr1][0] <= secondList[ptr2][1] && secondList[ptr2][0] <= firstList[ptr1][1]
     * 区间数组合并然后按照左值排序，遍历区间数组判断区间是否重叠然后输出重叠部分
     * Merge interval arrays, sort by left value, and check for overlaps to output the intersecting parts.
     *
     * @param firstList
     * @param secondList
     * @return
     */
    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        // 任意一方为空，则无交叉，返回空数组
        // If either list is empty, return an empty array since there are no intersections.
        if (firstList.length == 0 || secondList.length == 0) {
            return new int[0][0];
        }
        List<int[]> res = new ArrayList<>();
        // 组合成一个合并后的区间列表
        // Combine into a merged interval list.
        int[][] allIntervals = new int[firstList.length + secondList.length][];
        // 采用原生API进行排序效率优于手写for循环
        // Using built-in API for copying is more efficient than manual for loops.
        System.arraycopy(firstList, 0, allIntervals, 0, firstList.length);
        System.arraycopy(secondList, 0, allIntervals, firstList.length, secondList.length);

        // 排序
        // Sort the intervals.
        Arrays.sort(allIntervals, (o1, o2) -> o1[0] - o2[0]);
        // 哨兵，用于记录在遍历过程中，前一右值最大(右侧最长)的区间
        // Sentinel to track the previous interval with the maximum right value during traversal.
        int[] tempLongest = allIntervals[0];
        for (int i = 1; i < allIntervals.length; i++) {
            // 区间存在重合
            // There is an overlap between intervals.
            if (allIntervals[i][0] <= tempLongest[1]) {
                // 如果当前区间比哨兵的右边还小，说明这段区间还囊括在哨兵内，直接将当前区间记录输出，哨兵保持不变
                // If the current interval's right value is less than the sentinel's right value, it is fully contained within the sentinel.
                if (allIntervals[i][1] < tempLongest[1]) {
                    res.add(new int[]{allIntervals[i][0], allIntervals[i][1]});
                }
                // 否则区间存在部分重叠，左边取当前值，右边取哨兵；更新哨兵为当前区间
                // Otherwise, there is a partial overlap; take the left value from the current interval and the right value from the sentinel.
                else {
                    res.add(new int[]{allIntervals[i][0], tempLongest[1]});
                    tempLongest = allIntervals[i];
                }
            }
            // 区间不存在任何重叠，更新哨兵
            // If there is no overlap, update the sentinel.
            else {
                tempLongest = allIntervals[i];
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    /**
     * 双指针法 - 分类讨论
     * Two-Pointer Method - Classification Discussion
     *
     * @param firstList
     * @param secondList
     * @return
     */
    public static int[][] intervalIntersection1(int[][] firstList, int[][] secondList) {
        // 任意一方为空，则无交叉，返回空数组
        // If either list is empty, return an empty array since there are no intersections.
        if (firstList.length == 0 || secondList.length == 0) {
            return new int[0][0];
        }

        List<int[]> res = new ArrayList<>();
        // 两个指针分别扫描第一个和第二个区间集
        // Two pointers scan the first and second interval sets respectively.
        int ptr1 = 0;
        int ptr2 = 0;
        while (ptr1 < firstList.length && ptr2 < secondList.length) {
            int left1 = firstList[ptr1][0];
            int right1 = firstList[ptr1][1];
            int left2 = secondList[ptr2][0];
            int right2 = secondList[ptr2][1];

            // 完全不重叠的情况
            // ptr1当前区间在ptr2当前区间左侧，ptr1右移
            // If ptr1's current interval is to the left of ptr2's current interval, move ptr1 to the right.
            if (right1 < left2) {
                ptr1++;
            }
            // ptr2在ptr1左侧，ptr2右移
            // If ptr2's current interval is to the left of ptr1's current interval, move ptr2 to the right.
            else if (right2 < left1) {
                ptr2++;
            }
            // ptr1右端交叉在ptr2区间内，求出交叉区间，并且右移ptr1
            // If ptr1's right end intersects within ptr2's interval, calculate the intersection and move ptr1 to the right.
            else if (right1 >= left2 && right1 <= right2) {
                res.add(new int[]{Math.max(left1, left2), right1});
                ptr1++;
            }
            // ptr2右端交叉在ptr1区间内，求出交叉区间，并且右移ptr2
            // If ptr2's right end intersects within ptr1's interval, calculate the intersection and move ptr2 to the right.
            else if (right2 >= left1 && right2 <= right1) {
                res.add(new int[]{Math.max(left1, left2), right2});
                ptr2++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }


    /**
     * 双指针法 - 优化判断条件
     * Two-Pointer Method - Optimized Condition Check
     *
     * @param firstList
     * @param secondList
     * @return
     */
    public static int[][] intervalIntersection2(int[][] firstList, int[][] secondList) {
        // 任意一方为空，则无交叉，返回空数组
        // If either list is empty, return an empty array since there are no intersections.
        if (firstList.length == 0 || secondList.length == 0) {
            return new int[0][0];
        }

        List<int[]> res = new ArrayList<>();
        // 两个指针分别扫描第一个和第二个区间集
        // Two pointers scan through the first and second interval lists
        int ptr1 = 0;
        int ptr2 = 0;

        // 遍历两个区间列表
        // Iterate through both interval lists
        while (ptr1 < firstList.length && ptr2 < secondList.length) {
            // 检查是否有交叉
            // Check for intersection
            if (firstList[ptr1][0] <= secondList[ptr2][1] && secondList[ptr2][0] <= firstList[ptr1][1]) {
                // 计算交叉区间
                // Calculate the intersecting interval
                res.add(new int[]{Math.max(firstList[ptr1][0], secondList[ptr2][0]), Math.min(firstList[ptr1][1], secondList[ptr2][1])});
            }

            // 移动指针 - 看哪一个的右值更大，移动小的那一方
            // Move pointers - compare the right values and move the pointer of the smaller one
            if (firstList[ptr1][1] < secondList[ptr2][1]) {
                // 当前区间结束于较小的区间，移动 firstList 的指针
                // The current interval ends at the smaller one, move the pointer of firstList
                ptr1++;
            } else {
                // 当前区间结束于较小的区间，移动 secondList 的指针
                // The current interval ends at the smaller one, move the pointer of secondList
                ptr2++;
            }
        }

        return res.toArray(new int[res.size()][]);
    }








}
