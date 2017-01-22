/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
       /* ListNode pseudo = new ListNode(0);
        pseudo.next = l1;
        ListNode p1 = pseudo; 
        ListNode p2 =l2;
        while(p1.next!=null){
            if(p2==null){
                return pseudo.next; 
            }
           if(p1.next.val<=p2.val){
               p1=p1.next;
           } 
           else{
               p2.next =p1.next;
               p1.next = p2;
               p2 =p2.next; //move forward the pointer 
           }
        }
        p1.next =p2; 
        return pseudo.next; */
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
