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
	 * 暴力法求解循环数组中的下一个更大元素问题
	 * 对于每个元素，首先从当前位置向后查找是否存在更大的元素，
	 * 如果没有找到则从头开始遍历至当前位置，找到第一个比当前元素大的值。
	 *
	 * A brute-force approach to solve the next greater element problem for a circular array.
	 * For each element, first check if there exists a greater element by searching forward.
	 * If not found, then start from the beginning and search until the current position for the first greater element.
	 *
	 * @param nums 给定的循环数组
	 * @return 返回一个数组，其中每个元素对应其右侧第一个更大的元素；若不存在则返回-1
	 * @param nums Given circular array.
	 * @return Returns an array where each element corresponds to the first greater element on its right; returns -1 if none exists.
	 */
	public static int[] nextGreaterElements(int[] nums) {
		// 创建结果数组，初始值都设为-1，表示找不到更大元素时的默认值
		// Create a result array, initializing all values to -1, which indicates the default value when no greater element is found.
		int[] res = new int[nums.length];
		Arrays.fill(res, -1);

		// 遍历数组的每一个元素
		// Traverse each element in the array.
		for (int i = 0; i < nums.length; i++) {
			// 标记是否需要进入第二阶段，即从数组开头到当前位置继续寻找
			// A flag to indicate whether to proceed to the second phase, searching from the start of the array to the current position.
			boolean flag = false;

			// 从当前位置向后查找[i+1, nums.length-1]区间中的更大元素
			// Search for a greater element in the range [i+1, nums.length-1].
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[j] > nums[i]) {
					// 找到下一个更大元素，记录到结果数组中并跳出循环
					// Found the next greater element, record it in the result array and break the loop.
					res[i] = nums[j];
					break;
				}
				// 如果已经遍历到数组末尾，但没有找到更大元素
				// If we've reached the end of the array without finding a greater element.
				if (j == nums.length - 1) {
					flag = true; // 设置标记，说明需要从头继续查找
					// Set the flag to indicate that we need to continue searching from the start.
				}
			}

			// 如果从当前位置向后没有找到更大元素，或者当前元素是数组最后一个元素
			// If no greater element was found going forward, or if the current element is the last element in the array.
			if (flag || i == nums.length - 1) {
				// 从头开始查找[0, i-1]区间，寻找下一个更大元素
				// Search from the beginning of the array to find the next greater element in the range [0, i-1].
				for (int j = 0; j < i; j++) {
					if (nums[j] > nums[i]) {
						// 找到更大元素，记录并跳出循环
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
	 * 暴力法优化版 -- 通过取余操作实现数组的环形访问(但是提交leetcode反而耗时更多了)
	 *
	 * An optimized brute-force version -- Using the modulus operation to achieve circular access in the array (but submission to LeetCode actually took longer).
	 *
	 * @param nums 给定的循环数组
	 * @return 返回一个数组，其中每个元素对应其右侧第一个更大的元素；若不存在则返回-1
	 * @param nums Given circular array.
	 * @return Returns an array where each element corresponds to the first greater element on its right; returns -1 if none exists.
	 */
	public static int[] nextGreaterElements1(int[] nums) {
		// 数组长度，便于数学运算
		// Length of the array, for convenience in calculations.
		int n = nums.length;
		// 创建结果数组，初始值都设为-1，表示找不到更大元素时的默认值
		// Create a result array, initializing all values to -1, which indicates the default value when no greater element is found.
		int[] res = new int[n];
		Arrays.fill(res, -1);

		// 遍历数组的每一个元素
		// Traverse each element in the array.
		for (int i = 0; i < nums.length; i++) {
			// 遍历当前元素nums[i]的左右两部分，通过取余实现
			// Traverse both sides of the current element nums[i] by using the modulus operation to achieve circular behavior.
			for (int j = i + 1; j < n + i; j++) {
				// 待比较元素的遍历游标，不超过n时遍历i右侧部分，超过n时遍历i左侧部分
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
	 * 单调栈
	 *
	 * Monotonic stack to solve the problem.
	 *
	 * @param nums 给定的循环数组
	 * @return 返回一个数组，其中每个元素对应其右侧第一个更大的元素；若不存在则返回-1
	 * @param nums Given circular array.
	 * @return Returns an array where each element corresponds to the first greater element on its right; returns -1 if none exists.
	 */
	public static int[] nextGreaterElements2(int[] nums) {
		// 获取数组的长度
		// Get the length of the array.
		int n = nums.length;

		// 创建结果数组
		// Create the result array.
		int[] res = new int[n];
		// 初始化结果数组，默认值为-1
		// Initialize the result array, with default values of -1.
		Arrays.fill(res, -1);
		// 创建栈用于存储元素的索引
		// Create a stack to store the indices of elements.
		Stack<Integer> stack = new Stack<>();

		// 遍历数组两次以处理环形情况
		// Traverse the array twice to handle the circular case.
		for (int i = 0; i < 2 * n; i++) {
			// 计算当前元素的索引
			// Calculate the index of the current element.
			int idx = i % n;

			// 当栈不为空且当前元素大于栈顶元素对应的值
			// While the stack is not empty and the current element is greater than the top element in the stack.
			while (!stack.isEmpty() && nums[stack.peek()] < nums[idx]) {
				// 更新结果数组
				// Update the result array.
				res[stack.pop()] = nums[idx];
			}

			// 在第一次遍历时压入索引
			// Push the index onto the stack during the first traversal.
			if (i < n) {
				stack.push(idx);
			}
		}

		// 返回结果数组
		// Return the result array.
		return res;
	}





}
