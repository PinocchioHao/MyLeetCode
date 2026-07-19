package AceCodingInterview75Qs.heap_priority_queue;

/*
 *
 *
 *
2336. Smallest Number in Infinite Set

Medium

You have a set which contains all positive integers [1, 2, 3, 4, 5, ...].

Implement the SmallestInfiniteSet class:

SmallestInfiniteSet() Initializes the SmallestInfiniteSet object to contain all positive integers.
int popSmallest() Removes and returns the smallest integer contained in the infinite set.
void addBack(int num) Adds a positive integer num back into the infinite set, if it is not already in the infinite set.


Example 1:

Input
["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest", "addBack", "popSmallest", "popSmallest", "popSmallest"]
[[], [2], [], [], [], [1], [], [], []]
Output
[null, null, 1, 2, 3, null, 1, 4, 5]

Explanation
SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
smallestInfiniteSet.addBack(2);    // 2 is already in the set, so no change is made.
smallestInfiniteSet.popSmallest(); // return 1, since 1 is the smallest number, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 2, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 3, and remove it from the set.
smallestInfiniteSet.addBack(1);    // 1 is added back to the set.
smallestInfiniteSet.popSmallest(); // return 1, since 1 was added back to the set and
                                   // is the smallest number, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 4, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 5, and remove it from the set.


Constraints:

1 <= num <= 1000
At most 1000 calls will be made in total to popSmallest and addBack.

 *
 *
 */

import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class LeetCode2336 {

    int reorderCnt = 0;

    public static void main(String[] args) {

        int[] arr = {3,2,1,5,6,4};

        LeetCode2336 example = new LeetCode2336();

//        System.out.println(example.findKthLargest( arr, 2));
    }

}


/**
 * 使用小顶堆+curr指针+HashSet来模拟无限元素集合的操作
 * 小顶堆跟Set结合，始终存新加入的元素，且最小元素位于堆顶 -- 不用set也可以heap先查再添加，但是时间开销会增加
 * pop的时候先看小顶堆里面是否有元素，小顶堆被弹完了之后就移动指针指向最小元素
 * add的时候判断当前元素如果大于curr指针，那么原来集合中移动存在当前元素；如果小于则看小顶堆里面有没有，没有就加入
 */
class SmallestInfiniteSet {

    private PriorityQueue<Integer> heap;
    private Set<Integer> set;
    private int curr;


    public SmallestInfiniteSet() {
        heap = new PriorityQueue();
        set = new HashSet();
        curr = 0;
    }

    public int popSmallest() {
        int res = 0;
        if(heap.size() > 0){
            res = heap.poll();
            set.remove(res);
        } else{
            curr++;
            res = curr;
        }
        return res;
    }

    public void addBack(int num) {
        if (num > curr) {
            return;
        }
        if (!set.contains(num)){
            heap.offer(num);
            set.add(num);
        }
    }
}

