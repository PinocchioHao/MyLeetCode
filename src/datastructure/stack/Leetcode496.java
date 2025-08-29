package datastructure.stack;

import java.util.*;

/**
 * 496. Next Greater Element I
 * Easy
 * The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
 *
 * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
 *
 * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.
 *
 * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
 *
 *
 * Example 1:
 *
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
 * Output: [-1,3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 * - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
 * - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 * Example 2:
 *
 * Input: nums1 = [2,4], nums2 = [1,2,3,4]
 * Output: [3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
 * - 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 104
 * All integers in nums1 and nums2 are unique.
 * All the integers of nums1 also appear in nums2.
 *
 *
 * Follow up: Could you find an O(nums1.length + nums2.length) solution?
 */
public class Leetcode496 {

	public static void main(String[] args) {
		int nums1[] = {4,1,2};
		int nums2[] = {1,3,2,4};
		int[] nums3 = nextGreaterElement1(nums1, nums2);
		System.out.println();

	}

	/**
	 * 暴力求解
	 * A brute-force solution.
	 * @param nums1 第一个数组
	 * @param nums2 第二个数组
	 * @return 返回一个数组，其中每个元素是nums1中元素在nums2中对应的下一个更大元素
	 * @param nums1 The first array.
	 * @param nums2 The second array.
	 * @return Returns an array where each element is the next greater element of nums1 in nums2.
	 */
	public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
		// res 都初始化为-1要更好点，避免了循环中if判断，以及代码更加简洁
		// Initializing res to -1 is better to avoid if checks in the loop and makes the code cleaner.
		int[] res = new int[nums1.length];
		// 遍历nums1中每个元素
		// Iterate through each element in nums1.
		for (int i = 0; i < nums1.length; i++) {
			int idx = -1;
			// nums2中找到nums1当前元素位置
			// Find the index of the current nums1 element in nums2.
			for (int j = 0; j < nums2.length; j++) {
				if (nums1[i] == nums2[j]) {
					idx = j;
				}
			}
			// 从当前元素位置向后查找下一个更大的元素
			// Search for the next greater element from the current index in nums2.
			for (int j = idx; j < nums2.length; j++) {
				if (nums1[i] < nums2[j]) {
					res[i] = nums2[j];
					break;
				}
				// 如果没有比它大的
				// If no greater element is found.
				if (j == nums2.length - 1) {
					res[i] = -1;
				}
			}
		}
		return res;
	}


	/**
	 * 使用单调栈解决下一个更大元素问题
	 * 遍历nums2时，将每个元素入栈，并在入栈前检查栈顶元素与当前元素的大小关系。
	 * 如果当前元素大于栈顶元素，说明栈顶元素的下一个更大元素已经找到了，
	 * 将栈顶元素弹出，并将它和当前元素的关系存入map中。
	 * 最后栈中剩下的元素是那些没有右边更大元素的。
	 *
	 * @param nums1 需要找下一个更大元素的数组
	 * @param nums2 作为参考数组，包含nums1中的所有元素，并按照顺序排列
	 * @return nums1中每个元素在nums2中右侧第一个比它大的值的数组
	 *
	 * Solving the next greater element problem using a monotonic stack.
	 * Traverse nums2, pushing each element onto the stack and comparing it with the top of the stack.
	 * If the current element is greater than the top of the stack, it means the stack's top element has found
	 * its next greater element. Pop the stack and store this relationship in the map.
	 * At the end, the remaining elements in the stack are those that don't have a greater element to their right.
	 *
	 * @param nums1 Array for which the next greater element is needed
	 * @param nums2 Reference array containing all elements of nums1 in the same order
	 * @return An array where each element of nums1 is mapped to the next greater element in nums2, or -1 if none exists
	 */
	public static int[] nextGreaterElement1(int[] nums1, int[] nums2) {
		// 存放返回结果的数组，长度与nums1相同
		// Result array of the same length as nums1
		int[] res = new int[nums1.length];

		// map记录nums2中每个元素和它右侧第一个比它大的元素之间的关系
		// key是nums2中的某个元素，value是它右边第一个比它大的元素
		// The map stores the relationship between each element of nums2 and its next greater element.
		// The key is the element from nums2, and the value is the next greater element found.
		Map<Integer, Integer> greaterMap = new HashMap<>();

		// 单调栈，存放nums2中暂时没有找到右边更大元素的元素
		// Monotonic stack to store elements from nums2 that haven't found their next greater element yet.
		Stack<Integer> stack = new Stack<>();

		// 遍历nums2数组，寻找每个元素的下一个更大元素
		// Traverse nums2 to find the next greater element for each item
		for (int i = 0; i < nums2.length; i++) {
			// 如果栈顶元素小于当前元素，说明栈顶元素的下一个更大元素就是当前元素
			// 弹出栈顶元素，并在map中记录它与当前元素的对应关系
			// If the top of the stack is smaller than the current element, the current element
			// is the next greater element for the top of the stack. Pop the stack and record
			// the relationship in the map.
			while (!stack.isEmpty() && nums2[i] > stack.peek()) {
				greaterMap.put(stack.pop(), nums2[i]);
			}
			// 当前元素入栈，等待找到它的下一个更大元素
			// Push the current element onto the stack, waiting to find its next greater element
			stack.push(nums2[i]);
		}

		// 遍历nums1，根据map获取对应的下一个更大元素，如果没有则返回-1
		// Traverse nums1 and retrieve the corresponding next greater element from the map, or return -1 if none exists
		for (int i = 0; i < nums1.length; i++) {
			// 使用getOrDefault方法，如果map中没有对应的下一个更大元素，返回-1
			// Using getOrDefault to return -1 if no next greater element is found in the map
			res[i] = greaterMap.getOrDefault(nums1[i], -1);
		}

		return res;
	}

}
