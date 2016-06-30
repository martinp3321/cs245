import java.util.PriorityQueue;


//MARTIN MURPHY
//HUFFMAN
//03-21-16
//CS245
//GALLES


public class Huffman {
	
	public static void uncompressFile(boolean verbose, BinaryFile in, TextFile out) {
		///Magic #///
		char magicNumberHigh = '0';
		char magicNumberLow = '0';
		if (!in.EndOfFile()) {
			magicNumberHigh = in.readChar();
		}
		if (!in.EndOfFile()) {
			magicNumberLow = in.readChar();
		}
		if (magicNumberHigh == 'H' && magicNumberLow == 'F') {
			///Decode///
			HuffmanNode root = new HuffmanNode();
			HuffmanTree.decode(root, in);
			/// Verbose output: Huffman Tree ///
			if (verbose) {
				System.out.println();
				System.out.println("=============HUFFMAN CODING===============");
				System.out.println("=============MARTIN MURPHY===============");
				System.out.println("=============CS245===============");
				System.out.println("=============3/21/16===============");
				System.out.println();
				System.out.println("=============VERBOSE MODE===============");
				System.out.println();
				System.out.println("=============HUFFMAN TREE UNCOMPRESSED===============");
				
				HuffmanTree.print(root, 0);
			}
			///Uncompress///
			char tmp;
			while(!in.EndOfFile()) {
				tmp = HuffmanTree.decodeNext(root, in);
				out.writeChar(tmp);
			}
		}
		else {
			System.out.println("=============FORMAT ERROR: UNCOMPRESS FAIL=============");
		}
	}
		

	public static void compressFile(boolean verbose, boolean force, TextFile in, BinaryFile out) {
		char tmpChar;
		String tmpCode;
		int[] charFreq = new int[256];
		PriorityQueue<HuffmanNode> queue = new PriorityQueue<HuffmanNode>();
		int originalFileSize = 0;
		/// Count char frequency and the file size ///
		while (!in.EndOfFile()) {
			tmpChar = in.readChar();
			charFreq[(int) tmpChar]++;
			originalFileSize += 8; 
		}
		/// Verbose output: Frequency ///
		if (verbose) {
			System.out.println();
			System.out.println("=============HUFFMAN CODING===============");
			System.out.println("=============MARTIN MURPHY===============");
			System.out.println("=============CS245===============");
			System.out.println("=============3/21/16===============");
			System.out.println();
			System.out.println("=============VERBOSE MODE===============");
			System.out.println();
			System.out.println("=============CHAR FREQ===============");
			
			for (int i = 0; i < 256; i++) {
				if (charFreq[i] > 0) {
					
					ASCIIzero(i);
					System.out.print(i + ": " + charFreq[i] + '\n');

				}
			}
			System.out.println();

		}
		/// Create HuffmanNode for each char and push them into a queue ///
		for (int i = 0; i < 256; i++) {
			if (charFreq[i] > 0) {
				HuffmanNode node = new HuffmanNode(true);
				node.setCode((char) i);
				node.setFreq(charFreq[i]);
				queue.add(node);
			}
		}
		/// Create the Huffman Tree ///
		while(queue.size() > 1) {
			HuffmanNode tmpLeft = queue.remove();
			HuffmanNode tmpRight = queue.remove();
			HuffmanNode tmp = new HuffmanNode(false);
			tmp.setFreq(tmpLeft.freq() + tmpRight.freq());
			tmp.setLeft(tmpLeft);
			tmp.setRight(tmpRight);
			queue.add(tmp);
		}
		HuffmanNode root = queue.remove();
		///Verbose Out///
		if (verbose) {
			System.out.println("=============HUFFMAN TREE===============");
			HuffmanTree.print(root, 0);
			System.out.println();
			System.out.println();
		}
		/// Create the lookup table ///
		String[] lookupTable = new String[256];
		HuffmanTree.lookupTable(root, new StringBuffer(), lookupTable);
		/// Verbose Output ///
		if (verbose) {
			System.out.println("=============HUFFMAN CODE===============");
			
			for (int i = 0; i < 256; i++) {
				if (charFreq[i] > 0) {
					
					ASCIIzero(i);
					System.out.print(i + ": " + lookupTable[i] + '\n');
				}
			}
			System.out.println();
		}
		/// Find the size of compressed file ///
		int compressedFileSize = 6 + HuffmanTree.size(root);
		for (int i = 0; i < 256; i++) {
			if (charFreq[i] > 0) {
				compressedFileSize += charFreq[i] * lookupTable[i].length();
			}
		}
		compressedFileSize = compressedFileSize + (8 - compressedFileSize % 8);
		/// Compress the file ///
		if (force || compressedFileSize < originalFileSize) {
			out.writeChar('H');
			out.writeChar('F');
			HuffmanTree.encode(root, out);
			in.rewind();
			while (!in.EndOfFile()) {
				tmpChar = in.readChar();
				tmpCode = lookupTable[(int) tmpChar];
				for (int i = 0; i < tmpCode.length(); i++) {
					if (tmpCode.charAt(i) == '0') {
						out.writeBit(false);
					} else {
						out.writeBit(true);
					}
				}
			}
		}
		else {
			System.out.println("=============FAILURE: FILE NOT COMPRESSING=============");
		}
	}
	
	///Zero Append Encode//
	private static void ASCIIzero(int tmp) {
		if (tmp > 0) {
			while (tmp < 100) {
				tmp *= 10;
				System.out.print("0");
			}
		}
		else {
			System.out.print("00");
		}
	}
	
	public static void main(String[] args) {

		char mode = 'x';
		String infile = new String();
		String outfile = new String();
		boolean verbose = false;
		boolean force = false;
		boolean isInfile = true;
		for (String arg : args) {
			if (arg.startsWith("-")) {
				if (arg.equals("-c")) {
					mode = 'c';
				}
				if (arg.equals("-u")) {
					mode = 'u';
				}
				if (arg.equals("-v")) {
					verbose = true;
				}
				if (arg.equals("-f")) {
					force = true;
				}
			}
			else {
				if (isInfile) {
					infile = arg;
					isInfile = false;
				}
				else {
					outfile = arg;
				}
			}
		}
		if (mode == 'c') {
			TextFile in = new TextFile(infile, 'r');
			BinaryFile out = new BinaryFile(outfile, 'w');
			compressFile(true, true, in, out);
			in.close();
			out.close();
		}
		else if (mode == 'u') {
			BinaryFile in = new BinaryFile(infile, 'r');
			TextFile out = new TextFile(outfile, 'w');
			uncompressFile(true, in, out);
			in.close();
			out.close();
		}
		else {
			System.out.println("=============SUGGESTED SYNTAX=============");
			System.out.println( "java Huffman (-c|-u) [-v] [-f]  infile outfile");
		}

	}
}


class HuffmanTree {
	
	

	public static void print(HuffmanNode node, int indent) {
		for (int i = 0; i < indent; i++) {
			System.out.print(' ');
		}
		if (node.isLeaf()) {
			System.out.println((int) node.code());
		}
		else {
			System.out.println("node");
			print(node.left(), indent + 1);
			print(node.right(), indent + 1);
		}
	}
	
	public static void lookupTable(HuffmanNode node, StringBuffer code, String[] table) {
		if (node.isLeaf()) {
			table[(int) node.code()] = code.toString();
		}
		else {
			StringBuffer leftCode = new StringBuffer(code);
			leftCode.append('0');
			lookupTable(node.left(), leftCode, table);
			StringBuffer rightCode = new StringBuffer(code);
			rightCode.append('1');
			lookupTable(node.right(), rightCode, table);
		}
	}

	public static char decodeNext(HuffmanNode node, BinaryFile in) {
		if (node.isLeaf()) {
			return node.code();
		}
		else {
			boolean tmp = in.readBit();
			if (tmp) {
				return decodeNext(node.right(), in);
			}
			else {
				return decodeNext(node.left(), in);
			}
		}
	}

	public static void decode(HuffmanNode node, BinaryFile in) {
		if (!in.readBit()) {
			node.setIsLeaf(false);
			HuffmanNode leftChild = new HuffmanNode();
			node.setLeft(leftChild);
			decode(node.left(), in);
			HuffmanNode rightChild = new HuffmanNode();
			node.setRight(rightChild);
			decode(node.right(), in);
		}
		else {
			node.setIsLeaf(true);
			node.setCode(in.readChar());
		}
	}

	public static void encode(HuffmanNode node, BinaryFile out) {
		if (node.isLeaf()) {
			out.writeBit(true);
			out.writeChar(node.code());
		}
		else {
			out.writeBit(false);
			encode(node.left(), out);
			encode(node.right(), out);
		}
	}

	public static int size(HuffmanNode node) {
		if (node.isLeaf()) {
			return 9;
		}
		else {
			return 1 + size(node.left()) + size(node.right());
		}
	}
}


class HuffmanNode implements Comparable<HuffmanNode>{

	private boolean isLeaf;
	private char code;
	private int freq;
	private HuffmanNode left;
	private HuffmanNode right;

	public HuffmanNode(boolean isLeaf, char code, int freq, HuffmanNode left, HuffmanNode right) {
		this.isLeaf = isLeaf;
		this.code = code;
		this.freq = freq;
		this.left = left;
		this.right = right;
	}
	
	public int compareTo(HuffmanNode otherNode) {
		if (this.freq > otherNode.freq) {
			return 1;
		}
		else if (this.freq < otherNode.freq) {
			return -1;
		}
		else {
			if (this.height(0) > otherNode.height(0)) {
				return 1;
			}
			else if (this.height(0) < otherNode.height(0)) {
				return -1;
			}
			else {
				return 0;
			}
		}
	}

	public HuffmanNode(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public HuffmanNode() {}
	
	private int height(int depth) {
		if (isLeaf) {
			return depth + 1;
		}
		else {
			return max(left.height(depth + 1), right.height(depth + 1));
		}
	}

	private static int max(int a, int b) {
		if (a > b) {
			return a;
		}
		else {
			return b;
		}
	}
	
	public int freq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public HuffmanNode left() {
		return left;
	}

	public void setLeft(HuffmanNode left) {
		this.left = left;
	}

	public HuffmanNode right() {
		return right;
	}

	public void setRight(HuffmanNode right) {
		this.right = right;
	}

	public char code() {
		return code;
	}

	public void setCode(char code) {
		this.code = code;
	}

}
