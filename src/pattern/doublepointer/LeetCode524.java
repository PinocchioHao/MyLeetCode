package pattern.doublepointer;

import java.util.ArrayList;
import java.util.List;

/*
 * 
 * 524. 通过删除字母匹配到字典里最长单词
给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。

示例 1:

输入:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

输出: 
"apple"
示例2:

输入:
s = "abpcplea", d = ["a","b","c"]

输出: 
"a"

 * 
 * 
 * 
 */

public class LeetCode524 {
	

	public static void main(String[] args) {
			
		String s = "abbcceeddfgsh";
		List<String> d = new ArrayList<String>();
		d.add("agfdd");
		d.add("abc");
		d.add("aeefgs");
		d.add("ace");
		
		
		String s1 = "abbcceeddfgsh";
		List<String> d1 = new ArrayList<String>();
		d1.add("a");
		d1.add("b");
		d1.add("c");
		
//		
		
		
		
//		System.out.println(findLongestWord(s, d));
		System.out.println(findLongestWord(s1, d1));
	}
	

	//双指针法
    public static String findLongestWord(String s, List<String> d) {
    	//返回结果
        String rlt = "";
        //遍历d字符串数组
        //判断d的各子元素是否是s的按顺序的子串，并且记录最长且字典序最小的子串返回
        for(String dSub:d){
        	// s指针
            int sPointer = 0;
            // d的子元素的指针
            int dSubPointer = 0;
            // 字符匹配个数
            int matchNum = 0;
            while(dSubPointer < dSub.length() && sPointer < s.length()){
            	// 若字符相等，则d子元素指针右移，字符匹配数自增
                if(s.charAt(sPointer) == dSub.charAt(dSubPointer)){
                    dSubPointer++;
                    matchNum++;
                }
                // 若字符匹配数为d的子字符串长度，并且当前匹配数大于等于前一个rlt的长度才可能更新rlt
                if(matchNum == dSub.length() && matchNum>=rlt.length()){
                	//大于则直接更新
                    if(matchNum>rlt.length()){
                        rlt = dSub;
                    }
                    //如果等于就按照字典序更新为字典序小的
                    else{
                        rlt = rlt.compareTo(dSub)>0?dSub:rlt;
                    }
                }
                // 每次操作完毕s指针都会右移
                sPointer++;
            }

        }
        return rlt;
    }
     
    

}
