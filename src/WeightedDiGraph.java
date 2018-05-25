import java.util.ArrayList;

public class WeightedDiGraph {
		
	protected Graph graph;
			
	public WeightedDiGraph(Graph aGraph) {
		graph = aGraph;
	}
	
//	public ArrayList<IntEdge> getInEdges(int j) {
//		ArrayList<IntEdge> edges = new ArrayList<IntEdge>();
//		for (int i = 0; i < graph.length; i++) {
//			Edge edge = graph.graphArray[i][j];
//			if ( edge != null ) {
//				edges.add( new IntEdge(edge) );
//			}
//		}
//		return edges;
//	}

	
	public ArrayList<IntEdge> getOutEdges(int i) {
		ArrayList<IntEdge> edges = new ArrayList<IntEdge>();
		for (int j = 0; j < graph.length; j++) {
			Edge edge = graph.graphArray[i][j];
			if ( edge != null ) {
				IntNode intNode = new IntNode( edge.getEdgeTo() );
				try {
					intNode.setIntValue( Integer.parseInt( edge.getValue() ) );
				}
				catch (NumberFormatException e) {
					throw e;
				}
				intNode.setIndex(j);
				IntEdge intEdge = new IntEdge(edge);
				intEdge.setIntEdgeTo( intNode );
				edges.add(intEdge);
			}
		}
		return edges;
	}
}
