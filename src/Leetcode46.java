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
//            //�˴���һ��temp������list�޸ĸ��ǵ�����ӵ�Ԫ�ء�
//            List<Integer> temp = new ArrayList<>();
//            for (Integer i : list){
//                temp.add(i);
//            }
//            lists.add(temp);
//            return;
//        }
//        //ѭ������ÿ��index�������µ�Ԫ��
//        for (int i = 0;i < start;i++){
//            list.add(i,nums[start - 1]);
//            rec(nums,start + 1,n);
//            //�˸�
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
    	
    	
    	
//    	 //�õ��ϴ����еĽ��
//        List<List<Integer>> all_end = insertNum(nums,numIdx-1);
//        int current_size = all_end.size();
//        //����ÿһ�����
//        for (int j = 0; j < current_size; j++) { 
//            //�����ֵķ�϶�����µ�����
//            for (int k = 0; k <= all_end.get(j).size(); k++) {
//                List<Integer> temp = new ArrayList<>(all_end.get(j));
//                temp.add(k, nums[numIdx]);
//                //��ӵ������
//                all_end.add(temp);
//            };
//
//        }
//        //���� all_end ��ʱ�ȱ�����֮ǰ�Ľ�����������Ľ�������԰�֮ǰ�Ľ��Ҫɾ��
//        for (int j = 0; j < current_size; j++) {
//            all_end.remove(0);
//        }
//        return all_end;
//    	
//    	
    	
    	
    	
    	
    	
    }
	
	


	
 
}
