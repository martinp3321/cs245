

public class HashTable {
	
	private static int DEFAULT_SIZE = 31;

	private int size;
	private HNode[] nodes;
	
	public HashTable(int size) {
		this.size = size;
		nodes = new HNode[size];
	}
	
	public HashTable() {
		size=DEFAULT_SIZE;
		nodes = new HNode[size];
	}
	
	public Integer find(String key) {
		int vertex = -1;
		int hash   = Hash(key, vertex, true);
		HNode node  = getNode(hash);
		
		while (node != null) {
			if (node.getCity().equals(key)) {
				vertex = node.getVertex();
				break;
			} 
			node = node.getNext();
		}
		
		return vertex;
	}
	
	public int getVertex(String city) {
		
		int vertex = -1;
		int hash   = Hash(city, vertex, true);
		HNode node  = getNode(hash);
		
		while (node != null) {
			if (node.getCity().equals(city)) {
				vertex = node.getVertex();
				break;
			} 
			node = node.getNext();
		}
		
		return vertex;
	}

	public int Hash(String city, int vertex, boolean lookup) {
		
		int h, hashIndex, i;
		h = hashIndex = i = 0;
		
		for (i = 0; i < city.length(); i++)
			h = (h << 4) + (int) city.charAt(i);

		hashIndex = h % size;

		if (lookup != true) {
			HNode node = new HNode(city, vertex, null);
			node.setNext(nodes[hashIndex]);
			nodes[hashIndex] = node;
		}

		return hashIndex;
	}
	
	public HNode getNode(int hash) {
		return nodes[hash];
	}
	
	
	


	public void delete(String key) {

		
	}
	
	
	public int hash(String key) {
		int h = 0;
		for (int i = 0; i < key.length(); i++) {
			h = (h << 4) + (int) key.charAt(i);
		}
		return h % size;
	}

	public void insert(int value, String key) {
		int hashV = hash(key);
		HNode node = new HNode(key, value);
		node.setNext(nodes[hashV]);
		nodes[hashV] = node;
		
	}

	
	
	
	
	
}
