package AceCodingInterview75Qs.queue;

/*
 *
 *
 *

933. Number of Recent Calls

Easy

You have a RecentCounter class which counts the number of recent requests within a certain time frame.

Implement the RecentCounter class:

RecentCounter() Initializes the counter with zero recent requests.
int ping(int t) Adds a new request at time t, where t represents some time in milliseconds, and returns the number of requests that has happened in the past 3000 milliseconds (including the new request). Specifically, return the number of requests that have happened in the inclusive range [t - 3000, t].
It is guaranteed that every call to ping uses a strictly larger value of t than the previous call.



Example 1:

Input
["RecentCounter", "ping", "ping", "ping", "ping"]
[[], [1], [100], [3001], [3002]]
Output
[null, 1, 2, 3, 3]

Explanation
RecentCounter recentCounter = new RecentCounter();
recentCounter.ping(1);     // requests = [1], range is [-2999,1], return 1
recentCounter.ping(100);   // requests = [1, 100], range is [-2900,100], return 2
recentCounter.ping(3001);  // requests = [1, 100, 3001], range is [1,3001], return 3
recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002], range is [2,3002], return 3


Constraints:

1 <= t <= 109
Each test case will call ping with strictly increasing values of t.
At most 104 calls will be made to ping.


 *
 *
 */

import java.util.*;

public class LeetCode933 {


    public static void main(String[] args) {
        RecentCounter1 r = new RecentCounter1();
        System.out.println(r.ping(642));
        System.out.println(r.ping(1849));
        System.out.println(r.ping(4921));
        System.out.println(r.ping(5936));
        System.out.println(r.ping(5957));

    }


    // 这个完全没有用到队列的特性，大数据量一来会撑爆，本题要用队列构建一个最近3000以内的滑窗
    class RecentCounter {
        private Deque<Integer> requests = new ArrayDeque<>();
        public RecentCounter() {
        }

        public int ping(int t) {
            requests.addFirst(t);
            // 用stream filter会全遍历，开销极大
//            int count = (int) requests.stream().filter(cnt -> cnt >= t - 3000).count();
            int count = 0;
            // 剪枝遍历
            for (int i : requests) {
                if (i>=t-3000) {
                    count++;
                } else {
                    break;
                }
            }
            return count;
        }
    }

    // 使用队列来维护一个最近3000范围的滑窗
    static class RecentCounter1 {
        private Deque<Integer> requests;
        public RecentCounter1() {
            requests = new ArrayDeque<>();
        }

        public int ping(int t) {
            requests.addFirst(t);
            // 维护滑窗
            while (requests.peekLast() < t - 3000) {
                requests.removeLast();
            }
            return requests.size();
        }
    }











/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */

}