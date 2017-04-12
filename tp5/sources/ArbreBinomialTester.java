/**
 * a tester class for the ArbreBinomial class
 *
 * autors: Adam Martin-Côté & Pascal Lacasse
 * 2017-03-28
 */

import java.util.ArrayList;

	class ArbreBinomialTester{

	    public static void main(String[] args){
	    	

//	    	Node test = generateHeap1();
//	    	test.print();
//	    	testFusion(); 	
//
//	    	testFindValue(4);
//	    	testFindValue(7);
//	    	testFindValue(1);
//	    	testFindValue(12);
//	    	
//            
//	    	testDelete(1);
//	    	testDelete(2);
//	    	testDelete(3);
//	    	testDelete(4);
//	    	testDelete(5);
//	    	testDelete(7);
//	    	testDelete(9);
	    	

	    	//testMonceauAffichage();
	    	//testMonceauFusion();
	    	System.out.println("-----------------------------------------");
	    	testMonceauDelete(77);
	    }
	    
	    private static void testMonceauAffichage(){
	    	Monceau m = generateHeap2();
	    	m.print();
	    }
	    
	    private static void testMonceauFusion(){
	    	Monceau m1 = generateHeap2();
	    	Monceau m2 = generateHeap3();
	    	m2.fusion(m1);
	    	m2.print();	    	
	    }
	    
	    private static void testMonceauDelete(int v){
	    	Monceau m1 = generateHeap2();
	    	Monceau m2 = generateHeap3();
	    	m2.fusion(m1);
	    	m2.print();
	    	System.out.println("1-----------------------------------------");

	    	System.out.println(m2.delete(v));
	    	m2.print();
	    	System.out.println("2-----------------------------------------");

	    }
	    
	    private static Node generateHeap1(){
	    	Node node12345678;
	    	try{
		    	Node node1 = new Node(1);
		    	Node node2 = new Node(2);
		    	Node node12 = node1.fusion(node2);
		    	
		    	Node node3 = new Node(3);
		    	Node node4 = new Node(4);
		    	Node node34 = node3.fusion(node4);

		    	Node node1234 = node12.fusion(node34);
		    	
		    	Node node5 = new Node(5);
		    	Node node6 = new Node(6);
		    	Node node56 = node5.fusion(node6);

		    	Node node7 = new Node(7);
		    	Node node8 = new Node(8);
		    	Node node78 = node7.fusion(node8);
		    	
		    	Node node5678 = node56.fusion(node78);
		    	
		    	node12345678 = node1234.fusion(node5678);
		    	return node12345678;
	    	}
	    	catch(Throwable e){
	    		System.out.println(e.getMessage());
	    	}
			return null;    	
	    }
	    
	    private static Monceau generateHeap2(){
	    	Node node12345678;
	    	Node nodeA = null;
	    	Node nodeB = null;
	    	Node nodeC = null; // 4,2,1
	    	try{
		    	Node node1 = new Node(1);
		    	Node node2 = new Node(2);
		    	Node node12 = node1.fusion(node2);
		    	
		    	Node node3 = new Node(3);
		    	Node node4 = new Node(4);
		    	Node node34 = node3.fusion(node4);

		    	nodeA = node12.fusion(node34);
		    	
		    	Node node5 = new Node(5);
		    	Node node6 = new Node(6);
		    	nodeB = node5.fusion(node6);

		    	nodeC = new Node(7);

	    	}
	    	catch(Throwable e){
	    		System.out.println(e.getMessage());
	    	}
			Monceau m = new Monceau();
			m.arbres.add(nodeA);
			m.arbres.add(nodeB);
			m.arbres.add(nodeC);
			return m;
	    }
	    
	    private static Monceau generateHeap3(){
	    	Node node12345678;
	    	Node nodeA = null;
	    	Node nodeB = null;
	    	Node nodeC = null; // 4,2,1
	    	try{
		    	Node node1 = new Node(11);
		    	Node node2 = new Node(22);
		    	Node node12 = node1.fusion(node2);
		    	
		    	Node node3 = new Node(33);
		    	Node node4 = new Node(44);
		    	Node node34 = node3.fusion(node4);

		    	nodeA = node12.fusion(node34);
		    	
		    	Node node5 = new Node(55);
		    	Node node6 = new Node(66);
		    	nodeB = node5.fusion(node6);

		    	nodeC = new Node(77);

	    	}
	    	catch(Throwable e){
	    		System.out.println(e.getMessage());
	    	}
			Monceau m = new Monceau();
			m.arbres.add(nodeA);
			m.arbres.add(nodeB);
			m.arbres.add(nodeC);
			return m;
	    }
	    
    	/////////////////////////////
    	// Test de la fonction fusion
    	/////////////////////////////
	    private static void testFusion(){
	    	Node root1 = generateHeap1();
	    	Node root10 = generateHeap1();
	    	try{
	    		Node resultatFusion = root1.fusion(root10);
	    		resultatFusion.print();
	    	}catch(DifferentOrderTrees e){
	    		System.out.println("erreur ordre different: " + e.getMessage());
	    	}catch(Throwable e){
	    		System.out.println("erreur inconnue lors du test de fusion : " + e.getMessage());
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


	    	Node nodeToDelete = root1.findValue(valueToDelete);
	    	if(nodeToDelete == null){
	    		System.out.println(valueToDelete + " is not in the tree.");
	    		return;
	    	}
	    	ArrayList<Node> deletedTree = nodeToDelete.delete();
	    	for (Node i : deletedTree){
	    		i.print();
	    		System.out.println();
	    	}
	    }

}