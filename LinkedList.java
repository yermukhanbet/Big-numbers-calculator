package data_structures;

// the only import statement you are allowed.
import java.util.Iterator;
import java.util.NoSuchElementException;
// do not import anything else.

/**
 * 
 * @author Yessen Yermukhanbet
 *
 * @param <E>
 */
public class LinkedList<E> implements ListI<E> { 
	public class node <E> {				
		node next; 
		E data;
		public node(E data) {			
			this.data=data;
			next=null;
		}

	}
	private node<E> head;				
	private int currentSize;
	public LinkedList() {
		head= null;
		currentSize=0;
	}
	@Override
	/**
	 * adds an object to the beginning of the list
	 * Created new node, which will point to the same node as head
	 * Then make head point to new node
	 */
	public void addFirst(E obj) {	
		node<E> node = new node<E>(obj);		
		node.next = head;
		head = node;
		currentSize++;
	}

	@Override
	/**
	 * adds an object to the end of the list
	 * created temporary node, which points to head
	 * till the time when temporary node will point to null, move it further
	 * as it will reach the last element of our list, make it point to our new object
	 */
	public void addLast(E obj) {
		node<E> node = new node<E>(obj);
		node<E> temp = head;
		if(head==null) {
			head = node;
			currentSize++;
			return;
		} 
		while(temp.next!=null) {
			temp=temp.next;
		}
		temp.next=node;
		currentSize++;
	}

	@Override
	/**
	 * removes first element in our list
	 * created temporary data, which stores the data in head in order then return it
	 */
	public E removeFirst() {
		E tmp = head.data;
		if(head==null) {
			return null;
		}
		if(head.next==null) {
			head=null;
		}
		else {
			head=head.next;
		}
		currentSize--;
		return tmp;
	}

	@Override
	/**
	 * removes last element in the list 
	 * created two temporary pointers
	 * current points to head, previous to null at first
	 * till the time when current will be the last element, move current and previous forward
	 * afterwards make previous the last one by pointing it to null
	 */
	public E removeLast() {
		if(head==null) {
			return null;
		}
		if(head.next==null) {
			return removeFirst();
		}
		node<E> current = head;
		node<E> previous = null;
		while(current.next!=null){
			previous=current;
			current=current.next;
		}
		previous.next=null;
		currentSize--;
		return current.data;
	}

	@Override
	/**
	 * returns first element in the list
	 */
	public E peekFirst() {
		if(head==null) {
			return null;
		}
		return head.data;
	}

	@Override
	/**
	 * returns last element in the list
	 * created temporary node which points to head at first
	 * till the time when it will be the last element, move it forward
	 * return temporary nodes data
	 */
	public E peekLast() {
		node<E> current = head;
		if(head==null) {
			return null;
		}
		while(current.next!=null) {
			current=current.next;
		}
		return current.data;
	}

	@Override
	/**
	 * makes the list empty by making head point to null
	 */
	public void makeEmpty() {
		head=null;
	}

	@Override
	/**
	 * checks if the list is empty
	 */
	public boolean isEmpty() {

		return(head==null);

	}

	@Override
	/**
	 * list is never will be full
	 */
	public boolean isFull() {

		return false;
	}

	@Override
	/**
	 * returns current size of list
	 */
	public int size() {

		return currentSize;
	}

	@Override
	/**
	 * checks if the list contains an element
	 * created temporary node, that points to head at first
	 * till the time when the temporary node's data will be equal to the element that we are looking for, move it forward
	 * checks if it is equal using comparable interface
	 */
	public boolean contains(E obj) {
		node<E> tmp=head;
		if(head==null) {
			return false;
		}
		while(tmp.next!=null) {
			tmp=tmp.next;
			if(((Comparable<E>)obj).compareTo(tmp.data)==0);
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {

		return new IteratorHelper();
	}
	public class IteratorHelper implements Iterator<E>{
		node<E> index;
		public IteratorHelper() {
			index=head;
		}
		@Override
		public boolean hasNext() {
			return (index!=null);
		}

		@Override
		public E next() {
			if(!hasNext())
				throw new NoSuchElementException();
			E val=index.data;
			index=index.next;

			return val;
		}
	}
}
