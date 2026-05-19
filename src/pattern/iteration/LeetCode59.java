package pattern.iteration;

/*
 *
 *
 *
59. Spiral Matrix II
Medium

Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.



Example 1:


Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]
Example 2:

Input: n = 1
Output: [[1]]


Constraints:

1 <= n <= 20
 *
 *
 */

public class LeetCode59 {

    int reorderCnt = 0;

    public static void main(String[] args) {


        LeetCode59 example = new LeetCode59();

        System.out.println(example.generateMatrix(3));
    }


    public int[][] generateMatrix(int n) {
        int left = 0;
        int right = n-1;
        int top = 0;
        int bottom= n-1;
        int[][] res = new int[n][n];
        int num = 1;
        // 边走边缩
        while (left <= right && top <=bottom){
            for (int i = left; i <=right ; i++) {
                res[top][i] = num;
                num++;
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                res[i][right] = num;
                num++;
            }
            right--;
            if(bottom >= top){
                for (int i = right; i >= left ; i--) {
                    res[bottom][i] = num;
                    num++;
                }
            }
            bottom--;
            if (right >= left){
                for (int i = bottom; i >= top ; i--) {
                    res[i][left] = num;
                    num++;
                }
            }
            left++;
        }

        return res;

    }


}