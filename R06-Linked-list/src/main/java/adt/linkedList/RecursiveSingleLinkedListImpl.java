package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}


	@Override
	public boolean isEmpty() {
		return this.getData() == null;
	}

	@Override
	public int size() {
		int size = 0;
		
		if(!this.isEmpty()) {
			size = 1 + this.next.size();
		}
		
		return size;
	}

	@Override
	public T search(T element) {
		T search = null;
		
		if(element!= null && !this.isEmpty()) {
			if (this.getData().equals(element)) {
				search = this.data;
			} else {
				search = this.next.search(element);
			}
		}
		
		return search;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.isEmpty()) {
				this.setData(element);
				this.setNext(new RecursiveDoubleLinkedListImpl<T>());
			} else {
				this.next.insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element!= null && !this.isEmpty()) {
			if (this.getData().equals(element)) {
				this.setData(this.next.getData());
				this.setNext(this.next.getNext());
			} else {
				this.next.remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[this.size()];
		toArray2(array, 0);
		return array;
	}
	
	private void toArray2(T[] array, int i) {
		if (!this.isEmpty()) {
			array[i] = this.getData();
			this.next.toArray2(array, ++i);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}


	@Override
	public void removeDuplicates(SingleLinkedListNode<T> node) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public SingleLinkedListNode<T> getHead() {
		// TODO Auto-generated method stub
		return null;
	}

}
