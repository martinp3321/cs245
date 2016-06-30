import java.io.*;
//Martin Murphy
//CS245


class Serialize {



    /***
     * Serialize a tree by writing out a Pre-Order traversal, with an end-of-children marker.
     * Use the ')' character for the end-of-child marker
     * @param tree The tree to serialize
     * @param outputFile The file to write to.  You can use outfile.print() to print a character
     *        to this file
     * @throws IOException
     */
	public static void serialize(GenNode tree, PrintStream outputFile) throws IOException 
	{
		if (tree != null) {
    		outputFile.print(tree.element());
    		serialize(tree.leftchild(), outputFile);
    		outputFile.print(')');
    		serialize(tree.rightsib(), outputFile);
    			    			
    	}

		
	}

	/***
	 * Read in a serialized general tree form a file, and return it
	 * @param inputFile The input file to read the tree from.  
	 * @return The resulting tree
	 * @throws IOException
	 */
	public static GenNode dserialize(PushbackReader inputFile) throws IOException 
	{
		return null;
	}

	
	/***
	 * Print a General Tree out to standard out in a human-readable format
	 * @param tree: The tree to print out
	 * @param offset:  The horizontal offset of the printed tre
	 */
	public static void print(GenNode tree, int offset) {
		if (tree != null) {
			for (int i=0; i<offset; i++)
				System.out.print("   ");
			System.out.println(tree.element());
			print(tree.leftchild(),offset+1);
			print(tree.rightsib(),offset);
		}
	}


	/***
	 * Return true if and only off two general treees are equal
	 * @param t1 
	 * @param t2
	 * @return True iff t1 is equal to t2
	 */
	public static boolean equal(GenNode t1, GenNode t2)
	{
		if (t1 == null && t2 == null)
		{
			return true;
		}
		if (t1 == null || t2 == null)
		{
			return false;
		}

		if (t1.element() != t2.element())
			return false;
		
		return equal(t1.leftchild(), t2.leftchild()) && equal(t1.rightsib(), t2.rightsib());
		
	}
	
	
	public static boolean testOne(GenNode tree, String name) throws IOException
	{
		System.out.println(name + ":");
		print(tree,0);
		System.out.println(name + " Serialized:");
		serialize(tree, System.out);
		serialize(tree, new PrintStream("test1"));
		GenNode treeRead = dserialize( new PushbackReader(new FileReader("test1")));
		System.out.println();
		boolean success = equal(tree,treeRead);
		if (success)
		{
			System.out.println("Tree successfully serialized / deserialized");
		}
		else
		{
			System.out.println("ERROR:  Tree not successfully serialized / deserialized");
		}
		System.out.println(name + " Read back in");
		print(treeRead,0);		
		return success;
	}
	



	public static void main(String args[]) throws IOException {
		GenNode tree1, tree2, tree3;

		tree1 = new GenNode('A',
				new GenNode('B',
						null,
						new GenNode('C',
								new GenNode('D',
										null,
										new GenNode('E',
												null,
												new GenNode('F',
														null,
														null))),
								new GenNode('G',
										null,
										null))),
				null);



		tree2 = new GenNode('A',
				new GenNode('B',
						new GenNode('C',
								new GenNode('D',
										new GenNode('E',
												null,
												null),
										null),
								null),
						null),
				null);

		tree3 = new GenNode('A',
				new GenNode('B',
						new GenNode('C',
								null,
								new GenNode('D',
										null,
										null)),
						new GenNode('E',
								null,
								new GenNode('F',
										new GenNode('G',
												null,
												new GenNode('H',
														null,
														new GenNode('I',
																null,
																null))),
										null))),
				null);
		
		boolean success = true;
		success &= testOne(tree1, "tree1");
		success &= testOne(tree2, "tree2");
		success &= testOne(tree3, "tree3");
		
		if (success)
		{
			System.out.println("SUCCESS");
		}
		else
		{
			System.out.println("FAILURE");
		}
	}



}