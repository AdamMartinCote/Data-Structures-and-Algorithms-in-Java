import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;


public class Bellman {
	private Graph graph;
	private Node sourceNode;
	private List<Vector<Double>> piTable;
	private List<Vector<Integer>> rTable;
	
	public Bellman (Graph g) {
		this.graph = g;
	}
	
	public void setSourceNode(Node source) {
		this.sourceNode = source;
	}
	
	public void shortestPath() {
		// completer
	
	}
	
	public void  diplayShortestPaths() {
		Stack<Node> path=new Stack<Node>();
		// A completer	
		
	}

	public void displayTables() {
		// A completer
		
	}
}
