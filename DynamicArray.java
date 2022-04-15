package com.jhunrel_evangelista.github.array;

import java.util.NoSuchElementException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Arrays;

/**
	A dynamic array that could grow and shrink automatically.
	It is also generic type to be able to hold any object that the user might need to use.

	@since Mar. 28, 2022
	@version 1.3
	@author Jhunrel Evangelista
*/
public class Array<Type> implements

	Iterator<Type>, Iterable<Type>, Comparable<Array> {

	// private generic array and size field of type integer.
	private Type[] array;
	private int size;


	/**
		@param capacity
		@since Mar. 28, 2022
		@since version: 1.0
	*/
	public Array(int capacity) {
		this.array = (Type[]) new Object[ capacity ];
	}


	/**
		@param null
		@since Mar. 28, 2022
		@since version: 1.0
	*/
	public Array() {
		this.array = (Type[]) new Object[ 3 ];
	}


	/**
		Helper method that checks if the index passed as a parameter
		is in range and not equals to null.

		@return Boolean
		@since Mar. 28, 2022
		@since version: 1.0
	*/
	private boolean invalidIndex(int index) {
		if ((Integer)index != null)
			if (index >= 0 && index < this.size)
				return false;

		return true;
	}


	/**
		Method that is used to add an element to the array.

		@since Mar. 28, 2022
		@since version: 1.0
	*/
	public void add(Type item) {
		if (this.size == this.array.length) {
			Type[] newArray = (Type[]) new Object[ this.size + 1 ];

			for (int index = 0; (index < this.size); ++index)
				newArray[ index ] = this.array[ index ];

			this.array = newArray;
		}
		this.array[ this.size++ ] = item;
	}


	/**
		Method that is used to insert an element at specified location in the array.

		@throws NoSuchElementException
		@since Mar. 28, 2022
		@since version: 1.0
	*/
	public void insert(int location, Type item) {
		if (this.invalidIndex(location))
			throw new NoSuchElementException();

		Type[] newArray = (Type[]) new Object[ ++this.size ];

		for (int index = 0; (index < location); ++index)
			newArray[ index ] = this.array[ index ];

		newArray[ location++ ] = item;

		for (int index = location; (index < this.size); ++index)
			newArray[ index ] = this.array[ index - 1 ];

		this.array = newArray;
	}


	/**
		Method that is used to copy all the elements from another Array passed as the parameter.

		@since Mar. 29, 2022
		@since version: 1.1
	*/
	public void merge(Array<Type> array) {
		if (array.size() == 0) return;

		for (int index = 0; (index < array.size()); ++index)
			this.add(array.get(index));
	}


	/**
		Method that is used to replace an element at specified index in the array.

		@throws NoSuchElementException
		@since Mar. 28, 2022
		@since version: 1.0
	*/
	public void replace(int index, Type item) {
		if (this.invalidIndex(index))
			throw new NoSuchElementException();

		this.array[ index ] = item;
	}


	/**
		Method that is used to retrieve an element at specified index in the array.

		@return Type
		@throws NoSuchElementException
		@since Mar. 28, 2022
		@since version: 1.0
	*/
	public Type get(int index) {
		if (this.invalidIndex(index))
			throw new NoSuchElementException();

		return this.array[ index ];
	}


	/**
		Method that is used to retrieve the first element in the array.

		@return Type
		@throws NoSuchElementException
		@since Mar. 29, 2022
		@since version: 1.1
	*/
	public Type getFirstElement() {
		if (this.size < 1)
			throw new NoSuchElementException();

		return this.array[ 0 ];
	}


	/**
		Method that is used to retrieve the last element in the array.

		@return Type
		@throws NoSuchElementException
		@since Mar. 29, 2022
		@since version: 1.1
	*/
	public Type getLastElement() {
		if (this.size < 1)
			throw new NoSuchElementException();

		return this.array[ this.size - 1 ];
	}


	/**
		Method that is used to delete an element at specified index in the array.

		@since Mar. 28, 2022
		@since version: 1.0
	*/
	public void remove(int target) {
		if (this.invalidIndex(target)) return;

		for (int index = target; (index < this.size - 1); ++index)
			this.array[ index ] = this.array[ index + 1 ];

		--this.size;
	}


	/**
		Method that is used to delete the first element in the array.

		@since Mar. 29, 2022
		@since version: 1.1
	*/
	public void removeFirstElement() {
		this.remove(0);
	}


	/**
		Method that is used to delete the last element in the array.

		@since Mar. 29, 2022
		@since version: 1.1
	*/
	public void removeLastElement() {
		this.remove(this.size - 1);
	}


	/**
		Method that is used to delete all the elements in the array.

		@since Mar. 28, 2022
		@since version: 1.0
	*/
	public void clear() {
		if (this.size == 0) return;

		this.array = (Type[]) new Object[(this.size = 0) + 3 ];
	}


	/**
		Method that is used to get the index of the specified element.

		@return Integer
		@since Mar. 28, 2022
		@since version: 1.0
	*/
	public int indexOf(Type item) {
		for (int index = 0; (index < this.size); ++index)
			if (item.equals(this.array[ index ]) || item == this.array[ index ])
				return index;

		return -1;
	}


	/**
		Method that is used to check if the array contains the specified element.

		@return Boolean
		@since Mar. 28, 2022
		@since version: 1.0
	*/
	public boolean contains(Type item) {
		return this.indexOf(item) > -1;
	}


	/**
		Method that is used to check if the array is empty.

		@return Boolean
		@since Mar. 28, 2022
		@since version: 1.0
	*/
	public boolean isEmpty() {
		return this.size == 0;
	}


	/**
		Method that is used to check if another Array is equals to this Array.

		@return Boolean
		@since Mar. 28, 2022
		@since version: 1.0
	*/
	@Override
	public boolean equals(Object array) {
		if (this.size() != ((Array)array).size())
			return false;

		for (int index = 0; (index < this.size); ++index)
			if (! this.get(index).equals(((Array)array).get(index)))
				return false;

		return true;
	}


	/**
		Method that is used to compare another Array to this Array.

		@return Integer
		@since Mar. 28, 2022
		@since version: 1.0
	*/
	@Override
	public int compareTo(Array array) {
		if (this.size > array.size())
			return 1;

		if (this.size < array.size())
			return -1;

		return 0;
	}


	/**
		Method that is used to get the actual array for sorting and some other particular purposes.

		@return Type[]
		@since Mar. 29, 2022
		@since version: 1.1
	*/
	public Type[] array() {
		return this.array;
	}


	/**
		Method that is used to sort elements in ascending order.

		@since Mar. 28, 2022
		@since version: 1.0
	*/
	public void sort() {
		Arrays.sort(this.array, (Comparator<? super Type>)Comparator.naturalOrder());
	}


	/**
		Method that is used to sort elements in ascending order.

		@since Mar. 29, 2022
		@since version: 1.1
	*/
	public void ascendingOrder() {
		this.sort();
	}


	/**
		Method that is used to sort elements in ascending order.

		@since Mar. 29, 2022
		@since version: 1.1
	*/
	public void naturalOrder() {
		this.sort();
	}


	/**
		Method that is used to sort elements in descending order.

		@since Mar. 28, 2022
		@since version: 1.0
	*/
	public void reversed() {
		Arrays.sort(this.array, (Comparator<? super Type>)Comparator.reverseOrder());
	}


	/**
		Method that is used to sort elements in descending order.

		@since Mar. 29, 2022
		@since version: 1.1
	*/
	public void descendingOrder() {
		this.reversed();
	}


	/**
		Method that is used to sort elements in descending order.

		@since Mar. 29, 2022
		@since version: 1.1
	*/
	public void reverseOrder() {
		this.reversed();
	}


	/**
		Method that is used to get the actual number of elements inside of the array.

		@return Integer
		@since Mar. 28, 2022
		@since version: 1.0
	*/
	public int size() {
		return this.size;
	}


	/**
		Method that is used to get the actual length or capacity that the array could hold.

		@return Integer
		@since Mar. 28, 2022
		@since version: 1.0
	*/
	public int capacity() {
		return this.array.length;
	}


	/**
		Method that is used to get the actual number of available memory spaces in the array.

		@return Integer
		@since Mar. 28, 2022
		@since version: 1.0
	*/
	public int availableSlot() {
		return this.array.length - this.size;
	}


	/**
		Method that is used to get a string representation of all the elements in the array.

		@return String
		@since Mar. 28, 2022
		@since version: 1.0
	*/
	@Override
	public String toString() {
		StringBuilder stringRepresentation = new StringBuilder("{\n");

		for (int index = 0; (index < this.size); ++index) {
			if (index != this.size - 1)
				stringRepresentation
				.append("index[ ")
				.append(Integer.toString(index))
				.append(" ] --> element[ ")
				.append(this.array[ index ].toString())
				.append(" ]\n");

			if (index == this.size - 1)
				stringRepresentation
				.append("index[ ")
				.append(Integer.toString(index))
				.append(" ] --> element[ ")
				.append(this.array[ index ].toString())
				.append(" ]\n}");
		}
		return stringRepresentation.toString();
	}


	/**
		Method that is used to remove duplicates from the current array.

		@since Mar. 30, 2022
		@since version: 1.2
	*/
	public void toSet() {
		Type[] newArray = (Type[]) new Object[ this.size ];

		this.ascendingOrder();
		int size = 0;

		for (int index = 0; (index < this.size); ++index) {
			if (size == 0)
				newArray[ size++ ] = this.array[ index ];

			if (! newArray[ size - 1 ].equals(this.array[ index ]))
				newArray[ size++ ] = this.array[ index ];
		}
		this.array = (Type[]) new Object[ this.size = size ];

		for (int index = 0; (index < size); ++index)
			this.array[ index ] = newArray[ index ];
	}


	/**
		Method that is used to iterate through each element in the array.

		@return Iterator
		@since Mar. 28, 2022
		@since version: 1.0
	*/
	@Override
	public Iterator<Type> iterator() {
		Iterator<Type> iterator = new Iterator<>() {

			// private variable that has a default value of zero.
			private int index = 0;


			/**
			Method that checks if there were still available elements left in the array during each iteration.

			@return Boolean
			@since Mar. 28, 2022
			@since version: 1.0
			*/
			@Override
			public boolean hasNext() {
				return (this.index < size && this.index > -1) && (array[ this.index ] != null);
			}


			/**
			Method that gets the next available element in the array during each iteration.

			@return Type
			@since Mar. 28, 2022
			@since version: 1.0
			*/
			@Override
			public Type next() {
				return array[ this.index++ ];
			}

		};
		return iterator;
	}


	/**
		Method that checks if there were still available elements left in the array during each iteration.

		@return Boolean
		@since Mar. 31, 2022
		@since version: 1.3
	*/
	@Override
	public boolean hasNext() {
		return this.iterator().hasNext();
	}


	/**
		Method that gets the next available element in the array during each iteration.

		@return Type
		@since Mar. 31, 2022
		@since version: 1.3
	*/
	@Override
	public Type next() {
		return this.iterator().next();
	}
}
