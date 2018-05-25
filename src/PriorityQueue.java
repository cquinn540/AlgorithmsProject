import java.util.Iterator;
import java.util.ArrayList;

/**
 * @version 1.0
 * 
 * This class is an abstract class for a priority queue.  The direction of the comparison
 * in when the less method is overwritten will determine if the subclass is a min heap or
 * max heap.
 * 
 * The internal heap keeps track of whether it is in a sorted or heapified state.
 * 
 * @author Colin Quinn
 * 
 * Used some Algorithms from "Algorithms" 4th edition by Robert Sedgewick and Kevin Wayne
 * and "Introduction to Algorithms" 3rd edition by Thomas H. Cormen, Charles E. Leiserson,
 * Ronald L. Rivest, and Clifford Stein
 *
 * @param <K>	A generic key that can be compared with other keys
 */
abstract class PriorityQueue<K extends Comparable<K>> implements Iterable<K> {
	
	protected K[] heap;			// heap is stored in array form
	private int size;			// size = heap.length - 1 because position 0 not used
	private boolean sorted;		// flag for if heap in sorted state
	private boolean heapified;	// flag for if heap in heapified state
	
	/**
	 * Compares i and j
	 * Override to test if i is less than j to make a min heap
	 * Override to test if i is greater than j to make a max heap
	 * 
	 * Idea for helper function from "Algorithms" Sedgewick, Wayne
	 * 
	 * @param i
	 * @param j
	 * @return	true if is less than j; false otherwise
	 */
	protected abstract boolean less(int i, int j);
	
	/**
	 * Create empty heap
	 */
	@SuppressWarnings("unchecked")
	public PriorityQueue() {
		size = 0;
		heap = (K[]) new Comparable[1];
		sorted = false;
		heapified = false;
	}
	
	/**
	 * Create heap of size aSize
	 * @param aSize
	 */
	@SuppressWarnings("unchecked")
	public PriorityQueue(int aSize) {
		size = aSize;
		heap = (K[]) new Comparable[size + 1];
		sorted = false;
		heapified = false;
	}
	
	/**
	 * Create heap from array of Keys
	 * array[0] is ignored
	 * @param aHeap
	 */
	@SuppressWarnings("unchecked")
	public PriorityQueue(K[] keys) {
		size = keys.length;
		heap = (K[]) new Comparable[size + 1];
		int i = 1;
		for (K key : keys) {
			heap[i] = key;
			i++;
		}
		buildHeap();
	}
	/**
	 * removes root from heap and re-heapifies heap
	 * 
	 * adapted from "Algorithms" pg 318
	 * 
	 * @return	Key: root of heap or null if heap is empty
	 */
	protected K extractRoot() {
		if (!heapified) {
			buildHeap();
		}
		if (size < 1) {
			return null;
		}
		K root = heap[1];
		exchange(1, size);
		// get rid of loitering key
		heap[size] = null;
		size--;
		heapify(1, size);
//		if (size < heap.length / 4) {
//			heap = resize(heap, heap.length / 2);
//		}
		return root;
	}
	/**
	 * returns root of heap without modifying heap
	 * will heapify heap first if heap is in sorted order
	 * 
	 * @return	Key: root of heap
	 */
	protected K root() {
		if (!heapified) {
			buildHeap();
		}
		return heap[1];
	}
	/**
	 * adds key to priority queue while maintaining heap property
	 * if heap is sorted, heapified heap first
	 * 
	 * adapted from "Algorithms" pg 318
	 * 
	 * @param key
	 */
	public void insert(K key) {
		if (!heapified) {
			buildHeap();
		}
		size++;
		if (size == heap.length) {
			heap = resize(heap, heap.length * 2);
		}
		// place new key at bottom of heap
		heap[size] = key;
		swim(size);
	}
	/**
	 * converts heap to sorted array if array is not
	 * already sorted
	 * 
	 * from "Introduction to Algorithms" pg 160
	 */
	public void heapSort() {	
		if (!sorted) {
			if (!heapified) {
				buildHeap();
			}
			int aSize = size;
			for (int i = aSize; i >= 2; i--) {
				// extract root
				exchange(1, i);
				aSize--;
				// heapify new root
				heapify(1, aSize);
			}
			sorted = true;
			heapified = false;
		}
	}
	/**
	 * converts heap to sorted array
	 * creates arrayList of intermediate steps
	 * @return arrayList of intermediate steps
	 */
	public ArrayList<String> stepHeapSort() {
		// structures to build return ArrayList
		ArrayList<String> output = new ArrayList<String>();
		StringBuilder lineItems = new StringBuilder();
		
		buildHeap();
		int aSize = size;
		for (int i = aSize; i >= 2; i--) {
			// extract root
			exchange(1, i);
			aSize--;
			// heapify new root
			heapify(1, aSize);
			// print intermediate step
			for (int j = 1; j <= size; j++) {
				if (j > 1) {
					lineItems.append(", ");
				}
				lineItems.append(j + ":" + heap[j]);
			}
			output.add(lineItems + "\n");
			lineItems.setLength(0);
		}
		sorted = true;
		heapified = false;
		return output;
	}
	/**
	 * reorders heap array into a heap
	 * sets heapified flag to false and sorted flag to true
	 * 
	 * from "Introduction to Algorithms" pg 157
	 */
	public void buildHeap() {
		for (int i = size / 2; i >= 1; i--) {
			heapify(i, size);
		}
		sorted = false;
		heapified = true;
	}
	/**
	 * 
	 * @return true if heap is empty false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	/**
	 * 
	 * @return size of heap
	 */
	public int size() {
		return size;
	}
	/**
	 * test if heap is sorted
	 * @return true if heap is sorted, false otherwise
	 */
	public boolean isSorted() {
		return sorted;
	}
	/**
	 * test if heap is heapified
	 * @return true if heap is heapified, false otherwise
	 */
	public boolean isHeapified() {
		return heapified;
	}
	/**
	 *  gets a copy of internal array
	 * @return K[] copy of heap
	 */
	public K[] getArray() {
		return resize(heap, size + 1);
	}
	/**
	 * examines index i
	 * if i is too low in heap:
	 * exchanges i with parent until heap property restored
	 * 
	 * from "Algorithms" pg 316
	 * 
	 * @param i		index of array to examine
	 */
	private void swim(int i) {
		int parent;
		while ( i > 1 && less( i, parent = parent(i) ) ) {
			exchange(parent, i);
			i = parent;
		}
	}
	/**
	 * examines index i in heap of size aSize
	 * if i is too high in heap:
	 * exchanges i with lowest child until heap property restored
	 * 
	 * adapted from "Introduction to Algorithms" pg 154
	 * 
	 * @param i
	 * @param aSize
	 */
	private void heapify(int i, int aSize) {
		int minimum = i;
		int left = left(i);
		int right = right(i);
		
		if (left <= aSize && less(left, minimum) ) {
			minimum = left;
		}
		if (right <= aSize && less(right, minimum) ) {
			minimum = right;
		}
		
		if (minimum != i) {
			exchange(minimum, i);
			heapify(minimum, aSize);
		}
	}
	/**
	 * get left child of index
	 * @param index
	 * @return index of left child
	 */
	private int left(int index) {
		return index * 2;
	}
	/**
	 * get right child of index
	 * @param index
	 * @return index of right child
	 */
	private int right(int index) {
		return (index * 2) + 1;
	}
	/**
	 * get parent of index
	 * @param index
	 * @return index of parent
	 */
	private int parent(int index) {
		return index / 2;
	}
	/**
	 * exchanges keys between tow indices in heap
	 * @param i		index of a key
	 * @param j		index of the other key
	 */
	private void exchange(int i, int j) {
		K temp = heap[i];
		heap[i] =  heap[j];
		heap[j] = temp;
	}
	/**
	 * copies array values into new array of specified size
	 * 
	 * adapted from "Algorithms" pg 136
	 * 
	 * @param array
	 * @param newSize
	 * @return	new array
	 */
	@SuppressWarnings("unchecked")
	private K[] resize(K[] array, int newSize) {
		K[] temp = (K[]) new Comparable[newSize];
		for (int i = 0; i < array.length; i++) {
			temp[i] = array[i];
		}
		return temp;
	}
	
	@Override
	public Iterator<K> iterator() {
		return new PriorityQueueIterator();
	}
	
	private class PriorityQueueIterator implements Iterator<K> {
		
		private int position;
		
		public PriorityQueueIterator() {
			position = 1;
		}
		
		@Override
		public boolean hasNext() {
			return (position <= size) ? true : false;
		}
		
		@Override
		public K next() {
			K key = heap[position];
			position++;
			return key;
		}
	}
}
