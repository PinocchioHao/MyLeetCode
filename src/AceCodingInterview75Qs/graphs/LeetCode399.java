package AceCodingInterview75Qs.graphs;

/*
 *
 *
 *
399. Evaluate Division
Medium

You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.



Example 1:

Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation:
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
note: x is undefined => -1.0
Example 2:

Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]
Example 3:

Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
Output: [0.50000,2.00000,-1.00000,-1.00000]


Constraints:

1 <= equations.length <= 20
equations[i].length == 2
1 <= Ai.length, Bi.length <= 5
values.length == equations.length
0.0 < values[i] <= 20.0
1 <= queries.length <= 20
queries[i].length == 2
1 <= Cj.length, Dj.length <= 5
Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 *
 *
 */

import java.util.*;

public class LeetCode399 {

    int reorderCnt = 0;

    public static void main(String[] args) {

        int[][] arr = {{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}};

        LeetCode399 example = new LeetCode399();

//        System.out.println(example.calcEquation(6, arr));
    }

    // 将除法转为图，A/B转为A->B和B->A，以及它们的权重，针对queries里面的每一组做图遍历
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 1. 建图：Map<起点, Map<终点, 权重>>
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            String u = equations.get(i).get(0);
            String v = equations.get(i).get(1);
            double val = values[i];
            // 先找graph里有没有以u为key的，如果有则直接拿出来返回，没有则执行lambda表达式现场新建HashMap往里面.put(v,val)
            graph.computeIfAbsent(u, k -> new HashMap<>()).put(v, val);
            graph.computeIfAbsent(v, k -> new HashMap<>()).put(u, 1.0 / val);

            // 也可用以下传统方法建图，注意没有就先new一个，然后put进去
//            // 拿到起点 u，看看外层 Map 里有没有为它建立过内层 Map
//            if (!graph.containsKey(u)) {
//                // 如果没有，手动 new 一个内层 Map，并塞进外层 Map
//                graph.put(u, new HashMap<>());
//            }
//            // 此时，u 对应的内层 Map 绝对百分百存在了
//            // 直接把它 get 出来，然后把邻居 v 和权重放进去
//            graph.get(u).put(v, val);

        }

        // 2. 准备结果数组
        double[] results = new double[queries.size()];

        // 3. 针对每一个 query，做一次独立的 DFS 寻路
        for (int i = 0; i < queries.size(); i++) {
            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);

            // 特殊情况：如果图里压根没有这个变量，直接判死刑，返回 -1.0
            if (!graph.containsKey(start) || !graph.containsKey(end)) {
                results[i] = -1.0;
            } else if (start.equals(end)) {
                // 自己除以自己，结果必为 1.0，不加这个分支，放到dfs里面也没啥影响
                results[i] = 1.0;
            } else {
                // 正常的图寻路，每次询问都用一个干净的 visited 集合
                Set<String> visited = new HashSet<>();
                results[i] = dfs(start, end, 1, graph, visited);
            }
        }

        return results;
    }

    /**
     * 自顶向下 DFS 寻路函数
     *
     * @param curr           当前所在的节点
     * @param target         我们要找的终点
     * @param currentProduct 从起点一路走过来，已经累乘好的权重值
     * @param graph          图结构
     * @param visited        防重集合
     * @return 最终的乘积结果，找不到则返回 -1.0
     */
    private double dfs(String curr, String target, double currentProduct, Map<String, Map<String, Double>> graph, Set<String> visited) {
        // 1. 进场打标签，防止死循环
        visited.add(curr);

        // 2. 基准情况（找到了！）：如果当前节点就是终点
        // 直接把这一路走来、已经算好的 currentProduct 吐出去
        if (curr.equals(target)) {
            return currentProduct;
        }

        // 3. 拿到当前节点的所有邻居开始遍历
        Map<String, Double> neighbors = graph.get(curr);
        for (String neighbor : neighbors.keySet()) {

            // 如果邻居没去过，继续往前走
            if (!visited.contains(neighbor)) {

                // 【核心变化点】：自顶向下
                // 迈向邻居这一步时，直接用“当前的积”乘上“到邻居的权重”
                // 算出一个全新的累乘值，当成“历史包袱”直接丢给下一层
                double nextProduct = currentProduct * neighbors.get(neighbor);

                double result = dfs(neighbor, target, nextProduct, graph, visited);

                // 关键传递：如果子搜索返回的不是 -1.0，说明它在更深的地方摸到了终点
                // 因为结果在终点处已经完全算好了，所以这里不需要做任何加工，直接原样往上传
                if (result != -1.0) {
                    return result;
                }
            }
        }

        // 4. 走投无路，返回 -1.0
        return -1.0;
    }
}