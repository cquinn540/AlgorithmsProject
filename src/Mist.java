import java.io.File;
import java.util.ArrayList;

/**
 * @version 2.0
 * 
 * changed to no longer be a placeholder
 * 
 * @author Colin Quinn
 *
 */

public class Mist {

	public Mist (File inputFile, Graph graph) {
		

		PrimMST mst = new PrimMST(graph);
		ArrayList<IntEdge> mstEdges = mst.getMSTEdges();
		ArrayList<String> output = formatMST(mstEdges);
		Io mistIo = new Io(inputFile, output);
		
//		WeightedDiGraph diGraph = new WeightedDiGraph(graph);
//		ArrayList<IntEdge> outEdges = diGraph.getOutEdges(1);
//		ArrayList<IntEdge> inEdges = diGraph.getInEdges(1);
//		
//		for (IntEdge edge : outEdges) {
//			System.out.print(edge + ", ");
//		}
//		System.out.println();
//		for (IntEdge edge : inEdges) {
//			System.out.print(edge + ", ");
//		}
		
	}
	
	public ArrayList<String> formatMST(ArrayList<IntEdge> edges) {
		ArrayList<String> output = new ArrayList<String>();
		StringBuilder lineItems = new StringBuilder();
		
		output.add("MST Edges, in order of addition:\n");
		
		int i = 0;
		int mstSum = 0;
		for(IntEdge edge : edges) {
			
			mstSum += edge.getIntValue();
			
			if (i > 0) {
				lineItems.append(", ");
			}
			if (i % 8 == 0) {
				lineItems.append("\n");
			}
			lineItems.append(
				edge.getEdgeFrom().getCode() + " - " +
				edge.getEdgeTo().getCode()
			);
			i++;
		}
		output.add( lineItems.toString() + "\n\n");
		output.add("MST Length:   " + mstSum);
		
		return output;
	}
	
	
}
