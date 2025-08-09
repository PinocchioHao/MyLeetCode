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
	 * ������ - �ᳬʱ
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
	 * ջ
	 * @param nums
	 * @return
	 */
	public static boolean find132pattern1(int[] nums) {
		if (nums == null || nums.length < 3) {return false;}
		// ջά������"2"
		Stack<Integer> stack = new Stack<>();
		int n = nums.length;
		// ��ǰ���󣬵�i��Ԫ��ǰ��С��ֵ����Ӧ����"1"
		int[] minNumBeforei = new int[n];
		minNumBeforei[0] = nums[0];
		for (int i = 1; i < n; i++) {
			minNumBeforei[i] = Math.min(minNumBeforei[i - 1], nums[i]);
		}
		// �Ӻ���ǰ������Ѱ��132ģʽ��minNumBeforei[i]��Ӧ����"1"��stack��ſ��ܵĹ���"2"����ǰԪ��nums[i]Ϊ����"3"
		for (int i = n - 1; i >= 0; i--) {
			// ά������2��ջ��Ԫ��ʼ�ձ������1��
			while (!stack.isEmpty() && stack.peek() <= minNumBeforei[i]) {
				stack.pop();
			}
			// ����132����
			if (!stack.isEmpty() && stack.peek() < nums[i]) {
				return true;
			}

			stack.push(nums[i]);
		}

		return false;
	}


	/**
	 * ����ջ - ջά������3,2֮���ϵ��iΪ����1
	 * @param nums
	 * @return
	 */
	public static boolean find132pattern2(int[] nums) {
		if (nums == null || nums.length < 3) {return false;}
		// ջά������"3"
		Stack<Integer> stack = new Stack<>();
		int n = nums.length;
		// ��ǰ���󣬵�i��Ԫ��ǰ��С��ֵ����Ӧ����"1"
		int second_max = Integer.MIN_VALUE;

		// �Ӻ���ǰ������Ѱ��132ģʽ����ǰԪ��nums[i]Ϊ����"1"��second_maxΪ����"2"��ֵ, stack����ջά������"3","1"��
		for (int i = n - 1; i >= 0; i--) {
			// ����132����
			if (nums[i] < second_max) {
				return true;
			}
			// ά��3
			while (!stack.isEmpty() && stack.peek() < nums[i]) {
				second_max = Math.max(second_max, stack.pop());
			}

			stack.push(nums[i]);
		}

		return false;
	}


}
