package algorithm.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * 
 * 46 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。

示例:

输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

 * 
 * 
 * 
 */

public class LeetCode46 {
	
	
	public static void main(String[] args) {
		
		
//		int [] a = {1,2,3};
//		permute(a);
//		System.out.println(res);
//		
//		
		
		
		int [] a = {1,2,3};
		System.out.println(permute2(a));
			
		
		
		
		
	}
	
	
	public static List<List<Integer>> permute2(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        int[] visited = new int[nums.length];
        backtrack2(res, nums, new ArrayList<Integer>(), visited);
        return res;

    }

	//res:结果
	//nums:输入要排列的数据
	//tmp:路径
	//visited:当前状态
    private static void backtrack2(List<List<Integer>> res, int[] nums, ArrayList<Integer> tmp, int[] visited) {
        if (tmp.size() == nums.length) {
        	//此处添加的是对路径tmp的一份拷贝
        	//因为tmp会随时被修改
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) continue;
            //进行递归操作前，将节点置为已访问状态，可看作顺着一条路向下走
            visited[i] = 1;
            tmp.add(nums[i]);
            backtrack2(res, nums, tmp, visited);
            //递归操作后，进行回溯操作，从下往上回，状态重置，撤销选择
            visited[i] = 0;
            tmp.remove(tmp.size() - 1);
        }
    }
	
	
	
	
	
	
	
	//以下为labuladong的解法，访问条件 visited使用list.contains处理
	
	static List<List<Integer>> res = new LinkedList<>();

	/* 主函数，输入一组不重复的数字，返回它们的全排列 */
	static List<List<Integer>> permute(int[] nums) {
	    // 记录「路径」
	    LinkedList<Integer> track = new LinkedList<>();
	    backtrack(nums, track);
	    return res;
	}

	// 路径：记录在 track 中
	// 选择列表：nums 中不存在于 track 的那些元素
	// 结束条件：nums 中的元素全都在 track 中出现
	static void backtrack(int[] nums, LinkedList<Integer> track) {
	    // 触发结束条件
	    if (track.size() == nums.length) {
	    	//!!!注意这里添加的是一份拷贝！
	        res.add(new LinkedList(track));
	        return;
	    }
	    
	    for (int i = 0; i < nums.length; i++) {
	        // 排除不合法的选择
	        if (track.contains(nums[i]))
	            continue;
	        // 做选择
	        track.add(nums[i]);
	        // 进入下一层决策树
	        backtrack(nums, track);
	        // 取消选择
	        track.removeLast();
	    }
	}


}
