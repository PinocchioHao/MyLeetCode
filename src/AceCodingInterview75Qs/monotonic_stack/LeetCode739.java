package AceCodingInterview75Qs.monotonic_stack;

/*
 *
 *
 *
739. Daily Temperatures
Medium

Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.

Example 1:

Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
Example 2:

Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]
Example 3:

Input: temperatures = [30,60,90]
Output: [1,1,0]


Constraints:

1 <= temperatures.length <= 105
30 <= temperatures[i] <= 100
 *
 *
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class LeetCode739 {

    public static void main(String[] args) {

        int[] arr1 = {73,74,75,71,69,72,76,73};

        LeetCode739 example = new LeetCode739();

        System.out.println(example.dailyTemperatures(arr1));
    }


    // 单调栈找到右边第一个比自己大的元素，求下标差
    // 同步维护两个栈：numStack 存温度值用来比较，idxStack 存原数组下标用来算距离
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<Integer> idxStack = new ArrayDeque<>();

        int len = temperatures.length;
        int[] res = new int[len]; // 默认全为 0

        for(int i = 0; i < len; i++) {
            int curr = temperatures[i];

            // 【核心清算逻辑】：当前温度 curr 如果比栈顶温度高，说明栈顶那几天终于等到了升温！
            while(!numStack.isEmpty() && numStack.peekLast() < curr) {
                // 弹出那个苦苦等待升温的历史下标
                int prevIdx = idxStack.removeLast();
                // 计算等待的天数：当前下标 i - 历史下标 prevIdx
                res[prevIdx] = i - prevIdx;
                // 下标结算完毕，对应的温度值也完成使命，一并弹出
                numStack.removeLast();
            }

            // 处理完所有能被今天“拯救”的历史温度后，把今天本身压入栈中，等待未来的升温
            numStack.addLast(curr);
            idxStack.addLast(i);
        }

        // 遍历结束后，仍然留在栈里的元素就是永远没等到升温的，res 数组里对应的默认值就是 0
        return res;
    }


    // 优化版，只用一个下标栈即可
    public int[] dailyTemperatures1(int[] temperatures) {
        Deque<Integer> idxStack = new ArrayDeque<>();

        int len = temperatures.length;
        int[] res = new int[len]; // 默认全为 0

        for(int i = 0; i < len; i++) {
            int curr = temperatures[i];

            // 【核心清算逻辑】：当前温度 curr 如果比栈顶温度高，说明栈顶那几天终于等到了升温！
            while(!idxStack.isEmpty() && temperatures[idxStack.peekLast()] < curr) {
                // 弹出那个苦苦等待升温的历史下标
                int prevIdx = idxStack.removeLast();
                // 计算等待的天数：当前下标 i - 历史下标 prevIdx
                res[prevIdx] = i - prevIdx;
            }

            // 处理完所有能被今天“拯救”的历史温度后，把今天本身压入栈中，等待未来的升温
            idxStack.addLast(i);
        }

        // 遍历结束后，仍然留在栈里的元素就是永远没等到升温的，res 数组里对应的默认值就是 0
        return res;
    }




}