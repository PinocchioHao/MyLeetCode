package AceCodingInterview75Qs.hash_map_set;

/*
 *
 *
 *

2352. Equal Row and Column Pairs

Medium

Given a 0-indexed n x n integer matrix grid, return the number of pairs (ri, cj) such that row ri and column cj are equal.

A row and column pair is considered equal if they contain the same elements in the same order (i.e., an equal array).



Example 1:


Input: grid = [[3,2,1],[1,7,6],[2,7,7]]
Output: 1
Explanation: There is 1 equal row and column pair:
- (Row 2, Column 1): [2,7,7]
Example 2:


Input: grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
Output: 3
Explanation: There are 3 equal row and column pairs:
- (Row 0, Column 0): [3,1,2,2]
- (Row 2, Column 2): [2,4,2,2]
- (Row 3, Column 2): [2,4,2,2]


Constraints:

n == grid.length == grid[i].length
1 <= n <= 200
1 <= grid[i][j] <= 105

 *
 *
 */


import java.util.*;
import java.util.stream.Collectors;

public class LeetCode2352 {


    public static void main(String[] args) {
        int[][] grid = {{3,2,1},{1,7,6},{2,7,7}};
        System.out.println(equalPairs(grid));
    }

    // 题目要求出相同的行和相同的列一共有几个
    // HashMap记录行元素以及出现次数
    // 遍历列的时候进行判断并次数累加
    public static int equalPairs(int[][] grid) {
        int n = grid.length;
        int count = 0;
        HashMap<List<Integer>, Integer> rowMap = new HashMap<>();
        for (int[] row : grid) {
            // 这里效率不如直接for循环加元素
            List<Integer> rowList = Arrays.stream(row).boxed().collect(Collectors.toList());
            rowMap.merge(rowList, 1, Integer::sum);
        }

        for (int i = 0; i < n; i++) {
            // 按列添加元素
            List<Integer> colList = new ArrayList<>();
            for (int j = 0; j < n; j ++) {
                colList.add(grid[j][i]);
            }
            // 查找并累加次数
            count += rowMap.getOrDefault(colList, 0);
        }

        return count;
    }


}