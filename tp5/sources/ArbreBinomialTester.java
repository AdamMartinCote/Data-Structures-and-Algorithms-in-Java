/**
 * a tester class for the ArbreBinomial class
 *
 * autors: Adam Martin-Côté & Pascal Lacasse
 * 2017-03-28
 */

import java.util.ArrayList;

	class ArbreBinomialTester{

	    public static void main(String[] args){
	    	
	    	int test1[] = {1, 2};
	    	
	    	int test2[] = {3, 4};
	    	
	    	//monceau1
	    	Node root1 = new Node(1);
	    	Node node2 = new Node(2, root1);
	    	Node node3 = new Node(3, root1);
	    	Node node4 = new Node(4, node3);
	    	Node node5 = new Node(5, root1);
	    	Node node6 = new Node(6, node5);
	    	Node node7 = new Node(7, node5);
	    	Node node8 = new Node(8, node7);
	    	
	    	//monceau2
	    	Node root10 = new Node(2);
	    	Node node12 = new Node(5, root10);
	    	Node node13 = new Node(10, root10);
	    	Node node14 = new Node(36, node13);
	    	Node node15 = new Node(21, root10);
	    	Node node16 = new Node(55, node15);
	    	Node node17 = new Node(33, node15);
	    	Node node18 = new Node(54, node17);
	    	try{
	    		Node resultatFusion = root1.fusion(root10);
	    		resultatFusion.print();
	    	}catch(DifferentOrderTrees e){
	    		System.out.println("Error");
	    	}
	    	
	    	try{
	    		Node resultatFusion2 = root10.fusion(root1);
	    		resultatFusion2.print();
	    	}catch(DifferentOrderTrees e){
	    		System.out.println("Error");
	    	}
	    }

}