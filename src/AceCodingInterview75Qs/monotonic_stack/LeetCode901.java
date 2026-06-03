package AceCodingInterview75Qs.monotonic_stack;

/*
 *
 *
 *
901. Online Stock Span
Medium

Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's price for the current day.

The span of the stock's price in one day is the maximum number of consecutive days (starting from that day and going backward) for which the stock price was less than or equal to the price of that day.

For example, if the prices of the stock in the last four days is [7,2,1,2] and the price of the stock today is 2, then the span of today is 4 because starting from today, the price of the stock was less than or equal 2 for 4 consecutive days.
Also, if the prices of the stock in the last four days is [7,34,1,2] and the price of the stock today is 8, then the span of today is 3 because starting from today, the price of the stock was less than or equal 8 for 3 consecutive days.
Implement the StockSpanner class:

StockSpanner() Initializes the object of the class.
int next(int price) Returns the span of the stock's price given that today's price is price.


Example 1:

Input
["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
[[], [100], [80], [60], [70], [60], [75], [85]]
Output
[null, 1, 1, 1, 2, 1, 4, 6]

Explanation
StockSpanner stockSpanner = new StockSpanner();
stockSpanner.next(100); // return 1
stockSpanner.next(80);  // return 1
stockSpanner.next(60);  // return 1
stockSpanner.next(70);  // return 2
stockSpanner.next(60);  // return 1
stockSpanner.next(75);  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
stockSpanner.next(85);  // return 6


Constraints:

1 <= price <= 105
At most 104 calls will be made to next.
 *
 *
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class LeetCode901 {

    public static void main(String[] args) {

        int[] arr1 = {73, 74, 75, 71, 69, 72, 76, 73};

        LeetCode901 example = new LeetCode901();

//        System.out.println(example.dailyTemperatures(arr1));
    }


    // 用两个单调栈同步操作：
    // 1. priceStack 记录历史价格，维护一个单调递减的序列
    // 2. spanStack 记录对应价格所涵盖的“连续小于等于它的天数”（即历史距离）
    // 优化后可以采用 Deque<int[]> stack 存二元组来表示
    class StockSpanner {

        Deque<Integer> priceStack;
        Deque<Integer> spanStack;

        public StockSpanner() {
            priceStack = new ArrayDeque<>();
            spanStack = new ArrayDeque<>();
        }

        public int next(int price) {
            // 每一天刚开始时，跨度至少包含今天自己，所以初始值为 1
            int span = 1;

            // 【核心合并逻辑】：当前价格如果大于等于栈顶价格，说明过去的某些天被当前天“覆盖”了
            while (!priceStack.isEmpty() && priceStack.peekLast() <= price) {
                // 弹出那个比今天便宜（或相等）的历史价格
                priceStack.removeLast();
                // 把那个历史价格所累积的跨度，全部“吸收”到今天的跨度里来
                span += spanStack.removeLast();
            }

            // 吸收完所有能吸收的历史数据后，把今天的最强形态（当前价格 + 融合后的总跨度）压入栈中
            // 它们将作为新的整体，等待未来的某一天来挑战
            spanStack.addLast(span);
            priceStack.addLast(price);

            // 返回今天刚刚压入栈的最终跨度
            return spanStack.peekLast();
        }
    }
}