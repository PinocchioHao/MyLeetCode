package monotonic.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 456. 132 Pattern
 Medium

 Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].

 Return true if there is a 132 pattern in nums, otherwise, return false.


 Example 1:

 Input: nums = [1,2,3,4]
 Output: false
 Explanation: There is no 132 pattern in the sequence.
 Example 2:

 Input: nums = [3,1,4,2]
 Output: true
 Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
 Example 3:

 Input: nums = [-1,3,2,0]
 Output: true
 Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 */
public class Leetcode456 {

    public static void main(String[] args) {
        int nums11[] = {1, 2, 3};
        int nums111[] = {3, 2, 1};
        int nums2[] = {1, 2,  4, 3};
		System.out.println(find132pattern2(nums11));
//		System.out.println(find132pattern2(nums111));
//		System.out.println(find132pattern2(nums2));

    }


	/**
	 * 暴力法 - 会超时
	 * @param nums
	 * @return
	 */
	public static boolean find132pattern(int[] nums) {
		if (nums == null || nums.length < 3) {return false;}
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				for (int k = j + 1; k < nums.length; k++) {
					if(nums[i] < nums[j] && nums[k] <nums[j] && nums[i] < nums[k]) {
						return true;
					}
				}
			}
		}
		return false;
	}


	/**
	 * 栈
	 * @param nums
	 * @return
	 */
	public static boolean find132pattern1(int[] nums) {
		if (nums == null || nums.length < 3) {return false;}
		// 栈维护规则"2"
		Stack<Integer> stack = new Stack<>();
		int n = nums.length;
		// 从前到后，第i个元素前最小的值，对应规则"1"
		int[] minNumBeforei = new int[n];
		minNumBeforei[0] = nums[0];
		for (int i = 1; i < n; i++) {
			minNumBeforei[i] = Math.min(minNumBeforei[i - 1], nums[i]);
		}
		// 从后往前遍历，寻找132模式，minNumBeforei[i]对应规则"1"，stack存放可能的规则"2"，当前元素nums[i]为规则"3"
		for (int i = n - 1; i >= 0; i--) {
			// 维护规则2：栈中元素始终必须大于1，
			while (!stack.isEmpty() && stack.peek() <= minNumBeforei[i]) {
				stack.pop();
			}
			// 满足132规则
			if (!stack.isEmpty() && stack.peek() < nums[i]) {
				return true;
			}

			stack.push(nums[i]);
		}

		return false;
	}


	/**
	 * 单调栈 - 栈维护规则3,2之间关系，i为规则1
	 * @param nums
	 * @return
	 */
	public static boolean find132pattern2(int[] nums) {
		if (nums == null || nums.length < 3) {return false;}
		// 栈维护规则"3"
		Stack<Integer> stack = new Stack<>();
		int n = nums.length;
		// 从前到后，第i个元素前最小的值，对应规则"1"
		int second_max = Integer.MIN_VALUE;

		// 从后往前遍历，寻找132模式，当前元素nums[i]为规则"1"，second_max为最大的"2"的值, stack单调栈维护规则"3","1"，
		for (int i = n - 1; i >= 0; i--) {
			// 满足132规则
			if (nums[i] < second_max) {
				return true;
			}
			// 维护3
			while (!stack.isEmpty() && stack.peek() < nums[i]) {
				second_max = Math.max(second_max, stack.pop());
			}

			stack.push(nums[i]);
		}

		return false;
	}


}
