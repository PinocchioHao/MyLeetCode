package AceCodingInterview75Qs.dp_2d;

/*
 *
 *
 *
714. Best Time to Buy and Sell Stock with Transaction Fee
Solved
Medium
Topics
premium lock icon
Companies
Hint
You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.

Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.

Note:

You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
The transaction fee is only charged once for each stock purchase and sale.


Example 1:

Input: prices = [1,3,2,8,4,9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
- Buying at prices[0] = 1
- Selling at prices[3] = 8
- Buying at prices[4] = 4
- Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
Example 2:

Input: prices = [1,3,7,5,10,3], fee = 3
Output: 6


Constraints:

1 <= prices.length <= 5 * 104
1 <= prices[i] < 5 * 104
0 <= fee < 5 * 104
 *
 *
 */

public class LeetCode714 {


    public static void main(String[] args) {

        int[] arr1 = {1};
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};

        LeetCode714 example = new LeetCode714();

        System.out.println(example.maxProfit(arr2,2));
    }


    // 经典股票买卖问题使用状态机dp解法
    // dp数组记录当前位置持仓和空仓的最大收益，初始空仓收益为0，初始持仓收益为-prices[0]，因为买入相当于-，卖出相当于+
    // 状态转移方程：have[i] = Math.max(have[i-1], donthave[i-1] - prices[i]);
    // donthave[i] = Math.max(donthave[i-1], have[i-1] + prices[i]);
    // 最终空仓收益肯定比持仓收益高，返回donthave[len-1]
    public int maxProfit(int[] prices, int fee) {
        // int len = prices.length;
        // int[] have = new int[len];
        // int[] donthave = new int[len];
        // donthave[0] = 0;
        // have[0] = -prices[0];
        // for (int i = 1; i < len; i++){
        //     have[i] = Math.max(have[i-1], donthave[i-1] - prices[i]);
        //     donthave[i] = Math.max(donthave[i-1], have[i-1] + prices[i] - fee);
        // }
        // return donthave[len-1];

        // 不如两个dp数组直观，并且也没降啥开销，还容易写错
        // int len = prices.length;
        // int[][] dp = new int[2][len];
        // // 持仓
        // dp[0][0] = -prices[0];
        // // 空仓
        // dp[1][0] = 0;
        // for (int i = 1; i < len; i++){
        //     dp[0][i] = Math.max(dp[0][i-1], dp[1][i-1] - prices[i]);
        //     dp[1][i] = Math.max(dp[1][i-1], dp[0][i-1] + prices[i] - fee);
        // }

        // return dp[1][len-1];


        // 压缩为一个变量
        // 初始状态 (第 0 天)
        int have = -prices[0]; // 持仓宇宙：花了买股票的钱，收益为负
        int donthave = 0;      // 空仓宇宙：没买没卖，收益为 0

        // 从第 1 天开始推导状态转移
        for (int i = 1; i < prices.length; i++) {
            // 今天的持仓状态来源 = max(昨天继续持仓死扛, 昨天空仓但今天抄底买入)
            int nextHave = Math.max(have, donthave - prices[i]);

            // 今天的空仓状态来源 = max(昨天继续空仓躺平, 昨天持仓但今天高位卖出扣除手续费)
            int nextDontHave = Math.max(donthave, have + prices[i] - fee);

            // 滚动更新进入下一天
            have = nextHave;
            donthave = nextDontHave;
        }
        // 最后一天，空仓的收益绝对大于等于持仓（因为烂在手里不如卖掉换钱）
        return donthave;
    }


}