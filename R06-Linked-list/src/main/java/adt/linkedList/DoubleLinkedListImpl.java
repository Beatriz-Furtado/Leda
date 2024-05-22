package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		this.head = new DoubleLinkedListNode<T>();
		this.last = (DoubleLinkedListNode<T>) head;
	}
	
	public void insert(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> newNil = new DoubleLinkedListNode<>();
			DoubleLinkedListNode<T> newLast = new DoubleLinkedListNode<>();
			
			newLast.setData(element);
			newLast.setNext(newNil);
			newNil.setPrevious(newLast);
			this.last.setNext(newLast);
			newLast.setPrevious(getLast());
				
			if (this.getLast().isNIL()) {
				this.setHead(newLast);
			}
			
			this.setLast(newLast);
		}
	}
	
	@Override
	public void insertFirst(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> newNil = new DoubleLinkedListNode<>();
			DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<>();
			
			newHead.setData(element);
			newHead.setNext(this.getHead());
			newHead.setPrevious(newNil);
			newNil.setNext(newHead);
			((DoubleLinkedListNode<T>) this.head).setPrevious(newHead);
			
			if (this.getHead().isNIL()) {
				this.setLast(newHead);
			}
			
			this.setHead(newHead);
		}
	}
	
	@Override
	public void remove(T element) {
		if (!this.isEmpty() && element != null) {
			if (this.getHead().getData().equals(element)) {
				this.removeFirst();
			} else if (this.last.getData().equals(element)) {
				this.removeLast();
			} else {
				DoubleLinkedListNode<T> auxNode = (DoubleLinkedListNode<T>) this.getHead();
				
				while (!auxNode.isNIL() && !auxNode.getData().equals(element)) {
					auxNode = (DoubleLinkedListNode<T>) auxNode.getNext();
				}
				
				if(!auxNode.isNIL()) {
					auxNode.getPrevious().setNext(auxNode.getNext());
					((DoubleLinkedListNode<T>) auxNode.getNext()).setPrevious(auxNode.getPrevious());
				}
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!this.isEmpty()) {
			this.setHead(this.getHead().getNext());
			
			if (this.isEmpty()) {
				this.setLast((DoubleLinkedListNode<T>) this.getHead());
			} else {
				((DoubleLinkedListNode<T>) this.getHead()).setPrevious(new DoubleLinkedListNode<>());
			}
		}
	}

	@Override
	public void removeLast() {
		if (!this.last.isNIL()) {
			this.setLast(this.getLast().getPrevious());
			
			if (this.getLast().isNIL()) {
				this.setHead(this.getLast());
			}else {
				this.getLast().setNext(new DoubleLinkedListNode<>());
			}
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
