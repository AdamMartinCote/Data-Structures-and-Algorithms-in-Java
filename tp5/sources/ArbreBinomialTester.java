/**
 * a tester class for the ArbreBinomial class
 *
 * autors: Adam Martin-Côté & Pascal Lacasse
 * 2017-03-28
 */

import java.util.ArrayList;

	class ArbreBinomialTester{

	    public static void main(String[] args){
	    	

	    	//testFusion(); 	

	    	//testFindValue(4);

	    	testDelete(1);
	    	testDelete(2);
	    	testDelete(3);
	    	testDelete(4);
	    	testDelete(5);
	    	testDelete(7);
	    	testDelete(8);
	    	
	    }
	    
	    private static Node generateHeap1(){
	    	//monceau1
	    	Node root1 = new Node(1);
	    	Node node2 = new Node(2, root1);
	    	Node node3 = new Node(3, root1);
	    	Node node4 = new Node(4, node3);
	    	Node node5 = new Node(5, root1);
	    	Node node6 = new Node(6, node5);
	    	Node node7 = new Node(7, node5);
	    	Node node8 = new Node(8, node7);
	    	
	    	return root1;
	    }
	    
	    private static Node generateHeap2(){
	    	Node root10 = new Node(2);
	    	Node node12 = new Node(5, root10);
	    	Node node13 = new Node(10, root10);
	    	Node node14 = new Node(36, node13);
	    	Node node15 = new Node(21, root10);
	    	Node node16 = new Node(55, node15);
	    	Node node17 = new Node(33, node15);
	    	Node node18 = new Node(54, node17);

	    	return root10;
	    }
	    
    	/////////////////////////////
    	// Test de la fonction fusion
    	/////////////////////////////
	    private static void testFusion(){
	    	Node root1 = generateHeap1();
	    	Node root10 = generateHeap2();
	    	try{
	    		Node resultatFusion = root1.fusion(root10);
	    		resultatFusion.print();
	    	}catch(DifferentOrderTrees e){
	    		System.out.println("erreur ordre different: " + e.getMessage());
	    	}catch(Throwable e){
	    		System.out.println("erreur inconnue lors du test de fusion");
	    	}
	    }
	    
    	////////////////////////////////
    	// Test de la fonction findValue
    	////////////////////////////////
	    private static void testFindValue(int valueToSearch){
	    	Node root1 = generateHeap1();
	    	Node returnedNode = root1.findValue(valueToSearch);
	    	
	    	if (returnedNode != null)
	    		System.out.println(returnedNode.getVal());
	    	else
	    		System.out.println("findValue(" + valueToSearch + ") returned null");
	    	
	    }
	    
    	/////////////////////////////
    	// Test de la fonction delete
    	/////////////////////////////
	    private static void testDelete(int valueToDelete){
	    	Node root1 = generateHeap1();

	    	
	    	System.out.println("\nroot1 before : \n");
	    	root1.print();

	    	System.out.println("\nroot1 after delete(" + valueToDelete + ") : \n");

	    	ArrayList<Node> deletedTree = root1.findValue(valueToDelete).delete();
	    	for (Node i : deletedTree){
	    		i.print();
	    		System.out.println();
	    	}
	    }

}