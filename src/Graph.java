
import java.util.ArrayList;

/**
 * @version 1.0
 * 
 * This class creates a two dimensional array of edges
 * and an ArrayList of Nodes
 * 
 * Indices of array of edges correspond to indices of list of Nodes
 * 
 * @author Colin Quinn
 *
 */

public class Graph {
	ArrayList<Node> nodeList = new ArrayList<Node>();
	Edge[][] graphArray;
	int length;
	/**
	 * Constructor doesn't build graph to avoid issues in GUI
	 */
	public Graph() {
		length = 0;
	}
	/**
	 * Builds 2 dimensional array of edges and list of nodes
	 * @param lines 	collection of strings read in from graph file
	 */
	public void build(ArrayList<String> lines) {
		
		nodeList.clear();		// get rid of nodes from previous graph
		String line = null;		// holds each line of input
		line = lines.get(0);
		String[] nodeCodes = line.split("\\s+");
		
		// creates node array list ignoring first two words in input
		if (nodeCodes.length > 2) {
			for (int i = 2; i < nodeCodes.length; i++) {
				System.out.print(nodeCodes[i] + " ");
				nodeList.add( new Node(nodeCodes[i]) );
			}
		}
		length = nodeList.size();
		
		// makes sure graph has at least one node
		if (length > 0) {
			graphArray = new Edge[length][length];
			
			// gets node name and value from first two words of line
			for (int i = 1; i < lines.size(); i++) {
				line = lines.get(i);
				String[] lineItems = line.split("\\s+");			
				Node currentNode = nodeList.get(i - 1);
				currentNode.setName(lineItems[0]);
				
				String value = lineItems[1];
				if ( value.contentEquals("~") ) {
					value = "no value";
				}
				currentNode.setValue(value);
				
				/* places edges in correct positions in graph
				 * due to graph keys in file must adjust indices
				 * by j - 2 and i - 1
				 */
				for (int j = 2; j < lineItems.length; j++) {
					
					if ( lineItems[j].contentEquals("~") ) {
						graphArray[i - 1][j - 2] = null;
					}
					else {
						graphArray[i - 1][j - 2] = new Edge(
								lineItems[j],
								nodeList.get(j - 2),
								currentNode
							);
					}
				}
			}
		}
		System.out.println("Array built");
	}
	
	/**
	 * 
	 * @return length	one dimension because graph has equal dimensions
	 */
	public int getLength() {
		return length;
	}
}
	
