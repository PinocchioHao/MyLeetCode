package AceCodingInterview75Qs.binary_search;

/*
 *
 *
 *
875. Koko Eating Bananas
Medium

Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.



Example 1:

Input: piles = [3,6,7,11], h = 8
Output: 4
Example 2:

Input: piles = [30,11,23,4,20], h = 5
Output: 30
Example 3:

Input: piles = [30,11,23,4,20], h = 6
Output: 23


Constraints:

1 <= piles.length <= 104
piles.length <= h <= 109
1 <= piles[i] <= 109
 *
 *
 */

public class LeetCode875 {

    public static void main(String[] args) {

        int[] arr1 = {3, 6, 7, 11};
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};

        LeetCode875 example = new LeetCode875();

        int rlt = example.minEatingSpeed(arr1, 8);
        System.out.println(rlt);
    }


    // 在速度[1, piles.max]里面找一个能够吃完的最小速度，数据量很大使用二分查找
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 0;
        int res = 0;
        for(int pile : piles){
            right = Math.max(pile, right);
        }

        while (left <= right){
            int mid = left + (right - left)/2;
            // 找能吃完中的最小，记录下标
            if(canFinish(piles, h, mid)){
                // 吃得完，则记录下标并往小的一遍缩，求更小的
                res = mid;
                right = mid - 1;
            } else{
                // 吃不完，直接缩向大的那侧
                left = mid + 1;
            }
        }

        return res;

    }

    boolean canFinish(int[] piles, int h, int speed){
        boolean canFinish = false;
        // 一定注意用long，防止累加过程中整形溢出
        long finishHours = 0;
//        System.out.println("speed: " + speed);
        for (int pile : piles){
            // int除法整除后不需要向上取整的特殊操作
            int time = (pile - 1) / speed + 1;
            finishHours += time;
        }

        return finishHours <= h;
    }



}