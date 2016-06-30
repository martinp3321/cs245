import java.util.Stack;


//Martin Murphy

class LinkedList {
	protected ListNode head;
	protected ListNode tail;

	public LinkedList() {
		head = new ListNode(null);
		tail = head;
	}

	// Method add(Object o)
	// Adds the object at the end of the linked list
	public void add(Object o) {
		ListNode newElem = new ListNode(o);
		tail.setNext(newElem);
		//newElem.prev()=tail;
		tail.next().setPrev(tail);
		tail = tail.next();
	}




	// Method reverse
	// Reverses the string
	public void reverse() {
		if (head == null) {
			System.out.println("The list is empty");
			return;
		}
		ListNode tmp = head.next();
		Stack<Object> reverseNums = new Stack<Object>();
		while (tmp != null) {
			reverseNums.push((Object) tmp.data());
			tmp = tmp.next();
		}
		tmp = head.next();
		while (!reverseNums.empty()) {
			tmp.setData(reverseNums.pop());
			tmp = tmp.next();
		}

	}

	// Method toString()
    // Creates a String representation of the list:
    //  Left bracket, followed by by concatenating the result of toString() 
    //  called on each element of the list (separated by commas), fooled by a 
    //  right bracket.
    //  Empty list:  toString => "[]"
    //  List containing the single Integer 3: toString => "[3]"
    //  List containing three integers 1, 2, 3:  toString => "[1,2,3]"
	public String toString() {
		String result = "[";
		ListNode tmp = head.next();
		while (tmp != null) {
			if (result.length() == 1) {
				result = result + tmp.data();
			} else {
				result = result + "," + tmp.data();
			}
			tmp = tmp.next();
		}
		return result + "]";
	}

	// Method remove(Object o)
	// Removes the first occurrence of the Object o from the list. If
	// the object appears more than once, only the first occurrence is
	// removed. If the object does not occur in the list, the method
	// does nothing.
	public void removeFirst(Object o) {
		ListNode tmp = head;
		if (tmp.next() == null){
			System.out.println("The list is empty");
			tail = null;
			return;
		}
		else {
			while (tmp.next() != null){
				if (tmp.next().data().equals(o) == true){
					tmp.setNext(tmp.next().next());
					if ( tmp.next() == null)
						tail = tmp;
					break;
				}
				tmp = tmp.next();
			}
		}
	}
	
	
	
	
	 // Method removeLast(Object o)
    //  Removes the last occurrence of the Object o from the list.  If
    //    the object appears more than once, only the first occurrence is
    //    removed.  If the object does not occur in the list, the method
    //    does nothing. removeFirst and removeLast should reuse as much
    //    code as possible!
	public void removeLast(Object o) {
		ListNode tmp = tail;
		if (tmp.prev() == null){
			System.out.println("The list is empty");
			tail = null;
			return;
		}
		else {
			System.out.println("else");
			while (tmp != null){
				System.out.println("while");
				if (tmp.data().equals(o)){
					System.out.println("ANSWER");
					tmp.prev().setNext(tmp.next());
					//System.out.println("ANSWER");
					
					if ( tmp.prev() == null)
						head = tmp;
					break;
				}
				tmp = tmp.prev();
			}
		}
	}


	public static void main(String args[]) {
		LinkedList l = new LinkedList();
		for (int i = 0; i < 10; i++) {
			l.add(new Integer(i));
		}

		System.out.println("Linked list: ");
		System.out.println(l);
		System.out.println();
		
		l.add(2);
		l.add(4);
		System.out.println(l);
		System.out.println();
		
		
		System.out.println("RemoveLast ");
		l.removeLast(2);
		System.out.println(l);
		System.out.println();
		
		System.out.println("Remove 0:");
		l.removeFirst(new Integer(0));
		System.out.println(l);
		System.out.println();
		
		
		l.add(14);
		System.out.println(l);
		System.out.println("Remove 14:");
		l.removeFirst(new Integer(14));
		System.out.println(l);
		System.out.println();
		

		System.out.println("Reverse: ");
		l.reverse();
		System.out.println(l);
		System.out.println();
		
		System.out.println("Reverse Back: ");
		l.reverse();
		System.out.println(l);

		System.out.println("Remove 0:");
		l.removeFirst(new Integer(0));
		System.out.println(l);
		System.out.println();

		System.out.println("Remove 1:");
		l.removeFirst(new Integer(1));
		System.out.println(l);
		System.out.println();

		System.out.println("Remove 2:");
		l.removeFirst(new Integer(2));
		System.out.println(l);
		System.out.println();

		System.out.println("Remove 9:");
		l.removeFirst(new Integer(9));
		System.out.println(l);
		System.out.println();

		System.out.println("Remove 5:");
		l.removeFirst(new Integer(5));
		System.out.println(l);
		System.out.println();

		System.out.println("Remove 2:");
		l.removeFirst(new Integer(2));
		System.out.println(l);
		System.out.println();

		System.out.println("Adding 11:");
		l.add(new Integer(11));
		System.out.println(l);
		System.out.println();

		System.out.println("Adding 4:");
		l.add(new Integer(4));
		System.out.println(l);
		System.out.println();

		System.out.println("Remove 11:");
		l.removeFirst(new Integer(11));
		System.out.println(l);
		System.out.println();

		System.out.println("Adding 20:");
		l.add(new Integer(20));
		System.out.println(l);
		System.out.println();

		System.out.println("Remove 4:");
		l.removeFirst(new Integer(4));
		System.out.println(l);
		System.out.println();

		System.out.println("Remove 5:");
		l.removeFirst(new Integer(5));
		System.out.println(l);
		System.out.println();

		System.out.println("Remove 8:");
		l.removeFirst(new Integer(8));
		System.out.println(l);
		System.out.println();

		LinkedList l2 = new LinkedList();
		System.out.println(l2);
		l2.add(new Integer(2));
		System.out.println(l2);
		l2.removeFirst(new Integer(2));
		System.out.println(l2);

	}

}