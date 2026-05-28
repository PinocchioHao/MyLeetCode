package AceCodingInterview75Qs.backtrack;

/*
 *
 *
 *
216. Combination Sum III
Medium

Find all valid combinations of k numbers that sum up to n such that the following conditions are true:

Only numbers 1 through 9 are used.
Each number is used at most once.
Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.



Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Explanation:
1 + 2 + 4 = 7
There are no other valid combinations.
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6],[1,3,5],[2,3,4]]
Explanation:
1 + 2 + 6 = 9
1 + 3 + 5 = 9
2 + 3 + 4 = 9
There are no other valid combinations.
Example 3:

Input: k = 4, n = 1
Output: []
Explanation: There are no valid combinations.
Using 4 different numbers in the range [1,9], the smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1, there are no valid combination.


Constraints:

2 <= k <= 9
1 <= n <= 60
 *
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LeetCode216 {


    public static void main(String[] args) {

        int[] arr1 = {3, 6, 7, 11};
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};

        LeetCode216 example = new LeetCode216();

        System.out.println(example.combinationSum3(3, 7));
    }

    // ==========================================
    // 主函数：初始化容器并触发回溯
    // ==========================================
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        // 初始状态：找 k 个数，目标和为 n，从数字 1 开始选，当前路径为空
        backtrack(k, n, 1, new ArrayList<>(), res);
        return res;
    }

    /**
     * 核心回溯函数
     * * @param k        还需要凑齐的总个数（这里作为固定基准，配合 path.size() 使用）
     * @param target   【精简技巧】：目标和。每向路径中加一个数 i，target 就减去 i。减到 0 说明和刚好匹配。
     * @param startIdx 【防重技巧】：当前这一层可以从哪个数字开始选。严格控制升序，杜绝重复组合（如 [1,2] 和 [2,1]）。
     * @param path     当前正在探索的一条路径（一条道走到黑的小本本）
     * @param res      全局结果集
     */
    public void backtrack(int k, int target, int startIdx, List<Integer> path, List<List<Integer>> res){
        // 1. 终止条件（死胡同或终点）：只要路径里塞满了 k 个数，就必须停下
        if (path.size() == k){
            // 如果不仅个数够了，连目标和也刚好扣减到 0，说明找到了完美组合
            if (target == 0) {
                // 【深拷贝陷阱】：必须 new 一个新 ArrayList 保存当前快照，否则后续的回溯会把原 path 删空
                res.add(new ArrayList<>(path));
            }
            // 无论对错，只要满 k 个数就必须返回（这也是一种隐性剪枝）
            return;
        }

        // 2. 单层探索逻辑：从 startIdx 开始，最多选到 9
        for (int i = startIdx; i <= 9; i++) {

            // 【极限剪枝】：因为循环里的数字 i 是递增的（1, 2, 3...）
            // 如果当前的 target 连当前的 i 都减不起（结果变负数）了，
            // 那后面比 i 更大的数字绝对更加减不起！直接 break 砍掉整个后续循环，效率拉满！
            if (target - i < 0) break;

            // 【做选择】：把数字 i 加入当前路径
            path.add(i);

            // 【递归下探】：
            // target - i：目标和相应减少
            // i + 1：下一层必须从比 i 更大的数字开始选，保持严格升序，避免重复
            backtrack(k, target - i, i + 1, path, res);

            // 【撤销选择】：刚才加进去的 i 已经试探完了所有可能，把它从路径末尾删掉，腾出位置给 for 循环的下一个数字
            path.removeLast();
        }
    }

}