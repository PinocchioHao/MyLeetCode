package pattern.doublepointer;

import java.util.Arrays;

/*
 * 
 * 162. 两数之和--已排序
给定一个整数数组 nums和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。



示例:

给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]

 * 
 * 
 * 
 */

public class LeetCode167 {
	
	
	public static void main(String[] args) {
		
		
		int [] a = {11,14,1,2,3};
		
		
				
		System.out.println(twoSum(a,14));
			
		
		
	}
	
    public static int[] twoSum(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        int idxl = 0;
        int idxr = 0;

        Arrays.sort(nums);

        while(left<right){
            int sum = nums[left]+nums[right];
            if(sum == target){
                idxl = left;
                idxr = right;
                break;
            }
            else if(sum<target){
                left++;
            }
            else {
                right--;
            }
        }
        int[] rlt = new int[]{idxl,idxr};
        return rlt;

    }


}
