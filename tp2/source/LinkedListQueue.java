
public class LinkedListQueue<AnyType> implements Queue<AnyType>
{	
	// Un noeud de la file
	@SuppressWarnings("hiding")
	private class Node<AnyType> 
	{
		private AnyType data;
		private Node<AnyType> next;
		
		public Node(AnyType data, Node<AnyType> next) 
		{
			this.data = data;
			this.next = next;
		}

		public void setNext(Node next) 
		{
			this.next = next;
		}
		
		public Node<AnyType> getNext() 
		{
			return next;
		}
		
		public AnyType getData() 
		{
			return data;
		}
	}
   
	private int size = 0;		//Nombre d'elements dans la file.
	private Node<AnyType> headNode = new Node<AnyType>(null, null);	// this node doesn't hold data
	private Node<AnyType> lastElement = headNode;
	
	//Indique si la file est vide
	public boolean empty() 
	{ 
		return size == 0; 
	}
	
	//Retourne la taille de la file
	public int size() 
	{ 
		return size; 
	}
	
	//Retourne l'element en tete de file
	//Retourne null si la file est vide
	//complexit� asymptotique: O(1)
	public AnyType peek()
	{
		return (headNode == lastElement) ? null : headNode.getNext().getData();
	}
	
	//Retire l'element en tete de file
	//complexit� asymptotique: O(1)
	public void pop() throws EmptyQueueException
	{
		//A completer
		if(headNode == lastElement) throw new EmptyQueueException();
		else{ 
			headNode = headNode.getNext();
			size--;
		}
	}
	
	//Ajoute un element a la fin de la file
	//complexit� asymptotique: O(1)
	public void push(AnyType item)
	{		
		//A completer
		Node<AnyType> insertedNode = new Node<AnyType>(item, null);
		lastElement.setNext(insertedNode);
		lastElement = insertedNode;		
		size++;
	}  
}
