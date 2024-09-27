package doublepointer;

/*
 * 
 * 792. 匹配子序列的单词数
给定字符串 S 和单词字典 words, 求words[i]中是S的子序列的单词个数。

示例:
输入: 
S = "abcde"
words = ["a", "bb", "acd", "ace"]
输出: 3
解释: 有三个是S 的子序列的单词: "a", "acd", "ace"。

 * 
 * 
 * 
 */

public class LeetCode792 {
	
	
	public static void main(String[] args) {
		
		

		String s = "abbcceeddfgsh";
		String[] words = {"abb","c","cce","dfgh","dfdfssv"};

		
		
		System.out.println(numMatchingSubseq(s, words));
		
		
		
	}
	
	

	// 会超时！！！
    public static int numMatchingSubseq(String S, String[] words) {

        int rlt = 0;
        for(String subWord : words){
            int subWordPointer = 0;
            int sPointer = 0;
            int matchNum = 0;
            while(subWordPointer<subWord.length() && sPointer<S.length()){
                if(subWord.charAt(subWordPointer) == S.charAt(sPointer)){
                    subWordPointer++;
                    matchNum++;
                }
                if(matchNum == subWord.length()){
                    rlt++;
                    break;
                }
                sPointer++;
            }

        }
        return rlt;
    }


    
}
