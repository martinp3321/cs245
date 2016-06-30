

public class BinomialQueue {

	private QNode[] queue;
	public  QNode head;
	private QNode subTree;
	
	public BinomialQueue(int size) {
		queue   = new QNode[size];
		head    = null;
		subTree = null;
	}
	
	
	public void printQueue() {
		
		System.out.println("Queue: ");
		for (int vertex = 0; vertex < queue.length; vertex++) {
			if (queue[vertex] != null) {
				System.out.println("Value: "+vertex + " Priority: " + queue[vertex].getDistance());
			} else {
				System.out.println("Value: "+vertex + " Priority: " + queue[vertex]);
			}
			
		}
		System.out.println();
	}
	
	
	public void printHeap(QNode node) {
		
		if (node != null) {
			System.out.println(node.getVertex() + ": " + node.getDistance());
			printHeap(node.getLeftChild());
			printHeap(node.getRightSib());
		}
	}
	
	
	
	public void insertElem(int vertex, int priority) {
		
		QNode node = new QNode(vertex, 0, priority, null, null, null);

		if (head == null) {
			head = node;
		} else {
			node.setRightSib(head);
			head = node;
			QNode tmp = head;
			
			while (tmp != null) {
				if (tmp.getRightSib() != null ) {
					if (tmp.getDegree() == tmp.getRightSib().getDegree()) {
						tmp = Merge(tmp, tmp.getRightSib()); /* Merges trees together */
						head = tmp;
					} else {
						tmp = tmp.getRightSib();
					}
				} else {
					break;
				}
			}
		}
		
		
		queue[vertex] = node;
	}
	
	private QNode Merge(QNode node1, QNode node2) {
		
		QNode tmp;
		
		if (node1.getDistance() < node2.getDistance()) { 
			node2.setRightSib(node1.getLeftChild());
			node1.setLeftChild(node2);
			node2.setParent(node1);
			tmp = node1;
		} else { 
			node1.setRightSib(node2.getLeftChild());
			node2.setLeftChild(node1);
			node1.setParent(node2);
			tmp = node2;
		}
		
		
		tmp.incrementDegree();
		return tmp;
	}
	
	
	
	public int removeSmallest() {
		
		
		if (head.getLeftChild() == null && head.getRightSib() == null) {
			int min = head.getVertex();
			queue[min] = null;
			head = null;
			return min;
		}

		if (head.getLeftChild() != null && head.getRightSib() == null) { 
			int min = head.getVertex();
			queue[min] = null;
			QNode tmp = head.getLeftChild();
			subTree = tmp;
			head = null;
			tmp.setParent(null);
			
			while (tmp.getRightSib() != null) {
				Reverse(tmp, tmp.getRightSib());
				tmp.setParent(null);
			}
			
			head = subTree;
			return min;
		}		
		
		QNode minNode = head;
		QNode tmp = head.getRightSib();

		while (tmp != null) {
			if (tmp.getDistance() < minNode.getDistance())
				minNode = tmp;
			tmp = tmp.getRightSib();
		}

		int min = minNode.getVertex();
		queue[min] = null;
		tmp = head;

		if (min == head.getVertex() && head.getLeftChild() == null) {
			head = head.getRightSib();
			return min;
		}

		if (min == head.getVertex() && head.getLeftChild() != null) {
			head.getLeftChild().setRightSib(head.getRightSib());
			head = head.getLeftChild();
			head.setParent(null);
			return min;
		}

		while (tmp.getRightSib() != null) {
			if (tmp.getRightSib().getVertex() != min) {
				tmp = tmp.getRightSib();
			} else {
				break;
			}
		}
		tmp.setRightSib(null);
		
		
		
		tmp = minNode.getLeftChild();
		subTree = tmp;
		
		if (tmp != null) { 
			tmp.setParent(null);
			
			while (tmp.getRightSib() != null) {
				Reverse(tmp, tmp.getRightSib()); 
				tmp.setParent(null);
			}
			
			
			Reorder(head, subTree);
			
			tmp = head;
			QNode tmp2 = head;
	
			while (tmp != null) {
				if (tmp.getRightSib() != null ) {
					if (tmp.getDegree() == tmp.getRightSib().getDegree()) {
						tmp = Merge(tmp, tmp.getRightSib()); 
						tmp2.setRightSib(tmp);
					} else {
						tmp2 = tmp;
						tmp = tmp.getRightSib();
					}
				} else {
					break;
				}
			}
		}
		
		subTree = null;
		return min;
	}
	
	
	private void Reverse(QNode nO, QNode nT) {

		nO.setRightSib(nT.getRightSib());
		nT.setRightSib(subTree);
		subTree = nT;
	}
	
	
	private void Reorder(QNode nO, QNode nT) {
		
		QNode tmp = nO;
		
		while (tmp != null) {
			if (nT.getDegree() <= tmp.getDegree()) {
				QNode tmp2 = tmp.getRightSib();
				tmp.setRightSib(nT.getRightSib());
				nT.setRightSib(tmp);
				tmp = tmp2;
			} else {
				break;
			}
		}
		head = nT;
	}
	

	public void decreaseKey(int vertex, int new_priority) {
		
		queue[vertex].setDistance(new_priority);
		
		QNode tmp = queue[vertex];
		int tmpDistance, tmpVertex;
		tmpDistance = tmpVertex = 0;
		
		while (tmp.getParent() != null) {
			
			if (tmp.getParent().getDistance() > new_priority) {
				tmpVertex   = vertex;
				tmpDistance = new_priority;
				tmp.setVertex(tmp.getParent().getVertex());
				tmp.setDistance(tmp.getParent().getDistance());
				queue[tmp.getVertex()] = tmp; 
				tmp.getParent().setVertex(tmpVertex);
				tmp.getParent().setDistance(tmpDistance);
				tmp = tmp.getParent();
				queue[vertex] = tmp; 
			} else {
				break;
			}
		}
	}


	
	
}
