public class Leetcode654 {
	static int max = 0;
	static int maxIdx = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub


		int[] nums = {2,0,4,6,3};
		int[] nums2 = {1,2,3,4};
		int[] nums3 = {4,3,2,1};

		findMax(nums);
		
		System.out.println(max);
		System.out.println(maxIdx);
		
		System.out.println(findMax(nums3, 0, 3));

	}


	
 
	
	public static void findMax(int[] nums) {

		for (int i = 0; i < nums.length-1; i++) {
			if (nums[i+1]>nums[i]) {
				max = nums[i+1];
				maxIdx = i+1 ;
			}
		}
	}
	
	//������
//    public static int findMax(int[] nums, int leftIdx, int rightIdx){
//        int maxIdx = 0;
//        for(int i = leftIdx; i<rightIdx-1; i++){
//            if(nums[i+1]>nums[i]){
//                maxIdx = i+1;
//            }
//            else {
//				maxIdx = i;
//			}
//        }
//        return maxIdx;
//    }
//	
    public static int findMax(int[] nums, int leftIdx, int rightIdx){
        int maxIdx = -1;
        int max = -1;
        for(int i = leftIdx; i<=rightIdx; i++){
            if(nums[i]>max){
                max = nums[i];
                maxIdx = i;
            }
        }
        return maxIdx;
    }

}
