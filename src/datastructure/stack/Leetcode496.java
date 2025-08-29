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
	 * �������
	 * A brute-force solution.
	 * @param nums1 ��һ������
	 * @param nums2 �ڶ�������
	 * @return ����һ�����飬����ÿ��Ԫ����nums1��Ԫ����nums2�ж�Ӧ����һ������Ԫ��
	 * @param nums1 The first array.
	 * @param nums2 The second array.
	 * @return Returns an array where each element is the next greater element of nums1 in nums2.
	 */
	public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
		// res ����ʼ��Ϊ-1Ҫ���õ㣬������ѭ����if�жϣ��Լ�������Ӽ��
		// Initializing res to -1 is better to avoid if checks in the loop and makes the code cleaner.
		int[] res = new int[nums1.length];
		// ����nums1��ÿ��Ԫ��
		// Iterate through each element in nums1.
		for (int i = 0; i < nums1.length; i++) {
			int idx = -1;
			// nums2���ҵ�nums1��ǰԪ��λ��
			// Find the index of the current nums1 element in nums2.
			for (int j = 0; j < nums2.length; j++) {
				if (nums1[i] == nums2[j]) {
					idx = j;
				}
			}
			// �ӵ�ǰԪ��λ����������һ�������Ԫ��
			// Search for the next greater element from the current index in nums2.
			for (int j = idx; j < nums2.length; j++) {
				if (nums1[i] < nums2[j]) {
					res[i] = nums2[j];
					break;
				}
				// ���û�б������
				// If no greater element is found.
				if (j == nums2.length - 1) {
					res[i] = -1;
				}
			}
		}
		return res;
	}


	/**
	 * ʹ�õ���ջ�����һ������Ԫ������
	 * ����nums2ʱ����ÿ��Ԫ����ջ��������ջǰ���ջ��Ԫ���뵱ǰԪ�صĴ�С��ϵ��
	 * �����ǰԪ�ش���ջ��Ԫ�أ�˵��ջ��Ԫ�ص���һ������Ԫ���Ѿ��ҵ��ˣ�
	 * ��ջ��Ԫ�ص������������͵�ǰԪ�صĹ�ϵ����map�С�
	 * ���ջ��ʣ�µ�Ԫ������Щû���ұ߸���Ԫ�صġ�
	 *
	 * @param nums1 ��Ҫ����һ������Ԫ�ص�����
	 * @param nums2 ��Ϊ�ο����飬����nums1�е�����Ԫ�أ�������˳������
	 * @return nums1��ÿ��Ԫ����nums2���Ҳ��һ���������ֵ������
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
		// ��ŷ��ؽ�������飬������nums1��ͬ
		// Result array of the same length as nums1
		int[] res = new int[nums1.length];

		// map��¼nums2��ÿ��Ԫ�غ����Ҳ��һ���������Ԫ��֮��Ĺ�ϵ
		// key��nums2�е�ĳ��Ԫ�أ�value�����ұߵ�һ���������Ԫ��
		// The map stores the relationship between each element of nums2 and its next greater element.
		// The key is the element from nums2, and the value is the next greater element found.
		Map<Integer, Integer> greaterMap = new HashMap<>();

		// ����ջ�����nums2����ʱû���ҵ��ұ߸���Ԫ�ص�Ԫ��
		// Monotonic stack to store elements from nums2 that haven't found their next greater element yet.
		Stack<Integer> stack = new Stack<>();

		// ����nums2���飬Ѱ��ÿ��Ԫ�ص���һ������Ԫ��
		// Traverse nums2 to find the next greater element for each item
		for (int i = 0; i < nums2.length; i++) {
			// ���ջ��Ԫ��С�ڵ�ǰԪ�أ�˵��ջ��Ԫ�ص���һ������Ԫ�ؾ��ǵ�ǰԪ��
			// ����ջ��Ԫ�أ�����map�м�¼���뵱ǰԪ�صĶ�Ӧ��ϵ
			// If the top of the stack is smaller than the current element, the current element
			// is the next greater element for the top of the stack. Pop the stack and record
			// the relationship in the map.
			while (!stack.isEmpty() && nums2[i] > stack.peek()) {
				greaterMap.put(stack.pop(), nums2[i]);
			}
			// ��ǰԪ����ջ���ȴ��ҵ�������һ������Ԫ��
			// Push the current element onto the stack, waiting to find its next greater element
			stack.push(nums2[i]);
		}

		// ����nums1������map��ȡ��Ӧ����һ������Ԫ�أ����û���򷵻�-1
		// Traverse nums1 and retrieve the corresponding next greater element from the map, or return -1 if none exists
		for (int i = 0; i < nums1.length; i++) {
			// ʹ��getOrDefault���������map��û�ж�Ӧ����һ������Ԫ�أ�����-1
			// Using getOrDefault to return -1 if no next greater element is found in the map
			res[i] = greaterMap.getOrDefault(nums1[i], -1);
		}

		return res;
	}

}
