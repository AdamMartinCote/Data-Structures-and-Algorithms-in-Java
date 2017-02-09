
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

		public void setNext(Node<AnyType> next) 
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
	private Node<AnyType> last;	//Dernier element de la liste
	
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
		return (empty()) ? null : last.getNext().getData();
	}
	
	//Retire l'element en tete de file
	//complexit� asymptotique: O(1)
	public void pop() throws EmptyQueueException
	{
		//A completer
		if (empty()) throw new EmptyQueueException();
		else if (size == 1) {
			last = null;
			size--;
		}
		else {
			Node<AnyType> tmp = last.getNext().getNext();
			last.setNext(tmp);
			size--;
		}
	}
	
	//Ajoute un element a la fin de la file
	//complexit� asymptotique: O(1)
	public void push(AnyType item)
	{		
		if (empty()){
			last = new Node<AnyType>(item, null);	// last node points back to itself (as head)
			last.setNext(last); 					// need to set after its creation
		}
		else {
			Node<AnyType> newLast = new Node<AnyType>(item, last.getNext());
			last.setNext(newLast);
			last = newLast;
		}
		size++;
	}  
}
