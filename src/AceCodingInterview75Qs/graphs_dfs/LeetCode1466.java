package AceCodingInterview75Qs.graphs_dfs;

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

        System.out.println(example.minReorder2(6, arr));
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

    int reorderCount = 0; // 记录需要反转的道路数量

    public int minReorder1(int n, int[][] connections) {
        // 【第一步：建图】强行建一个无向图，但带上“原装(1)”和“高仿(0)”的标记
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] conn : connections) {
            int from = conn[0];
            int to = conn[1];
            // from 到 to 是原装路，标记为 1
            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(new int[]{to, 1});
            // to 到 from 是为了遍历加的高仿路，标记为 0
            graph.computeIfAbsent(to, k -> new ArrayList<>()).add(new int[]{from, 0});
        }

        // 引入图的 visited 数组防止死循环
        boolean[] visited = new boolean[n];

        // 【第二步：从 0 号城市开始地毯式扩散】
        dfs1(0, graph, visited);

        return reorderCount;
    }

    private void dfs1(int currentCity, Map<Integer, List<int[]>> graph, boolean[] visited) {
        // 进场打标签
        visited[currentCity] = true;

        // 扩散：去敲所有邻居的门
        if (graph.containsKey(currentCity)) {
            for (int[] next : graph.get(currentCity)) {
                int neighbor = next[0];
                int isOriginal = next[1];

                // 如果邻居还没去过
                if (!visited[neighbor]) {
                    // 关键核心：如果你是从 0 往外扩散，却走了一条“原装路” (isOriginal == 1)
                    // 说明原路是背离 0 的，现实中的人过不来，必须反转！
                    if (isOriginal == 1) {
                        reorderCount++;
                    }

                    // 顺着这个邻居继续往下轰炸
                    dfs1(neighbor, graph, visited);
                }
            }
        }
    }


    // 构建权重图，记正向的权重为1，逆向的权重为0
    // 从0号城市出发，dfs遍历图，累加权重即可得到题目要求需要逆向的边数
    public int minReorder2(int n, int[][] connections) {
        // int[] 0号位置为邻接点，1号位置为权重
        Map<Integer, List<int[]>> graph = new HashMap();

        // 建图
        for(int[] edge : connections){
            int from = edge[0];
            int to = edge[1];
            // 正向的权重记1，反向的也记录权重为0
            graph.computeIfAbsent(from, k -> new ArrayList()).add(new int[]{to, 1});
            graph.computeIfAbsent(to, k -> new ArrayList()).add(new int[]{from, 0});
        }
        boolean[] visited = new boolean[n];
        dfs(0, graph, visited);
        return reorderCnt;

    }


    public void dfs(int curr, Map<Integer, List<int[]>> graph, boolean[] visited){
        // 访问即置为true
        visited[curr] = true;
        List<int[]> neighbors = graph.get(curr);
        for(int[] neighbor : neighbors) {
            // 如果邻居没去过就访问
            if(!visited[neighbor[0]]){
                // 需要逆向的边即从0出发正向的权重
                reorderCnt += neighbor[1];
                dfs(neighbor[0], graph, visited);
            }
        }
    }

}