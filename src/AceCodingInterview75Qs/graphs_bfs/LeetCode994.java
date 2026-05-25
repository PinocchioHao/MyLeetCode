package AceCodingInterview75Qs.graphs_bfs;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 994. Rotting Oranges
 Medium

 You are given an m x n grid where each cell can have one of three values:

 0 representing an empty cell,
 1 representing a fresh orange, or
 2 representing a rotten orange.
 Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

 Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.



 Example 1:


 Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 Output: 4
 Example 2:

 Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 Output: -1
 Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
 Example 3:

 Input: grid = [[0,2]]
 Output: 0
 Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.


 Constraints:

 m == grid.length
 n == grid[i].length
 1 <= m, n <= 10
 grid[i][j] is 0, 1, or 2.

 */


public class LeetCode994 {

    public int orangesRotting(int[][] grid) {
        int rowNums = grid.length;
        int colNums = grid[0].length;
        int minutes = 0; // 记录腐烂扩散所耗费的分钟数
        int fresh = 0;   // 统计全图初始状态下好橘子的总数
        int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}}; // 上下左右四个扩散方向

        Deque<int[]> deque = new ArrayDeque();

        // 1. 【预处理阶段】：拉网式扫描，完成多源 BFS 的初始化
        for(int i = 0; i < rowNums; i++){
            for(int j = 0; j < colNums; j++){
                if(grid[i][j] == 1){
                    fresh++; // 发现好橘子，计数加 1
                } else if(grid[i][j] == 2){
                    deque.addLast(new int[]{i,j}); // 烂橘子是腐烂源头，作为第一批种子集体入队
                }
            }
        }

        // 2. 【多源 BFS 轰炸阶段】
        // 循环条件极其精妙：只有当图里还有好橘子(fresh > 0)，且队列里还有可以继续扩散的传染源时才继续
        while(fresh > 0 && deque.size() > 0){
            int size = deque.size(); // 当前这一分钟内，同时向外扩散的烂橘子数量

            for(int i = 0; i < size; i++){
                int[] curr = deque.removeFirst(); // 弹出一个当前的传染源

                // 朝四个方向喷射毒液
                for(int[] dir : dirs){
                    int nextRow = curr[0] + dir[0];
                    int nextCol = curr[1] + dir[1];

                    // 边界及状态检查：只有不越界，且对方是好橘子（值为 1）时才被感染
                    if (nextRow >= 0 && nextRow < rowNums && nextCol >= 0 && nextCol < colNums && grid[nextRow][nextCol] == 1){
                        deque.addLast(new int[]{nextRow, nextCol}); // 被感染的橘子入队，成为下一分钟的传染源
                        grid[nextRow][nextCol] = 2; // 【入队即染色】：立刻标记为坏橘子，防止被其他传染源重复入队
                        fresh--; // 全局好橘子数量减 1
                    }
                }
            }
            // 这一批传染源的一轮轰炸彻底结束，时间过去 1 分钟
            minutes++;
        }

        // 3. 【结算阶段】：如果队列空了但依然有顽固的好橘子没被感染，说明它们被空地(0)或边界死死隔绝了
        if(fresh > 0){
            return -1; // 无法全灭，返回 -1
        } else {
            return minutes; // 成功全灭，返回累计时间
        }
    }
}
