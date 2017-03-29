/**
 * a tester class for the ArbreBinomial class
 *
 * autors: Adam Martin-Côté & Pascal Lacasse
 * 2017-03-28
 */

import java.util.ArrayList;

	class ArbreBinomialTester{

	    public static void main(String[] args){
	    	//monceau1
	    	Node root1 = new Node(1);
	    	Node node2 = new Node(2, root1);
	    	Node node3 = new Node(3, root1);
	    	Node node4 = new Node(4, node3);
	    	Node node5 = new Node(5, root1);
	    	Node node6 = new Node(6, node5);
	    	Node node7 = new Node(7, node5);
	    	Node node8 = new Node(8, node7);
	    	
	    	root1.addEnfant(node2);
	    	root1.addEnfant(node3);
	    	root1.addEnfant(node5);
	    	
	    	node3.addEnfant(node4);
	    	
	    	node5.addEnfant(node6);
	    	node5.addEnfant(node7);
	    	
	    	node7.addEnfant(node8);

	    	//monceau2
	    	Node root10 = new Node(2);
	    	Node node12 = new Node(5, root10);
	    	Node node13 = new Node(10, root10);
	    	Node node14 = new Node(36, node13);
	    	Node node15 = new Node(21, root10);
	    	Node node16 = new Node(55, node15);
	    	Node node17 = new Node(33, node15);
	    	Node node18 = new Node(54, node17);
	    	
	    	root10.addEnfant(node12);
	    	root10.addEnfant(node13);
	    	root10.addEnfant(node15);
	    	                    
	    	node13.addEnfant(node14);
	    	                    
	    	node15.addEnfant(node16);
	    	node15.addEnfant(node17);
	    	                    
	    	node17.addEnfant(node18);
	    	
	    	try{
	    		Node resultatFusion = root1.fusion(root10);
	    		resultatFusion.print();
	    	}catch(DifferentOrderTrees e){
	    		System.out.println(e.getMessage());
	    	}catch(Throwable e){
	    		System.out.println("hmmm...");
	    	}
	    	
	    	System.out.println("--------------");
	    	ArrayList<Node> t = node16.delete();	//revoir delete()
	    	System.out.println(t.size());
	    	//for (int i = 0; i < t.size(); i++){
	    	//	t.get(i).print();
	    	//}

	    	//try{
	    	//
	    	//System.out.println("--------------");
	    	//
	    	//Node resultatFusion2 = root10.fusion(root1);
	    	//resultatFusion2.print();
	    	//}catch(DifferentOrderTrees e){
	    	//	System.out.println(e.getMessage());
	    	//}catch(Throwable e){
	    	//	System.out.println(e.getMessage());
	    	//}
	    }

}