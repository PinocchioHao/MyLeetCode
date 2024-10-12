package dfs;

/**
 * 547. Number of Provinces
 * Medium
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
 *
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 *
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
 *
 * Return the total number of provinces.
 *
 *
 * Example 1:
 *
 *
 * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * Output: 2
 * Example 2:
 *
 *
 * Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] is 1 or 0.
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 */

public class LeetCode547 {


    public static void main(String[] args) {

        int[][] M = {{1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}};


        System.out.println(findCircleNum(M));

    }
	/**
	 * d[i][j]=d[j][i]，可做降维处理
	 * The matrix is symmetric (d[i][j] = d[j][i]), so it can be optimized by reducing dimensionality.
	 * @param isConnected - A 2D matrix representing the connection between nodes (provinces or cities)
	 * @return The number of provinces (connected components in the matrix)
	 */
	public static int findCircleNum(int[][] isConnected) {
		// 省份数-降为1维处理
		// Number of provinces
		int rows = isConnected.length;
		int count = 0;
		int[] visited = new int[rows];
		for (int i = 0; i < rows; i++) {
			// 第一次访问
			// First time visiting the node
			if (visited[i] == 0) {
				dfs(isConnected, i, visited);
				count++;
			}
		}
		return count;
	}

	/**
	 * 深度优先搜索，标记所有相连的节点为已访问
	 * Perform Depth-First Search (DFS) and mark all connected nodes as visited
	 * @param isConnected - The 2D matrix representing connections
	 * @param i - The current node being visited
	 * @param visited - An array to track which nodes have been visited
	 */
	private static void dfs(int[][] isConnected, int i, int[] visited) {
		for (int j = 0; j < isConnected.length; j++) {
			// 如果节点i与节点j相连且未访问过，则继续遍历
			// If node i is connected to node j and j has not been visited, continue DFS
			if (isConnected[i][j] == 1 && visited[j] == 0) {
				visited[j] = 1;
				dfs(isConnected, j, visited);
			}
		}
	}



}
