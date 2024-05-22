package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}
	
	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.isEmpty()) {
				this.setData(element);
				this.setNext(new RecursiveDoubleLinkedListImpl<T>());
				((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(this);
				
			} else {
				this.getNext().insert(element);
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			if (!this.isEmpty()) {
				RecursiveDoubleLinkedListImpl<T> newNode = new RecursiveDoubleLinkedListImpl<>();
				
				newNode.setData(this.getData());
				this.setData(element);
				newNode.setNext(this.getNext());
				this.setNext(newNode);
				newNode.setPrevious(this);
				((RecursiveDoubleLinkedListImpl<T>) newNode.getNext()).setPrevious(newNode);
				
			} else {
				this.insert(element);
			}
		}
	}
	
	@Override
	public void remove(T element) {
		if (element != null && !this.isEmpty()) {
			if (this.getData().equals(element)) {
				if (this.previous.isEmpty()) {
					this.removeFirst();
				}else if (this.getNext().isEmpty()) {
					this.removeLast();
				} else {
					this.previous.setNext(this.getNext());
					((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(this.previous);
				}
			} else {
				this.getNext().remove(element);
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!this.isEmpty()) {
			if (this.getNext().isEmpty() && this.previous.isEmpty()) {
				this.setData(null);
				this.setNext(null);
				this.setPrevious(null);
			} else {
				this.setData(this.getNext().getData());
				this.setNext(this.getNext().getNext());
				((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(this);
			}
		}
	}

	@Override
	public void removeLast() {
		if (!this.isEmpty()) {
			if (this.getNext().isEmpty()) {
				this.setData(null);
				this.setNext(null);
				
				if (this.previous.isEmpty()) {
					this.setPrevious(null);
				}
			} else {
				((RecursiveDoubleLinkedListImpl<T>) this.getNext()).removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
