import java.util.Arrays;

public class Driver {
	
	
	
	public static void testStack()
	{
		
		PersistentStack S = new PersistentStack();
		
		S.push("A");
		S.push("B");
		S.push("C");
		S.push("D");
		S.pop();
		S.pop();
		S.push("E");
		S.push("F");
		S.push("G");
		S.pop();
		S.pop();
		S.pop();
		S.push("H");
		S.push("I");
		S.pop();
		S.push("J");
		System.out.println(Arrays.toString(S.getAllElements(S.top, false)) + "\n\n\n\n\n");
		
//		while (!S.empty()) {
//	         String value = S.pop();
//	         System.out.print(value);
//	         System.out.print(" ");
//	      }
//	      System.out.println("");
//		
		for (int i = 0; i <= S.currentTime(); i++)
		{
			System.out.println(Arrays.toString(S.getAllElements(i, false)));
		}

	}
	
	
	
	
	public static void main(String argsp[]) {
		testStack();
//		testNamesBST();
//		testAbstractBST();
	}

}
