import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Graph {

	private List<Node> nodes; // Noeuds
	private List<Edge> edges; // Les arcs
	static final double inf = 99999;
	public Graph() {
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
	}
	
	
	public void readFromFile(String filePath, String separtor) {

		File file = new File(filePath);
		try{
			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			
			List<String> listOfLines = new ArrayList<String>();
			String line;
			while ((line = br.readLine()) != null){
				listOfLines.add(line);
			}
					
			// generate nodes with names from first line, id are given sequentially (0, 1, 2...)
			String[] tokens = listOfLines.get(0).split(separtor);
			int id = 0;
			for (String s : tokens){
				nodes.add(new Node(id,s));
				++id;
			}
			
			// the following lines represents the out edges of each Node created
			// iteration from here = an edge source
			// values in the line = an edge destination and price

			for (int i = 1; !listOfLines.get(i).equals("") && i < listOfLines.size(); ++i){
				tokens = listOfLines.get(i).split(separtor);
				
				
				for (int j = 0; j < tokens.length; ++j){
					if (!tokens[j].equals("inf") && !tokens[j].equals("0")){
						edges.add(new Edge(nodes.get(i - 1), nodes.get(j),Double.parseDouble(tokens[j])));
					}
				}
			}
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		catch(Throwable e){
			System.out.println(e.toString());
		}

	}
	
	public List<Edge> getOutEdges(Node source) {
		List<Edge> outEdges = new LinkedList<Edge>(); 
		for(Edge edge: edges){
			if(edge.getSource() == source)
				outEdges.add(edge);
		}
		
		return outEdges;	
	}
	
	public List<Edge> getInEdges(Node dest) {
		List<Edge> inEdges = new LinkedList<Edge>(); 
		for(Edge edge: edges){
			if(edge.getDestination() == dest)
				inEdges.add(edge);
		}
			
		return inEdges;		
	}
	// Accesseurs 
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	public List<Edge> getEdges() {
		return edges;
	}
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	public Node getNodeByName(String name){
		for (Node node : nodes) {
			if(node.getName().equals(name)){
				return node;
			}
		}
		return null;
	}
}
