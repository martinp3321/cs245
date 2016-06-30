

public class DTable {

	private VertElement[] vertices;
	private BinomialQueue queue;
	private AdjacencyList graph;
	private String route;
	
	public DTable(int length, AdjacencyList graph) {
		vertices   = new VertElement[length];
		queue      = new BinomialQueue(vertices.length);
		this.graph = graph;
	}
	
	
	public void Dijkstra(int start) {
		
		
		for (int i = 0; i < vertices.length; i++) {
			VertElement vertex;
			if (i == start) {
				vertex = new VertElement(0, -1, false);
			} else {
				vertex = new VertElement(Integer.MAX_VALUE, -1, false);	
			}
			vertices[i] = vertex;
		}
		vertices[start].setDistance(0);
		
		queue.insertElem(start, 0);
		
		
		for (int vertex = 0; vertex < vertices.length; vertex++) {	
			if (vertex == start) {
				continue;
			} else {
				int distance = vertices[vertex].getDistance();
				queue.insertElem(vertex, distance);
			}
		}

		while (queue.head != null) {
			int vertex = queue.removeSmallest();
			
			
			for (Edge edge = graph.getEdge(vertex); edge != null; edge = edge.getNext()) {
				if (vertices[edge.getNeighbor()].getDistance() > vertices[vertex].getDistance() + edge.getCost()) {
					int newDistance = vertices[vertex].getDistance() + edge.getCost();
					vertices[edge.getNeighbor()].setDistance(newDistance);
					queue.decreaseKey(edge.getNeighbor(), newDistance);
					vertices[edge.getNeighbor()].setRoute(vertex);
				}
			}
		}
	}
	
	
	public void printTable() {
		
		for (int i = 0; i < vertices.length; i++) {
			System.out.println(i + ": " + "distance: " + vertices[i].getDistance() + 
							   " route: " + vertices[i].getRoute() + 
							   " known: " + vertices[i].getKnown());
		}
	}
	
	
	public void printShortestRoute(int start, String[] cities) {
		
		for (int i = 0; i < cities.length; i++) {
			Route head = new Route(cities[i], null);
			System.out.print(cities[i] + "  " + vertices[i].getDistance() + ": ");
		
			int tmp = i;
			while (tmp != start) {
				tmp = vertices[tmp].getRoute();
				Route node = new Route(cities[tmp], head);
				head = node;
			}
			
			
			Route route = head;
			while (route != null) {
				System.out.print(route.getCity() + " ");
				route = route.getNext();
			}
			System.out.println();
		}
	}
}
