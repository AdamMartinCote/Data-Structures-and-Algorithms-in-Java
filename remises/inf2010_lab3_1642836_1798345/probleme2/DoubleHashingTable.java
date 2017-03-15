package probleme2;

public class DoubleHashingTable<T>{
	
   private static final int DEFAULT_TABLE_SIZE = 11;
   
   private T[] items;
   
   @SuppressWarnings("unchecked")
   public DoubleHashingTable(){
	   items = (T[]) new Object[DEFAULT_TABLE_SIZE];
   }
   
   public int nbElement(){
	   return items.length;
   }
   
   /**
    * Get an element from the table
    * @param x
    * @return the element
    */
   public T get(T x){
	   int i = 0;
	   int pos = (h1(Math.abs(x.hashCode())) + i*h2(Math.abs(x.hashCode())))% nbElement();
	   
	   while (!items[pos].equals(x) && items[pos] != null){
		   i++;
		   pos = (h1(Math.abs(x.hashCode())) + i*h2(Math.abs(x.hashCode())))% nbElement();
	   }
	   return items[pos];

   }
   
   /**
    * Insert an element in the table
    * @param e
    */
   public void insert(T e){
	   int i = 0;
	   int pos = (h1(e.hashCode()) + i*h2(e.hashCode()))% nbElement();
	   
	   while (items[pos] != null){
		   i++;
		   pos = (h1(e.hashCode()) + i*h2(e.hashCode()))% nbElement();
	   }
	   items[pos] = e;
   }

   private int h1(int x){
	   return (x % this.nbElement());
   }
   
   private int h2(int x){
	   int r = findHighestPrime(items.length);
	   return (r - (x % r));
   }

   /**
    * Find the highest prime number under a given value
    * @param n the maximum value
    * @return a prime number
    */
	private static int findHighestPrime(int n){
		int i;
		boolean isPrime;
		for(i = n; i > 0; i--){	// a loop for each number 
			isPrime = true;
			for (int j = i - 1; j > 1; j--){	// tell if prime
				if ((i % j) == 0){
					isPrime = false;
					break;
				}
			}
			if (isPrime) return i;
		}
		return 0;
	}
}