package AceCodingInterview75Qs.queue;

/*
 *
 *
 *

649. Dota2 Senate

Medium

In the world of Dota2, there are two parties: the Radiant and the Dire.

The Dota2 senate consists of senators coming from two parties. Now the Senate wants to decide on a change in the Dota2 game. The voting for this change is a round-based procedure. In each round, each senator can exercise one of the two rights:

Ban one senator's right: A senator can make another senator lose all his rights in this and all the following rounds.
Announce the victory: If this senator found the senators who still have rights to vote are all from the same party, he can announce the victory and decide on the change in the game.
Given a string senate representing each senator's party belonging. The character 'R' and 'D' represent the Radiant party and the Dire party. Then if there are n senators, the size of the given string will be n.

The round-based procedure starts from the first senator to the last senator in the given order. This procedure will last until the end of voting. All the senators who have lost their rights will be skipped during the procedure.

Suppose every senator is smart enough and will play the best strategy for his own party. Predict which party will finally announce the victory and change the Dota2 game. The output should be "Radiant" or "Dire".



Example 1:

Input: senate = "RD"
Output: "Radiant"
Explanation:
The first senator comes from Radiant and he can just ban the next senator's right in round 1.
And the second senator can't exercise any rights anymore since his right has been banned.
And in round 2, the first senator can just announce the victory since he is the only guy in the senate who can vote.
Example 2:

Input: senate = "RDD"
Output: "Dire"
Explanation:
The first senator comes from Radiant and he can just ban the next senator's right in round 1.
And the second senator can't exercise any rights anymore since his right has been banned.
And the third senator comes from Dire and he can ban the first senator's right in round 1.
And in round 2, the third senator can just announce the victory since he is the only guy in the senate who can vote.


 *
 *
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class LeetCode649 {


    public static void main(String[] args) {
        LeetCode649 leetCode649 = new LeetCode649();
        System.out.println(leetCode649.predictPartyVictory("RRDDD"));
    }


    // 顺序靠前能够ban掉顺序靠后的，而自己进入下一轮
    // 所以可以用两个队列分别记录D和R的下标
    // 从头往后读，较小的一方ban掉另一方后，新下标为原来下标+剩余人数，排在下一轮
    public String predictPartyVictory(String senate) {
        Deque<Integer> dq = new ArrayDeque<>();
        Deque<Integer> rq = new ArrayDeque<>();
        char[] arr = senate.toCharArray();
        for (int i = 0; i < arr.length; i++ ) {
            if (arr[i] == 'D') dq.addLast(i);
            else rq.addLast(i);
        }

        while (!dq.isEmpty() && !rq.isEmpty()){
            if (dq.peekFirst() < rq.peekFirst()){
                int newIdx = dq.peekFirst() + dq.size() + rq.size();
                dq.addLast(newIdx);
                dq.removeFirst();
                rq.removeFirst();
            } else {
                int newIdx = rq.peekFirst() +  dq.size() + rq.size();
                rq.addLast(newIdx);
                rq.removeFirst();
                dq.removeFirst();
            }
        }
        return dq.isEmpty()? "Radiant" : "Dire";
    }



    // 优化版
    public String predictPartyVictory1(String senate) {
        Deque<Integer> dq = new ArrayDeque<>();
        Deque<Integer> rq = new ArrayDeque<>();
        char[] arr = senate.toCharArray();
        int len = arr.length;
        // 初始化下标入队列
        for (int i = 0; i < len; i++ ) {
            if (arr[i] == 'D') dq.addLast(i);
            else rq.addLast(i);
        }
        // 某一方被干掉，则跳出循环
        while (!dq.isEmpty() && !rq.isEmpty()){
            // 取靠前的值进行比较，考前的一方干掉另一方，自己进入下一轮
            int dIdx = dq.removeFirst();
            int rIdx = rq.removeFirst();
            if (dIdx < rIdx){
                // 往后排到下一轮不必要精确的数字+ dq.size() + rq.size()，只需要加数组长度标识大小关系即可
                int newIdx = dIdx + len;
                dq.addLast(newIdx);
            } else {
                int newIdx = rIdx +  len;
                rq.addLast(newIdx);
            }
        }
        return dq.isEmpty()? "Radiant" : "Dire";
    }

}