package dynamicProgramming;

/*
 * 5、最长回文子串

给定一个字符串 s，找到 s 中最长的回文子串。你可以假设s 的最大长度为 1000。

示例 1：

输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。
示例 2：

输入: "cbbd"
输出: "bb"
 * 
 * 
 * 
 */

public class LeetCode5 {
	
	
	public static void main(String[] args) {
		
		String string = "af";		
		
		System.out.println(longestPalindrome(string));
		
		System.out.println(longestPalindromeDp(string));
	}
	
	//解法1，两端扩散法
    public static String longestPalindrome(String s) {
    	
    	if (s.length() == 0 || s.length() == 1) {
			return s;
		}
   
    	int rltLeft = 0;
    	int rltRight = 0;
    	int rltLength = 1;
    	//奇数情况
    	for (int i = 0; i < s.length(); i++) {
    		int l = i-1;
    		int r = i+1;
    		while (l>=0 && r<=s.length()-1 && s.charAt(l)==s.charAt(r)) {
    			if (r-l+1>=rltLength) {
    				rltLeft = l;
        			rltRight = r;
        			rltLength = r-l+1;
				}
    			l--;
    			r++;
			}	
    		
		}
    	//偶数情况
    	for (int i = 0; i < s.length(); i++) {
    		int l = i;
    		int r = i+1;
    		while (l>=0 && r<=s.length()-1 && s.charAt(l)==s.charAt(r)) {
    			if (r-l+1>=rltLength) {
    				rltLeft = l;
        			rltRight = r;
        			rltLength = r-l+1;
				}
    			l--;
    			r++;
			}	
    		
		}
    	
    	return s.substring(rltLeft,rltRight+1);
    }
    
    
  //解法2，动态规划
    public static String longestPalindromeDp(String s) {
    	
    	int len = s.length();
    	if (len == 0 || len == 1) {
			return s;
		}
    	int rltLeft = 0;
    	int rltRight = 0;
    	int rltLen = 1;
    	//状态转移变量保存的是s[i,j]是否是回文串
    	boolean[][]	dp = new boolean[len][len];
    	for (int i = 0; i < len; i++) {
    		dp[i][i] = true;
		}
    	//从j->i填充状态转移数组
    	for (int j = 0; j < len; j++) {
    		for (int i = 0; i < j; i++) {
				if (s.charAt(i) != s.charAt(j)) {
					dp[i][j] = false;
				}
				else {
					//边界条件，恰好走到中间不能走时，即填充两端相等中间有一个或者0个字符的情况
					if ((j-1)-(i+1)+1<2) {
						dp[i][j] = true;
					}
					
					else {
						//该状态是否是状态
						dp[i][j] = dp[i+1][j-1];
					}
				}
				
				
				if (j-i+1>rltLen && dp[i][j]) {
					rltLeft = i;
					rltRight = j;
					rltLen = j-i+1;
				}
				
			}
			
		}
    
    	

    	
    	return s.substring(rltLeft,rltRight+1);
    }
	
	

}
