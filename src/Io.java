
import java.io.*;
import java.util.ArrayList;

/**
 * @version 1.0
 * 
 * Writes graph to file and names file after original file + "_out"
 * 
 * @author Colin Quinn
 *
 */

public class Io {
	
	
	/**
	 * 
	 * @param inputFile		File graph was built from
	 * @param graph
	 */
	public Io( File inputFile, Graph graph ) {
		
		ArrayList<String> output = this.createNodeEdgeList(graph);
		this.write(inputFile, output);
	}
	
	/**
	 * 
	 * @param inputFile
	 * @param output
	 */
	public Io( File inputFile, ArrayList<String> output ) {
		this.write(inputFile, output);
	}
	
	protected void write (File inputFile, ArrayList<String> output) {
		
		try {
			String simpleName = inputFile.getName().replaceAll(".txt", "");
			FileWriter writer = new FileWriter(simpleName + "_out.txt");
			
			for (String line : output) {
				writer.write(line);
			}

			System.out.println("Wrote file: " + simpleName + "_out.txt");
			writer.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public ArrayList<String> createNodeEdgeList(Graph graph) {
		
		ArrayList<String> nodeEdgeList = new ArrayList<String>();
		
		for (int i = 0; i < graph.length; i++) {
			
			Node currentNode = graph.nodeList.get(i);
			nodeEdgeList.add( currentNode.toString() + "\n" );
			
			// Get outgoing edges from row i
			for (int j = 0; j < graph.length; j++) {
				Edge edge = graph.graphArray[i][j];
				if (edge != null) {
					nodeEdgeList.add( edge.toString() + "\n" );
				}
			}
			// Get incoming edges from column i
			for(int k = 0; k < graph.length; k++) {
				Edge edge = graph.graphArray[k][i];
				if (edge != null) {
					nodeEdgeList.add(
							edge.getEdgeTo().getName() + " has edge from " +
							edge.getEdgeFrom().getName() + " labeled " +
							edge.getValue() + "\n"
					);
				}
			}
			nodeEdgeList.add("\n");
		}
		return nodeEdgeList;
	}
}
