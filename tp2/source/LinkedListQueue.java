
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
	private Node<AnyType> first = null;	
	private Node<AnyType> last = null;	//Dernier element de la liste
	
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
		return (first == null) ? null : first.data;		
	}
	
	//Retire l'element en tete de file
	//complexit� asymptotique: O(1)
	@SuppressWarnings("unchecked")
	public void pop() throws EmptyQueueException
	{
		//A completer
		if(first == null) throw new EmptyQueueException();
		else first = first.next;		
	}
	
	//Ajoute un element a la fin de la file
	//complexit� asymptotique: O(1)
	public void push(AnyType item)
	{		
		//A completer
		if(last == null)
			Node<AnyType> secondToLast = new Node<AnyType>(last);
		last = new Node<AnyType>(item, null);
		
		if (size++ == 0)
		
	}  
}
