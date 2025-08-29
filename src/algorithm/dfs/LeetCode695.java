package algorithm.dfs;

/**
 * 695. Max Area of Island
 * Medium
 *
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * The area of an island is the number of cells with a value 1 in the island.
 *
 * Return the maximum area of an island in grid. If there is no island, return 0.
 *
 *
 * Example 1:
 *
 * Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Output: 6
 * Explanation: The answer is not 11, because the island must be connected 4-directionally.
 * Example 2:
 *
 * Input: grid = [[0,0,0,0,0,0,0,0]]
 * Output: 0
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] is either 0 or 1.
 */
public class LeetCode695 {

    public static void main(String[] args) {

        int[][] grid = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}
        };


        System.out.println(maxAreaOfIsland(grid));

    }

    /**
     * 遍历所有区域，每次找到第一次未被访问的岛屿则计算面积，更新最大面积
     *
     * Traverse all regions and calculate the area each time an unvisited island is found for the first time, updating the maximum area.
     *
     * @param grid
     * @return
     */
    public static int maxAreaOfIsland(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    int area = dfs(grid, i, j, visited);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }

    /**
     * dfs求岛屿面积，访问岛屿后将连接区域置为已访问
     *
     * Calculate the area of the island using DFS, marking connected regions as visited after accessing the island.
     *
     * @param grid
     * @param i
     * @param j
     * @param visited
     * @return
     */
    private static int dfs(int[][] grid, int i, int j, boolean[][] visited) {
        // 递归边界：如果出界或者当前区域不为陆地，则面积为0
        // Base case for recursion
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || visited[i][j] || grid[i][j] == 0) {
            return 0;
        }
        visited[i][j] = true;
        // 向四周进行dfs遍历计算区域面积，面积: 当前面积1+上下左右递归结果的面积
        // Perform DFS traversal to calculate the area of the region: current area 1 + the area from recursive results for up, down, left, and right.
        int area = 1 + dfs(grid, i + 1, j, visited) +
                dfs(grid, i - 1, j, visited) +
                dfs(grid, i, j + 1, visited) +
                dfs(grid, i, j - 1, visited);
        return area;
    }


}
