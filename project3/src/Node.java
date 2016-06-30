public class Node{
	String key; //first or last name
	String value; //whole string
	Node left;
	Node right;	
	public Node(String data){
		this.key = data;
		left = null;
		right = null;
	}
}