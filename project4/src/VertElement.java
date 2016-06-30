

public class VertElement {
	
	private int distance;
	private int route;
	private boolean known;
	
	public VertElement(int distance, int route, boolean known) {
		this.distance = distance;
		this.route 	  = route;
		this.known    = known;
	}
	
	
	public void setKnown(boolean known) {
		this.known = known;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public boolean getKnown() {
		return known;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public void setRoute(int route) {
		this.route = route;
	}
	
	public int getRoute() {
		return route;
	}
	
	
	
	
}
