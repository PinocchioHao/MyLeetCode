package monotonic.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 503. Next Greater Element II
 * Medium
 * Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.
 *
 * The next greater number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.
 *
 * Example 1:
 *
 * Input: nums = [1,2,1]
 * Output: [2,-1,2]
 * Explanation: The first 1's next greater number is 2;
 * The number 2 can't find next greater number.
 * The second 1's next greater number needs to search circularly, which is also 2.
 * Example 2:
 *
 * Input: nums = [1,2,3,4,3]
 * Output: [2,3,4,-1,4]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 */
public class Leetcode503 {

    public static void main(String[] args) {
        int nums1[] = {1, 2, 1};
        int nums11[] = {1, 2, 3};
        int nums111[] = {3, 2, 1};
        int nums2[] = {1, 2, 3, 4, 3};
		int[] nums3 = nextGreaterElements2(nums1);
		int[] nums4 = nextGreaterElements2(nums111);
		int[] nums5 = nextGreaterElements2(nums11);
		int[] nums6 = nextGreaterElements2(nums2);
        System.out.println();

    }

	/**
	 * ���������ѭ�������е���һ������Ԫ������
	 * ����ÿ��Ԫ�أ����ȴӵ�ǰλ���������Ƿ���ڸ����Ԫ�أ�
	 * ���û���ҵ����ͷ��ʼ��������ǰλ�ã��ҵ���һ���ȵ�ǰԪ�ش��ֵ��
	 *
	 * A brute-force approach to solve the next greater element problem for a circular array.
	 * For each element, first check if there exists a greater element by searching forward.
	 * If not found, then start from the beginning and search until the current position for the first greater element.
	 *
	 * @param nums ������ѭ������
	 * @return ����һ�����飬����ÿ��Ԫ�ض�Ӧ���Ҳ��һ�������Ԫ�أ����������򷵻�-1
	 * @param nums Given circular array.
	 * @return Returns an array where each element corresponds to the first greater element on its right; returns -1 if none exists.
	 */
	public static int[] nextGreaterElements(int[] nums) {
		// ����������飬��ʼֵ����Ϊ-1����ʾ�Ҳ�������Ԫ��ʱ��Ĭ��ֵ
		// Create a result array, initializing all values to -1, which indicates the default value when no greater element is found.
		int[] res = new int[nums.length];
		Arrays.fill(res, -1);

		// ���������ÿһ��Ԫ��
		// Traverse each element in the array.
		for (int i = 0; i < nums.length; i++) {
			// ����Ƿ���Ҫ����ڶ��׶Σ��������鿪ͷ����ǰλ�ü���Ѱ��
			// A flag to indicate whether to proceed to the second phase, searching from the start of the array to the current position.
			boolean flag = false;

			// �ӵ�ǰλ��������[i+1, nums.length-1]�����еĸ���Ԫ��
			// Search for a greater element in the range [i+1, nums.length-1].
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[j] > nums[i]) {
					// �ҵ���һ������Ԫ�أ���¼����������в�����ѭ��
					// Found the next greater element, record it in the result array and break the loop.
					res[i] = nums[j];
					break;
				}
				// ����Ѿ�����������ĩβ����û���ҵ�����Ԫ��
				// If we've reached the end of the array without finding a greater element.
				if (j == nums.length - 1) {
					flag = true; // ���ñ�ǣ�˵����Ҫ��ͷ��������
					// Set the flag to indicate that we need to continue searching from the start.
				}
			}

			// ����ӵ�ǰλ�����û���ҵ�����Ԫ�أ����ߵ�ǰԪ�����������һ��Ԫ��
			// If no greater element was found going forward, or if the current element is the last element in the array.
			if (flag || i == nums.length - 1) {
				// ��ͷ��ʼ����[0, i-1]���䣬Ѱ����һ������Ԫ��
				// Search from the beginning of the array to find the next greater element in the range [0, i-1].
				for (int j = 0; j < i; j++) {
					if (nums[j] > nums[i]) {
						// �ҵ�����Ԫ�أ���¼������ѭ��
						// Found a greater element, record it and break the loop.
						res[i] = nums[j];
						break;
					}
				}
			}
		}
		return res;
	}


	/**
	 * �������Ż��� -- ͨ��ȡ�����ʵ������Ļ��η���(�����ύleetcode������ʱ������)
	 *
	 * An optimized brute-force version -- Using the modulus operation to achieve circular access in the array (but submission to LeetCode actually took longer).
	 *
	 * @param nums ������ѭ������
	 * @return ����һ�����飬����ÿ��Ԫ�ض�Ӧ���Ҳ��һ�������Ԫ�أ����������򷵻�-1
	 * @param nums Given circular array.
	 * @return Returns an array where each element corresponds to the first greater element on its right; returns -1 if none exists.
	 */
	public static int[] nextGreaterElements1(int[] nums) {
		// ���鳤�ȣ�������ѧ����
		// Length of the array, for convenience in calculations.
		int n = nums.length;
		// ����������飬��ʼֵ����Ϊ-1����ʾ�Ҳ�������Ԫ��ʱ��Ĭ��ֵ
		// Create a result array, initializing all values to -1, which indicates the default value when no greater element is found.
		int[] res = new int[n];
		Arrays.fill(res, -1);

		// ���������ÿһ��Ԫ��
		// Traverse each element in the array.
		for (int i = 0; i < nums.length; i++) {
			// ������ǰԪ��nums[i]�����������֣�ͨ��ȡ��ʵ��
			// Traverse both sides of the current element nums[i] by using the modulus operation to achieve circular behavior.
			for (int j = i + 1; j < n + i; j++) {
				// ���Ƚ�Ԫ�صı����α꣬������nʱ����i�Ҳಿ�֣�����nʱ����i��ಿ��
				// The index for comparison, where indices less than n traverse the right side of i, and those greater than n traverse the left side.
				int nextIdx = j % n;
				if (nums[nextIdx] > nums[i]) {
					res[i] = nums[nextIdx];
					break;
				}
			}
		}
		return res;
	}


	/**
	 * ����ջ
	 *
	 * Monotonic stack to solve the problem.
	 *
	 * @param nums ������ѭ������
	 * @return ����һ�����飬����ÿ��Ԫ�ض�Ӧ���Ҳ��һ�������Ԫ�أ����������򷵻�-1
	 * @param nums Given circular array.
	 * @return Returns an array where each element corresponds to the first greater element on its right; returns -1 if none exists.
	 */
	public static int[] nextGreaterElements2(int[] nums) {
		// ��ȡ����ĳ���
		// Get the length of the array.
		int n = nums.length;

		// �����������
		// Create the result array.
		int[] res = new int[n];
		// ��ʼ��������飬Ĭ��ֵΪ-1
		// Initialize the result array, with default values of -1.
		Arrays.fill(res, -1);
		// ����ջ���ڴ洢Ԫ�ص�����
		// Create a stack to store the indices of elements.
		Stack<Integer> stack = new Stack<>();

		// �������������Դ��������
		// Traverse the array twice to handle the circular case.
		for (int i = 0; i < 2 * n; i++) {
			// ���㵱ǰԪ�ص�����
			// Calculate the index of the current element.
			int idx = i % n;

			// ��ջ��Ϊ���ҵ�ǰԪ�ش���ջ��Ԫ�ض�Ӧ��ֵ
			// While the stack is not empty and the current element is greater than the top element in the stack.
			while (!stack.isEmpty() && nums[stack.peek()] < nums[idx]) {
				// ���½������
				// Update the result array.
				res[stack.pop()] = nums[idx];
			}

			// �ڵ�һ�α���ʱѹ������
			// Push the index onto the stack during the first traversal.
			if (i < n) {
				stack.push(idx);
			}
		}

		// ���ؽ������
		// Return the result array.
		return res;
	}





}
