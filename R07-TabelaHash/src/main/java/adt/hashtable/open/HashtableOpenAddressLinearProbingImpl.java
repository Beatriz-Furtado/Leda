package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (this.isFull()) {
			throw new HashtableOverflowException();
		}
		
		if (element != null && this.search(element) == null) {
			int probe = 0;
			int indexHash = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, probe);
			
			while(this.table[indexHash] != null && !this.table[indexHash].equals(this.deletedElement) && probe < this.capacity()) {
				probe++;
				indexHash = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, probe);
				this.COLLISIONS++;
			}
			
			this.table[indexHash] = element;
			this.elements++;
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !this.isEmpty()) {
			int indexHash = this.indexOf(element);
			
			if (indexHash >= 0) {
				this.table[indexHash] = this.deletedElement;
				this.elements--;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T search(T element) {
		T search = null;
		
		if (element != null && !this.isEmpty()) {
			int indexHash = this.indexOf(element);
			
			if (indexHash >= 0) {
				search = (T) this.table[indexHash];
			}
		}
		
		return search;
	}

	@Override
	public int indexOf(T element) {
		int indexOf = -1;
		
		if (element != null && !this.isEmpty()) {
			int probe = 0;
			int indexHash = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, probe);
			
			while(this.table[indexHash] != null && !this.table[indexHash].equals(element) && probe < this.capacity()) {
				probe++;
				indexHash = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, probe);
			}
			
			if (this.table[indexHash] != null && this.table[indexHash].equals(element)) {
				indexOf = indexHash;
			}
		}
		
		return indexOf;
	}

}
