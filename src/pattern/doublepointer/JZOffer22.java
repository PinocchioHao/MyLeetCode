package pattern.doublepointer;

/*
 * 
 * 22. 链表中倒数第k个节点
输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。



示例：

给定一个链表: 1->2->3->4->5, 和 k = 2.

返回链表 4->5.

 * 
 * 
 * 
 */

public class JZOffer22 {
	
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
	

	
	
    //先让快的跑k下，再让慢的追，当快的跑到底时，慢的就是倒数第k个
    public ListNode getKthFromEnd(ListNode head, int k) {

        ListNode fast = head;
        ListNode slow = head;

        //从倒数第一开始，所以先-1
        k--;
        while(k!=0){
            fast = fast.next;
            k--;
        }

        while(fast!=null && fast.next!=null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    
     
    

}
