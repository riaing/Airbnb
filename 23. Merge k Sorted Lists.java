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
        private Comparator<ListNode> listComparator = new Comparator<ListNode>(){
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
        PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(lists.length, listComparator); 
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
####################################################
思路2：Merge and Conquer 
类似merge sort，每次将所有的list两两之间合并，直到所有list合并成一个。如果用迭代而非递归，则空间复杂度为O(1)。时间复杂度：
2n * k/2 + 4n * k/4 + ... + (2^x)n * k/(2^x) = nk * x
k/(2^x) = 1 -> 2^x = k -> x = log2(k)
所以时间复杂度为O(nk log(k))，与方法一相同。

public class Solution {
      private ListNode mergeHelper(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        
        int mid = start + (end - start) / 2;
        ListNode left = mergeHelper(lists, start, mid);
        ListNode right = mergeHelper(lists, mid + 1, end);
        return mergeTwoLists(left, right);
    }
    
    public ListNode mergeKLists(ListNode[] lists) {
       if (lists == null || lists.length == 0 ){
           return null;
       }
        return mergeHelper(lists, 0, lists.length - 1);
    }
    //就是merge two sorted list的code
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode pseudo = new ListNode(0);
        ListNode tmp = pseudo; 
        while(l1!=null && l2!=null){
            if(l1.val<= l2.val){
                tmp.next = l1;
                l1= l1.next; 
            }
            else{
                tmp.next = l2;
                l2=l2.next;
            }
            tmp=tmp.next;
        }
        
        if(l1!=null){ //if someparts left,这里用if就行！因为l1是以一个head为头的一串list！ 
            tmp.next= l1;
        }
        else{
            tmp.next = l2; 
        }
        return pseudo.next; 
    }  
}

#######merge and sort. 这里先把开头和结尾两两合并成一个list，往中间走；再round一次再两两合并，和上思路一样
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode pseudo = new ListNode(0);
        ListNode tmp = pseudo; 
        while(l1!=null && l2!=null){
            if(l1.val<= l2.val){
                tmp.next = l1;
                l1= l1.next; 
            }
            else{
                tmp.next = l2;
                l2=l2.next;
            }
            tmp=tmp.next;
        }
        
        if(l1!=null){ //if someparts left,这里用if就行！因为l1是以一个head为头的一串list！ 
            tmp.next= l1;
        }
        else{
            tmp.next = l2; 
        }
        return pseudo.next; 
    }
     
    
    public ListNode mergeKLists(ListNode[] lists) {
       if (lists == null || lists.length == 0 ){
           return null;
       }
       int end = lists.length -1; 
       while (end > 0 ){
           int start = 0; 
           while ( start < end ){
               lists[start] = mergeTwoLists(lists[start], lists[end]);
               end --;
               start ++; 
           }
       }
       return lists[0];
     
    }
    
}
