package pattern.doublepointer.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/*
 * 
 * 
 * 76. 最小覆盖子串

给你一个字符串 S、一个字符串 T 。请你设计一种算法，可以在 O(n) 的时间复杂度内，从字符串 S 里面找出：包含 T 所有字符的最小子串。



示例：

输入：S = "ADOBECODEBANC", T = "ABC"
输出："BANC"


提示：

如果 S 中不存这样的子串，则返回空字符串 ""。
如果 S 中存在这样的子串，我们保证它是唯一的答案。

 * 
 * 
 * 
 */

public class LeetCode76 {
	public static void main(String[] args) {
		System.out.println(minWindow("afsdfgdadf","affga"));
	}
	
	// 复杂度为O(N),因为循环体内虽然有两重while，但是走过的最大路程也就2*t.size()这么长
    public static String minWindow(String s, String t) {
    	//滑窗左右指针
    	int left = 0;
    	int right = 0;
    	//最终输出的起始位置
    	int start = 0;
    	//最小匹配区间的长度，默认不匹配
    	int minLen = Integer.MAX_VALUE;
    	// t中各字符与其对应出现频率
    	Map<Character, Integer> needs = new HashMap<Character, Integer>();
    	// 窗口中各字符出现频率
    	Map<Character, Integer> window = new HashMap<Character, Integer>();
    	// 当前滑窗中和t中字符匹配的数量
    	// 记录的是匹配到的字符串数
    	// 即窗口中某一字符串出现的频率大于等于它在t中出现的频率时，matchDistance才会进行自增,且增1次，记作该字符已匹配
    	int matchDistance = 0;
    	//更新needs数组，记录各字符出现频率
    	for(char c : t.toCharArray()) {
    		//记录频率，如果没出现的话默认就出现了0次，每发现1次频率+1
    		needs.put(c, needs.getOrDefault(c, 0) + 1);
    	}
    	while(right<s.length()) {
    		char c1 = s.charAt(right);
    		// 进行右窗口右移动作时,当前字符若在t中
    		if (needs.containsKey(c1)) {
    			// 窗口扩张，将当前字符纳入窗口中
				window.put(c1, window.getOrDefault(c1, 0) + 1);
				// 如果有匹配的，则matchDistance递增
				// 因为当窗口中某一字符出现次数大于等于它在t中出现的频率时，才记这个字符被匹配
				// 所以该字符匹配条件：window.get(c1) - needs.get(c1) == 0时，matchDistance自增
				// 注意切忌用 == ！！！！！
//				if (window.get(c1) == needs.get(c1)) {
				if (window.get(c1) - needs.get(c1) == 0) {
					matchDistance++;
				}
			}
    		// 每一轮右指针都会往右移一位
    		right++;
    		
    		//上述步骤移动right找到可行解
    		//下面步骤移动left找到局部最优解
    		
    		// 当窗口在右移过程中将t中的字符都给包含进去时，对可行解进行优化，即右移left指针以缩小范围
    		while (matchDistance == needs.size()) {
    			// 若得到了更小的匹配子串，则更新输出结果
				if(right - left < minLen) {
					start = left;
					minLen = right - left;
				}
				char c2 = s.charAt(left);
				// 进行左窗口右移时，若当前字符在t中
				if(needs.containsKey(c2)) {
	    			// 窗口收缩，将当前字符移出窗口
					window.put(c2, window.get(c2)-1);
					// 如果经过移出操作后，窗口中的字符不能满足包含t中的所有字符了
					// 则matchDistance自减，之后窗口会继续右移来寻找匹配
					if(window.get(c2)<needs.get(c2)) {
						matchDistance--;
					}
				}
				// 每一轮操作左指针右移一位
				left++;
			}
    	}
    	// 若最小匹配区间没有被更新过，则返回空串，否则返回符合的最小区间
    	return minLen == Integer.MAX_VALUE ? "" : s.substring(start,start+minLen);
    } 
}
