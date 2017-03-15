
public class BinaryTree<AnyType extends Number & Comparable <?super AnyType>> {
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
		if(node.val.compareTo(elem)>0){
			if(node.left == null){
				node.left = new Node<AnyType>(elem);
			}
			else{
			insert(node.left, elem);
			}
		}
		else{
			if(node.right == null){
				node.right = new Node<AnyType>(elem);
			}
			else{
			insert(node.right, elem);
			}
		}
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
		int compteur1 = 0;
		int compteur2 = 0;
		
			if(tree.left != null){
				compteur1++;
				compteur1+=getHauteur(tree.left);
			}
			if(tree.right != null){
				compteur2++;
				compteur2+=getHauteur(tree.right);
			}
			if(compteur1 <= compteur2)
				return compteur2;
			else return compteur1;
	}	
	
	@SuppressWarnings("unchecked")
	private String printPrefixe(Node<AnyType> node) {
		// COMPLETER
		String chemin = node.val.toString();
		if(node.left != null){
			chemin += " " + printPrefixe(node.left);
		}
		if (node.right != null){
			chemin += " " + printPrefixe(node.right);
		}
		return chemin;
	}

	@SuppressWarnings("unchecked")
	private String printInfixe(Node<AnyType> node) {
		// COMPLETER
		String chemin = "";
		if(node.left != null){
			chemin += printInfixe(node.left);
		}
		chemin += node.val.toString() + " ";
		if (node.right != null){
			chemin += printInfixe(node.right);
		}
		return chemin;
	}
	
	@SuppressWarnings("unchecked")
	private String printPostfixe(Node<AnyType> node) {
		// COMPLETER
		String chemin = "";
		if(node.left != null){
			chemin += printPostfixe(node.left) + " ";
		}
		if (node.right != null){
			chemin += printPostfixe(node.right) + " ";
		}
		return chemin+= node.val.toString();
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

