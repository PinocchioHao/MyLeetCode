package AceCodingInterview75Qs.heap_priority_queue;

/*
 *
 *
 *
215. Kth Largest Element in an Array
Medium

Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

Can you solve it without sorting?



Example 1:

Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
Example 2:

Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4


Constraints:

1 <= k <= nums.length <= 105
-104 <= nums[i] <= 104

 *
 *
 */

import java.util.*;

public class LeetCode215 {

    int reorderCnt = 0;

    public static void main(String[] args) {

        int[] arr = {3,2,1,5,6,4};

        LeetCode215 example = new LeetCode215();

        System.out.println(example.findKthLargest( arr, 2));
    }

    // 大顶堆，先压再弹
     public int findKthLargest(int[] nums, int k) {
         PriorityQueue<Integer> heap = new PriorityQueue(Collections.reverseOrder());
         for(int num : nums){
             heap.offer(num);
         }

         for(int i = 0; i < k - 1; i++){
             heap.poll();
         }

         return heap.peek();
     }


    // 小顶堆，维护大小为K的小顶堆，遍历完整个数组，堆顶元素即为第k大的 元素
     public int findKthLargest1(int[] nums, int k) {
         PriorityQueue<Integer> heap = new PriorityQueue();
         for(int i = 0; i < nums.length; i++){
             heap.offer(nums[i]);
             if (i >= k){
                 heap.poll();
             }
         }
         return heap.peek();
     }


}