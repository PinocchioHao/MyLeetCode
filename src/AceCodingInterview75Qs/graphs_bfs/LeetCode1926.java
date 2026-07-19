package AceCodingInterview75Qs.graphs_bfs;

/*
 *
 *
 *
1926. Nearest Exit from Entrance in Maze
Medium

You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+'). You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol] denotes the row and column of the cell you are initially standing at.

In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot step outside the maze. Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell that is at the border of the maze. The entrance does not count as an exit.

Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.



Example 1:


Input: maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
Output: 1
Explanation: There are 3 exits in this maze at [1,0], [0,2], and [2,3].
Initially, you are at the entrance cell [1,2].
- You can reach [1,0] by moving 2 steps left.
- You can reach [0,2] by moving 1 step up.
It is impossible to reach [2,3] from the entrance.
Thus, the nearest exit is [0,2], which is 1 step away.
Example 2:


Input: maze = [["+","+","+"],[".",".","."],["+","+","+"]], entrance = [1,0]
Output: 2
Explanation: There is 1 exit in this maze at [1,2].
[1,0] does not count as an exit since it is the entrance cell.
Initially, you are at the entrance cell [1,0].
- You can reach [1,2] by moving 2 steps right.
Thus, the nearest exit is [1,2], which is 2 steps away.
Example 3:


Input: maze = [[".","+"]], entrance = [0,0]
Output: -1
Explanation: There are no exits in this maze.


Constraints:

maze.length == m
maze[i].length == n
1 <= m, n <= 100
maze[i][j] is either '.' or '+'.
entrance.length == 2
0 <= entrancerow < m
0 <= entrancecol < n
entrance will always be an empty cell.

 *
 *
 */

import java.util.*;

public class LeetCode1926 {

    public static void main(String[] args) {

        int[][] arr = {{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}};
        int[] a = {1, 1};
        System.out.println();


        LeetCode1926 example = new LeetCode1926();

//        System.out.println(example.calcEquation(6, arr));
    }


    // 方案一：拿出队时再染墙（延迟标记法，队列中可能会产生重复元素）
    public int nearestExit(char[][] maze, int[] entrance) {
        // 定义上、下、右、左四个方向
        int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        // 确定迷宫的边界线
        int bottomEdge = maze.length - 1;
        int rightEdge = maze[0].length - 1;
        int leftEdge = 0;
        int topEdge = 0;

        int steps = 0; // 记录当前走的步数（层数）
        // 队列记录位置
        Deque<int[]> deque = new ArrayDeque<>();
        deque.addLast(entrance); // 起点入队

        while (deque.size() > 0) {
            int size = deque.size(); // 这一层的节点数量

            for (int i = 0; i < size; i++) {
                int[] currPos = deque.removeFirst(); // 弹出当前层的一个节点

                // 【延迟标记的防线】：因为入队没染墙，同一个点可能进来了好几次。
                // 这里判断如果已经是 '+' 了，说明这个点之前已经处理过了，直接跳过。
                if (maze[currPos[0]][currPos[1]] == '.') {

                    // 业务判断：如果不是起点(steps > 0)且已经踩到了边界，说明找到了最近出口，直接返回步数
                    // steps>0是为了防止迷宫入口就在出口的边界，这里入口不能算作出口，所有需要steps>0的情况
                    if (steps > 0 && (currPos[0] == topEdge || currPos[0] == bottomEdge || currPos[1] == leftEdge || currPos[1] == rightEdge)) {
                        return steps;
                    }

                    // 向四周扩散
                    for (int j = 0; j < dir.length; j++) {
                        int nextrow = currPos[0] + dir[j][0];
                        int nextcol = currPos[1] + dir[j][1]; // ★ 修正：原代码写成了 currPos[0] 和 dir[j][0]

                        // 边界及墙壁合法性检查
                        if (nextcol >= leftEdge && nextcol <= rightEdge && nextrow >= topEdge && nextrow <= bottomEdge && maze[nextrow][nextcol] != '+') {
                            // 缺点所在：这里只是把邻居加进队列，并没有改变迷宫状态。
                            // 如果有其他点也相邻这个 nextrow/nextcol，它也会把它重复加进队列。
                            deque.addLast(new int[]{nextrow, nextcol});
                        }
                    }

                    // 【染色时机】：等这个点的四周都扩散完了，才把它自己变成墙。
                    maze[currPos[0]][currPos[1]] = '+';
                }
            }
            steps++; // 这一层的所有节点全部轰炸完毕，步数加 1
        }
        return -1; // 队列走空了也出不去，返回 -1
    }


    // 方案二：入队时立刻染墙（卡位标记法，性能最优，无任何重复元素入队）
    public int nearestExit1(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;

        // 1. 定义经典的四个方向向量：上、下、左、右
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // 2. 队列标准初始化（存坐标数组 [r, c]）
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(entrance);

        // 3. 核心：一旦起点入队，立刻将其污染成墙 '+', 防止以后走回头路
        maze[entrance[0]][entrance[1]] = '+';

        // 记录当前走了多少步（层数）
        int steps = 0;

        // 4. 开始标准的分层 BFS
        while (!queue.isEmpty()) {
            int size = queue.size();

            // 严格的“按层轰炸”：这一层的所有节点，代表它们到起点的距离都是 steps
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int currRow = curr[0];
                int currCol = curr[1];

                // 【核心业务判断】：如果当前点是边界，且它不是起点（也就是步数 > 0） -- 因为入口不能算作出口，须排除entrance直接在最外层被认作出口的情况
                // 那么它就是离起点最近的出口，直接返回当前步数！
                if (steps > 0 && (currRow == 0 || currRow == m - 1 || currCol == 0 || currCol == n - 1)) {
                    return steps;
                }

                // 朝上下左右四个方向扩散
                for (int[] dir : dirs) {
                    int nextRow = currRow + dir[0];
                    int nextCol = currCol + dir[1];

                    // 边界控制：判断新坐标是否合法（不越界、且是通路 '.'）
                    if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n && maze[nextRow][nextCol] == '.') {
                        // 合法邻居入队
                        queue.offer(new int[]{nextRow, nextCol});
                        // 动作必须连贯：只要入队，立刻改写为 '+'，打上已访问标记
                        maze[nextRow][nextCol] = '+';
                    }
                }
            }
            // 这一层全部扩散完毕，步数加 1，下一层节点准备开辟新战场
            steps++;
        }

        // 队列走空了也没触发 return，说明被墙死锁了，根本出不去
        return -1;
    }
}


