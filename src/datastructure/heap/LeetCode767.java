package datastructure.heap;

/*
 *
 *
 *
767. Reorganize String
Medium

Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.

Return any possible rearrangement of s or return "" if not possible.



Example 1:

Input: s = "aab"
Output: "aba"
Example 2:

Input: s = "aaab"
Output: ""


Constraints:

1 <= s.length <= 500
s consists of lowercase English letters.
 *
 *
 */

public class LeetCode767 {

    int reorderCnt = 0;

    public static void main(String[] args) {


        LeetCode767 example = new LeetCode767();

        System.out.println(example.reorganizeString("zifrfbctby"));
    }



    public String reorganizeString(String s) {

        int[] charsWithTimes = new int[26];
        int maxTimes = 0;
        char maxTimesChar = ' ';
        for (char c : s.toCharArray()){
            charsWithTimes[c - 'a']++;
            int times = charsWithTimes[c - 'a'];
            maxTimes = times>=maxTimes?times:maxTimes;
            maxTimesChar = times>=maxTimes?c:maxTimesChar;
        }

        int leftTimes = s.length()-maxTimes;
        // 最多出现次数的字母小于等于其它字母+1才有可能
        if(maxTimes > leftTimes + 1) return "";

        // 输出一个可能存在的，按照出现次数打印
        char[] res = new char[s.length()];

        int idx = 0;
        // 第一步：先把出现次数最多的那个字母，隔一个放一个，全填在偶数位 (0, 2, 4...)
        // 把最多次数的穿插好就保证了不会重复了
        while (charsWithTimes[maxTimesChar - 'a'] > 0) {
            res[idx] = maxTimesChar;
            idx += 2;
            charsWithTimes[maxTimesChar - 'a']--;
        }

        // 第二步：剩下的字母不需要排序！继续按照顺序间隔填入
        for (int i = 0; i < 26; i++) {
            while (charsWithTimes[i] > 0) {
                // 如果偶数位（0, 2, 4...）越界了，立刻切到奇数位（1, 3, 5...）
                if (idx >= res.length) {
                    idx = 1;
                }
                res[idx] = (char) (i + 'a');
                idx += 2;
                charsWithTimes[i]--;
            }
        }

        return new String(res);

    }


    // TODO heap解法

}