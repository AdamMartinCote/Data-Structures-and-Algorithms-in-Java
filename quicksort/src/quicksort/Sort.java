package quicksort;

public final class Sort {
	public static <AnyType extends Comparable<? super AnyType>> void quicksort(AnyType[] a) {
		quicksort(a, 0, a.length - 1);
	}

	private static final int CUTOFF = 6;

	public static <AnyType> void swapReferences(AnyType[] a, int index1, int index2) {
		AnyType tmp = a[index1];
		a[index1] = a[index2];
		a[index2] = tmp;
	}

	private static <AnyType extends Comparable<? super AnyType>> void quicksort(AnyType[] a, int left, int right) {
		System.out.println("DEBUG: call to quicksort");

		if (left + CUTOFF <= right) {
			AnyType pivot = median3(a, left, right);
			// Begin partitioning
			int i = left, j = right - 1;
			for (;;) {
				while (a[++i].compareTo(pivot) < 0) {
				}
				while (a[--j].compareTo(pivot) > 0) {
				}
				if (i < j)
					swapReferences(a, i, j);
				else
					break;
			}
			swapReferences(a, i, right - 1);
			quicksort(a, left, i - 1);
			quicksort(a, i + 1, right);
		} else
			insertionSort(a, left, right);
	}

	private static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a, int left, int right) {
		for (int p = left + 1; p <= right; p++) {
			AnyType tmp = a[p];
			int j;
			for (j = p; j > left && tmp.compareTo(a[j - 1]) < 0; j--)
				a[j] = a[j - 1];
			a[j] = tmp;
		}
	}

	private static <AnyType extends Comparable<? super AnyType>> AnyType median3(AnyType[] a, int left, int right) {
		int center = (left + right) / 2;
		if (a[center].compareTo(a[left]) < 0)
			swapReferences(a, left, center);
		if (a[right].compareTo(a[left]) < 0)
			swapReferences(a, left, right);
		if (a[right].compareTo(a[center]) < 0)
			swapReferences(a, center, right);
		swapReferences(a, center, right - 1);
		return a[right - 1];
	}

	public static void main(String[] args) {
		Integer[] a = new Integer[15];
		for (int i = 0; i < a.length; i++)
			a[i] = 15 - i;

		for (Integer item : a)
			System.out.print(item + " ");
		System.out.println();

		Sort.quicksort(a);

		for (Integer item : a)
			System.out.print(item + " ");
		System.out.println();
	}
}