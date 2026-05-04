package AceCodingInterview75Qs.array_string;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/*
 *
 *
 *
1431. Kids With the Greatest Number of Candies
Easy

There are n kids with candies. You are given an integer array candies, where each candies[i] represents the number of candies the ith kid has, and an integer extraCandies, denoting the number of extra candies that you have.

Return a boolean array result of length n, where result[i] is true if, after giving the ith kid all the extraCandies, they will have the greatest number of candies among all the kids, or false otherwise.

Note that multiple kids can have the greatest number of candies.



Example 1:

Input: candies = [2,3,5,1,3], extraCandies = 3
Output: [true,true,true,false,true]
Explanation: If you give all extraCandies to:
- Kid 1, they will have 2 + 3 = 5 candies, which is the greatest among the kids.
- Kid 2, they will have 3 + 3 = 6 candies, which is the greatest among the kids.
- Kid 3, they will have 5 + 3 = 8 candies, which is the greatest among the kids.
- Kid 4, they will have 1 + 3 = 4 candies, which is not the greatest among the kids.
- Kid 5, they will have 3 + 3 = 6 candies, which is the greatest among the kids.
Example 2:

Input: candies = [4,2,1,1,2], extraCandies = 1
Output: [true,false,false,false,false]
Explanation: There is only 1 extra candy.
Kid 1 will always have the greatest number of candies, even if a different kid is given the extra candy.
Example 3:

Input: candies = [12,1,12], extraCandies = 10
Output: [true,false,true]


Constraints:

n == candies.length
2 <= n <= 100
1 <= candies[i] <= 100
1 <= extraCandies <= 50
 *
 *
 */


public class LeetCode1431 {


    public static void main(String[] args) {
        int arr[] = {2,3,5,1,3};
        System.out.println(kidsWithCandies(arr, 3));
        System.out.println(kidsWithCandies1(arr, 3));
    }


    // Brute Force - O(n^2)
    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> rlt = new ArrayList();
        for(int i = 0; i<candies.length; i ++){
            boolean flag = true;
            for(int j = 0; j < candies.length; j++){
                if(extraCandies + candies[i] < candies[j]){
                    flag = false;
                    break;
                }
            }
            rlt.add(flag);
        }

        return rlt;
    }

    // Optimized - O(n)
    // find max value in the candies array,
    // then check if each kid's candies + extraCandies is greater than or equal to the max value
    public static List<Boolean> kidsWithCandies1(int[] candies, int extraCandies) {

        int max = Arrays.stream(candies).max().getAsInt();
        // Stream API - O(n) time complexity, O(n) space complexity
        return Arrays.stream(candies)
                .mapToObj(c -> c + extraCandies >= max)
                .collect(Collectors.toList());
        // 常规写法
//        List<Boolean> rlt = new ArrayList();
//        for(int i = 0; i<candies.length; i ++){
//            rlt.add(candies[i] + extraCandies >= max);
//        }
//        return rlt;
    }
}