
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dijkstra {

	public String[] cities;
	public BinomialQueue queue;
	public AdjacencyList graph;
	public DTable dTable;
	public HashTable table;
	
	
	public Dijkstra() {
		
	}
	
	
	public void getNumCities(String[] args) {
		BufferedReader input;
		String nextLine;
		int count = 0;
		
		try {
			input = new BufferedReader(new FileReader(args[0]));
			nextLine = input.readLine();
			
			while (nextLine.compareTo(".") != 0) {
				count++;
				nextLine = input.readLine();
			}
		} catch (IOException e) {
			System.out.println("File Error");
		}
		
		
		cities = new String[count];
	}
	
	
	public void printCities() {
		for (int vertex = 0; vertex < cities.length; vertex++)
			System.out.println(cities[vertex]);
	}
	
	
	public void readFile(String[] args) {
		BufferedReader input;
		String start, finish, nextLine;
		int cost;
		int vertex = 0;

		try {
			input = new BufferedReader(new FileReader(args[0]));
			nextLine = input.readLine();
			while (nextLine.compareTo(".") != 0) {
				cities[vertex++] = nextLine;
				nextLine = input.readLine();
			}

			
			makeTable();
			
			graph = new AdjacencyList(cities.length);
			
			nextLine = input.readLine(); // Reads '.'
			while (nextLine != null) {
				start    = nextLine;
				nextLine = input.readLine();
				finish   = nextLine;
				nextLine = input.readLine();
				cost     = Integer.valueOf(nextLine).intValue();
				nextLine = input.readLine();
				makeList(start, finish, cost);
			}
		} catch (IOException e) {
			System.out.println("Error");
		}
	}
	
	
	public void Dijkstra(int start) {
		
		dTable = new DTable(cities.length, graph);
		
		dTable.Dijkstra(start);
	}

	
	public void printDTable() {
		
		dTable.printTable();
	}
	
	
	public void printPaths(int start) {
		
		System.out.println("Shortest\n");
		dTable.printShortestRoute(start, cities);
	}
	
	
	private void makeTable() {
		
		table = new HashTable(cities.length);
		
		for (int vertex = 0; vertex < cities.length; vertex++) {
			table.Hash(cities[vertex], vertex, false);
		}
	}
	
	
	private void makeList(String start, String finish, int cost) {
		int vertex 	 = table.getVertex(start);
		int neighbor = table.getVertex(finish);
		
		graph.makeList(finish, vertex, neighbor, cost);
	}
	
	
	public void printList() {
		System.out.println("Original");
		
		for (int vertex = 0; vertex < cities.length; vertex++) {
			graph.printList(cities[vertex], vertex);
		}
		
		System.out.println();
	}
	
	
	
	
	
	public static void main(String[] args) {
	
		Dijkstra d = new Dijkstra();
		int start = 0;
		
		if (args.length != 1) {
			System.out.println(" 1 input file argument needed.");
		} else {
			d.getNumCities(args);
			d.readFile(args);
			d.printList();
			d.Dijkstra(start);
			d.printPaths(start);
		}
	}
}
