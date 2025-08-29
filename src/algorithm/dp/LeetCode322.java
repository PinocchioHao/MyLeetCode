package algorithm.dp;

/*
 * 
 * 322. 零钱兑换
给定不同面额的硬�? coins 和一个�?�金�? amount。编写一个函数来计算可以凑成总金额所�?的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回-1�?



示例1:

输入: coins = [1, 2, 5], amount = 11
输出: 3 
解释: 11 = 5 + 5 + 1
示例 2:

输入: coins = [2], amount = 3
输出: -1


 * 
 * 
 * 
 */

public class LeetCode322 {
	
	
	public static void main(String[] args) {
		
		
		int[] coins = {1,2,5};
		
		System.out.println(coinChange(coins,5));
	}
	

//	区别爬楼梯问题！！！！！！！！！！！！！
//	这里的内外循环能换吗�??
//	显然不能，因为我们这里定义的子问题是，必须�?�择第k个硬币时，凑成金额i的方案�??-----------组合
//	如果交换了，我们的子问题就变了，那就是对于金额i, 我们选择硬币的方案�??             -----------排列

    public static int coinChange(int[] coins, int amount) {
    	
    	int[] dp = new int[amount+1];
    	
    	dp[0] = 1;
    	
		for (int j = 0; j < coins.length; j++) {
	    	for (int i = 1; i <= amount; i++) {
    			if(i-coins[j]>=0) {
        			dp[i] += dp[i-coins[j]];
    			}
	    	}
		}
		
    	return dp[amount];
    }

}
