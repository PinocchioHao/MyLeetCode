package AceCodingInterview75Qs.hash_map_set;

/*
 *
 *
 *

2215. Find the Difference of Two Arrays

Easy

Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:

answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
Note that the integers in the lists may be returned in any order.



Example 1:

Input: nums1 = [1,2,3], nums2 = [2,4,6]
Output: [[1,3],[4,6]]
Explanation:
For nums1, nums1[1] = 2 is present at index 0 of nums2, whereas nums1[0] = 1 and nums1[2] = 3 are not present in nums2. Therefore, answer[0] = [1,3].
For nums2, nums2[0] = 2 is present at index 1 of nums1, whereas nums2[1] = 4 and nums2[2] = 6 are not present in nums1. Therefore, answer[1] = [4,6].
Example 2:

Input: nums1 = [1,2,3,3], nums2 = [1,1,2,2]
Output: [[3],[]]
Explanation:
For nums1, nums1[2] and nums1[3] are not present in nums2. Since nums1[2] == nums1[3], their value is only included once and answer[0] = [3].
Every integer in nums2 is present in nums1. Therefore, answer[1] = [].


Constraints:

1 <= nums1.length, nums2.length <= 1000
-1000 <= nums1[i], nums2[i] <= 1000

 *
 *
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LeetCode2215 {


    public static void main(String[] args) {
        int arr[] = {2,1,-1};
        int arr2[] = {1, 12, -5, -6, 50, 3};
        System.out.println(findDifference(arr, arr2).toString());
    }

    // 哈希表，分别将两个数组的元素存入两个哈希表中，然后遍历其中一个哈希表，找出不在另一个哈希表中的元素。时间复杂度O(n)，空间复杂度O(n)
    public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            set2.add(num);
        }
        // Java 8 的流式写法，效率较低，实际面试中不建议使用
//        List<Integer> res1 = set1.stream().filter(num -> !set2.contains(num)).collect(Collectors.toList());
//        List<Integer> res2 = set2.stream().filter(num -> !set1.contains(num)).collect(Collectors.toList());
        // 手搓for循环写法，效率更高
        List<Integer> res1 = new ArrayList<>();
        for (int n : set1) {
            if (!set2.contains(n)) res1.add(n);
        }

        List<Integer> res2 = new ArrayList<>();
        for (int n : set2) {
            if (!set1.contains(n)) res2.add(n);
        }

        List<List<Integer>> ans = new ArrayList<>();
        ans.add(res1);
        ans.add(res2);
        return ans;
//        return List.of(res1, res2); // Java 9+ 的简洁写法
    }

    // 考虑到数组元素的范围是 -1000 <= nums1[i], nums2[i] <= 1000，也可以使用布尔数组来代替哈希表，空间复杂度 O(2001)，时间复杂度 O(n)
}