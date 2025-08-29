package algorithm.dfs;

/**
 *
 * 200. Number of Islands
 * Solved
 * Medium
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 *
 * Example 1:
 *
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 * Example 2:
 *
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is '0' or '1'.
 *
 *
 */
public class LeetCode200 {


	public static void main(String[] args) {

		char[][] grid = {
				{'1', '1', '1'},
				{'0', '1', '0'},
				{'1', '1', '1'}
		};

		System.out.println(numIslands(grid));
		
	}

	/**
	 * dfs解法 - 遍历二维数组，如果是第一次访问，则dfs遍历其所有相连区域，计数
	 * DFS solution - Traverse the 2D array, if the cell is visited for the first time,
	 * use DFS to explore all connected regions and count them.
	 * dfs遍历记录当前位置是否访问，递归上下左右四个方向
	 * DFS keeps track of whether the current position has been visited,
	 * and recursively explores the four directions: up, down, left, and right.
	 * @param grid
	 * @return
	 */
	public static int numIslands(char[][] grid) {
		int rows = grid.length;
		int columns = grid[0].length;
		boolean[][] visited = new boolean[rows][columns];
		int count = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (grid[i][j] == '1' && !visited[i][j]) {
					dfs(grid, i, j, visited);
					count++;
				}
			}
		}
		return count;
	}

	private static void dfs(char[][] grid, int rowIdx, int colIdx, boolean[][] visited) {
		if(rowIdx>grid.length-1 || colIdx>grid[0].length-1 || rowIdx<0 || colIdx<0 || grid[rowIdx][colIdx] == '0' || visited[rowIdx][colIdx]){return;}
		visited[rowIdx][colIdx] = true;
		// 注意要考虑四个方向，而不是仅仅从左上角往右小角，会漏案例
		// Note: Make sure to explore in all four directions, not just from top-left to bottom-right, to avoid missing cases.
		dfs(grid, rowIdx, colIdx+1, visited);
		dfs(grid, rowIdx, colIdx-1, visited);
		dfs(grid, rowIdx-1, colIdx, visited);
		dfs(grid, rowIdx + 1, colIdx, visited);
	}


	/**
	 * 优化解法 - 把访问过的岛屿置为0
	 * Optimized solution - Mark the visited islands as '0'
	 * @param grid
	 * @return
	 */
	public static int numIslands1(char[][] grid) {
		int count = 0;
		int rows = grid.length;
		int columns = grid[0].length;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (grid[i][j] == '1') {
					dfs1(grid, i, j);
					count++;
				}
			}
		}
		return count;
	}

	private static void dfs1(char[][] grid, int rowIdx, int colIdx) {
		if (rowIdx < 0 || colIdx < 0 || rowIdx > grid.length - 1 || colIdx > grid[0].length - 1 || grid[rowIdx][colIdx] == '0'){
			return;
		}
		grid[rowIdx][colIdx] = '0';
		dfs1(grid, rowIdx, colIdx+1);
		dfs1(grid, rowIdx, colIdx-1);
		dfs1(grid, rowIdx+1, colIdx);
		dfs1(grid, rowIdx-1, colIdx);
	}



}
