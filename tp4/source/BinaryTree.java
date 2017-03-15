
public class BinaryTree<AnyType> {
	private Node<AnyType> root = null; // Racine de l'arbre

	// insert element in arbre 
	public void insert (AnyType elem) {
	        if(root==null){
	        	this.root= new Node<AnyType>(elem);
	        }else	
	        	insert(root, elem);      
	}
	
	
	
	@SuppressWarnings("unchecked")
	private void insert(Node<AnyType> node, AnyType elem) {
		// A completer
	}
    
	
	public int getHauteur () {
		return this.getHauteur(this.root);
	}
 
	public String printPrefixe() {
		return "{ " + this.printPrefixe(this.root) + " }";
	}
	public String printInFixe() {
		return "{ " + this.printInfixe(this.root) + " }";
	}
	
	public String printPostFixe() {
		return "{ " + this.printPostfixe(this.root) + " }";
	}
	
	private int getHauteur(Node<AnyType> tree) {
		// A completer 
		
	}	
	
	@SuppressWarnings("unchecked")
	private String printPrefixe(Node<AnyType> node) {
		// COMPLETER
	}

	@SuppressWarnings("unchecked")
	private String printInfixe(Node<AnyType> node) {
		// COMPLETER
	}
	
	@SuppressWarnings("unchecked")
	private String printPostfixe(Node<AnyType> node) {
		// COMPLETER
	}
	
	private class Node<AnyType> {
		AnyType val; 	// Valeur du noeud
		Node right; 	// fils droite
		Node left; 		// fils gauche

		public Node (AnyType val) {
			this.val = val;
			right = null;
			left = null;
		}

	}


}

