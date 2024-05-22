package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.getHead().isNIL();
	}

	@Override
	public int size() {
		int size = 0;
		
		SingleLinkedListNode<T> auxNode = head;
		while(!auxNode.isNIL()) {
			size++;
			auxNode = auxNode.next;
		}
		return size;
	}

	@Override
	public T search(T element) {
		T search = null;
		
		if (element != null && !this.isEmpty()) {
			SingleLinkedListNode<T> auxNode = head;
			
			while (!auxNode.isNIL() && auxNode.getData().equals(element)) {
				auxNode = auxNode.next;
			}
			search = auxNode.getData();
		}
		return search;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.isEmpty()) {
				SingleLinkedListNode<T> newHead = new SingleLinkedListNode(element, this.getHead());
				this.setHead(newHead);
			} else {
				SingleLinkedListNode<T> auxHead = head;
				
				while(!auxHead.isNIL()) {
					auxHead = auxHead.getNext();
				}
				
				auxHead.setData(element);
				auxHead.setNext(new SingleLinkedListNode<>());
			}	
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !isEmpty()) {
			if (getHead().getData().equals(element)) {
				setHead(getHead().getNext());
			} else {
				SingleLinkedListNode<T> auxHead = head;
				
				while(!auxHead.isNIL() && !auxHead.getData().equals(element)) {
					auxHead = auxHead.getNext();
				}
				
				if (!auxHead.isNIL()) {
					auxHead.setData(auxHead.getNext().getData());
					auxHead.setNext(auxHead.getNext().getNext());
				}
			}
		}
	}

	/**@Override
	public T[] toArray() {
		SingleLinkedListNode<T> auxHead = head;
		
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[this.size()];
		int index = 0;
		
		while (!auxHead.isNIL()) {
			array[index] = auxHead.getData();
			index++;
			auxHead = auxHead.getNext();
		}
		
		return array;
	}
	**/
	public void removeDuplicates(SingleLinkedListNode<T> node) {
		if (!node.isNIL()) {
			SingleLinkedListNode<T> auxHead = node;
			SingleLinkedListNode<T> next = auxHead.getNext();
			
			while (!next.isNIL()) {
				if (next.getData().equals(node.getData())) {
					auxHead.setNext(next.getNext());
				}else {
					auxHead = auxHead.getNext();
				}
				next = auxHead.getNext();
			}
		}
	}
	
	@Override
	public T[] toArray() {
		SingleLinkedListNode<T> auxHead = head;
		String retorno = "";
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[this.size()];
		int index = 0;
		
		while (!auxHead.isNIL()) {
			array[index] = auxHead.getData();
			retorno += auxHead.getData() + " ";
			index++;
			auxHead = auxHead.getNext();
		}
		System.out.println(retorno);
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return this.head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}
}
