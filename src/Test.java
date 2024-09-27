import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {

//		Scanner scanner = new Scanner(System.in);
//		while (scanner.hasNext()) {
//			System.out.println(scanner.next());
//			
//		}
//		

//		int totalNum = 0;
//		for (int i = 1; i < 100; i++) {
//			int a = i;
//			int bitCnt = 0;
//			while (a!=0) {
//				a=a/10;
//				bitCnt ++ ;
//				
//			}
//			if (bitCnt%2 == 0) {
//				totalNum ++;
//
//			}
//			
//		}
//		
//		
//		
//		
//		System.out.println(totalNum);
//	
//	

		System.out.println(addDigits(877));
		
		
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);

		list.remove(3);
		System.out.println(list);
		
		
		

	}

	public static int addDigits(int num) {

		/*
		 * iteration
		 */
//	    while(num/10!=0){
//	        int sum=0;
//	        while(num!=0){
//	            sum+=num%10;
//	            num=num/10;
//	        }
//	        num=sum;
//	    }
//	    return num;

		
		/*
		 * iteration+recursion
		 */
//		if (num < 10)
//			return num;
//		else {
//			int sum = 0;
//			while (num != 0) {
//				sum += num % 10;
//				num = num / 10;
//			}
//			return addDigits(sum);
//		}

		
		/*
		 * recursion
		 */
		if (num < 10)
			return num ;
		else return addDigits(num%10+addDigits(num/10));
	    
	}
	

	
	
	//Definition for a binary tree node.
	public class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode(int x) { val = x; }
	}
	

	

}
