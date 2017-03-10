package probleme2;


public class MyHashMap<KeyType, ValueType>
{
   private DoubleHashingTable< Entry<KeyType, ValueType>[] > items;

   private class DoubleHashingTable<Entry>{
	   private Entry[] table;	// main entry container
	   private int R;

	   public DoubleHashingTable(Entry[] e){
		   R = findHighestPrime(e.length - 1);
		   
		   for (Entry i : e){
			   int h1 = i.hashCode() % e.length;
			   int h2 = R - (i.hashCode() % R);
			   int position = (h1 + h2/* *i ????*/) % e.length;
		   }		   
	   }
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
   
   
   public MyHashMap( )
   {
      items = new DoubleHashingTable< Entry<KeyType,ValueType> >();
   }

   public void put(KeyType key, ValueType val)
   {
      items.insert(new Entry<KeyType,ValueType>(key,val));
   }

   public ValueType get(KeyType key)
   {
      return (items.get(new Entry<KeyType,ValueType>(key,null) )).value;
   }

   public boolean isEmpty()
   {
      return ( items.nbElement() == 0 ); 
   }

   private static class Entry<KeyType,ValueType>
   {
      public KeyType key;
      public ValueType value;

      public Entry(KeyType key, ValueType value)
      {
         this.key = key;
         this.value = value;
      }

      public boolean equals(Object cmp)
      {
         return this.hashCode() == cmp.hashCode();
      }

      public int hashCode()
      {
         return key.hashCode();
      }
   }    
}