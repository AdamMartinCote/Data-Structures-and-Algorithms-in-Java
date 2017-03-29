
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
        ordre = 0;
        this.valeur = valeur;
        this.parent = parent;
        enfants = new ArrayList<Node>();
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

    private void moveUp() {
        Node parentSwap = parent.parent;
        ArrayList<Node> enfantsSwap = parent.getEnfants();

        parent.ordre++;
        parent.parent = this;
        parent.enfants = this.getEnfants();

        ordre--;
        parent = parentSwap;
        enfants = enfantsSwap;
    }

    public ArrayList<Node> delete() {
        while (this.parent != null)
            this.moveUp();

        for (Node i : getEnfants())
            i.parent = null;

        return getEnfants();
    }

    public void print(){
        print(this, "");
    }
    
    private void print(Node currentNode, String prefix) {
    	if (currentNode == null) return;
        System.out.println(prefix + "|___" + currentNode.valeur);

        for(int i = 0 ; i < currentNode.getEnfants().size(); i++){
            if(currentNode.getEnfants().get(i) != null)
            	print(currentNode.getEnfants().get(i), "    " + prefix);
        }
    }

    public Node findValue(int valeur) {

        return findValue(this, valeur);
    }
    
    public Node findValue (Node n, int valeur) {
        if (n.valeur == valeur) return n;

        Node ret;
        for (int i = 0 ; i < n.getEnfants().size(); i++){
            if (n.getEnfants().get(i).valeur < valeur) {
                ret = findValue(n.getEnfants().get(i), valeur);
                if (ret != null) return ret;
            }
        }
        return null;
    }

    
}
