package probleme1;

import java.util.ArrayList;
import java.util.Random;

public class QuadraticSpacePerfectHashing<AnyType> {
	static int p = 46337;

	int a, b;
	AnyType[] items;

	QuadraticSpacePerfectHashing() {
		a = b = 0;
		items = null;
	}

	QuadraticSpacePerfectHashing(ArrayList<AnyType> array) {
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array) {
		AllocateMemory(array);
	}

	public int Size() {
		if (items == null)
			return 0;

		return items.length;
	}

	public boolean containsKey(int key) {
		// A completer
		return (items[key] != null);

	}

	public boolean containsValue(AnyType x) {
		// A completer
		return (items[((a * x.hashCode() + b) % p) % items.length] != null);

	}

	public void remove(AnyType x) {
		// A completer
		items[((a * x.hashCode() + b) % p) % items.length] = null;
	}

	public int getKey(AnyType x) {
		// A completer
		return ((a * x.hashCode() + b) % p) % items.length;
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array) {
		Random generator = new Random(System.nanoTime());

		if (array == null || array.size() == 0) {
			// A completer
			return;
		}
		if (array.size() == 1) {
			a = b = 0;
			items[0] = array.get(0);
			return;
		}

		do {
			items = null;

			int m = array.size() * array.size();

			a = generator.nextInt(p - 1) + 1;	// [1..p]
			b = generator.nextInt(p);			// [0..p]
			items = (AnyType[]) new Object[m];

			// A completer
			for (int i = 0; i < array.size(); i++) {
				items[((a * array.get(i).hashCode() + b) % p) % m] = array.get(i);
			}

		} while (collisionExists(array));
	}

	@SuppressWarnings("unchecked")
	private boolean collisionExists(ArrayList<AnyType> array) {
		// A completer
		int count = 0;
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null)
				count++;
		}
		return (count != array.size());
	}

	public String toString() {
		String result = "";
		// A completer
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null)
				result += "(" + i + ", " + items[i] + "),";
		}
		result = result.substring(0, result.length() - 1) + ".";

		return result;
	}
}
