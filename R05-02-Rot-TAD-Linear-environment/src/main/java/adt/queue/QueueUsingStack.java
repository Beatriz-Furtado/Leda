package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull()) {
			throw new QueueOverflowException();
		}
		
		if (element != null) {
			try {
					this.stack1.push(element);
			} catch (StackOverflowException  e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) {
			throw new QueueUnderflowException();
		}
		
		T value = null;
		
		try {
			
			this.transferStacksElements(this.stack1, this.stack2);
			value = this.stack2.pop();
			this.transferStacksElements(this.stack2, this.stack1);
			
		} catch (StackOverflowException | StackUnderflowException e) {
			e.printStackTrace();
		}
		return value;
	}

	@Override
	public T head() {
		T head = null;
		
		if (!this.isEmpty()) {
			try {
				
				this.transferStacksElements(this.stack1, this.stack2);
				head = this.stack2.top();
				this.transferStacksElements(this.stack2, this.stack1);
				
			} catch (StackOverflowException | StackUnderflowException e) {
				e.printStackTrace();
			}
			
		}
		return head;
	}
	
	private void transferStacksElements(Stack<T> stackPop, Stack<T> stackPush) throws StackOverflowException, StackUnderflowException {
		while (!stackPop.isEmpty()) {
			stackPush.push(stackPop.pop());
		}
	}

	@Override
	public boolean isEmpty() {
		return this.stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.stack1.isFull();
	}
}
