package linkedlist;

/**
 707
 */
public class LeetCode707 {
     int size;
     ListNode head;


    public  class ListNode {
        public ListNode next;
        public int val;

        public ListNode(int val) {
            this.val = val;
        }
    }
//
//
//    public static   void main(String[] args) {
//        LeetCode707 myLinkedList = new LeetCode707();
//
//        myLinkedList.addAtHead(1);
//        myLinkedList.addAtTail(3);
//        myLinkedList.addAtIndex(1, 2);    // 链表变为 1->2->3
//        System.out.println(myLinkedList.get(1));              // 返回 2
//        myLinkedList.deleteAtIndex(1);    // 现在，链表变为 1->3
//        System.out.println(myLinkedList.get(1));              // 返回 3
//
//        System.out.println();
//
//    }




    public LeetCode707() {
        size = 0;
        // 初始为dummy而非null，方便操作
        head = new ListNode(Integer.MIN_VALUE);
    }



    public  int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode cur = head;
        while (index >= 0) {
            cur = cur.next;
            index--;
        }
        return cur.val;
    }

    public  void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public  void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public  void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        index = Math.max(0, index);
        size++;
        ListNode pred = head;
        for (int i = 0; i < index; i++) {
            pred = pred.next;
        }
        ListNode toAdd = new ListNode(val);
        toAdd.next = pred.next;
        pred.next = toAdd;
    }

    public  void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        size--;
        ListNode pred = head;
        for (int i = 0; i < index; i++) {
            pred = pred.next;
        }
        pred.next = pred.next.next;
    }

    


}
