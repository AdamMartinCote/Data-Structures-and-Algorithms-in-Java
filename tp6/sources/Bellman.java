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
	
	public void shortestPath(){
		
		// init
		int k = 1;
		int n = graph.getNodes().size();
		boolean hasChangedSinceLastIteration = true;
		Double alternativePathLength;
		
		piTable = new LinkedList<Vector<Double>>();
		rTable = new LinkedList<Vector<Integer>>();

		// set first line of both tables
		piTable.add(new Vector<Double>(n));
		rTable.add(new Vector<Integer>(n));
		for (int i = 0; i < n; ++i){
			piTable.get(0).add(Graph.inf);
			rTable.get(0).add((int)Graph.inf);
		}
		piTable.get(0).set(0, 0.0); // set S
		
		
		while(k <= n && hasChangedSinceLastIteration){
			hasChangedSinceLastIteration = false;
			
			piTable.add(new Vector<Double>(n));
			rTable.add(new Vector<Integer>(n));
			for (int i = 0; i < n; ++i){
				piTable.get(k).add(piTable.get(k - 1).get(i));
				rTable.get(k).add(rTable.get(k - 1).get(i));
			}
			
			// iterate over this row of piTable
			for(int nodeIndex = 0; nodeIndex < n; ++nodeIndex){
				
				// iterate over each alternative source for that node
				// pi^k(x):= min{ pi^k-1(x), min {pi^k(y) + dxy }};
				for (Edge edge : graph.getInEdges(graph.getNodes().get(nodeIndex))){
					alternativePathLength = (edge.getDistance() + piTable.get(k - 1).get(edge.getSource().getId()));
					if (alternativePathLength < piTable.get(k - 1).get(nodeIndex)){
						piTable.get(k).set(nodeIndex, alternativePathLength);
						rTable.get(k).set(nodeIndex, edge.getSource().getId());
						hasChangedSinceLastIteration = true;
					}
				}
			}
			
			++k;
		}
	}
		
	public void  diplayShortestPaths() {
		Stack<Node> path=new Stack<Node>();
		boolean circuitNeg = false;
		Integer sourceId = 0;
		
		// pour les n noeuds
		Vector<Integer> lastRowOfRTable = rTable.get(rTable.size() - 1);
		
		System.out.println("\n=> Les chemins sont : \n");
		for (Node node : graph.getNodes()){
			if (node.getId() != 0){
				
				// build stack
				sourceId = node.getId();
				do{
					path.push(graph.getNodes().get(sourceId));
					sourceId = lastRowOfRTable.get(sourceId);
					for (Node n : path){
						if ( n.getId() == sourceId){
							circuitNeg = true;
						}
					}
				}while (sourceId != 0 && !circuitNeg);
				
				if(!circuitNeg){
					System.out.print("[S - " + node.getName() + "] ");
					System.out.print(piTable.get(piTable.size() - 1).get(node.getId()));
					System.out.print(" : S ");
					while(!path.isEmpty())
						System.out.print("-> " + path.pop().getName());
					System.out.println();
				}
				else
					break;
			}
		}
		if(circuitNeg){
			System.out.println("==> le graphe contient un circuit négatif :");
			
			System.out.print("[" + graph.getNodes().get(sourceId).getName() + " - " 
					+ graph.getNodes().get(sourceId).getName() + "] : " 
					+ graph.getNodes().get(sourceId).getName());
			while(!path.isEmpty())
				System.out.print(" -> " + path.pop().getName());
		}
	}

	public void displayTables() {
		System.out.println("<< PITable >>:");
		System.out.println("k\t:\tS\tB\tC\tD\tE\tF");
		for(int i = 0; i < piTable.size(); i++){
			System.out.print(i+ "\t:\t");
			for(int j = 0; j < piTable.get(i).size(); j++){
				if(piTable.get(i).get(j) < 1000)
					System.out.print(piTable.get(i).get(j) + "\t");
				else System.out.print("inf\t");
			}
			System.out.println();
		}
		
		System.out.println("<< RTable >>:");
		System.out.println("k\t:\tS\tB\tC\tD\tE\tF");
		for(int i = 0; i < rTable.size(); i++){
			System.out.print(i+ "\t:\t");
			for(int j = 0; j < rTable.get(i).size(); j++){
				if(rTable.get(i).get(j) != null)
					if(rTable.get(i).get(j) < graph.getNodes().size())
						System.out.print(graph.getNodes().get(rTable.get(i).get(j)).getName() + "\t");
					else System.out.print("-\t");
				else
					System.out.print("-  ");
			}
			System.out.println();
		}
	}
}


























