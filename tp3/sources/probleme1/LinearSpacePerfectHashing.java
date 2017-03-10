package probleme1;

import java.util.Random;
import java.util.ArrayList;

public class LinearSpacePerfectHashing<AnyType>
{
	static int p = 400;//46337;

	QuadraticSpacePerfectHashing<AnyType>[] data;
	int a, b;

	LinearSpacePerfectHashing()
	{
		a=b=0; data = null;
	}

	LinearSpacePerfectHashing(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array)
	{
		Random generator = new Random( System.nanoTime() );

		if(array == null || array.size() == 0)
		{
			data = null;
			return;
		}
		if(array.size() == 1)
		{
			a = b = 0;
			//data = (QuadraticSpacePerfectHashing<AnyType>[]) new Object[1];
			data[0] = new QuadraticSpacePerfectHashing<AnyType>(array);
			return;
		}
		else{
			int m = array.size();
			a = generator.nextInt(p - 1) + 1;	// [1..p]
			b = generator.nextInt(p);			// [0..p]
			
			data = new QuadraticSpacePerfectHashing[m];
			
			// for each elements of "array": if the alveol is empty -> create a quadratic with that value
			// 	if this alveol is not empty: go throught the whole quadratic, keeping values in a tmp arrayList
			//	then recreate the quadratic with that arrayList
			
			for(AnyType itemToInsert : array){

				ArrayList<AnyType> tmp = new ArrayList<AnyType>();			
				
				if(data[getKey(itemToInsert)] == null){	// if the slot is empty, create a quadra containing the element
					tmp.add(itemToInsert);
					data[getKey(itemToInsert)] = new QuadraticSpacePerfectHashing<AnyType>(tmp);
				}
				else{ // if the slot is not empty, add to it by recreating the quadra
					for(AnyType item : data[getKey(itemToInsert)].getArray()){
						if(item != null) tmp.add(item);
					}
					tmp.add(itemToInsert);
					data[getKey(itemToInsert)] = new QuadraticSpacePerfectHashing<AnyType>(tmp);
				}
			}
		}
	}

	public int Size()
	{
		if( data == null ) return 0;

		int size = 0;
		for(int i=0; i<data.length; ++i)
		{
			size += (data[i] == null ? 1 : data[i].Size());
		}
		return size;
	}

	public boolean containsKey(int key)
	{
		int pos = (((a * key) + b) % p ) % data.length;
		
		return (data[pos] == null) ? false : data[pos].containsKey(key);
	}
	
	public int getKey (AnyType x) {
		// A completer
		return ((a * x.hashCode() + b) % p) % data.length;
		
	}
	
	public boolean containsValue (AnyType x) {
		return (data[getKey(x)] == null) ? false : (data[getKey(x)].containsValue(x));
	}
	
	public void remove (AnyType x) {
		if (data[getKey(x)] == null) return;
		data[getKey(x)].remove(x);
	}

	public String toString () {
		String result = "";
		
		// A completer
		for(QuadraticSpacePerfectHashing<AnyType> item : data){
			if(item != null) result += "(" + item.toString() + "),";
		}
		if(result.length() > 0) result = result.substring(0, result.length() - 1) + ".";
		
		return result; 
	}
	
}
