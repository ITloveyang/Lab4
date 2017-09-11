import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import java.awt.Window.Type;

public class NewJFrame extends JFrame {
	String filename;
	Graph WordGraph = new Graph();
	private JPanel contentPane;
	private JTextField Word1;
	private JTextField Word2;
	private JTextField textField_2;
	private JTextField textField_3;
	JLabel label = new JLabel("");
	public NewJFrame() {
		try{
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			}catch (Exception e){e.printStackTrace();}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 181, 377);
		contentPane.add(panel);
		panel.setLayout(null);
		
		Word1 = new JTextField();
		Word1.setBounds(85, 13, 86, 24);
		panel.add(Word1);
		Word1.setColumns(10);
		
		Word2 = new JTextField();
		Word2.setBounds(85, 50, 86, 24);
		panel.add(Word2);
		Word2.setColumns(10);
		
		JButton Open = new JButton("OpenFile");
		Open.setBounds(14, 137, 113, 27);
		panel.add(Open);
		
		JButton QueryBridgeWords = new JButton("QueryBridgeWords");
		QueryBridgeWords.setBounds(14, 177, 113, 27);
		panel.add(QueryBridgeWords);
		
		JButton Show = new JButton("ShowGraph");
		Show.setBounds(14, 217, 113, 27);
		panel.add(Show);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setBounds(14, 257, 113, 27);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.setBounds(14, 297, 113, 27);
		panel.add(btnNewButton_4);
		
		JLabel Label1 = new JLabel("Word1");
		Label1.setBounds(24, 16, 47, 18);
		panel.add(Label1);
		
		JLabel Label2 = new JLabel("Word2");
		Label2.setBounds(24, 53, 47, 18);
		panel.add(Label2);
		
		JButton button = new JButton("New button");
		button.setBounds(14, 337, 113, 27);
		panel.add(button);
		
		textField_2 = new JTextField();
		textField_2.setBounds(200, 13, 365, 64);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblShowPlace = new JLabel("Show Place");
		lblShowPlace.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowPlace.setBounds(579, 13, 120, 18);
		contentPane.add(lblShowPlace);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(579, 44, 120, 338);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(200, 90, 365, 292);
		contentPane.add(scrollPane);
		
		label.setHorizontalAlignment(JLabel.CENTER);
		scrollPane.setViewportView(label);
		
		OpenAction openAction = new OpenAction();
		Open.addActionListener(openAction);
		
		queryBridgeWordsAction QueryBridgeWordsAction = new queryBridgeWordsAction();
		QueryBridgeWords.addActionListener(QueryBridgeWordsAction);
		
		showGraphAction ShowGraph = new showGraphAction();
		Show.addActionListener(ShowGraph);
	}
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
				WordGraph.setNodeColor(word2, "blue");
				String[] BridgeWords = S.split(" ");
				for (String tempString : BridgeWords){
					WordGraph.setNodeColor(tempString, "green");
					WordGraph.setEdgeColor(word1,tempString, "green");
					WordGraph.setEdgeColor(tempString,word2,"green");
				}
				showGraph.showDirectedGraph(WordGraph);
				flush();
				JOptionPane.showMessageDialog(null,S,"BridgeWords:",JOptionPane.PLAIN_MESSAGE);
			
			}
		}
		
	}
	private class showGraphAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			showGraph.showDirectedGraph(WordGraph);
	        flush();
		}
	}
	void flush(){
		try {
			ImageIcon image = new ImageIcon(ImageIO.read(new File("tmp/img.png")));
			//image.setImage(image.getImage().getScaledInstance(label.getWidth(), label.getHeight(),Image.SCALE_DEFAULT ));
			label.setIcon(image);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
