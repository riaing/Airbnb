#另一个需要difine comparator的题： 
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        //corner case 
        if (lists == null || lists.length == 0){
            return null; 
        }
        //important! have to define how to compare ListNode( OBJECT)! 
        PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>(){
            public int compare(ListNode n1, ListNode n2){
                if (n1.val >= n2.val ){
                    return 1;
                }
                else{
                    return -1;
                }
            }
            }); 
        
        ListNode pseudo = new ListNode(0);
        ListNode head = pseudo; 
        //add the head of every lists to heap
        for (int i = 0; i <lists.length; i ++ ){
            if (lists[i] != null){ //avoid [[]],empty list 
                heap.offer(lists[i]);
            }
            
        }
        while (heap.size()!=0){
        ListNode addIn = heap.poll(); 
        //add the min from heap to new LinkedList 
        head.next = addIn; 
        head = head.next; 
            if ( addIn.next != null ){
                //add the next node from the above list to heap 
                heap.offer(head.next);//head.next and addIn.next should be the same? 
            }
     
        }
        return pseudo.next; 
    }
}
