
public class IntEdge extends Edge implements Comparable<IntEdge> {
	
	protected int intValue;
	private IntNode intEdgeTo;
	private IntNode intEdgeFrom;

	public IntEdge(Edge edge) {
		super( edge.getValue(), edge.getEdgeTo(), edge.getEdgeFrom() );
		try {
			intValue = Integer.parseInt( edge.getValue() );
		}
		catch (NumberFormatException e) {
			throw e;
		}
	}
	
	public int getIntValue() {
		return intValue;
	}
	
	public IntNode getIntEdgeTo() {
		return intEdgeTo;
	}
	
	public IntNode getIntEdgeFrom() {
		return intEdgeFrom;
	}
	
	public void setIntEdgeTo(IntNode aNode) {
		intEdgeTo = aNode;
	}
	
	
	public void setIntEdgeFrom(IntNode aNode) {
		intEdgeFrom = aNode;
	}
	
	public int compareTo(IntEdge other) {
	    final int LESS = -1;
	    final int EQUAL = 0;
	    final int GREATER = 1;
	    
	    if ( intValue < other.getIntValue() ) {
	    	return LESS;
	    }
	    if ( intValue > other.getIntValue() ) {
	    	return GREATER;
	    }
	    if ( this.getEdgeTo().getName().compareTo( other.getEdgeTo().getName() ) < 0 ) {
	    	return LESS;
	    }
	    if ( this.getEdgeTo().getName().compareTo( other.getEdgeTo().getName() ) > 0 ) {
	    	return GREATER;
	    }
	    
	    return EQUAL;
	}

}
