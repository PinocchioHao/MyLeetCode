package algorithm.dfs;

/**
 * 463. Island Perimeter
 * Easy
 * You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.
 *
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
 *
 * The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
 *
 *
 * Example 1:
 *
 * Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
 * Output: 16
 * Explanation: The perimeter is the 16 yellow stripes in the image above.
 * Example 2:
 *
 * Input: grid = [[1]]
 * Output: 4
 * Example 3:
 *
 * Input: grid = [[1,0]]
 * Output: 4
 *
 * Constraints:
 *
 * row == grid.length
 * col == grid[i].length
 * 1 <= row, col <= 100
 * grid[i][j] is 0 or 1.
 * There is exactly one island in grid.
 */
public class LeetCode463 {


	public static void main(String[] args) {

		int[][] grid = {
				{1, 1, 1},
				{0, 0, 0},
				{1, 1, 1}
		};

		System.out.println(islandPerimeter2(grid));

	}

	/**
	 * 因为接壤导致两个方格公用的边都不会被算入周长内，所以得到周长公式：
	 * 周长 = 陆地个数*4 - 接壤边数*2
	 * 所以找到接壤边就好解了
	 *
	 * Since shared edges between adjacent land cells are not counted toward the perimeter,
	 * the formula for perimeter becomes:
	 * Perimeter = (number of land cells * 4) - (number of shared edges * 2)
	 * Therefore, we only need to find the shared edges to solve the problem.
	 *
	 * @param grid
	 * @return
	 */
	public static int islandPerimeter(int[][] grid) {
		int land = 0;
		int nearSide = 0;
		int perimeter = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					// 当前值为1则陆地+1
					// If the current cell is land, increment the land count
					land++;

					// 因为遍历每个格子，为了防止多算，只需要找左边和上方两个边界
					// To avoid double counting, only check the left and upper boundaries
					if (i > 0 && grid[i - 1][j] == 1) {
						// 左边相邻为陆地，接壤边数+1
						// If the cell above is also land, increment the shared edge count
						nearSide++;
					}
					if (j > 0 && grid[i][j - 1] == 1) {
						// 上方相邻为陆地，接壤边数+1
						// If the cell to the left is also land, increment the shared edge count
						nearSide++;
					}
				}
			}
		}
		perimeter = land * 4 - nearSide * 2;
		// 计算得到的周长
		// Calculate the total perimeter
		return perimeter;
	}

	/**
	 * 遍历每个格子，判断相邻四个格子，如果为水或者边界则周长+1
	 *
	 * Iterate through each cell and check its four neighboring cells.
	 * If a neighbor is water or a boundary, increment the perimeter by 1.
	 *
	 * @param grid
	 * @return
	 */
	public static int islandPerimeter1(int[][] grid) {
		int rows = grid.length;
		int cols = grid[0].length;
		int perimeter = 0;

		// 遍历每个格子
		// Loop through each cell
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				// 如果是陆地，检查四周，周围是水或边界则周长+1
				// If the cell is land, check its surroundings. If it borders water or a boundary, increment the perimeter
				if (grid[i][j] == 1) {
					// 上边界或上方是水
					// Top boundary or water
					if (i == 0 || grid[i - 1][j] == 0) {
						perimeter++;
					}
					// 下边界或下方是水
					// Bottom boundary or water
					if (i == rows - 1 || grid[i + 1][j] == 0) {
						perimeter++;
					}
					// 左边界或左侧是水
					// Left boundary or water
					if (j == 0 || grid[i][j - 1] == 0) {
						perimeter++;
					}
					// 右边界或右侧是水
					// Right boundary or water
					if (j == cols - 1 || grid[i][j + 1] == 0) {
						perimeter++;
					}
				}
			}
		}
		return perimeter;
	}

	/**
	 * 深度优先搜索 (DFS)
	 * Depth-first search (DFS) approach
	 *
	 * @param grid
	 * @return
	 */
	public static int islandPerimeter2(int[][] grid) {
		int perimeter = 0;
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				// 当前格子为陆地且未被访问，进行DFS
				// If the current cell is land and has not been visited, start a DFS
				if (grid[i][j] == 1 && visited[i][j] == false) {
					// 考虑到可能存在多个岛屿，采用累加（虽然题目中限定了一个岛屿）
					// Consider the possibility of multiple islands, so the perimeter is accumulated (even though the problem specifies a single island)
					perimeter += dfs(grid, i, j, visited);
				}
			}
		}
		return perimeter;
	}

	/**
	 * 该DFS表示从陆地出发，计算它的周长
	 * Perform DFS from a land cell and calculate its perimeter.
	 *
	 * @param grid
	 * @param row
	 * @param col
	 * @param visited
	 * @return
	 */
	public static int dfs(int[][] grid, int row, int col, boolean[][] visited) {
		// 递归边界条件，如果遇到边界或者遇到水，则贡献一条边，即周长+1
		// Base case for recursion: if a boundary or water is encountered, increment the perimeter by 1
		if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0) {
			return 1;
		}
		// 如果已经访问，则不计周长直接返回0
		// If the cell has been visited, return 0 without adding to the perimeter
		if (visited[row][col]) {
			return 0;
		}
		// 标记当前格子为已访问
		// Mark the current cell as visited
		visited[row][col] = true;

		// 递归检查四周的格子，累加周长
		// Recursively check the surrounding cells and accumulate the perimeter
		int perimeter = 0;
		perimeter += dfs(grid, row + 1, col, visited);
		perimeter += dfs(grid, row - 1, col, visited);
		perimeter += dfs(grid, row, col + 1, visited);
		perimeter += dfs(grid, row, col - 1, visited);
		return perimeter;
	}



}
