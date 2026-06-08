package pattern.sudoku;

/*
 *
 *
 *
37. Sudoku Solver
Hard

Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.



Example 1:


Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
Explanation: The input board is shown above and the only valid solution is shown below:




Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit or '.'.
It is guaranteed that the input board has only one solution.
 *
 *
 */

import java.util.*;

public class LeetCode37 {

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


        LeetCode37 example = new LeetCode37();

        example.solveSudoku(board);

        System.out.println(Arrays.deepToString(board));
    }

    public void solveSudoku(char[][] board) {
        // 从坐标 (0, 0) 开始执行深度优先搜索 (DFS)
        backtrack(board, 0, 0);
    }

    // ==========================================
    // 实现方式一：基于二维循环的状态空间搜索
    // 逻辑：利用传入的 curRow 和 curCol 减少重复遍历
    // ==========================================
    private boolean backtrack(char[][] board, int curRow, int curCol) {
        for (int i = curRow; i < 9; i++) {
            // 状态控制：
            // 若当前正在处理传入的初始行 (curRow)，则列从 curCol 开始；
            // 若已进入下一行 (i > curRow)，则列必须重置为 0，否则会漏遍历左侧列。
            int startCol = (i == curRow) ? curCol : 0;

            for (int j = startCol; j < 9; j++) {
                // 仅处理空闲单元格
                if (board[i][j] == '.') {

                    // 遍历当前单元格的可能状态集 ('1'-'9')
                    for (char k = '1'; k <= '9'; k++) {

                        // 剪枝：校验状态合法性
                        if (isValid(board, i, j, k)) {
                            // 1. 状态修改 (做选择)
                            board[i][j] = k;

                            // 2. 递归深入，传入当前坐标 (i, j)
                            // 若子树返回 true，说明已找到全局解，直接向上返回
                            if (backtrack(board, i, j)) {
                                return true;
                            }
                        }
                        // 3. 状态恢复 (回溯)：当前 k 无法推导至全局解，重置为空格
                        board[i][j] = '.';
                    }
                    // 当前单元格尝试了所有 1-9 的字符均不合法，
                    // 说明之前的状态选择存在错误，向父节点返回 false 触发回溯。
                    return false;
                }
            }
        }
        // 双层循环自然结束，未遇到任何 '.'，说明棋盘已填满，返回全局最优解标志
        return true;
    }


    // ==========================================
    // 实现方式二：单节点状态转移 (纯 DFS)
    // 逻辑：每次递归仅处理给定坐标 (row, col) 的单一单元格
    // ==========================================
    private boolean backtrack1(char[][] board, int row, int col) {
        // 1. 边界处理：列索引越界，向下一行转移
        if (col == 9) {
            row++;
            col = 0;
        }

        // 2. 终止条件：行索引越界，说明 0~8 行已全部处理完毕，找到解
        if (row == 9) {
            return true;
        }

        // 3. 状态转移：当前单元格非空，直接递归处理下一个单元格 (列数 +1)
        if (board[row][col] != '.') {
            return backtrack1(board, row, col + 1);
        }

        // 4. 候选集遍历：当前为空格，尝试填入 1-9
        for (char k = '1'; k <= '9'; k++) {
            // 校验合法性
            if (isValid(board, row, col, k)) {
                // 记录状态
                board[row][col] = k;

                // 向下递归一层，处理右侧下一个单元格
                if (backtrack1(board, row, col + 1)) {
                    return true;
                }

                // 恢复状态
                board[row][col] = '.';
            }
        }

        // 候选集为空或全部无效，返回 false 触发上一层回溯
        return false;
    }


    // ==========================================
    // 约束校验模块
    // 作用：验证在坐标 (row, col) 放置字符 k 是否满足数独的三大规则
    // ==========================================
    private boolean isValid(char[][] board, int row, int col, char k) {
        for (int i = 0; i < 9; i++) {
            // 规则 1：检查行冲突
            if (board[row][i] == k) return false;

            // 规则 2：检查列冲突
            if (board[i][col] == k) return false;

            // 规则 3：检查 3x3 子网格冲突
            // 计算当前坐标所在 3x3 网格的绝对起点坐标 (startRow, startCol)
            int startRow = (row / 3) * 3;
            int startCol = (col / 3) * 3;
            // 注意这里一个使用 i/3 一个 i%3
            // i / 3 计算行偏移 (0,0,0,1,1,1,2,2,2)
            // i % 3 计算列偏移 (0,1,2,0,1,2,0,1,2)
            if (board[startRow + i / 3][startCol + i % 3] == k) return false;
        }

        // 均无冲突，校验通过
        return true;
    }
}