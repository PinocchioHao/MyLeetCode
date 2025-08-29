package pattern.doublepointer;

/*
 * 
 * 141. 环形链表
给定一个链表，判断链表中是否有环。

 * 
 * 
 * 
 */

public class LeetCode141 {
	
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
	
	public static void main(String[] args) {
			
		
	}
	
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast){
                return true;
            }

        }
        return false;



    }

    
     
    

}
