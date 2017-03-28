
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

        // à compléter
        // verifier que les arbres ont le meme ordre
        // verifier que les noeuds sont bien des racines (parent == null)
        // respecter la condition d'ordre du monceau (val parent < val enfant)
        return null;
    }

    private void moveUp() {
        // à compléter
    }

    public ArrayList<Node> delete() {
        // à compléter
        return null;
    }

    public void print(String tabulation) {
        // à compléter
    }
    
    public Node findValue(int valeur) {
        return findValue(this, valeur);
    }
    
    public Node findValue (Node n, int valeur) {
        // à compléter
    }
    
}
