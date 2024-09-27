package dynamicProgramming;

/*
 * 
假设你正在爬楼梯。需�? n阶你才能到达楼顶�?

每次你可以爬 1 �? 2 个台阶�?�你有多少种不同的方法可以爬到楼顶呢�?

注意：给�? n 是一个正整数�?

示例 1�?

输入�? 2
输出�? 2
解释�? 有两种方法可以爬到楼顶�??
1.  1 �? + 1 �?
2.  2 �?
示例 2�?

输入�? 3
输出�? 3
解释�? 有三种方法可以爬到楼顶�??
1.  1 �? + 1 �? + 1 �?
2.  1 �? + 2 �?
3.  2 �? + 1 �?

 * 
 * 
 * 
 */

public class LeetCode70 {
	
	
	public static void main(String[] args) {
		
		System.out.println(climbStairs(2));
	}
	
	
    public static int climbStairs(int n) {
    	int[] dp = new int[n+1];
    	dp[0] = 1;
    	dp[1] = 1;
    		
		for(int i = 2; i<=n; i++) {
    		dp[i] = dp[i-1]+dp[i-2];
		}

    	return dp[n];
		
    }
	

}
