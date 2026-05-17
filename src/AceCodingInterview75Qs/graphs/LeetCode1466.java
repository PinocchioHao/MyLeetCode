package AceCodingInterview75Qs.graphs;

/*
 *
 *
 *
1466. Reorder Routes to Make All Paths Lead to the City Zero
Medium

There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.

Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.

This year, there will be a big event in the capital (city 0), and many people want to travel to this city.

Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.

It's guaranteed that each city can reach city 0 after reorder.



Example 1:


Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
Output: 3
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
Example 2:


Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
Output: 2
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
Example 3:

Input: n = 3, connections = [[1,0],[2,0]]
Output: 0


Constraints:

2 <= n <= 5 * 104
connections.length == n - 1
connections[i].length == 2
0 <= ai, bi <= n - 1
ai != bi
 *
 *
 */

import java.util.*;

public class LeetCode1466 {

    int reorderCnt = 0;

    public static void main(String[] args) {

        int[][] arr = {{0,1},{1,3},{2,3},{4,0},{4,5}};

        LeetCode1466 example = new LeetCode1466();

        System.out.println(example.minReorder(6, arr));
    }


    // 转为无向图，从0开始遍历完并记录顺边，即需要反转的边数。
    public int minReorder(int n, int[][] connections) {
        // 无向图
        List<List<Integer>> graph = new ArrayList<>();
        // 方向
        List<Set<Integer>> direction = new ArrayList<>();

        boolean[] visited = new boolean[n];
        // 初始化
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            direction.add(new HashSet<>());
        }
        // 建无向图，并记录方向
        for(int[] conn : connections){
            int from = conn[0];
            int to = conn[1];
            graph.get(from).add(to);
            graph.get(to).add(from);

            direction.get(from).add(to);
        }
        // 从0开始遍历
        dfs(0, graph, direction, visited);

        return reorderCnt;
    }

    public void dfs(int curr, List<List<Integer>> graph, List<Set<Integer>> direction, boolean[] visited){
        visited[curr] = true;
        List<Integer> neighbours = graph.get(curr);
        for (int neighbour : neighbours){
            if (!visited[neighbour]){
                // 当前路径为curr->neighbour，则需要反向，cnt++
                if (direction.get(curr).contains(neighbour)) reorderCnt++;
                dfs(neighbour, graph, direction, visited);
            }
        }
    }





}