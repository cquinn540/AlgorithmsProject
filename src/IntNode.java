/**
 * @ version 2.0
 * 
 * 02/18/2018  changed compareTo function to break ties based on name
 * instead of mnemonic, added setter method for intValue and index value
 * 
 * Extends node class and allows Nodes to have integer values
 * 
 * @author Colin Quinn
 *
 */
public class IntNode extends Node implements Comparable<IntNode> {
	
	private int intValue;
	private int index;
	
	/**
	 * creates an IntNode from a node
	 * Node's value field must be an integer
	 * recommended that all fields in node have been set
	 * 
	 * @param aNode
	 */
	public IntNode(Node aNode) {
		super( aNode.getCode() );
		try {
			intValue = Integer.parseInt( aNode.getValue() );
			this.setName( aNode.getName() );
			this.setValue( aNode.getValue() );
		}
		catch (NumberFormatException e) {
			throw e;
		}
		index = 0;
	}
	/**
	 * gets Node's integer value
	 * @return int value
	 */
	public int getIntValue() {
		return intValue;
	}
	/**
	 * compares Nodes based on integer value
	 * in the event of a tie, Node with greater lexographical name is greater
	 */
	
	public void setIntValue(int value) {
		intValue = value;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int aIndex) {
		index = aIndex;
	}
	
	@Override
	public int compareTo(IntNode other) {
	    final int LESS = -1;
	    final int EQUAL = 0;
	    final int GREATER = 1;
	    
	    if ( intValue < other.getIntValue() ) {
	    	return LESS;
	    }
	    if ( intValue > other.getIntValue() ) {
	    	return GREATER;
	    }
	    if ( this.getName().compareTo( other.getName() ) < 0 ) {
	    	return LESS;
	    }
	    if ( this.getName().compareTo( other.getName() ) > 0 ) {
	    	return GREATER;
	    }
	    
	    return EQUAL;
	}
	@Override
	public String toString() {
		return this.getCode();
	}

}
