package algorithm.dp;

/*
 *
 *
 *
122. Best Time to Buy and Sell Stock II
Solved
Medium
Topics
premium lock icon
Companies
You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can sell and buy the stock multiple times on the same day, ensuring you never hold more than one share of the stock.

Find and return the maximum profit you can achieve.



Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Total profit is 4.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.


Constraints:

1 <= prices.length <= 3 * 104
0 <= prices[i] <= 104
 *
 *
 */

public class LeetCode122 {


    public static void main(String[] args) {

        int[] arr1 = {1};
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};

        LeetCode122 example = new LeetCode122();

        System.out.println(example.maxProfit(arr2));
    }


    public int maxProfit(int[] prices) {
        // dp记录当前位置持仓和空仓的最大收益，这种方法是股票题套路，如果讨论其它状态的话，容易状态繁多且讨论不全
//        int[] have = new int[prices.length];
//        int[] donthave = new int[prices.length];
//        // 初始空仓收益为0
//        donthave[0] = 0;
//        // 初始持仓收益为-prices[0]，因为买入相当于-，卖出相当于+
//        have[0] = -prices[0];
//
//        for(int i = 1; i<prices.length; i++){
//            have[i] = Math.max(have[i-1], donthave[i-1] - prices[i]);
//            donthave[i] = Math.max(donthave[i-1], have[i-1] + prices[i]);
//        }
//
//        return donthave[prices.length -1];

        // dp压缩到两个变量
        // 初始状态 (第 0 天)
        int have = -prices[0]; // 持仓宇宙：花了买股票的钱，收益为负
        int donthave = 0; // 空仓宇宙：没买没卖，收益为 0

        // 从第 1 天开始推导状态转移
        for (int i = 1; i < prices.length; i++) {
            // 今天的持仓状态来源 = max(昨天继续持仓死扛, 昨天空仓但今天抄底买入)
            int nextHave = Math.max(have, donthave - prices[i]);

            // 今天的空仓状态来源 = max(昨天继续空仓躺平, 昨天持仓但今天高位卖出)
            int nextDontHave = Math.max(donthave, have + prices[i]);

            // 滚动更新进入下一天
            have = nextHave;
            donthave = nextDontHave;
        }
        // 最后一天，空仓的收益绝对大于等于持仓（因为烂在手里不如卖掉换钱）
        return donthave;
    }

}