package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 
 * 47 全排列II
给定一个可包含重复数字的序列，返回所有不重复的全排列。

示例:

输入: [1,1,2]
输出:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]

 * 
 * 
 * 
 */

public class LeetCode47 {
	
	
	public static void main(String[] args) {
		
		
//		int [] a = {1,2,3};
//		permute(a);
//		System.out.println(res);
//		
//		
		
		int [] a = {1,2,3};
		System.out.println(permuteUnique(a));
	}
	
	public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> rlt = new ArrayList();
        List<Integer> path = new ArrayList();
        int[] isused = new int[nums.length];
        Arrays.sort(nums);
        backTrack(nums,rlt,path,isused);
        return rlt;
    }


	//对于包含重复数字的全排列，其实只要在原有全排列的基础上，做一个剪枝操作即可
	//rlt:结果
	//nums:输入要排列的数据
	//path:路径
	//isused:当前状态
    private static void backTrack(int[] nums, List<List<Integer>> rlt, List<Integer> path,int[] isused){
        if(path.size() == nums.length){
            //此处添加的是对路径path的一份拷贝
        	//因为path会随时被修改
            rlt.add(new ArrayList(path));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(isused[i] == 1){
                continue;
            }
            //剪枝
            // i > 0 是为了保证 nums[i - 1] 有意义
            // nums[i] == nums[i - 1] && visited[i-1] == 0 表示上一个节点在深度优先遍历的过程中刚刚被回溯撤销
            // 这种枝可以画个图方便理解
            if(i>0 && nums[i] == nums[i-1] && isused[i-1] == 0){
                continue;
            }
            else{
                //进行递归操作前，将节点置为已访问状态，可看作顺着一条路向下走
                isused[i] = 1;
                path.add(nums[i]);
                backTrack(nums,rlt,path,isused);
                //递归操作后，进行回溯操作，从下往上回，状态重置，撤销选择
                isused[i] = 0;
                path.remove(path.size()-1);
            }
        }
    }

}
