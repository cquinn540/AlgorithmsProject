
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @version 1.0
 * 
 * Launches window that selects text files that describe graphs
 * 
 * @author Colin Quinn
 *
 */
public class FileSelect {
	
	JFrame frame;
	JFileChooser graphChooser;
	File selectedFile;
	/**
	 * creates file select window
	 */
	public FileSelect() {
		frame = new JFrame("Select a Graph");
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    graphChooser = new JFileChooser();
	    graphChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
	    graphChooser.setFileFilter(filter);
	    selectedFile = null;
	}
	/**
	 * 
	 * @param currentDirectory		the directory file select opens in
	 * @return input				String ArrayList of lines in file
	 * @throws Exception			throws either file, io exception
	 * 								or generic exception if wring selection
	 */
	public ArrayList<String> select(File currentDirectory) throws Exception {
		graphChooser.setCurrentDirectory(currentDirectory);
		int result = graphChooser.showOpenDialog(frame);
		if (result == JFileChooser.APPROVE_OPTION) {
			selectedFile = graphChooser.getSelectedFile();
			try {
				FileReader fileReader = new FileReader(selectedFile);
				BufferedReader reader = new BufferedReader(fileReader);
				
				String line = null;
				ArrayList<String> input = new ArrayList<String>();
				
				while((line = reader.readLine()) != null) {
					input.add(line);
				}
			
				reader.close();
				
				System.out.println("Read from " +
				selectedFile.getName());
				return input;
			}
			catch(Exception ex) {
				throw ex;
			}
		}
		else {
			throw new Exception();
		}
	}
	
	/**
	 * 
	 * @return		the file selected by user
	 */
	public File getSelectedFile() {
		if (selectedFile instanceof File){
			return selectedFile;
		}
		else {
			return null;
		}
	}
}
