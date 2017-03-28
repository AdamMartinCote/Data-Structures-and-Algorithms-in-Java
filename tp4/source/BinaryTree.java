
public class BinaryTree<AnyType extends Number & Comparable <?super AnyType>> {
	private Node<AnyType> root = null;

	// insert element in arbre 
	public void insert (AnyType elem) {
	        if(root==null){
	        	this.root= new Node<AnyType>(elem);
	        }else	
	        	insert(root, elem);      
	}
	
	
	@SuppressWarnings("unchecked")
	private void insert(Node<AnyType> node, AnyType elem) {
		// Si la valeur est de la node est plus grande que l'élément 
		// On ajoute a gauche, sinon a droite
		if(node.val.compareTo(elem) > 0){
			//Si l'enfant est null on ajoute une node avec l'élément comme valeur
			if(node.left == null){
				node.left = new Node<AnyType>(elem);
			}
			else{
			//Sinon on utilise insert récursivement
			insert(node.left, elem);
			}
		}
		else{
			//Si l'enfant est null on ajoute une node avec l'élément comme valeur
			if(node.right == null){
				node.right = new Node<AnyType>(elem);
			}
			else{
				//Sinon on utilise insert récursivement
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
		// On créé 2 competeur, un pour la gauche et l'autre pour la droite
		int compteur1 = 0;
		int compteur2 = 0;

		//Si la node a gauche n'est pas nulle on incrémente le compteur de gauche
		//et on utilise getHauteur récursivement
		if(tree.left != null){
			compteur1++;
			compteur1+=getHauteur(tree.left);
		}
		//Si la node a droite n'est pas nulle on incrémente le compteur de droite
		//et on utilise getHauteur récursivement
		if(tree.right != null){
			compteur2++;
			compteur2+=getHauteur(tree.right);
		}
		//On détermine et on retourne le plus grand compteur
		if(compteur1 <= compteur2)
			return compteur2;
		else return compteur1;
	}	
	
	@SuppressWarnings("unchecked")
	private String printPrefixe(Node<AnyType> node) {
		// On met la valeur de la node dans un string
		String chemin = node.val.toString();
		//si la node de gauche n'est pas nulle on utilise la fonction printPrefixe
		//recursivement et on additionne sa valeur de retour au chemin
		if(node.left != null){
			chemin += " " + printPrefixe(node.left);
		}
		//si la node de droite n'est pas nulle on utilise la fonction printPrefixe
		//recursivement et on additionne sa valeur de retour au chemin
		if (node.right != null){
			chemin += " " + printPrefixe(node.right);
		}
		return chemin;
	}

	@SuppressWarnings("unchecked")
	private String printInfixe(Node<AnyType> node) {
		// Déclaration de la valeur de retour vide
		String chemin = "";
		//si la node de gauche n'est pas nulle on utilise la fonction printInfixe
		//recursivement et on additionne sa valeur de retour au chemin
		if(node.left != null){
			chemin += printInfixe(node.left);
		}
		//on additionne au chemin la valeur de la node
		chemin += node.val.toString() + " ";
		//si la node de droite n'est pas nulle on utilise la fonction printInfixe
		//recursivement et on additionne sa valeur de retour au chemin
		if (node.right != null){
			chemin += printInfixe(node.right);
		}
		return chemin;
	}
	
	@SuppressWarnings("unchecked")
	private String printPostfixe(Node<AnyType> node) {
		// Déclaration de la valeur de retour vide
		String chemin = "";
		//si la node de gauche n'est pas nulle on utilise la fonction printPostfixe
		//recursivement et on additionne sa valeur de retour au chemin
		if(node.left != null){
			chemin += printPostfixe(node.left) + " ";
		}
		//si la node de droite n'est pas nulle on utilise la fonction printPostfixe
		//recursivement et on additionne sa valeur de retour au chemin
		if (node.right != null){
			chemin += printPostfixe(node.right) + " ";
		}
		//Finalement on ajoute la valeur de la node au chemin et on le retourne
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

