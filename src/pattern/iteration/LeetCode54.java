package pattern.iteration;

/*
 *
 *
 *
54. Spiral Matrix
Medium

Given an m x n matrix, return all elements of the matrix in spiral order.



Example 1:


Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:


Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100
 *
 *
 */

import java.util.ArrayList;
import java.util.List;

public class LeetCode54 {

    int reorderCnt = 0;

    public static void main(String[] args) {

        int[][] m = {{1},{2},{3}};

        LeetCode54 example = new LeetCode54();

        System.out.println(example.spiralOrder(m));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList();
        int m = matrix.length;
        int n = matrix[0].length;
        int top = 0;
        int bottom = m - 1;
        int left = 0;
        int right = n - 1;
        // 边走边缩
        // while (top<=bottom && left<=right){
        //     // 上方从左往右
        //     for(int i = left; i <= right; i++){
        //         res.add(matrix[top][i]);
        //     }
        //     top++;
        //     // 右边从上往下
        //     for(int i = top; i <= bottom; i++){
        //         res.add(matrix[i][right]);
        //     }
        //     right--;


        //     //下面从右往左（用top<=bottom是因为之前top已经+1了，剩下bottom如果还有行就从右往左走）
        //     if (top <= bottom){
        //         for(int i = right; i >= left; i--){
        //             res.add(matrix[bottom][i]);
        //         }
        //         bottom--;
        //     }

        //     //左边从下往上（left<=right同理，right已经-1了，剩下还有左列就从下往上）
        //     if (left<=right){
        //         for(int i = bottom; i >= top; i--){
        //             res.add(matrix[i][left]);
        //         }
        //         left++;
        //     }

        // }


        // 先走后缩
        while (top<=bottom && left<=right){
            // 上方从左往右
            for(int i = left; i <= right; i++){
                res.add(matrix[top][i]);
            }
            // 右边从上往下
            for(int i = top + 1; i <= bottom; i++){
                res.add(matrix[i][right]);
            }

            //下面从右往左（只有当剩余行数 > 1 时，才需要倒着走底行）
            if (top < bottom){
                for(int i = right - 1; i >= left; i--){
                    res.add(matrix[bottom][i]);
                }
            }


            //左边从下往上（只有当剩余列数 > 1 时，才需要倒着走左列）
            if (left < right){
                for(int i = bottom - 1; i > top; i--){
                    res.add(matrix[i][left]);
                }
            }
            top++;
            bottom--;
            left++;
            right--;
        }

        return res;

    }
}