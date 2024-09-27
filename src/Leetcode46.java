import java.util.ArrayList;
import java.util.List;

public class Leetcode46 {
	

//    public List<List<Integer>> permute(int[] nums) {
//    	
//    	
//    	
//    	if(nums.length == 0) {
//    		return lists;
//    	}
//    	for (int i = 0; i < nums.length; i++) {
//			
//		}
//    	
//    	
//    	insertNum(lists, num);
//    	
//    	
//    	
//        rec(nums,1,nums.length);
//        return lists;
//    }
//    
//    
//    public void rec(int[] nums,int start,int n){
//        if(start > n) {
//            //此处用一个temp，避免list修改覆盖掉已添加的元素。
//            List<Integer> temp = new ArrayList<>();
//            for (Integer i : list){
//                temp.add(i);
//            }
//            lists.add(temp);
//            return;
//        }
//        //循环，在每个index处插入新的元素
//        for (int i = 0;i < start;i++){
//            list.add(i,nums[start - 1]);
//            rec(nums,start + 1,n);
//            //退格
//            list.remove(i);
//        }
//    }

	
	
	public static void main(String[] args) {
		
		List<List<Integer>> lists = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		int[] nums = {2,0,4,6,3};

		lists = insertNum(nums, nums.length-1);
		System.out.println(lists);


	}

	
    public static List<List<Integer>> insertNum(int[] nums, int numIdx){   
    	
    	if(numIdx == 0) {
    		List<List<Integer>> tmp = new ArrayList<List<Integer>>();
    		List<Integer> list = new ArrayList<Integer>();
    		list.add(nums[0]);
    		tmp.add(list);
    		return tmp;
    	}
    	
        List<List<Integer>> lastResult = insertNum(nums, numIdx -1 );
        List<List<Integer>> tempLists = new ArrayList<List<Integer>>();
        int currSize = lastResult.size();
        
        for(int i = 0; i< currSize; i++) {
        	for(int j = 0; j < lastResult.get(i).size(); j++) {
            	List<Integer> temp = new ArrayList<>( lastResult.get(i));
        		temp.add(j,nums[numIdx]);
        		tempLists.add(temp);
        	}
        }
    
        return tempLists;
    	
    	
    	
//    	 //得到上次所有的结果
//        List<List<Integer>> all_end = insertNum(nums,numIdx-1);
//        int current_size = all_end.size();
//        //遍历每一种情况
//        for (int j = 0; j < current_size; j++) { 
//            //在数字的缝隙插入新的数字
//            for (int k = 0; k <= all_end.get(j).size(); k++) {
//                List<Integer> temp = new ArrayList<>(all_end.get(j));
//                temp.add(k, nums[numIdx]);
//                //添加到结果中
//                all_end.add(temp);
//            };
//
//        }
//        //由于 all_end 此时既保存了之前的结果，和添加完的结果，所以把之前的结果要删除
//        for (int j = 0; j < current_size; j++) {
//            all_end.remove(0);
//        }
//        return all_end;
//    	
//    	
    	
    	
    	
    	
    	
    }
	
	


	
 
}
