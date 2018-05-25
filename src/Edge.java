
/**
 * @version 1.0
 * 
 * This class defines Edges of a weighted directed graph
 * 
 * @author Colin Quinn
 *
 */

public class Edge {
	private String value;
	private Node edgeTo;
	private Node edgeFrom;
	
	/**
	 * Constructor
	 * @param aValue	value of edge
	 * @param to		Node edge leads to
	 * @param from		Node edge originates from
	 */
	public Edge(String aValue, Node to, Node from) {
		value = aValue;
		edgeTo = to;
		edgeFrom = from;
	}
	
	/**
	 * 
	 * @return	value of edge
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * 
	 * @return	Node edge leads to
	 */
	public Node getEdgeTo() {
		return edgeTo;
	}
	
	/**
	 * 
	 * @return	Node edge originates from
	 */
	public Node getEdgeFrom() {
		return edgeFrom;
	}

	public String toString() {
		return edgeFrom.getName() + " has edge to "  + edgeTo.getName() +
				" labeled " + value;
	}

}
