package pattern.sudoku;

/*
 *
 *
 *
36. Valid Sudoku
Medium

Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.


Example 1:


Input: board =
[['5','3','.','.','7','.','.','.','.']
,['6','.','.','1','9','5','.','.','.']
,['.','9','8','.','.','.','.','6','.']
,['8','.','.','.','6','.','.','.','3']
,['4','.','.','8','.','3','.','.','1']
,['7','.','.','.','2','.','.','.','6']
,['.','6','.','.','.','.','2','8','.']
,['.','.','.','4','1','9','.','.','5']
,['.','.','.','.','8','.','.','7','9']]
Output: true
Example 2:

Input: board =
[['8','3','.','.','7','.','.','.','.']
,['6','.','.','1','9','5','.','.','.']
,['.','9','8','.','.','.','.','6','.']
,['8','.','.','.','6','.','.','.','3']
,['4','.','.','8','.','3','.','.','1']
,['7','.','.','.','2','.','.','.','6']
,['.','6','.','.','.','.','2','8','.']
,['.','.','.','4','1','9','.','.','5']
,['.','.','.','.','8','.','.','7','9']]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.


Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit 1-9 or '.'.

 *
 *
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LeetCode36 {

    int reorderCnt = 0;

    public static void main(String[] args) {

        char[][] board = {{'8', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};



        LeetCode36 example = new LeetCode36();

        System.out.println(example.isValidSudoku(board));
    }


    public boolean isValidSudoku(char[][] board) {
        Map<Character, Integer>[] rowMap = new HashMap[9];
        Map<Character, Integer>[] colMap = new HashMap[9];
        Map<Character, Integer>[] cubeMap = new HashMap[9];
        // 必须先初始化，否则为9个null
        for (int i = 0; i < 9; i++) {
            rowMap[i] = new HashMap<>();
            colMap[i] = new HashMap<>();
            cubeMap[i] = new HashMap<>();
        }


        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.'){
                    // 填充每行元素集合
                    rowMap[i].merge(board[i][j], 1, Integer::sum);
                    // 填充每列元素集合
                    colMap[j].merge(board[i][j], 1, Integer::sum);

                    // 填充3x3方块元素集合
                    int m = i / 3;
                    int n = j / 3;
                    cubeMap[3*m + n].merge(board[i][j], 1, Integer::sum);
//                if (m == 0) {
//                    cubeMap[n].add(board[i][j]);
//                } else if (m == 1) {
//                    cubeMap[3+n].add(board[i][j]);
//                } else {
//                    cubeMap[6+n].add(board[i][j]);
//                }
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            System.out.println("---row:" + i + "    " + rowMap[i]);
            System.out.println("---col:" + i + "    " + colMap[i]);
            System.out.println("---cube:" + i + "    " + cubeMap[i]);
        }



        for (Map<Character, Integer> map : rowMap) {
            for (Integer v : map.values()){
                if (v > 1) {
                    return false;
                }
            }
        }

        for (Map<Character, Integer> map : colMap) {
            for (Integer v : map.values()){
                if (v > 1) {
                    return false;
                }
            }
        }

        for (Map<Character, Integer> map : cubeMap) {
            for (Integer v : map.values()){
                if (v > 1) {
                    return false;
                }
            }
        }


        return true;
    }


    // 用Set显著比Map高效
    public boolean isValidSudoku1(char[][] board) {
        // 数组 + Set 组合：分别记录 9 行、9 列、9 个九宫格的数字
        Set<Character>[] rowSet = new HashSet[9];
        Set<Character>[] colSet = new HashSet[9];
        Set<Character>[] cubeSet = new HashSet[9];

        // 初始化数组中的每一个 HashSet
        for (int i = 0; i < 9; i++) {
            rowSet[i] = new HashSet<>();
            colSet[i] = new HashSet<>();
            cubeSet[i] = new HashSet<>();
        }

        // 只需要一次双层循环，边遍历边校验
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char val = board[i][j];

                // 遇到空白格直接跳过
                if (val == '.') continue;

                // 计算当前坐标属于哪个 3x3 九宫格 (索引 0-8)
                // i / 3 决定了它在第几大行，j / 3 决定了它在第几大列
                int cubeIndex = 3 * (i / 3) + (j / 3);

                // 【技巧】：Set.add() 如果发现元素已存在，会返回 false
                // 所以可以直接将判重和插入合二为一，代码极其清爽
                if (!rowSet[i].add(val) ||
                        !colSet[j].add(val) ||
                        !cubeSet[cubeIndex].add(val)) {
                    // 只要行、列、九宫格有任何一个发生重复，直接宣告数独无效
                    return false;
                }
            }
        }

        // 遍历完所有已填数字均无冲突，数独有效
        return true;
    }



    // 原生数组进行优化，开销更小
    public boolean isValidSudoku2(char[][] board) {
        // row[i][num] 表示第 i 行是否出现过数字 num
        boolean[][] row = new boolean[9][9];
        // col[j][num] 表示第 j 列是否出现过数字 num
        boolean[][] col = new boolean[9][9];
        // cube[k][num] 表示第 k 个九宫格是否出现过数字 num
        boolean[][] cube = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    // 将字符 '1'-'9' 转换为索引 0-8
                    int num = board[i][j] - '1';
                    int cubeIndex = 3 * (i / 3) + j / 3;

                    // 如果在对应的行、列或九宫格中，这个数字的坑位已经被占了 (true)
                    if (row[i][num] || col[j][num] || cube[cubeIndex][num]) {
                        return false;
                    }

                    // 抢占坑位，打上已存在的标记
                    row[i][num] = true;
                    col[j][num] = true;
                    cube[cubeIndex][num] = true;
                }
            }
        }
        return true;
    }

}