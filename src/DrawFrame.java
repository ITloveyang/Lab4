import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;


public class DrawFrame extends JFrame {
	String filename;
	Graph WordGraph = new Graph();
	/**
	 * the Frame
	 */
	JButton QueryBridgeWords = new JButton("QueryBridgeWords");
	JTextField Word1 = new JTextField(30);
	JTextField Word2 = new JTextField(30);
	JButton Open = new JButton("OpenFile");
	JButton Show = new JButton("ShowGraph");
	Dialog myDialog  = new Dialog(this,"");
	JLabel label = new JLabel();
	private JPanel myPanel;
	public DrawFrame()
	{   
		try{
		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch (Exception e){e.printStackTrace();}
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(2*screenWidth/10,2*screenWidth/10);
		setLocationByPlatform(true);
		myPanel = new JPanel();
		
		OpenAction openAction = new OpenAction();
		Open.addActionListener(openAction);
		
		myPanel.add(Word1);
		myPanel.add(Word2);
		
		queryBridgeWordsAction QueryBridgeWordsAction = new queryBridgeWordsAction();
		QueryBridgeWords.addActionListener(QueryBridgeWordsAction);
		
		showGraphAction ShowGraph = new showGraphAction();
		Show.addActionListener(ShowGraph);
		
		myPanel.add(Open);
		myPanel.add(QueryBridgeWords);
		myPanel.add(Show);
		myPanel.add(label);
		
		add(myPanel);
		
	}
	/**
	 * choose and open file
	 * 
	 * @author LJR
	 *
	 */
	private class OpenAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {  
	        // TODO Auto-generated method stub  
	        JFileChooser jfc=new JFileChooser();  
	        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
	        jfc.showDialog(new JLabel(), "Ñ¡Ôñ");  
	        File file=jfc.getSelectedFile();   
	        filename =  file.getAbsolutePath();
	        System.out.println(filename);
	        WordGraph = MyFile.createDirectedGraph(filename);
	        if (WordGraph == null)
	        	System.out.println(0);
		}
	}
	
	private class queryBridgeWordsAction implements ActionListener
	{	
		String word1,word2;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String S = "";
			word1 = Word1.getText();
			word2 = Word2.getText();
			S = querry.queryBridgeWords(WordGraph,word1,word2);
			if (S.equals("1")){
				JOptionPane.showMessageDialog(null,"The first word is not exist","Error",JOptionPane.ERROR_MESSAGE);
			}
			else if (S.equals("2")){
				JOptionPane.showMessageDialog(null,"The second word is not exist","Error",JOptionPane.ERROR_MESSAGE);
			}
			else if (S.equals("3")){
				JOptionPane.showMessageDialog(null,"The both words are not exist","Error",JOptionPane.ERROR_MESSAGE);
			}
			else if (S.equals("0")){
				JOptionPane.showMessageDialog(null,"There is no BridgeWords","Sorry",JOptionPane.ERROR_MESSAGE);
			}
			else {
				WordGraph.setNodeColor(word1, "blue");
				WordGraph.setNodeColor(word2, "red");
				String[] BridgeWords = S.split(" ");
				for (String tempString : BridgeWords){
					WordGraph.setNodeColor(tempString, "green");
					WordGraph.setEdgeColor(word1,tempString, "yellow");
					WordGraph.setEdgeColor(tempString,word2,"yellow");
				}
				ShowGraph.showDirectedGraph(WordGraph);
				flush();
				JOptionPane.showMessageDialog(null,S,"BridgeWords:",JOptionPane.PLAIN_MESSAGE);
			
			}
		}
		
	}
	private class showGraphAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			ShowGraph.showDirectedGraph(WordGraph);
	        flush();
		}
	}
	void flush(){
		try {
			label.setIcon(new ImageIcon(ImageIO.read(new File("tmp/img.png"))));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
