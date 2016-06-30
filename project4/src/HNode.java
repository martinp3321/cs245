
public class HNode {

	private String city;
	private int vertex;
	HNode next;
	
	public HNode(String city, int vertex, HNode next) {
		this.city = city;
		this.vertex = vertex;
		this.next = next;
	}
	
	public HNode(String city, int vertex) {
		this.city = city;
		this.vertex = vertex;
		this.next = next;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setVertex(int vertex) {
		this.vertex = vertex;
	}
	
	public void setNext(HNode next) {
		this.next = next;
	}
	
	public String getCity() {
		return city;
	}
	
	public int getVertex() {
		return vertex;
	}
	
	public HNode getNext() {
		return next;
	}
}
