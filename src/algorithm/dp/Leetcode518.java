package algorithm.dp;


/**
 * 
 * 
 * 
 * 
 * 
给定不同面额的硬币和�?个�?�金额�?�写出函数来计算可以凑成总金额的硬币组合数�?�假设每�?种面额的硬币有无限个。�?



示例 1:

输入: amount = 5, coins = [1, 2, 5]
输出: 4
解释: 有四种方式可以凑成�?�金�?:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
示例 2:

输入: amount = 3, coins = [2]
输出: 0
解释: 只用面额2的硬币不能凑成�?�金�?3�?
示例 3:

输入: amount = 10, coins = [10] 
输出: 1
 *
 */




public class Leetcode518 {

	public static void main(String[] args) {

		
		int[] coins = {2,5};
		
		System.out.println(coinChange(coins,3));

	}



//	区别爬楼梯问题！！！！！！！！！！！！！
//	这里的内外循环能换吗�?
//	显然不能，因为我们这里定义的子问题是，必须�?�择第k个硬币时，凑成金额i的方案�??-----------组合
//	如果交换了，我们的子问题就变了，那就是对于金额i, 我们选择硬币的方案�??             -----------排列

//从内到外，内层循环计算给定当前种硬币币�?�范围内，计�?0~给定面�?�最小的组合�?
//外层循环则硬币币值每多一种，更新0~给定面�?�区间的各个面�?�的�?小组合数
	

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
