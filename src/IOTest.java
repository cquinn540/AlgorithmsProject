import java.io.*;
import java.util.ArrayList;

/**
 * @version 1.0
 * 
 * test class for file IO operations
 * 
 * @author Colin Quinn
 *
 */

public class IOTest {

	public static void main(String[] args) {
		
		Graph graph = null;
		
		try {
			File file = new File(args[0]);
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			
			String line = null;
			ArrayList<String> input = new ArrayList<String>();
			
			while((line = reader.readLine()) != null) {
				input.add(line);
			}
			
			graph = new Graph();
			graph.build(input);
			
			reader.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		if (graph != null) {
			try {
				FileWriter writer = new FileWriter("Output.txt");
				
				for (int i = 0; i < graph.length; i++) {
					for (int j = 0; j < graph.length; j++) {
						Edge edge = graph.graphArray[i][j];
						if (edge != null) {
							writer.write(edge.toString() + "\n"
								);
						}
					}

				}
				
				writer.close();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
	}

}
