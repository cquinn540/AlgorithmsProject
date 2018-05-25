import java.util.ArrayList;
import java.util.TreeMap;

public class PrimMST {
	
	protected ArrayList<IntNode> mstNodes = new ArrayList<IntNode>();
	protected ArrayList<IntEdge> mstEdges = new ArrayList<IntEdge>();

	public PrimMST(Graph graph) {
		
		// Initialize data structures
		WeightedDiGraph diGraph = new WeightedDiGraph(graph);
		MinHeap<IntEdge> frontierEdges = new MinHeap<IntEdge>();
		
		// Initialize lookup table for added nodes
		boolean[] inTree = new boolean[ graph.nodeList.size() ];
		for (int i = 0; i < graph.nodeList.size(); i++) {
			inTree[i] = false;
		}
		
		/*
		 *  Start node is first node in nodeList, distance to first node is 0
		 */
		IntNode currentNode = new IntNode( graph.nodeList.get(0) );
		currentNode.setIntValue(0);
		currentNode.setIndex(0);
		
		/*
		 * n - 1 edges in MST of graph with n edges
		 * runs until every safe edge added
		 */
		while ( graph.nodeList.size() - 1 > mstEdges.size() ) {
			
			// add current node to MST / add node's index to map
			mstNodes.add(currentNode);
			inTree[ currentNode.getIndex() ] = true;
			
			// Get list of edges adjacent to currentNode
			ArrayList<IntEdge> adjacentEdges = diGraph.getOutEdges( currentNode.getIndex() );
			
			// Add edges adjacent to currentNode to frontier
			for (IntEdge intEdge: adjacentEdges) {
				// Save time by not adding edges that lead to nodes already in tree
				if ( !inTree[ intEdge.getIntEdgeTo().getIndex() ] ) {
					frontierEdges.insert(intEdge);
				}
			}
			
			// Extract minimum value edge until an edge not already in MST is found
			IntNode nextNode;
			IntEdge newEdge;
			do {
				newEdge = frontierEdges.extractMin();
				nextNode = newEdge.getIntEdgeTo();
			}
			while ( inTree[ nextNode.getIndex() ] );
			
			// Add edge to MST
			mstEdges.add(newEdge);
			// Advance to next node
			currentNode = nextNode;	
		}
	}
	
	public ArrayList<IntNode> getMSTNodes() {
		return mstNodes;
	}
	
	public ArrayList<IntEdge> getMSTEdges() {
		return mstEdges;
	}

}
