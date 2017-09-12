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
import javax.swing.JTextArea;

import java.awt.Window.Type;
import java.awt.Color;
import java.awt.Font;

public class NewJFrame extends JFrame {
	String filename;
	Graph WordGraph = new Graph();
	private JPanel contentPane;
	private JTextField Word1;
	private JTextField Word2;
	private JTextArea InText;
	private JTextArea OutText;
	JLabel label = new JLabel("");
	public NewJFrame() {
		try{
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			}catch (Exception e){e.printStackTrace();}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 986, 662);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 0, 181, 605);
		contentPane.add(panel);
		panel.setLayout(null);
		
		Word1 = new JTextField();
		Word1.setBounds(81, 30, 86, 24);
		panel.add(Word1);
		Word1.setColumns(10);
		
		Word2 = new JTextField();
		Word2.setBounds(81, 70, 86, 24);
		panel.add(Word2);
		Word2.setColumns(10);
		
		JButton Open = new JButton("OpenFile");
		Open.setBounds(38, 137, 113, 27);
		panel.add(Open);
		
		JButton QueryBridgeWords = new JButton("BridgeWords");
		QueryBridgeWords.setBounds(38, 177, 113, 27);
		panel.add(QueryBridgeWords);
		
		JButton Show = new JButton("ShowGraph");
		Show.setBounds(38, 217, 113, 27);
		panel.add(Show);
		
		JButton NewText = new JButton("NewText");
		NewText.setBounds(38, 257, 113, 27);
		panel.add(NewText);
		
		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.setBounds(38, 297, 113, 27);
		panel.add(btnNewButton_4);
		
		JLabel Label1 = new JLabel("Word1");
		Label1.setFont(new Font("Cambria", Font.PLAIN, 18));
		Label1.setBounds(14, 29, 66, 23);
		panel.add(Label1);
		
		JLabel Label2 = new JLabel("Word2");
		Label2.setFont(new Font("Cambria", Font.PLAIN, 18));
		Label2.setBounds(14, 70, 66, 24);
		panel.add(Label2);
		
		JButton button = new JButton("New button");
		button.setBounds(38, 335, 113, 27);
		panel.add(button);
		
		InText = new JTextArea();
		InText.setBounds(200, 30, 753, 64);
		contentPane.add(InText);
		InText.setColumns(10);
		
		JLabel lblShowPlace = new JLabel("Show Place");
		lblShowPlace.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblShowPlace.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowPlace.setBounds(716, 107, 237, 29);
		contentPane.add(lblShowPlace);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(200, 136, 501, 469);
		contentPane.add(scrollPane);
		label.setBackground(Color.WHITE);
		
		label.setHorizontalAlignment(JLabel.CENTER);
		scrollPane.setViewportView(label);
		
		JLabel lblInputText = new JLabel("Input Text");
		lblInputText.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblInputText.setHorizontalAlignment(SwingConstants.CENTER);
		lblInputText.setBounds(200, 0, 753, 27);
		contentPane.add(lblInputText);
		
		JLabel lblPicture = new JLabel("Picture");
		lblPicture.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblPicture.setHorizontalAlignment(SwingConstants.CENTER);
		lblPicture.setBounds(200, 109, 502, 27);
		contentPane.add(lblPicture);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(716, 136, 237, 470);
		contentPane.add(scrollPane_1);
		
		OutText = new JTextArea();
		scrollPane_1.setViewportView(OutText);
		OutText.setEditable(false);
		OutText.setColumns(10);
		OutText.setLineWrap(true);
		
		
		OpenAction openAction = new OpenAction();
		Open.addActionListener(openAction);
		
		queryBridgeWordsAction QueryBridgeWordsAction = new queryBridgeWordsAction();
		QueryBridgeWords.addActionListener(QueryBridgeWordsAction);
		
		showGraphAction ShowGraph = new showGraphAction();
		Show.addActionListener(ShowGraph);
		
		generateNewTextAction GenerateNewTextAction = new generateNewTextAction();
		NewText.addActionListener(GenerateNewTextAction);
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
	        if (file == null) return;
	        filename =  file.getAbsolutePath();
	        System.out.println(filename);
	        WordGraph = MyFile.createDirectedGraph(filename);
	        ShowGraph.showDirectedGraph(WordGraph);
	        flush();
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
				JOptionPane.showMessageDialog(contentPane,"The first word is not exist","Error",JOptionPane.ERROR_MESSAGE);
			}
			else if (S.equals("2")){
				JOptionPane.showMessageDialog(contentPane,"The second word is not exist","Error",JOptionPane.ERROR_MESSAGE);
			}
			else if (S.equals("3")){
				JOptionPane.showMessageDialog(contentPane,"The both words are not exist","Error",JOptionPane.ERROR_MESSAGE);
			}
			else if (S.equals("0")){
				JOptionPane.showMessageDialog(contentPane,"There is no BridgeWords","Sorry",JOptionPane.ERROR_MESSAGE);
			}
			else {
				WordGraph.setNodeColor(word1, "blue");
				WordGraph.setNodeColor(word2, "blue");
				String[] BridgeWords = S.split(" ");
				String IsOrAre = " are: ";
				if (BridgeWords.length == 1) IsOrAre = " is: ";
				for (String tempString : BridgeWords){
					WordGraph.setNodeColor(tempString, "green");
					WordGraph.setEdgeColor(word1,tempString, "green");
					WordGraph.setEdgeColor(tempString,word2,"green");
				}
				ShowGraph.showDirectedGraph(WordGraph);
				flush();
				OutText.setText("The bridge words from "+word1+" "+word2+IsOrAre+S );
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
	
	private class generateNewTextAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String text = InText.getText();
	        String newText = GenerateNewText.generateNewText(WordGraph, text);
	        OutText.setText("The New Text is:"+newText);
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
