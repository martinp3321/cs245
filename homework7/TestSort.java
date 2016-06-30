import java.util.Arrays;
import java.util.Random;

//Martin Murphy
//CS245
//Hw7

public class TestSort {
	
	
	public static ListNode merge(ListNode l, ListNode r){
        ListNode lp = l, rp = r;
        ListNode newhead = new ListNode(-1,null);
        ListNode current = newhead;

        while(lp!=null || rp!=null){
            if(lp==null){
                current.next = rp;
                break;
            }else if(rp==null){
                current.next = lp;
                break;
            }else{
                if(lp.data <= rp.data){
                    current.next = lp;
                    lp = lp.next;
                    current = current.next;
                }else {
                    current.next = rp;
                    rp = rp.next;
                    current = current.next;
                }
            }
        }
        return newhead.next;
    }
	

	public static ListNode mergeSort(ListNode head) {
		 
		if (head == null || head.next == null)
			return head;
 
		int count = 0;
		ListNode p = head;
		while (p != null) {
			count++;
			p = p.next;
		}
 
		int middle = count / 2;
 
		ListNode l = head, r = null;
		ListNode p2 = head;
		int cHalf = 0;
		while (p2 != null) {
			cHalf++;
			ListNode next = p2.next;
 
			if (cHalf == middle) {
				p2.next = null;
				r = next;
			}
			p2 = next;
		}

		ListNode h1 = mergeSort(l);
		ListNode h2 = mergeSort(r);

		ListNode merged = merge(h1, h2);
 
		return merged;
	}
 
	
	


	public static void main(String args[])
	{
		final int SIZE = 10000;
		Random r = new Random();
		
		int data[] = new int[SIZE];
		ListNode list = null;
		
		for (int i = 0; i < SIZE; i++)
		{
			data[i] = r.nextInt();
			list = new ListNode(data[i], list);
		}
		
		
		list = mergeSort(list);
		Arrays.sort(data);
		
		ListNode tmp = list;
		boolean dataMismatch = false;
		boolean badList = false;
		for (int i = 0; i < SIZE; i++)
		{
			if (tmp == null)
			{
				badList = true;
				break;
			}
			if (data[i] != tmp.data)
			{
				dataMismatch = true;
			}
			tmp = tmp.next;
		}
		if (tmp != null)
		{
			badList = true;
		}
		
		if (badList)
		{
			System.out.println("List is the wrong length");
		}
		if (dataMismatch)
		{
			System.out.println("Data not sorted propertly");
		}
		if (!badList && !dataMismatch)
		{
			System.out.println("Success!");
		}
		
		
	}
	
	

}