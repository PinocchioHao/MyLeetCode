package iteration;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串""。
 * <p>
 * 示例1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 *
 * @author Pinocchao
 */


public class Leetcode14 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

//		Scanner scanner = new Scanner(System.in);
//		
//		while (scanner.hasNext()) {
//			System.out.println(scanner.next());
//		}
//		

        String[] strs = {"cir", "car"};


//		System.out.println("a".substring(0,1));

        System.out.println(longestCommonPrefix(strs));

        System.out.println(longestCommonPrefix2(strs));


    }


    /**
     * From front to rear, append character
     * @param strs
     * @return
     */
    public static String longestCommonPrefix2(String[] strs) {
        int minLength = Integer.MAX_VALUE;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            minLength = Math.min(minLength, strs[i].length());
        }
        for (int i = 0; i < minLength; i++) {
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].charAt(i) != strs[0].charAt(i)) {
                    return sb.toString();
                }
                // Traverse to the last element, which means all the prefix char is equal in this round, append char
                if (j == strs.length - 1) {
                    sb.append(strs[j].charAt(i));
                }
            }
        }

        return sb.toString();
    }


    /**
     * From rear to front, substring
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {

        if (strs == null || strs.length == 0) {
            return "";
        }

        int allStrLen = strs.length;
        String shortestString = strs[0];
        int endIdx = 0;
		// find the shortest string
        for (int i = 1; i < allStrLen; i++) {
            if (strs[i].length() < shortestString.length()) {
                shortestString = strs[i];
            }
        }

		// compare from rear to front, substring the longest prefix
        for (int i = shortestString.length(); i > 0; i--) {
            for (int j = 0; j < allStrLen; j++) {
                if (!shortestString.substring(0, i).equals(strs[j].substring(0, i))) {
                    break;
                }
                //End flag -- all strings in the array is conpared, and find the longest prefix, return
                if (j == allStrLen - 1) {
                    endIdx = i;
                    return shortestString.substring(0, endIdx);
                }
            }
        }
        //None of them match, return an empty string
        return "";

    }


}
