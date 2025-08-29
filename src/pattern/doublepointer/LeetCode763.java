package pattern.doublepointer;

import java.util.ArrayList;
import java.util.List;

/*
 * 
 * 763. 划分字母区间

字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。



示例 1：

输入：S = "ababcbacadefegdehijhklij"
输出：[9,7,8]
解释：
划分结果为 "ababcbaca", "defegde", "hijhklij"。
每个字母最多出现在一个片段中。
像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。


 * 
 * 
 * 
 */

public class LeetCode763 {
	
	
	public static void main(String[] args) {
		
		

		
		
	
		System.out.println(partitionLabels("ababcbacadefegdehijhklij"));
			
		
		
		
		
	}
	
	
	 public static List<Integer> partitionLabels(String S) {
		    //     List<Integer> rlt = new ArrayList();
		    //     int left = 0;
		    //     int right = S.length()-1;
		    //     int maxIdx = 0;
		    //     char[] schars = S.toCharArray();
		    //     while(left<S.length()-1 && right>=left){
		    //         if(left == maxIdx){
		    //             rlt.add(maxIdx);
		    //         }
		    //         if(schars[left] == schars[right]){
		    //             maxIdx = right;
		    //             left ++;
		    //             right = S.length()-1;
		    //         }
		    //         right--;
		    //     }
		    //     return rlt;



		        List<Integer> list = new ArrayList<>();
		        int i = 0;
		        int j = S.length() - 1;
		        int flag = 0;
		        while (S.length() > 0) {
		            while (S.charAt(i) != S.charAt(j)) {
		                j--;
		            }
		            flag = Math.max(flag, j);
		            if (i < flag) {
		                i++;
		                j = S.length() - 1;
		                continue;
		            }
		            list.add(j + 1);
		            S = S.substring(j + 1);
		            i = 0;
		            j = S.length() - 1;
		            flag = 0;
		        }
		        return list;

		    }


}
