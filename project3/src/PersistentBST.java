import java.util.Arrays;

//Martin Murphy
//CS245
//Project3

public class PersistentBST {
	public static  Node root;
	public Node[] timeArray;
	public int [] timeSize;
	int top;
	int size;
	public PersistentBST(){
		this.root = null;
		size = 20;
		timeArray = new Node[size];
		timeSize = new int[size];
		timeSize[0] = 0;
		top = 0;
	}
	

	
	public PersistentBST(String string) {
		// TODO Auto-generated constructor stub
		this.root = null;
	}


	
	public boolean delete(String string){
		Node parent = root;
		Node current = root;
		boolean isLeftChild = false;
		while(current.key!=string){
			parent = current;
			if(string.compareTo(current.key)<0){
				isLeftChild = true;
				current = current.left;
			}else{
				isLeftChild = false;
				current = current.right;
			}
			if(current ==null){
				return false;
			}
		}
		//if i am here that means we have found the node
		//Case 1: if node to be deleted has no children
		if(current.left==null && current.right==null){
			if(current==root){
				root = null;
			}
			if(isLeftChild ==true){
				parent.left = null;
			}else{
				parent.right = null;
			}
		}
		//Case 2 : if node to be deleted has only one child
		else if(current.right==null){
			if(current==root){
				root = current.left;
			}else if(isLeftChild){
				parent.left = current.left;
			}else{
				parent.right = current.left;
			}
		}
		else if(current.left==null){
			if(current==root){
				root = current.right;
			}else if(isLeftChild){
				parent.left = current.right;
			}else{
				parent.right = current.right;
			}
		}else if(current.left!=null && current.right!=null){
			
			//now we have found the minimum element in the right sub tree
			Node successor	 = getSuccessor(current);
			if(current==root){
				root = successor;
			}else if(isLeftChild){
				parent.left = successor;
			}else{
				parent.right = successor;
			}			
			successor.left = current.left;
		}		
		return true;		
	}
	
	public Node getSuccessor(Node deleleNode){
		Node successsor =null;
		Node successsorParent =null;
		Node current = deleleNode.right;
		while(current!=null){
			successsorParent = successsor;
			successsor = current;
			current = current.left;
		}
		//check if successor has the right child, it cannot have left child for sure
		// if it does have the right child, add it to the left of successorParent.
//		successsorParent
		if(successsor!=deleleNode.right){
			successsorParent.left = successsor.right;
			successsor.right = deleleNode.right;
		}
		return successsor;
	}
	

	
	public void insert(String string){
		Node newNode = new Node(string);
		if(top==0){
			top++;
			timeArray[top] = newNode;
			timeSize[top] = 1;
			root = newNode;
			return;
		}
		
		top++;
		Node oldTree = timeArray[top-1];
		Node newTree = new Node(oldTree.key);
		timeArray[top] = newTree;
		timeSize[top] = timeSize[top-1]+1;
		while (true) {
			if(string.compareTo(oldTree.key)<0){				
				if(oldTree.left==null){
					newTree.left = newNode;
					newTree.right = oldTree.right;
					return;
				}
				newTree.left = new Node(oldTree.left.key);
				newTree.right = oldTree.right;
				oldTree = oldTree.left;
				newTree = newTree.left;
				
			}else{
				if(oldTree.right==null){
					newTree.right = newNode;
					newTree.left = oldTree.right;
					return;
				}
				newTree.right = new Node(oldTree.right.key);
				newTree.left = oldTree.left;
				oldTree = oldTree.right;
				newTree = newTree.right;
			}
		}
	}

	public void display(int i){
		Node curr = timeArray[i];
		print(curr, "");
	}
	private void print(Node curr, String space) {
		if(curr != null){
			space += " ";
			print(curr.left, space);
			System.out.println(space + curr.key);
			print(curr.right, space);
		}
	}
	
	public int currentTime() {
		return top;
	}

	public String[] getAllElements() {
		// TODO Auto-generated method stub
		return getAllElements(top);
	}

	public String[] getAllElements(int time) {
		// TODO Auto-generated method stub
		String[] allElements = new String[timeSize[time]];
		Node curr = timeArray[time];
		
		return gatherElements(curr, allElements, 0);
	}
	private String[] gatherElements(Node curr, String[] allElements, int index)
	{
		if (curr!= null) {
			gatherElements(curr.left, allElements, index);
			allElements[index++] = curr.key;
			gatherElements(curr.right, allElements, index);
		}
		return allElements;
	}
	
	
	
	public boolean find(String string){
		return find(string, top);
	}


	public boolean find(String string, int time) {
		// TODO Auto-generated method stub
		Node current = timeArray[time];
		while(current!=null){
			if(current.key==string){
				return true;
			}else if(string.compareTo(current.key)<0){
				current = current.left;
			}else{
				current = current.right;
			}
		}
		return false;
	}
	

	   
	   
	public static void main(String arg[]){
		PersistentBST t = new PersistentBST();

//	      t.insert("jan");
//	      t.insert("feb");
//	      t.insert("mar");
//	      t.insert("apr");
//	      t.insert("may");
//	      t.insert("jun");
//	      t.insert("jul");
//	      t.insert("aug");
//	      t.insert("sep");
//	      t.insert("oct");
//	      t.insert("nov");
//	      t.insert("dec");
		
		
		t.insert("{ \"First\" : \"David\" , \"Last\" : \"Smith\"}");
		t.insert("{ \"First\" : \"Allan\" , \"Last\" : \"Green\"}");
		t.insert("{ \"First\" : \"Greg\" , \"Last\" : \"Allan\"}");
//		 t.display(2);
		 System.out.println(Arrays.toString(t.getAllElements()));
//		t.insert("{ \"First\" : \"Bob\" , \"Last\" : \"Cortez\"}");
//		t.insert("{ \"First\" : \"Quan\" , \"Last\" : \"Chang\"}");
//		t.insert("{ \"First\" : \"Ceya\" , \"Last\" : \"Mateo\"}");
//	//	t.delete("{ \"First\" : \"David\" , \"Last\" : \"Smith\"}");
//	//	t.delete("{ \"First\" : \"Allan\" , \"Last\" : \"Green\"}");
//		t.insert("{ \"First\" : \"last\" , \"Last\" : \"first\"}");
//		t.insert("{ \"First\" : \"Bale\" , \"Last\" : \"Zern\"}");
//		t.insert("{ \"First\" : \"Arix\" , \"Last\" : \"Miu\"}");
//		t.insert("{ \"First\" : \"Cron\" , \"Last\" : \"Koradou\"}");
//		t.insert("{ \"First\" : \"Amon\" , \"Last\" : \"Chamra\"}");
//		t.insert("{ \"First\" : \"Zechs\" , \"Last\" : \"Seumic\"}");
//	//	t.delete("{ \"First\" : \"Cron\" , \"Last\" : \"Koradou\"}");
//	//	t.delete("{ \"First\" : \"Greg\" , \"Last\" : \"Allan\"}");
//		t.insert("{ \"First\" : \"Cander\" , \"Last\" : \"Jhcor\"}");
	      
	      
	      System.out.println("Before DELETE");
	      t.display(2);
	   //   System.out.println(t);
	      
//	      t.delete("{ \"First\" : \"David\" , \"Last\" : \"Smith\"}");
//	      System.out.println(" ");
//	      
//	      System.out.println("After DELETE");
	      
//	      t.display(root, "");
	    //  System.out.println(t);
	}
}
	


	


