

public class Route {

	private String city;
	private Route next;
	
	public Route(String city, Route next) {
		this.city = city;
		this.next = next;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setNext(Route next) {
		this.next = next;
	}
	
	public String getCity() {
		return city;
	}
	
	public Route getNext() {
		return next;
	}
}
