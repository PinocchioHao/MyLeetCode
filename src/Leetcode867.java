public class Leetcode867 {

	public static void main(String[] args) {
		
		int[][] a = null;
		
		int[][] b = transpose(a);
		
		
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				System.out.println(b[i][j]);

			}
		}
		
		
	}
	
	
	
    public static int[][] transpose(int[][] A) {
    	if (A == null) {
			return null;
		}
    	int m1 = A.length;
    	int n1 = A[0].length;
    	int[][] res = new int[n1][m1];
    	for (int i = 0; i < m1; i++) {
			for (int j = 0; j < n1; j++) {
				res[j][i] = A[i][j];
			}
		}
    	return res;
    }

}
