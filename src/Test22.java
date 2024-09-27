import java.util.Arrays;

public class Test22 {

	public static void main(String[] args) {
		Integer[] intArr = {2,4,6,1,3};
		String[] strs = {"cddddddddddir","caaar","d","ab"};

		// comparator中的元素可先按参数顺序排序，例如(a,b)表示先a后b，如果a-b<0 则升序； a-b>0 则降序；
		// 可以这样理解：a,b分别是intArr中的两个按顺序的对象，如果a-b<0，则a<b,按照升序排列
		Arrays.sort(intArr, (a,b) -> a-b);
		// 可以使用compare方法，避免了极值情况可能的越界问题，a<b 返回-1  a=b 返回0  a>b 返回1
		Arrays.sort(intArr, (a, b) -> Integer.compare(a, b));

		// s1.length() - s2.length() < 0，则s1.length()排在s2.length()后面，该表达式意味着按照字符串长度升序排列
		Arrays.sort(strs, (s1, s2) -> s1.length() - s2.length());

		System.out.println(strs);
		System.out.println(intArr);

	}

	public static String longestCommonPrefix(String[] strs) {
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

}
