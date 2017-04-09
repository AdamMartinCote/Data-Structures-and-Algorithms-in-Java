import java.util.LinkedList;
import java.util.Queue;

public class RedBlackTree<T extends Comparable<? super T> > 
{
	enum ChildType{ left, right }
	private RBNode<T> root;  // Racine de l'arbre
   
   public RedBlackTree(){
       root = null;
   }
   
   public void printFancyTree(){
      printFancyTree( root, "", ChildType.right);
   }
   private void printFancyTree( RBNode<T> t, String prefix, ChildType myChildType){
      System.out.print( prefix + "|__"); // un | et trois _  
      if( t != null ){
         boolean isLeaf = (t.isNil()) || ( t.leftChild.isNil() && t.rightChild.isNil() );
         System.out.println( t );
         String _prefix = prefix;
         if( myChildType == ChildType.left )
            _prefix += "|   "; // un | et trois espaces
         else
            _prefix += "   " ; // trois espace
         if( !isLeaf ){
            printFancyTree( t.leftChild, _prefix, ChildType.left );
            printFancyTree( t.rightChild, _prefix, ChildType.right );
         }
      }
      else
         System.out.print("null\n");
   }
/*
 * recherche d'une cl�
 */
    public T find(int key){
      return find(root, key);
   }

   @SuppressWarnings("unchecked")
    private T find(RBNode<T> current, int key){
       if (current.value == null) return null;
	   if (current.value.equals(key))
		   return current.value;
	   else{
		   if (current.value.compareTo((T) Integer.valueOf(key)) > 0 && current.leftChild != null)
			   return find(current.leftChild, key);
		   else if (current.value.compareTo((T) Integer.valueOf(key)) < 0 && current.rightChild != null)
			   return find(current.rightChild, key);
		   else 
			   return null;
	   }
	 }
  /*
   * insertion d'une valeur 
   */
   public void insert(T val){
       insertNode( new RBNode<T>( val ) );
   }
   
   private void insertNode( RBNode<T> newNode ){ 
      if (root == null)  // Si arbre vide
          root = newNode;
      else{
         RBNode<T> position = root; // On se place a la racine     
         while( true ) // on insere le noeud (ABR standard)
         {
            int newKey = newNode.value.hashCode();
            int posKey = position.value.hashCode();
            if (newKey < posKey ){
               if ( position.leftChild.isNil()){
                  position.leftChild = newNode;
                  newNode.parent = position;
                  break;
               } 
               else 
                  position = position.leftChild;
            }else if ( newKey > posKey ){
               if ( position.rightChild.isNil()){
                  position.rightChild = newNode;
                  newNode.parent = position;
                  break;
               }
               else 
                  position = position.rightChild;
            }else // pas de doublons
               return;
         }
      }
      insertionCases( newNode );
   }
 /*
  * les cas d'insertion 
  */
   private void insertionCases( RBNode<T> X ){
      insertionCase1( X );
      if (insertionCase2(X)) return;
      insertionCase3(X);
      insertionCase4(X);
      insertionCase5(X);
   }
   private void insertionCase1 ( RBNode<T> X )
   {
       if (X.parent == null) X.setToBlack();
   }

   private boolean insertionCase2( RBNode<T> X )
   {
	   if (X.parent == null)
	       return false;
	   return (X.parent.isBlack());	   
   }

   private void insertionCase3( RBNode<T> X )
   {
	   RBNode<T> parent = X.parent;
	   RBNode<T> uncle = X.uncle();
	   
	   if (parent == null || uncle == null) return;
	   
	   if(parent.isRed() && uncle.isRed()){
		   parent.setToBlack();
		   insertionCases(parent);
		   uncle.setToBlack();
		   insertionCases(uncle);
	   }
	   
   }

   private void insertionCase4( RBNode<T> X )
   {
	   RBNode<T> parent = X.parent;
	   RBNode<T> uncle = X.uncle();
	   RBNode<T> grandParent = X.grandParent();
	   if(grandParent == null) return;
	   	   
	   if(parent.isRed() && uncle.isBlack()){
		   if (X == parent.leftChild && parent == grandParent.rightChild ){
               rotateRight(parent);
			   insertionCase5(X.rightChild);
		   }
		   else if (X == parent.rightChild && parent == grandParent.leftChild ){
               rotateLeft(parent);
			   insertionCase5(X.leftChild); 
		   }
	   }
   }

   private void insertionCase5( RBNode<T> X )
   {
	   RBNode<T> parent = X.parent;
	   RBNode<T> uncle = X.uncle();
	   RBNode<T> grandParent = X.grandParent();
	   if(grandParent == null) return;

	   if(parent.isRed() && uncle.isBlack()){
		   // changer couleur de G et P
		   parent.toggleColor();
		   grandParent.toggleColor();
		   
		   if (X == parent.rightChild && parent == grandParent.rightChild ){
               rotateLeft(grandParent);
		   }
		   else if (X == parent.leftChild && parent == grandParent.leftChild ){
               rotateRight(grandParent);
		   }
	   }
   }
   
   private void rotateLeft( RBNode<T> P )
   {
		RBNode<T> X = P.rightChild;
		
		P.rightChild		= X.leftChild;
		X.leftChild			= P;
		P.rightChild.parent	= P;
		X.parent 			= P.parent;
		P.parent 			= X;
		X.parent.leftChild	= X;
   }
   
   private void rotateRight( RBNode<T> P)
   {
		RBNode<T> X = P.leftChild;
		
		P.leftChild			= X.rightChild;
		X.rightChild		= P;
		P.leftChild.parent	= P;
		X.parent 			= P.parent;
		P.parent 			= X;
		X.parent.rightChild	= X;
   }

   public void printTreePreOrder()
   {
      if(root == null)
         System.out.println( "Empty tree" );
      else
      {
         System.out.print( "PreOrdre [ ");
         printTreePreOrder( root );
         System.out.println( " ]");
      }
      return;
   }
   public void printTreePostOrder()
   {
      if(root == null)
         System.out.println( "Empty tree" );
      else
      {
         System.out.print( "PostOrdre [ ");
         printTreePostOrder( root );
         System.out.println( " ]");
      }
      return;
   }
   public void printTreeAscendingOrder()
   {
      if(root == null)
         System.out.println( "Empty tree" );
      else
      {
         System.out.print( "AscendingOrdre [ ");
         printTreeAscendingOrder( root );
         System.out.println( " ]");
      }
      return;
   }
   
   public void printTreeDescendingOrder()
   {
      if(root == null)
         System.out.println( "Empty tree" );
      else
      {
         System.out.print( "DescendingOrdre [ ");
         printTreeDescendingOrder( root );
         System.out.println( " ]");
      }
      return;
   }

   
   private void printTreePreOrder( RBNode<T> P )
   {
       if (P != root)
           System.out.print(" ; ");

       System.out.print("(" +P.toString() + ")");
       if(P.leftChild != null && P.leftChild.value != null) printTreePreOrder(P.leftChild);
       if(P.rightChild != null && P.rightChild.value != null) printTreePreOrder(P.rightChild);
   }

   private void printTreePostOrder( RBNode<T> P )
   {
       if(P.leftChild != null && P.leftChild.value != null) printTreePostOrder(P.leftChild);
       if(P.rightChild != null && P.rightChild.value != null) printTreePostOrder(P.rightChild);
       System.out.print("(" +P.toString() + ")");
       if (P != root)
           System.out.print(" ; ");
   }
     
   private void printTreeAscendingOrder( RBNode<T> P )
   {
       if(P.leftChild != null && P.leftChild.value != null)
           printTreeAscendingOrder(P.leftChild);

       System.out.print("(" +P.toString() + ")");

       RBNode<T> greatestNode = root;
       while(greatestNode.rightChild.value != null && greatestNode.value.compareTo(P.value) <= 0){
           greatestNode = greatestNode.rightChild;
       }
       if (!greatestNode.equals(P))
           System.out.print(" ; ");

       if(P.rightChild != null && P.rightChild.value != null)
           printTreeAscendingOrder(P.rightChild);
   }
  
   private void printTreeDescendingOrder( RBNode<T> P )
   {
       if(P.rightChild != null && P.rightChild.value != null)
           printTreeDescendingOrder(P.rightChild);

       System.out.print("(" +P.toString() + ")");

       // check if current node is the smallest value
       RBNode<T> smallestNode = root;
       while(smallestNode.leftChild.value != null && smallestNode.value.compareTo(P.value) >= 0)
           smallestNode = smallestNode.leftChild;
       if (!smallestNode.equals(P))
           System.out.print(" ; ");

       if(P.leftChild != null && P.leftChild.value != null)
           printTreeDescendingOrder(P.leftChild);

   }
   
   public void printTreeLevelOrder()
   {
      if(root == null)
         System.out.println( "Empty tree" );
      else
      {
         System.out.print( "LevelOrdre [ ");
         
         Queue<RBNode<T>> q = new LinkedList<RBNode<T>>();
         q.add(root);
         // if root has left child print it
         // if root has right child, print it
         // if left has left

         RBNode<T> current = null;
         while(!q.isEmpty()){
             current = q.peek();
             q.remove();
             if (current != root)
                 System.out.print(" ; ");
             System.out.print("(" + current.toString() + ")");

             if(current.leftChild != null && current.leftChild.value != null)
                 q.add(current.leftChild);
             if(current.rightChild != null && current.rightChild.value != null)
                 q.add(current.rightChild);
         }

      }
      System.out.println(" ]");
      return;
   }
   
   private static class RBNode<T extends Comparable<? super T> > 
   {
      enum RB_COLOR { BLACK, RED }  // Couleur possible 
      RBNode<T>  parent;      // Noeud parent
      RBNode<T>  leftChild;   // Feuille gauche
      RBNode<T>  rightChild;  // Feuille droite
      RB_COLOR   color;       // Couleur du noeud
      T          value;       // Valeur du noeud
      // Constructeur (NIL)   
      RBNode() { setToBlack(); }
      // Constructeur (feuille)   
      RBNode(T val)
      {
         setToRed();
         value = val;
         leftChild = new RBNode<T>();
         leftChild.parent = this;
         rightChild = new RBNode<T>();
         rightChild.parent = this;
      }
      
      RBNode<T> grandParent()
      {
    	  return (parent == null) ? null : parent.parent;
      }
      
      RBNode<T> uncle()
      {
    	  if (grandParent() == null) 
    		  return null;
    	  else
    		  return (grandParent().leftChild != parent) ? grandParent().leftChild : grandParent().rightChild;
	  }
      
      RBNode<T> sibling()
      {
         // A COMPLETER
    	 if (parent == null) 
    		 return null;
    	 else
    		 return (parent.leftChild != this) ? parent.leftChild : parent.rightChild;
    		 
	  }
      
      public String toString()
      {
         return value + " , " + (color == RB_COLOR.BLACK ? "black" : "red"); 
      }
      
      boolean isBlack(){ return (color == RB_COLOR.BLACK); }
      boolean isRed(){ return (color == RB_COLOR.RED); }
      boolean isNil(){ return (leftChild == null) && (rightChild == null); }
      
      void setToBlack(){ color = RB_COLOR.BLACK; }
      void setToRed(){ color = RB_COLOR.RED; }
      void toggleColor(){ 
    	  if (isBlack()) setToRed();
    	  else setToBlack();
      }
   }
}
