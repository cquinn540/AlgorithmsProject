/**
 * @version 1.0
 * 
 * Implements a minimum heap by extending priority queue
 * 
 * @author Colin Quinn
 *
 * @param <K>
 */
public class MinHeap<K extends Comparable<K>> extends PriorityQueue<K> {
	
	public MinHeap() {
		super();
	}
	
	public MinHeap(int aSize) {
		super(aSize);
	}
	
	public MinHeap(K[] keys) {
		super(keys);
	}

	/**
	 * @return true if i is less than j; false otherwise
	 */
	@Override
	protected boolean less(int i, int j) {
		return heap[i].compareTo( heap[j] ) < 0;
	}
	
	/**
	 * returns minimum key without removing minimum key from heap
	 * @return minimum key
	 */
	public K min() {
		return root();
	}
	/**
	 * removes minimum key from heap
	 * @return minimum key
	 */
	public K extractMin() {
		return extractRoot();
	}
}
