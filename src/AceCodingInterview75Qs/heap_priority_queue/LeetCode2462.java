package AceCodingInterview75Qs.heap_priority_queue;

/*
 *
 *
 *
2462. Total Cost to Hire K Workers
Medium

You are given a 0-indexed integer array costs where costs[i] is the cost of hiring the ith worker.

You are also given two integers k and candidates. We want to hire exactly k workers according to the following rules:

You will run k sessions and hire exactly one worker in each session.
In each hiring session, choose the worker with the lowest cost from either the first candidates workers or the last candidates workers. Break the tie by the smallest index.
For example, if costs = [3,2,7,7,1,2] and candidates = 2, then in the first hiring session, we will choose the 4th worker because they have the lowest cost [3,2,7,7,1,2].
In the second hiring session, we will choose 1st worker because they have the same lowest cost as 4th worker but they have the smallest index [3,2,7,7,2]. Please note that the indexing may be changed in the process.
If there are fewer than candidates workers remaining, choose the worker with the lowest cost among them. Break the tie by the smallest index.
A worker can only be chosen once.
Return the total cost to hire exactly k workers.



Example 1:

Input: costs = [17,12,10,2,7,2,11,20,8], k = 3, candidates = 4
Output: 11
Explanation: We hire 3 workers in total. The total cost is initially 0.
- In the first hiring round we choose the worker from [17,12,10,2,7,2,11,20,8]. The lowest cost is 2, and we break the tie by the smallest index, which is 3. The total cost = 0 + 2 = 2.
- In the second hiring round we choose the worker from [17,12,10,7,2,11,20,8]. The lowest cost is 2 (index 4). The total cost = 2 + 2 = 4.
- In the third hiring round we choose the worker from [17,12,10,7,11,20,8]. The lowest cost is 7 (index 3). The total cost = 4 + 7 = 11. Notice that the worker with index 3 was common in the first and last four workers.
The total hiring cost is 11.
Example 2:

Input: costs = [1,2,4,1], k = 3, candidates = 3
Output: 4
Explanation: We hire 3 workers in total. The total cost is initially 0.
- In the first hiring round we choose the worker from [1,2,4,1]. The lowest cost is 1, and we break the tie by the smallest index, which is 0. The total cost = 0 + 1 = 1. Notice that workers with index 1 and 2 are common in the first and last 3 workers.
- In the second hiring round we choose the worker from [2,4,1]. The lowest cost is 1 (index 2). The total cost = 1 + 1 = 2.
- In the third hiring round there are less than three candidates. We choose the worker from the remaining workers [2,4]. The lowest cost is 2 (index 0). The total cost = 2 + 2 = 4.
The total hiring cost is 4.


Constraints:

1 <= costs.length <= 105
1 <= costs[i] <= 105
1 <= k, candidates <= costs.length
 *
 *
 */

import java.util.Arrays;
import java.util.PriorityQueue;

public class LeetCode2462 {

    int reorderCnt = 0;

    public static void main(String[] args) {

        int[] arr1 = {17,12,10,2,7,2,11,20,8};
        int[] arr2 = {2,1,3,4};

        LeetCode2462 example = new LeetCode2462();

        System.out.println(example.totalCost( arr1, 3, 4));
    }


    // 双指针+小顶堆
    public long totalCost(int[] costs, int k, int candidates) {
        long totalCost = 0;
        // 左右小顶堆与双指针
        PriorityQueue<Integer> leftHeap = new PriorityQueue();
        PriorityQueue<Integer> rightHeap = new PriorityQueue();
        int left = 0;
        int right = costs.length - 1;

        // 总共招募k人
        while (k > 0){
            // 维持左右两边的堆里都有 candidates 个人（除非没人了）
            // 注意条件 i <= j，防止左右指针相撞/交叉，同一个人不能进两次堆
            while (leftHeap.size() < candidates && left <= right){
                leftHeap.offer(costs[left]);
                left++;
            }
            while (rightHeap.size() < candidates && left <= right){
                rightHeap.offer(costs[right]);
                right--;
            }

            // peek堆顶挑最小值，如果堆为空则赋一个极大值
            int leftMin = leftHeap.size()>0 ? leftHeap.peek() : Integer.MAX_VALUE;
            int rightMin = rightHeap.size()>0 ? rightHeap.peek() : Integer.MAX_VALUE;

            // 优先招左边最便宜的
            if (leftMin <= rightMin){
                totalCost += leftHeap.poll();
            } else {
                totalCost += rightHeap.poll();
            }
            // 循环一次招募一人，k--
            k--;

        }

        return totalCost;

    }









}