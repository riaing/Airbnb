#另一个需要difine comparator的题: Round Numbers. 
    
思路1：priority queue

将每个list的最小节点放入一个priority queue (min heap)中。之后每从queue中取出一个节点，则将该节点在其list中的下一个节点插入，
以此类推直到全部节点都经过priority queue。由于priority queue的大小为始终为k，而每次插入的复杂度是log k，一共插入过nk个节点。
时间复杂度为O(nk logk)，空间复杂度为O(k)。

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
       /*方法二： 把comparator 分出来写    
        private Comparator<ListNode> ListComparator = new Comparator<ListNode>(){
        public int compare(ListNode n1 , ListNode n2 ){
             if (n1.val >= n2.val ){
                    return 1;
                }
                else{
                    return -1;
                }
        }
    };
    */
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
        /*方法二：直接call之前定义的comparator即可
        PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(lists.length, ListComparator); 
        */
        
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
