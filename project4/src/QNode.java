

public class QNode {
	private QNode parent;
	private QNode leftChild;
	private QNode rightSib;
	private int vertex;
	private int degree;
	private int distance;
	
	
	public QNode(int vertex, int degree, int distance, QNode parent, 
			QNode leftChild, QNode rightSib) {
		this.parent    = parent;
		this.leftChild = leftChild;
		this.rightSib  = rightSib;
		this.vertex    = vertex;
		this.degree    = degree;
		this.distance  = distance;
		
	}
	
	public void setVertex(int vertex) {
		this.vertex = vertex;
	}
	
	public void setDegree(int degree) {
		this.degree = degree;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public void setParent(QNode parent) {
		this.parent = parent;
	}
	
	public void setLeftChild(QNode leftChild) {
		this.leftChild = leftChild;
	}
	
	public void setRightSib(QNode rightSib) {
		this.rightSib = rightSib;
	}
	
	public void incrementDegree() {
		degree++;
	}
	
	public int getVertex() {
		return vertex;
	}
	
	public int getDegree() {
		return degree;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public QNode getParent() {
		return parent;
	}
	
	public QNode getLeftChild() {
		return leftChild;
	}
	
	public QNode getRightSib() {
		return rightSib;
	}
}
