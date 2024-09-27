package doublepointer.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 
 * 
 * 438. 找到字符串中所有字母异位词
给定一个字符串s和一个非空字符串p，找到s中所有是p的字母异位词的子串，返回这些子串的起始索引。

字符串只包含小写英文字母，并且字符串s和 p的长度都不超过 20100。

说明：

字母异位词指字母相同，但排列不同的字符串。
不考虑答案输出的顺序。
示例1:

输入:
s: "cbaebabacd" p: "abc"

输出:
[0, 6]

解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
示例 2:

输入:
s: "abab" p: "ab"

输出:
[0, 1, 2]

解释:
起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。


 * 
 * 
 * 
 */

public class LeetCode438 {
	public static void main(String[] args) {
		System.out.println(findAnagrams("abcfsdfvxacbgfgfv","abc"));
	}
	 public static List<Integer> findAnagrams(String s, String p) {

	        //窗口左右端
	        int left = 0;
	        int right = 0;
	        //窗口中匹配的长度
	        int matchDistance = 0;
	        //输出
	        List<Integer> rlt = new ArrayList<>(); 

	        Map<Character, Integer> needs = new HashMap<>();
	        Map<Character, Integer> window = new HashMap<>();
	        
	        char[] pchars = p.toCharArray();

	        //记录p中各个字符出现频率
	        for(char c : pchars ){
	            needs.put(c,needs.getOrDefault(c,0)+1);
	        }
	        //滑窗开始
	        while(right < s.length()){
	            //右指针右移，先找到包含p所有字符的子串
	            char c1 = s.charAt(right);
	            //如果字符匹配
	            if(needs.containsKey(c1)){
	                //记录到滑窗中
	                window.put(c1, window.getOrDefault(c1,0)+1);
	                //如果滑窗中的字符和频率匹配，则该字符完全匹配
	                if(window.get(c1) - needs.get(c1) == 0){
	                    //窗口中匹配长度+1
	                    matchDistance++;
	                }
	            }
	            //每轮都会右移右指针
	            right++;

	            //当找到子串时，移动左指针来找到异位词
	            while(matchDistance == needs.size()){
	                //找到异位词，rlt添加下标
	                if(s.substring(left,right).length() == p.length()){
	                    rlt.add(left);
	                }
	                char c2 = s.charAt(left);
	                //如果字符匹配
	                if(needs.containsKey(c2)){
	                    //滑窗中频率-1
	                    window.put(c2, window.get(c2)-1);
	                    //如果滑窗中的频率小于p的字符频率，即滑窗中不包含p子串了
	                    if(window.get(c2) < needs.get(c2)){
	                        //滑窗匹配长度-1
	                        //即开始下一轮右移右指针
	                        matchDistance--;
	                    }
	                }
	                //每轮都会右移左指针
	                left++;
	            }
	        }
	        return rlt;
	    }
}
