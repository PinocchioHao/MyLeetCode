package AceCodingInterview75Qs.hash_map_set;

/*
 *
 *
 *

1207. Unique Number of Occurrences

Easy

Given an array of integers arr, return true if the number of occurrences of each value in the array is unique or false otherwise.



Example 1:

Input: arr = [1,2,2,1,1,3]
Output: true
Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.
Example 2:

Input: arr = [1,2]
Output: false
Example 3:

Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
Output: true


Constraints:

1 <= arr.length <= 1000
-1000 <= arr[i] <= 1000

 *
 *
 */


import java.util.*;
import java.util.stream.Collectors;

public class LeetCode1207 {


    public static void main(String[] args) {
        int arr[] = {-1000,-1000,-999};
        int arr2[] = {1, 12, -5, -6, 50, 3};
        System.out.println(uniqueOccurrences(arr));
        System.out.println(uniqueOccurrences1(arr));
    }

    // HashMap 遍历一次统计每个元素出现的次数
    // 然后将出现的次数存入 HashSet 中，边存边判断，如果 HashSet 中已经存在该次数，则说明有重复的次数，返回 false，否则返回 true
    // 时间复杂度 O(n)，空间复杂度 O(n)
    public static boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Long> countMap = new HashMap();
//        for (int num : arr){
//            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
//        }

        countMap = Arrays.stream(arr).boxed().collect(Collectors.groupingBy(n->n, Collectors.counting()));
        countMap = Arrays.stream(arr).boxed().collect(Collectors.groupingBy(n->n, Collectors.counting()));


        Set<Long> set = new HashSet();
        for (long count : countMap.values()){
            if (set.contains(count)){
                return false;
            } else {
                set.add(count);
            }
        }
        return true;
    }

    // 考虑到数组元素的范围是 -1000 <= arr[i] <= 1000，可以使用一个长度为 2001 的数组来统计每个元素出现的次数，索引为元素值加上 1000
    // 然后再使用一个 HashSet 来判断出现的次数是否有重复
    // 时间复杂度 O(n)，空间复杂度 O(n)
    public static boolean uniqueOccurrences1(int[] arr) {
        int[] count = new int[2001];
        for (int num : arr) {
            count[num + 1000]++;
        }
        Set set = new HashSet();
        for (int cnt : count) {
            if (cnt!=0 && set.contains(cnt)) {
                return false;
            }
            set.add(cnt);
        }
        return true;

    }
}