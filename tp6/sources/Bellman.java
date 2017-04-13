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
		boolean flag = false;
		int compteur = 0;
		
		//On crée deux nouveaux vecteurs
		Vector<Double> vectPi = new Vector<Double>();
		Vector<Integer> vectR = new Vector<Integer>();
		
		//On popule les vecteurs avec infini et null
		for(int i = 0; i < graph.getNodes().size(); i++){
			vectPi.add(Graph.inf);
			vectR.add(null);
		}
		
		//On ajoute les vecteurs
		piTable.add(vectPi);
		rTable.add(vectR);
		
		//Si ce n'est pas la première fois qu'on ajoute un vecteur
		if(piTable.size() != 1){
			//On évalue combien de valeurs sont égales dans les 2 derniers
			//vecteurs de piTable
			for(int i = 0; i < piTable.get(piTable.size()-1).size(); i++){
				if(piTable.get(piTable.size()-1).get(i) == piTable.get(piTable.size()-2).get(i))
					compteur++;
			}
			
			//Si on a pas atteint le nombre de nodes ou que les deux dernières lignes de piTable ne sont pas égales
			if(piTable.size() != graph.getNodes().size() && compteur != piTable.size()){
				//On vérifie quelles nodes
				for(int i = 0; i < graph.getNodes().size(); i++){
					for(int j = 0; j < graph.getInEdges(graph.getNodes().get(i)).size(); j++)
						if(graph.getOutEdges(graph.getNodes().get(i)).get(j).getSource() == sourceNode){
							piTable.get(piTable.size()).set(i, graph.getOutEdges(graph.getNodes().get(i)).get(j).getDistance());
							rTable.get(rTable.size()).set(i, graph.getOutEdges(graph.getNodes().get(i)).get(j).getSource().getId());
					}
				}
			}
		}
		else{
			piTable.get(0).set(0, 0.0);
		}
		for(int i = 0; i < rTable.get(rTable.size()).size(); i++)
			if(rTable.get(rTable.size()).get(i) != null)
				shortestPath();	
	}
	
	public void  diplayShortestPaths() {
		Stack<Node> path=new Stack<Node>();
		
		// pour les 6 noeuds
		
		int thisId = 0;
		for (Node n : graph.getNodes()){
			do{
				path.add(graph.getNodes().get((rTable.get(rTable.size() - 1)).get(thisId)));	// get the node it "comes from"
			} while (!path.peek().getName().equals("S"));
			
			// pop each node of the stack and print it
			while(!path.empty()){
				System.out.print(path.pop().getName() + " -> ");
			}
			System.out.println();
			
			++thisId;
		}
		
		
	}

	public void displayTables() {
		// A completer
		
		
	}
}
