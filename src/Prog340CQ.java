
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

/**
 * @version 2.0
 * 
 * 02/18/2018 changed name of class from ICS340CQ to Prog340CQ as requested
 * 
 * Creates dropdown menu of operations that may be performed
 * on graphs
 * 
 * @author Colin Quinn
 *
 */

public class Prog340CQ {
	
	private JFrame frame;
	private JComboBox<String> menu;
	File currentDirectory;
	File inputFile;
	Graph graph;
	
	
	public static void main(String[] args) {
		Prog340CQ mainMenu = new Prog340CQ();
		mainMenu.frame.setVisible(true);
	}
	/**
	 * constructs combobox menu
	 */
	public Prog340CQ() {
		String currentPath = System.getProperty("user.dir");
		currentDirectory = new File(currentPath);
		graph = new Graph();
		
		frame = new JFrame();
		frame.setTitle("Menu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menu = new JComboBox<String>();
		menu.addItem("Select Input File");
		menu.addItem("Io");
		menu.addItem("Heap");
		menu.addItem("Mist");
		menu.addItem("Sale1");
		menu.addItem("Sale2");
		menu.addItem("Consat");
		menu.addItem("Exit");
		
		menu.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				JComboBox cb =(JComboBox)e.getSource();
				String actionName = (String)cb.getSelectedItem();
				int actionIndex = cb.getSelectedIndex();
				
				switch(actionIndex) {
					/* launches file chooser window
					 * creates graph based on file selected
					 */
					case 0:
						ArrayList<String> input = null;
						try {
							FileSelect selectGraph = new FileSelect();
							input = selectGraph.select(currentDirectory);
							inputFile = selectGraph.getSelectedFile();
						}
						catch (Exception ex){
							ex.printStackTrace();
						}
						if (input != null) {
							graph.build(input);
						}
						break;
					// writes graph to output text file
					case 1:
						Io io = new Io(inputFile, graph);
						break;
					case 2:
						Heap heap = new Heap(inputFile, graph);
						break;
					case 3:
						Mist mist = new Mist(inputFile, graph);
						break;
					case 4:
						Sale1 sale1 = new Sale1(inputFile, graph);
						break;
					case 5:
						Sale2 sale2 = new Sale2(inputFile, graph);
						break;
					case 6:
						Consat consat = new Consat(inputFile, graph);
						break;
					case 7:
						System.out.println("Bye");
						System.exit(0);
					default:
						System.out.println("Invalid Choice");
						System.exit(0);;
				}
				
			}
		});
		
		frame.getContentPane().add(menu);
		frame.pack();
	}
	
}
