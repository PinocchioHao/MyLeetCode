package pattern.doublepointer;

/*
 * 
 * 142. 环形链表 II
给定一个链表，返回链表开始入环的第一个节点。如果链表无环，则返回null。

为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。

说明：不允许修改给定的链表。



示例 1：

输入：head = [3,2,0,-4], pos = 1
输出：tail connects to node index 1
解释：链表中有一个环，其尾部连接到第二个节点。

 * 
 * 
 * 
 */

public class LeetCode142 {
	
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
	
    public ListNode detectCycle(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;

        //当链表为空或者只有一个元素时，无环
        if(head == null || head.next == null){
            return null;
        }
        //链表不为空就按照快慢指针遍历链表，直到快指针遍历到链表尾部或者是两指针相遇
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast){
                break;
            }
        }
        //遍历结束如果两指针不相遇，则无环
        if(slow != fast){
            return null;
        }
        //若相遇，则计算环开始位置
        //假设慢指针走了k步，则快指针走了2k步，设环起点位置距相遇位置为m
        //则环起点位置为k-m;
        //而从相遇点到下一次经过环起点也需要走k-m步。
        //所以把此刻的快或者慢指针放到链表头，另一指针从相遇位置继续走
        //当二者相遇时，极为起点位置
        //此题类似一道脑筋急转弯，画图比较好理解
        slow = head;
        while(fast != slow){
            slow = slow.next;
            fast = fast.next;
        }

        return slow;

    }
    
     
    

}
