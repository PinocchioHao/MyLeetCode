package pattern.iteration;

/*
 * 
 * 
 * 9、回文数
判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

示例 1:

输入: 121
输出: true
示例2:

输入: -121
输出: false
解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
示例 3:

输入: 10
输出: false
解释: 从右向左读, 为 01 。因此它不是一个回文数。
进阶:

你能不将整数转为字符串来解决这个问题吗？

 * 
 * 
 * 
 */

public class LeetCode9 {
	
	
	public static void main(String[] args) {
		
		System.out.println(isPalindrome(-121));
	}
	
    public static boolean isPalindrome(int x) {
    	String inputString = x+"";
    	
    	return dfs(inputString, 0, inputString.length()-1);
    }
	
    public static boolean dfs(String string,int start, int end) {
    	
    	while (start<=end) {
    		if (string.charAt(start) != string.charAt(end)) {
				return false;
			}
			start++;
    		end--;
		}
    	
    	return true;
	}


	public static boolean isPalindrome2(int x) {
		String xStr = String.valueOf(x);
		int left = 0;
		int right = xStr.length() - 1;
		while (left<right){
			if(xStr.charAt(left) != xStr.charAt(right)){
				return false;
			}
			left++;
			right--;
		}
		return true;
	}




}
