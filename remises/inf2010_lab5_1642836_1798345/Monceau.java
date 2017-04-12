
import java.util.ArrayList;

public class Monceau {
    ArrayList<Node> arbres;

    public Monceau() {
        arbres = new ArrayList<Node>();
    }
    
    public void fusion(Monceau autre) {

    	Monceau monceauFusionne = new Monceau();
    	ArrayList<Node> nouvelArbre = new ArrayList<Node>();
    			
    	
    	ArrayList<Node> fusionList;
    	Node retenue = null;
    	int j = 0;
    	while (j < arbres.size() || j < autre.arbres.size() || retenue != null){
    		fusionList = new ArrayList<Node>();
    		
    		// get all trees of order 'j' in an arrayList
	    	for(Node n : arbres)
				if (n.ordre == j)
					fusionList.add(n);
	    	for(Node n : autre.arbres)
				if (n.ordre == j)
					fusionList.add(n);
	    	if (retenue != null){
	    		fusionList.add(retenue);
	    		retenue = null;
	    	}
	    	

    		// fusion the arrayList according to 3 scenarios
    		if (fusionList.size() == 0)
    			j++;
    		else if(fusionList.size() == 1){
    			nouvelArbre.add(fusionList.get(0));
    			j++;
    		}
    		else if(fusionList.size() >= 2){
    			Node newNode = fusionList.get(0);
    			try {
    				newNode = newNode.fusion(fusionList.get(1));
    			}catch(Throwable e){
    				System.out.println("tentative de fusion infructueuse; erreur: " + e.getMessage());
    			}
    			nouvelArbre.add(newNode);
    			if(fusionList.size() == 3)
    				retenue = fusionList.get(2);
    			j++;
    		}    			

    	}
    	arbres = nouvelArbre;	
    }
    
    public void insert(int val) {
    	Monceau heapToMerge = new Monceau();
    	heapToMerge.arbres.add(new Node(val));
    	fusion(heapToMerge);    	
    }
    
    /**
     * Delete a value from the heap
     * 
     * @param val to delete
     * @return true if at least one value occurrence of the value was found and successfully deleted
     */
    public boolean delete (int val) {
    	boolean deleteOccured = false;
    	Node toDelete = null;
    	do{
    		for (Node n : arbres){
    			toDelete = n.findValue(val);
    			if(toDelete != null){
    				//System.out.println(toBeDeleted.getVal());
    				ArrayList<Node> subtrees = toDelete.delete();
    				deleteOccured = true;
    				// merge each of them
    				for (Node nn : subtrees){
    					n.print();
    					Monceau m = new Monceau();
    					m.arbres.add(nn);
    					fusion(m);
    				}
        		}
    		}
    		

    	} while(toDelete != null);

    	return deleteOccured;
    }
    
    public void print() {
    	for (Node node : arbres){
    		node.print();
    		System.out.println();
    	}
    }
    
}
