
/**
 * @version 1.0
 * 
 * This class defines a node in a weighted directed graph
 * 
 * @author Colin Quinn
 *
 */

public class Node {
	private String name;
	private String code;
	private String value;
	
	/**
	 * Due to graph file format constructing with
	 * mnemonic only is most efficient
	 *  
	 * @param aCode	The mnemonic for the Node
	 */
	public Node (String aCode) {
		code = aCode;
	}
	/**
	 * 
	 * @param aName		name of node
	 */
	public void setName(String aName) {
		name = aName;
	}
	/**
	 * 
 no	 * @param aCode		mnemonic node
	 */
	public void setCode(String aCode) {
		code = aCode;
	}
	/**
	 * 
	 * @param aValue	value of node
	 */
	public void setValue(String aValue) {
		value = aValue;
	}
	/**
	 * 
	 * @return			name of node
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @return			mnemonic node
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 
	 * @return			value of node
	 */
	public String getValue() {
		return value;
	}
	
	public String toString() {
		return "Node " + name + ", mnemonic " + code + ", value " + value;
	}
}
