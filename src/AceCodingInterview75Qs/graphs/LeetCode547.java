package AceCodingInterview75Qs.graphs;

/*
 *
 *
 *
547. Number of Provinces

Medium

There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.



Example 1:


Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2
Example 2:


Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3


Constraints:

1 <= n <= 200
n == isConnected.length
n == isConnected[i].length
isConnected[i][j] is 1 or 0.
isConnected[i][i] == 1
isConnected[i][j] == isConnected[j][i]
 *
 *
 */


public class LeetCode547 {

    public static void main(String[] args) {
        int[][] arr = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};

        LeetCode547 example = new LeetCode547();
        System.out.println(example.findCircleNum(arr));
    }


    public int findCircleNum(int[][] isConnected) {
        int cityNums = isConnected.length;
        boolean[] visited = new boolean[cityNums];
        int cnt = 0;
        // 遍历有多少块图
        for (int i = 0; i < isConnected.length; i++) {
            if (!visited[i]) {
                cnt++;
                dfs(i, isConnected, visited);
            }
        }
        return cnt;
    }

    // dfs遍历完相连的所有图，相当于把相邻的连城一块
    private void dfs(int currentCity, int[][] isConnected, boolean[] visited) {
        // 规范：一进函数，立刻封印当前节点，防止死循环
        visited[currentCity] = true;

        // 扫描 currentCity 的所有潜在邻居
        for (int neighbor = 0; neighbor < isConnected.length; neighbor++) {
            // 如果两座城市相连，且邻居城市还没去过，则继续深挖
            if (isConnected[currentCity][neighbor] == 1 && !visited[neighbor]) {
                dfs(neighbor, isConnected, visited);
            }
        }
    }






}


