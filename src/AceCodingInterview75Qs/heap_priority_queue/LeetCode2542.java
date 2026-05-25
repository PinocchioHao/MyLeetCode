package AceCodingInterview75Qs.heap_priority_queue;

/*
 *
 *
 *
2542. Maximum Subsequence Score
Medium

You are given two 0-indexed integer arrays nums1 and nums2 of equal length n and a positive integer k. You must choose a subsequence of indices from nums1 of length k.

For chosen indices i0, i1, ..., ik - 1, your score is defined as:

The sum of the selected elements from nums1 multiplied with the minimum of the selected elements from nums2.
It can defined simply as: (nums1[i0] + nums1[i1] +...+ nums1[ik - 1]) * min(nums2[i0] , nums2[i1], ... ,nums2[ik - 1]).
Return the maximum possible score.

A subsequence of indices of an array is a set that can be derived from the set {0, 1, ..., n-1} by deleting some or no elements.



Example 1:

Input: nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3
Output: 12
Explanation:
The four possible subsequence scores are:
- We choose the indices 0, 1, and 2 with score = (1+3+3) * min(2,1,3) = 7.
- We choose the indices 0, 1, and 3 with score = (1+3+2) * min(2,1,4) = 6.
- We choose the indices 0, 2, and 3 with score = (1+3+2) * min(2,3,4) = 12.
- We choose the indices 1, 2, and 3 with score = (3+3+2) * min(1,3,4) = 8.
Therefore, we return the max score, which is 12.
Example 2:

Input: nums1 = [4,2,3,1,1], nums2 = [7,5,10,9,6], k = 1
Output: 30
Explanation:
Choosing index 2 is optimal: nums1[2] * nums2[2] = 3 * 10 = 30 is the maximum possible score.


Constraints:

n == nums1.length == nums2.length
1 <= n <= 105
0 <= nums1[i], nums2[j] <= 105
1 <= k <= n
 *
 *
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class LeetCode2542 {

    int reorderCnt = 0;

    public static void main(String[] args) {

        int[] arr1 = {1,3,3,2};
        int[] arr2 = {2,1,3,4};

        LeetCode2542 example = new LeetCode2542();

        System.out.println(example.maxScore( arr1, arr2, 3));
    }

    // 把两个数组绑定，nums2从大到小排序，遍历到第i个元素则最小值就是nums2[i]，此时只需要维护一个nums1的个数为k的小顶堆就可以了
    // 算法核心：通过【排序】固定 nums2 的最小值，通过【小顶堆+贪心】动态维护最大的一组 nums1
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int len = nums1.length;

        // 1. 【数据绑定】：将两个零散的数组强行打包成“多行两列”的表格形式
        // pairs[i][0] 对应 nums1[i]（用于后续的堆内累加求和）
        // pairs[i][1] 对应 nums2[i]（用于排序和作为乘法公式的最小值）
        int[][] pairs = new int[len][2];
        for (int i = 0; i < len; i++) {
            pairs[i][0] = nums1[i];
            pairs[i][1] = nums2[i];
        }

        // 2. 【核心排序】：按照每一行的右口袋（nums2 的值）进行严格的从大到小（降序）排序
        // 这样做的数学红利是：往右遍历时，当前遇到的 pairs[i][1] 绝对是已选元素中对应的最大值约束（即乘法里的最小值）
        Arrays.sort(pairs, (a, b) -> (b[1] - a[1]));

        // 3. 【状态容器准备】
        // 声明一个小顶堆，用来维护在当前位置左侧，挑出来的最大的 k 个 nums1 元素
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        long sum = 0;    // 动态记录当前堆内所有元素（nums1）的总和，用 long 防止爆 int 21亿上限
        long maxRes = 0; // 记录全局最高得分成果

        // 4. 【拉网式单向遍历】
        for (int i = 0; i < pairs.length; i++) {
            int num1Val = pairs[i][0];
            int num2Min = pairs[i][1];

            // 无论三七二十一，先让当前的 nums1 进堆打擂台，并强行累加进当前的总和里
            heap.add(num1Val);
            sum += num1Val;

            // 【触发结算与淘汰机制】：
            // 索引 i 从 0 开始。当 i >= k - 1 时（例如 k=3，当 i=2 时），
            // 说明此时堆中恰好塞满了第一批满足长度要求的 k 个元素，必须开始启动分数对账
            if (i >= k - 1) {
                // 此时 num2Min 已经是这 k 个元素里在 nums2 数组中的天然最小值。
                // 拿当前的 sum（当前最好的 k 个数的总和）直接乘以当前的 num2Min 算出得分，挑战并更新全局最高分
                maxRes = Math.max(maxRes, sum * num2Min);

                // 【贪心排泄】：因为本轮分数已经结算完毕，
                // 且下一轮循环还会塞进来一个新的 num1 元素（堆大小会变成 k+1），
                // 为了给下一轮腾地方，同时确保手里留下的永远是最强战力，
                // 我们必须提前在这一轮的屁股后面，把当前堆里【最菜、最小】的那个元素从小顶堆的堆顶踹出去，并从 sum 中扣除
                int pop = heap.poll();
                sum -= pop;
            }
        }

        // 5. 返回经历过无数轮动态淘汰洗礼后留下的终极最大分数
        return maxRes;
    }


}