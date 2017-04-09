
import java.util.ArrayList;

public class Node {

    public int ordre;
    public Node parent;

    private int valeur;
    private ArrayList<Node> enfants;

    public Node(int valeur) {
        this.valeur = valeur;
        ordre = 0;
        this.parent = null;
        enfants = new ArrayList<Node>();
    }

    public Node(int valeur, Node parent) {
        ordre = parent.ordre + 1;
        this.valeur = valeur;
        this.parent = parent;
        enfants = new ArrayList<Node>();
        
        parent.addEnfant(this);
    }

    public int getVal() {
        return valeur;
    }

    public ArrayList<Node> getEnfants() {
        return enfants;
    }

    public void addEnfant(Node enfant) {
        enfants.add(enfant);
    }

    public boolean removeEnfant(Node enfant) {
        if (enfants.contains(enfant)) {
            enfants.remove(enfant);
            return true;
        }
        return false;
    }

    public void removeEnfants(ArrayList<Node> enfants) {
        this.enfants.removeAll(enfants);
    }

    public Node fusion(Node autre) throws DifferentOrderTrees {
    	// verifier que les arbres ont le meme ordre
    	if(autre.ordre != ordre)
    		throw new DifferentOrderTrees();
	
    	if(this.parent == null && autre.parent == null && autre.valeur > this.valeur){
    		this.addEnfant(autre);
    		autre.parent = this;
    		return this;
    	}
    	else if(this.parent == null && autre.parent == null && autre.valeur < this.valeur){
    		autre.addEnfant(this);
    		this.parent = autre;
    		return autre;
    	}
    	else
    		return null;
    }

    /**
     * Switch the node position with its immediate parent, updating both node's order in the process
     * this method breaks the order of the heap
     * 
     * this function will never be called with parent == null
     */
    private void moveUp(){
    	
//    	ArrayList<Node> enfantsTMP = enfants;
//    	Node parentTMP = parent;
//    	Node grandParent = null;
//    	if(parent.parent != null)
//    		grandParent = parent.parent;
//    	parent = grandParent;
//    	enfants = new ArrayList<Node>();
//    	for (Node i : parentTMP.getEnfants()){
//    		if (i != this)
//    			enfants.add(i);
//    	}
//    	enfants.add(parentTMP);
//    	ordre--;
//    	
//    	if(grandParent != null){
//    		grandParent.addEnfant(this);
//    		for (Node i : grandParent.getEnfants()){
//    			if (i == parentTMP)
//    				i = null;
//    		}
//    	}
//    	
//    	parentTMP.parent = this;
//    	parentTMP.enfants = enfantsTMP;
//    	parentTMP.ordre++;
//    	
//    	System.out.println("------------");
//    	this.parent.print();
//    	parentTMP.print();
//    	System.out.println("------------");
    	
    	int tmpValue = parent.getVal();
    	parent.valeur = valeur;
    	valeur = tmpValue;

    }
    
//    public ArrayList<Node> delete() {
//    	
//    	return delete(this);
//    	
////        while (this.parent != null)
////            this.moveUp();
////
////        for (Node i : getEnfants())
////            i.parent = null;
////
////        return getEnfants();
//    }
    
    public ArrayList<Node> delete() {
    	
    	ArrayList<Node> returnValue;
    	if (this.parent == null) {
    		for (Node i : getEnfants())
    			i.parent = null;
	        return getEnfants();
    	}
    	else {
    		this.moveUp();
    		returnValue = this.parent.delete();
    	}
    	return returnValue;
    }

    public void print(){
        print(this, "");
    }
    
    private void print(Node currentNode, String prefix) {
    	if (currentNode == null) return;
        System.out.println(prefix + "|___" + currentNode.valeur + " (" + currentNode.ordre +")");

        for(int i = 0 ; i < currentNode.getEnfants().size(); i++){
            if(currentNode.getEnfants().get(i) != null)
            	print(currentNode.getEnfants().get(i), "    " + prefix);
        }
    }

    public Node findValue(int valeur) {

        return findValue(this, valeur);
    }
    
    public Node findValue (Node n, int valeur) {
        if (n.valeur == valeur) 
        	return n;

        Node ret;
        for (int i = 0 ; i < n.getEnfants().size(); i++){
            if (n.getEnfants().get(i).valeur <= valeur) {
                ret = findValue(n.getEnfants().get(i), valeur);
                if (ret != null) 
                	return ret;
            }
        }
        return null;
    }

    
}
