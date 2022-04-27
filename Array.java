import java.util.Iterator;
import java.util.HashMap;

public class Array<Type> implements

	Iterable<Type>, Comparable<Array<?>> {

	private Type[] array;
	private int size;


	public Array(int capacity) {
		this.array = (Type[]) new Object[ capacity ];
	}

	public Array() {
		this.array = (Type[]) new Object[ 10 ];
	}


	private boolean validIndex(int index) {
		return (index >= 0 && index < this.size);
	}


	public void add(Type element) {
		if (this.size == this.array.length) {
			Type[] newArray = (Type[]) new Object[ this.size * 2 ];

			for (int i = 0; (i < this.size); ++i)
				newArray[ i ] = this.array[ i ];

			this.array = newArray;
		}
		this.array[ this.size++ ] = element;
	}


	public void addAll(Array<Type> array) {
		for (int i = 0; (i < array.size()); ++i)
			this.add(array.get(i));
	}


	public void insert(int index, Type element) {
		if (this.validIndex(index)) {
			Type[] newArray = (Type[]) new Object[ ++this.size ];

			for (int i = 0; (i < index); ++i)
				newArray[ i ] = this.array[ i ];

			newArray[ index++ ] = element;

			for (int i = index; (i < this.size); ++i)
				newArray[ i ] = this.array[ i - 1 ];

			this.array = newArray;
		}
	}


	public void insertAll(int index, Array<Type> array) {
		if (this.validIndex(index)) {
			Type[] newArray = (Type[]) new Object[ this.size += array.size() ];

			for (int i = 0; (i < index); ++i)
				newArray[ i ] = this.array[ i ];

			for (int i = 0; (i < array.size()); ++i)
				newArray[ index++ ] = array.get(i);

			for (int i = index; (i < this.size); ++i)
				newArray[ i ] = this.array[ i - array.size() ];

			this.array = newArray;
		}
	}


	public void replace(int index, Type element) {
		if (this.validIndex(index))
			this.array[ index ] = element;
	}


	public void replaceAll(Type currentElement, Type newElement) {
		while (!(this.indexOf(currentElement) < 0))
			this.replace(this.indexOf(currentElement), newElement);
	}


	public void remove(int index) {
		if (this.validIndex(index)) {
			for (int i = index; (i < (this.size - 1)); ++i)
				this.array[ i ] = this.array[ i + 1 ];

			--this.size;
		}
	}


	public void removeAll(Type element) {
		while (!(this.indexOf(element) < 0))
			this.remove(this.indexOf(element));
	}


	public void clear() {
		this.array = (Type[]) new Object[ 10 ];
	}


	public Type get(int index) {
		if (this.validIndex(index))
			return this.array[ index ];

		return null;
	}


	public int binarySearch(Type element) {
		this.ascendingOrder();

		int max = (this.size - 1);
		int min = 0;

		while (min <= max) {
			int mid = (min + (max - min) / 2);

			if (this.array[ mid ].equals(element))
				return mid;

			if (((Comparable)this.array[ mid ]).compareTo(element) < 0)
				min = (mid + 1);

			else
				max = (mid - 1);
		}
		return -1;
	}


	public int indexOf(Type element) {
		for (int i = 0; (i < this.size); ++i)
			if (element.equals(this.get(i)))
				return i;

		return -1;
	}


	public boolean contains(Type element) {
		return !(this.indexOf(element) < 0);
	}


	public boolean isEmpty() {
		return this.size == 0;
	}


	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (o == null || this == null)
			return false;

		if (!(((Array<Type>)o) instanceof Array<?>))
			return false;

		if (!(((Array<Type>)o).size() == this.size))
			return false;

		class CountTrack {
			public int count = 1;
		}

		HashMap<Type, CountTrack> counter = new HashMap<>();

		for (Type e : this) {
			final CountTrack count = counter.get(e);

			if (count != null)
				count.count++;

			else
				counter.put(e, new CountTrack());
		}

		for (Type e : ((Array<Type>)o)) {
			final CountTrack count = counter.get(e);

			if (count == null || count.count == 0)
				return false;

			count.count--;
		}
		return true;
	}


	@Override
	public int compareTo(Array<?> array) {
		if (array.size() == this.size)
			return 0;

		if (array.size() < this.size)
			return 1;

		return -1;
	}


	@Override
	public String toString() {
		StringBuilder string = new StringBuilder("[ ");

		for (int i = 0; (i < this.size); ++i) {
			if (i == (this.size - 1))
				string.append(this.get(i)).append(" ]");

			else
				string.append(this.get(i)).append(", ");
		}
		return (string.length() < 3) ? "[]" : string.toString();
	}


	@Override
	public Iterator<Type> iterator() {
		Iterator<Type> iterator = new Iterator() {
			private int index = 0;

			@Override
			public boolean hasNext() {
				return validIndex(this.index) && (array[ this.index ] != null);
			}

			@Override
			public Type next() {
				return array[ this.index++ ];
			}

			@Override
			public void remove() {
				if (validIndex(--this.index))
					for (int i = this.index; (i < (size - 1)); ++i)
						array[ i ] = array[ i + 1 ];

				--size;
			}
		};
		return iterator;
	}


	public void ascendingOrder() {
		quickSort(this.array, 0, (this.size - 1), false);
	}


	public void descendingOrder() {
		quickSort(this.array, 0, (this.size - 1), true);
	}


	private int partition(Type[] array, int min, int max, boolean reversed) {
		Type pivot = array[ max ];
		int middle = (min - 1);

		if (reversed) {
			for (int i = min; (i < max); ++i)
				if (((Comparable)array[ i ]).compareTo(pivot) > 0) {

					Type temp = array[ ++middle ];
					array[ middle ] = array[ i ];
					array[ i ] = temp;
				}
		} else {
			for (int i = min; (i < max); ++i)
				if (((Comparable)array[ i ]).compareTo(pivot) < 0) {

					Type temp = array[ ++middle ];
					array[ middle ] = array[ i ];
					array[ i ] = temp;
				}
		}
		Type temp = array[ ++middle ];
		array[ middle ] = array[ max ];
		array[ max ] = temp;

		return middle;
	}


	private void quickSort(Type[] array, int min, int max, boolean reversed) {
		if (min < max) {
			int partition = partition(array, min, max, reversed);
			quickSort(array, min, (partition - 1), reversed);
			quickSort(array, (partition + 1), max, reversed);
		}
	}


	public int nulls() {
		return (this.array.length - this.size);
	}


	public int capacity() {
		return this.array.length;
	}


	public int size() {
		return this.size;
	}
}
