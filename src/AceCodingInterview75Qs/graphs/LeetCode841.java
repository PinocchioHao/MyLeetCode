package AceCodingInterview75Qs.graphs;

/*
 *
 *
 *
841. Keys and Rooms
Solved
Medium
Topics
premium lock icon
Companies
There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0. Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.

When you visit a room, you may find a set of distinct keys in it. Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.

Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, return true if you can visit all the rooms, or false otherwise.



Example 1:

Input: rooms = [[1],[2],[3],[]]
Output: true
Explanation:
We visit room 0 and pick up key 1.
We then visit room 1 and pick up key 2.
We then visit room 2 and pick up key 3.
We then visit room 3.
Since we were able to visit every room, we return true.
Example 2:

Input: rooms = [[1,3],[3,0,1],[2],[0]]
Output: false
Explanation: We can not enter room number 2 since the only key that unlocks it is in that room.


Constraints:

n == rooms.length
2 <= n <= 1000
0 <= rooms[i].length <= 1000
1 <= sum(rooms[i].length) <= 3000
0 <= rooms[i][j] < n
All the values of rooms[i] are unique.
 *
 *
 */

import datastructure.tree.TreeNode;
import datastructure.tree.TreeNodeUtils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class LeetCode841 {

    public static void main(String[] args) {


        LeetCode841 example = new LeetCode841();

//        System.out.println(example.lowestCommonAncestor(tree));
    }


    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int roomNums = rooms.size();
        boolean[] visited = new boolean[roomNums];
        // 从0号房开始遍历图
        dfs(0, rooms, visited);
        // 遍历完后查看所有房间是否有被访问，这里也可以使用一个全局变量在dfs访问时累加，最后判断这个全局变量是否等于roomNums也可
        for (boolean idxVisited : visited) {
            if (!idxVisited) return false;
        }
        return true;
    }

    // dfs遍历图
    public void dfs(int roomIdx, List<List<Integer>> roomsGraph, boolean[] visited) {
        // 一遍历到就记为已访问
        visited[roomIdx] = true;
        List<Integer> keys = roomsGraph.get(roomIdx);
        // 拿出当前房子的所有钥匙并遍历它们
        for (int key : keys) {
            if (!visited[key]) {
                dfs(key, roomsGraph, visited);
            }
        }
    }


    // BFS解法，先打开1个房间，得到它的钥匙，入队后之后慢慢开其它门
    public boolean canVisitAllRooms1(List<List<Integer>> rooms) {
        int roomNums = rooms.size();
        boolean[] visited = new boolean[roomNums];
        // 队列记录房间下标
        Deque<Integer> deque = new ArrayDeque<>();
        // 初始化把0号房间入队
        deque.addLast(0);
        visited[0] = true;
        int cnt = 1;

        while (deque.size()>0){
            int currRoomIdx = deque.removeFirst();
            // 遍历当前房间的所有钥匙
            List<Integer> currKeys = rooms.get(currRoomIdx);
            for (int key : currKeys){
                // 访问所有没被访问过的
                if (!visited[key]){
                    // 一访问先把状态置为已访问
                    visited[key] = true;
                    cnt++;
                    // 新房间的钥匙入队，待会儿再开
                    deque.addLast(key);
                }
            }
        }
        // 访问完所有房间则返回true
        return cnt == roomNums;
    }




}