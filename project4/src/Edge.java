

public class Edge {

	private String city;
	private int cost;
	private Edge next;
	private int neighbor;
	
	
	public Edge(String city, int neighbor, int cost, Edge next) {
		
		
		this.cost = cost;
		this.next = next;
		this.city = city;
		this.neighbor = neighbor;
		
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setNeighbor(int neighbor) {
		this.neighbor = neighbor;
	}
	
	public String getCity() {
		return city;
	}
	
	public int getNeighbor() {
		return neighbor;
	}
	
	public int getCost() {
		return cost;
	}
	
	public Edge getNext() {
		return next;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public void setNext(Edge next) {
		this.next = next;
	}
	
	
}
