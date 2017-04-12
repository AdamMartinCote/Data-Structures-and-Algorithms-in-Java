
import java.util.ArrayList;

public class Node {

    public int ordre;
    public Node parent;

    private int valeur;
    private ArrayList<Node> enfants;

    public Node(int valeur) {
        this.valeur = valeur;
        this.parent = null;
        ordre = 0;
        enfants = new ArrayList<Node>();
    }

    public Node(int valeur, Node parent) {
    	this.valeur = valeur;
    	this.parent = parent;
        ordre = parent.ordre - 1;
        
        enfants = new ArrayList<Node>();
    }

    public int getVal() {
        return valeur;
    }

    public ArrayList<Node> getEnfants() {
        return enfants;
    }

    public void addEnfant(Node enfant) {
    	increaseOrderRecursively();
        enfants.add(enfant);

    }
    
    private void increaseOrderRecursively(){
    	++ordre;
    	for (Node aChild : enfants){
    		if(aChild != null)
    			aChild.increaseOrderRecursively();
    	}
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

    /**
     * Merges two subtrees, if their orders are the same
     * the subtree with the highest root value is appended to the other
     * 
     * @param autre
     * @return the merged tree
     * @throws DifferentOrderTrees
     */
    public Node fusion(Node autre) throws DifferentOrderTrees {
    	// verifier que les arbres ont le meme ordre
    	if(autre.ordre != ordre)
    		throw new DifferentOrderTrees();
	
    	if(this.parent == null && autre.parent == null && autre.valeur >= this.valeur){
    		this.addEnfant(autre);
    		autre.parent = this;
    		return this;
    	}
    	else if(this.parent == null && autre.parent == null && autre.valeur <= this.valeur){
    		autre.addEnfant(this);
    		this.parent = autre;
    		return autre;
    	}
    	else
    		return null;
    }

    /**
     * Switch the node position with its immediate parent, by swapping their values
     */
    private void moveUp(){
    	if (parent != null){
    		int tmpValue = parent.getVal();
    		parent.valeur = valeur;
    		valeur = tmpValue;
    		parent.moveUp();
    	}
    }
    
    /**
     * Removes this node from the tree by moving it to the root and cutting it
     * the childrens of that root node are returned in an array
     * 
     * @return an array of subtrees
     */
    public ArrayList<Node> delete() {
    	
    	enfants.clear();
    	moveUp();
    	Node root = this;
    	while (root.parent != null)
    		root = root.parent;
    	for (Node n : root.enfants)
    		n.parent =null;
    	return root.enfants;
    	
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
        
        Node ret = null;
        for(Node node : n.enfants){
        	if (node.getVal() <= valeur)
        		ret = findValue(node, valeur);
        	if (ret != null)
        		return ret;
        }
        return null;
    }

    
}
