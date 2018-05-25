import java.io.File;
import java.util.ArrayList;
/**
 * @version 1.0
 * 
 * This class tests the building and sorting capabilities of the minHeap class
 * 
 * @author Colin Quinn
 *
 */
public class Heap {
	
	public Heap(File inputFile, Graph graph) {
		
		// output that will be sent to io
		ArrayList<String> output = new ArrayList<String>();
		output.add("Heaps:\n");
		
		// string for holding line of output
		String lineItems;
		
		MinHeap<IntNode> minHeap = new MinHeap<IntNode>();
		
		/*
		 * Builds heap step by step using MinHeap.insert 
		 */
		int size = graph.nodeList.size();
		for(int i = 0; i < size; i++) {
			
			// add node i to heap
			minHeap.insert( new IntNode( graph.nodeList.get(i) ) );
			lineItems = printHeap(minHeap);
			
			// adds lineItems to output
			output.add( lineItems.toString() + "\n" );	
		}
		output.add("\n");
		
		/*
		 * tests MinHeap's array constructor and actual buildHeap method
		 */
		// creates array of IntNodes
		IntNode[] intNodeList = new IntNode[ graph.nodeList.size() ];
		int index = 0;
		for (Node node : graph.nodeList) {
			intNodeList[index] = new IntNode( node );
			index++;
		}
		// creates heap with Array of IntNodes
		MinHeap<IntNode> testMinHeap = new MinHeap<IntNode>(intNodeList);
		
//		// prints new heap to compare with previous heap
//		lineItems = printHeap(testMinHeap);
//		output.add( lineItems.toString() + "\n" );
		
		/*
		 * Uses step heap sort to show each step of heapsort
		 */
		output.add("Heapsort:\n");
		
		ArrayList<String> sortSteps = minHeap.stepHeapSort();
		
		for (String string : sortSteps) {
			output.add(string);
		}
		
//		/*
//		 * Tests faster heapsort method that does not ouput
//		 * each step
//		 */
//		output.add("\n");
//		//minHeap.buildHeap();
//		testMinHeap.heapSort();
//		lineItems = printHeap(testMinHeap);
//		output.add( lineItems.toString() + "\n" );
		
//		/*
//		 * Tests that heap extract works properly
//		 */
//		lineItems = printExtractHeap(testMinHeap);
//		output.add( lineItems.toString() + "\n" );
		
		// outputs to file
		Io heapIo = new Io(inputFile, output);
	}
	
	/**
	 * formats contents of heap
	 * @param heap MinHeap<IntNode>
	 * @return formatted String
	 */
	public String printHeap(MinHeap<IntNode> heap) {
		StringBuilder lineItems = new StringBuilder();
		int j = 1;
		for (IntNode node : heap) {
			if (j > 1) {
				lineItems.append(", ");
			}
			lineItems.append( j + ":" + node.getCode() );
			j++;
		}
		return lineItems.toString();
	}
	
	/**
	 * tests that keys are extracted in proper order
	 * @param heap
	 * @return
	 */
	public String printExtractHeap(MinHeap<IntNode> heap) {
		StringBuilder lineItems = new StringBuilder();
		int size = heap.size();
		for (int j = 1; j <= size; j++) {
			if (j > 1) {
				lineItems.append(", ");
			}
			lineItems.append( j + ":" + heap.extractMin().getCode() );
		}
		return lineItems.toString();
	}
}
