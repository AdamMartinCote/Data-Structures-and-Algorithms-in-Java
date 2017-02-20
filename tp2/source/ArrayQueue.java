/**
 * ArrayQueue
 * 
 * INF2010 - lab 2
 * 
 * @author Adam Martin-Côté & Pascal Lacasse
 *
 */
public class ArrayQueue<AnyType> implements Queue<AnyType> {
	private static final int STARTING_ARRAY_SIZE = 8;

	private int size = 0; 		// Nombre d'elements dans la file.
	private int startindex = 0; // Index du premier element de la file
	private AnyType[] table;

	@SuppressWarnings("unchecked")
	public ArrayQueue() {
		table = (AnyType[]) new Object[STARTING_ARRAY_SIZE];
	}

	/**
	 * Indique si la file est vide
	 */
	public boolean empty() {
		return size == 0;
	}

	/**
	 * Retourne la taille de la file
	 */
	public int size() {
		return size;
	}

	/**
	 * Retourne l'élément en tête de file sans modifier celle-ci Retourne nul si la file est vide
	 * 
	 * @return AnyType
	 */
	public AnyType peek() {
		return (size == 0) ? null : table[startindex % table.length];
	}

	/**
	 * Retire la valeur en tête de file; la valeur n'est PAS retournée par la fonction
	 */
	public void pop() throws EmptyQueueException {
		if (size == 0)
			throw new EmptyQueueException();
		else {
			startindex++;
			size--;
		}
	}

	/**
	 * Ajoute un élément à la fin de la file, double la taille si nécessaire
	 * 
	 * @param AnyType
	 */
	public void push(AnyType item) {
		if (size == table.length)
			resize(2);
		table[(size + startindex) % table.length] = item;
		size++;
	}

	/**
	 * Redimensionne la file. La capacité est multipliée par un facteur de "resizeFactor" Replace les éléments de la file au debut du tableau
	 * 
	 * @param resizeFactor
	 */
	@SuppressWarnings("unchecked")
	private void resize(int resizeFactor) {
		AnyType[] tmpTable = (AnyType[]) new Object[table.length * resizeFactor];
		for (int i = 0; i < table.length; i++) {
			tmpTable[i] = table[(startindex + i) % table.length];
		}
		table = tmpTable;
		startindex = 0;
	}
}
