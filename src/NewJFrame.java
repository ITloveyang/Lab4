
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
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
	Timer t = new Timer();
	List<String> shortestPath;
	String[] color = {"green","red","midnightblue","aqua","green4","red4","cyan","darkslateblue"};
	RandomWalk randomWalk;
	String LastNode,NowNode;
	String RandomPath;
	String ShortestPathString = "";
	JLabel label = new JLabel("");
	JButton CRandomWalk = new JButton("ContinueRandom");
	JButton Open = new JButton("OpenFile");
	JButton QueryBridgeWords = new JButton("BridgeWords");
	JButton Show = new JButton("ShowOriginalGraph");
	JButton NewText = new JButton("NewText");
	JButton BRandomWalk = new JButton("BeginRandom");
	JButton ShortPath = new JButton("ShortestPath");
	JButton ShowCertainPath = new JButton("ShowCertainPath");
	JButton StopRandom = new JButton("StopRandom");
	JButton BeginRandom2 = new JButton("BeginRandom");
	JButton Save = new JButton("SaveFile");
	JComboBox<String> comboBoxFr = new JComboBox<String>();
	JComboBox<String> comboBoxTo = new JComboBox<String>();
	JComboBox<String> comboBoxNo = new JComboBox<String>();
	String all = "All..";
	public NewJFrame() {
		try{
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch (Exception e){e.printStackTrace();}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1905, 1035);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 0, 181, 940);
		contentPane.add(panel);
		panel.setLayout(null);
		
		Word1 = new JTextField();
		Word1.setBounds(81, 276, 86, 24);
		panel.add(Word1);
		Word1.setColumns(10);
		
		Word2 = new JTextField();
		Word2.setBounds(81, 316, 86, 24);
		panel.add(Word2);
		Word2.setColumns(10);
				
		Open.setBounds(14, 13, 153, 27);
		panel.add(Open);
				
		QueryBridgeWords.setBounds(14, 235, 153, 27);
		panel.add(QueryBridgeWords);
			
		Show.setBounds(14, 93, 153, 27);
		panel.add(Show);
		
		NewText.setBounds(14, 164, 153, 27);
		panel.add(NewText);
		
		BRandomWalk.setBounds(14, 859, 153, 27);
		panel.add(BRandomWalk);
		
		
		CRandomWalk.setBounds(14, 900, 153, 27);
		panel.add(CRandomWalk);

        CRandomWalk.setEnabled(false);
        QueryBridgeWords.setEnabled(false);
    	Show.setEnabled(false);
    	NewText.setEnabled(false);
    	BRandomWalk.setEnabled(false);
    	ShortPath.setEnabled(false);
    	ShowCertainPath.setEnabled(false);
    	StopRandom.setEnabled(false);
    	Save.setEnabled(false);
    	BeginRandom2.setEnabled(false);
		
		JLabel Label1 = new JLabel("Word1");
		Label1.setFont(new Font("Cambria", Font.PLAIN, 18));
		Label1.setBounds(14, 275, 66, 23);
		panel.add(Label1);
		
		JLabel Label2 = new JLabel("Word2");
		Label2.setFont(new Font("Cambria", Font.PLAIN, 18));
		Label2.setBounds(14, 316, 66, 24);
		panel.add(Label2);
		
		
		ShortPath.setBounds(14, 419, 153, 27);
		panel.add(ShortPath);
		
		comboBoxFr.setBounds(55, 459, 112, 24);
		panel.add(comboBoxFr);
		
		comboBoxTo.setBounds(55, 496, 112, 24);
		panel.add(comboBoxTo);
		
		comboBoxNo.setBounds(55, 575, 112, 24);
		panel.add(comboBoxNo);
		
		JLabel lblF = new JLabel("Fr");
		lblF.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblF.setBounds(14, 459, 39, 23);
		panel.add(lblF);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblTo.setBounds(14, 496, 39, 23);
		panel.add(lblTo);
		
		JLabel lblNo = new JLabel("No");
		lblNo.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblNo.setBounds(14, 575, 39, 23);
		panel.add(lblNo);
		
		
		ShowCertainPath.setBounds(14, 533, 153, 27);
		panel.add(ShowCertainPath);
		
		
		BeginRandom2.setBounds(14, 742, 153, 27);
		panel.add(BeginRandom2);
		
		
		StopRandom.setBounds(14, 782, 153, 27);
		panel.add(StopRandom);
		
		JLabel lblType = new JLabel("Type-1");
		lblType.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblType.setBounds(14, 718, 66, 24);
		panel.add(lblType);
		
		JLabel lblType_1 = new JLabel("Type-2");
		lblType_1.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblType_1.setBounds(14, 834, 66, 24);
		panel.add(lblType_1);
		
		
		Save.setBounds(14, 53, 153, 27);
		panel.add(Save);
		
		JLabel lblFunction = new JLabel("Function-1");
		lblFunction.setForeground(Color.GRAY);
		lblFunction.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblFunction.setBounds(14, 139, 153, 23);
		panel.add(lblFunction);
		
		JLabel lblFunction_1 = new JLabel("Function-2");
		lblFunction_1.setForeground(Color.GRAY);
		lblFunction_1.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblFunction_1.setBounds(14, 211, 153, 23);
		panel.add(lblFunction_1);
		
		JLabel lblFunction_2 = new JLabel("Function-3");
		lblFunction_2.setForeground(Color.GRAY);
		lblFunction_2.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblFunction_2.setBounds(14, 395, 153, 23);
		panel.add(lblFunction_2);
		
		JLabel lblFunction_3 = new JLabel("Function-4");
		lblFunction_3.setForeground(Color.GRAY);
		lblFunction_3.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblFunction_3.setBounds(14, 694, 153, 23);
		panel.add(lblFunction_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(200, 32, 1311, 943);
		contentPane.add(scrollPane);
		label.setBackground(Color.WHITE);
		
		label.setHorizontalAlignment(JLabel.CENTER);
		scrollPane.setViewportView(label);
		
		JLabel lblInputText = new JLabel("Input Text");
		lblInputText.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblInputText.setHorizontalAlignment(SwingConstants.CENTER);
		lblInputText.setBounds(1525, 0, 348, 27);
		contentPane.add(lblInputText);
		
		JLabel lblPicture = new JLabel("Picture");
		lblPicture.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblPicture.setHorizontalAlignment(SwingConstants.CENTER);
		lblPicture.setBounds(200, 0, 1311, 27);
		contentPane.add(lblPicture);
		
		JLabel lblShowPlace = new JLabel("Show Place");
		lblShowPlace.setFont(new Font("Cambria", Font.PLAIN, 18));
		lblShowPlace.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowPlace.setBounds(1525, 108, 348, 29);
		contentPane.add(lblShowPlace);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(1525, 135, 348, 840);
		contentPane.add(scrollPane_1);
		
		OutText = new JTextArea();
		scrollPane_1.setViewportView(OutText);
		OutText.setEditable(false);
		OutText.setColumns(10);
		OutText.setLineWrap(true);
		
		InText = new JTextArea();
		InText.setBounds(1525, 32, 348, 64);
		contentPane.add(InText);
		InText.setColumns(10);
		
		OpenAction openAction = new OpenAction();
		Open.addActionListener(openAction);
		
		queryBridgeWordsAction QueryBridgeWordsAction = new queryBridgeWordsAction();
		QueryBridgeWords.addActionListener(QueryBridgeWordsAction);
		
		showGraphAction ShowGraph = new showGraphAction();
		Show.addActionListener(ShowGraph);
		
		generateNewTextAction GenerateNewTextAction = new generateNewTextAction();
		NewText.addActionListener(GenerateNewTextAction);
		
		beginRandomWalkAction BeginRandomWalkAction = new beginRandomWalkAction();
		BRandomWalk.addActionListener(BeginRandomWalkAction);
		
		continueRandomWalkAction ContinueRandomWalkAction = new continueRandomWalkAction();
		CRandomWalk.addActionListener(ContinueRandomWalkAction);
		
		beginRandomWalkAction2 BeginRandomWalkAction2 = new beginRandomWalkAction2();
		BeginRandom2.addActionListener(BeginRandomWalkAction2);
		
		stopRandomWalkAction StopRandomWalkAction = new stopRandomWalkAction();
		StopRandom.addActionListener(StopRandomWalkAction);
		
		shortPathAction ShortPathAction  = new shortPathAction();
		ShortPath.addActionListener(ShortPathAction);
		
		showCertainPathAction ShowCertainPathAction  = new showCertainPathAction();
		ShowCertainPath.addActionListener(ShowCertainPathAction);
		
		saveFileAction SaveFileAction  = new saveFileAction();
		Save.addActionListener(SaveFileAction);
		
	}
	private class OpenAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {  
	        // TODO Auto-generated method stub  
	        JFileChooser jfc=new JFileChooser();  
	        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
	        jfc.showDialog(new JLabel(), "选择");  
	        File file=jfc.getSelectedFile();  
	        if (file == null) return;
	        filename =  file.getAbsolutePath();
	        System.out.println(filename);
	        WordGraph = MyFile.createDirectedGraph(filename);
	        if (WordGraph == null){
	        	JOptionPane.showMessageDialog(contentPane,"No text in the file!","Error",JOptionPane.ERROR_MESSAGE);
		        CRandomWalk.setEnabled(false);
		        QueryBridgeWords.setEnabled(false);
		    	Show.setEnabled(false);
		    	NewText.setEnabled(false);
		    	BRandomWalk.setEnabled(false);
		    	ShortPath.setEnabled(false);
		    	ShortPath.setEnabled(false);
		    	ShowCertainPath.setEnabled(false);
		    	StopRandom.setEnabled(false);
		    	BeginRandom2.setEnabled(false);
		    	Save.setEnabled(false);
		    	comboBoxFr.removeAllItems();
				comboBoxTo.removeAllItems();
	        	return;
	        }
	        CRandomWalk.setEnabled(true);
	        QueryBridgeWords.setEnabled(true);
	    	Show.setEnabled(true);
	    	NewText.setEnabled(true);
	    	BRandomWalk.setEnabled(true);
	    	ShortPath.setEnabled(true);
	    	BeginRandom2.setEnabled(true);
	    	randomWalk = new RandomWalk(WordGraph);
	    	getNewString(WordGraph.getNodeList());
	        ShowGraph.showDirectedGraph(WordGraph);
	        flush();
		}
	}
	private class saveFileAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {  
			//弹出文件选择框
			JFileChooser chooser = new JFileChooser();
			
			//后缀名过滤器
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "图像文件png", "png");
			chooser.setFileFilter(filter);
			
			//下面的方法将阻塞，直到【用户按下保存按钮且“文件名”文本框不为空】或【用户按下取消按钮】
			int option = chooser.showSaveDialog(null);
			if(option==JFileChooser.APPROVE_OPTION){	//假如用户选择了保存
				File file = chooser.getSelectedFile();
				String fname = chooser.getName(file);	//从文件名输入框中获取文件名	
				//假如用户填写的文件名不带我们制定的后缀名，那么我们给它添上后缀
				if(fname.indexOf(".png")==-1){
					file=new File(chooser.getCurrentDirectory(),fname+".png");
				}
				try {
					FileOutputStream fos = new FileOutputStream(file);
					
					FileInputStream ins = new FileInputStream("tmp/img.png");
				        byte[] b = new byte[1024];
				        int n=0;
				        while((n=ins.read(b))!=-1){
				        	fos.write(b, 0, n);
				        }
				        
				    ins.close();
					fos.close();
					
				} catch (IOException c) {
					System.err.println("IO异常");
					c.printStackTrace();
				}	
			}
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
				WordGraph.clearColor();
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
			WordGraph.clearColor();
			ShowGraph.showDirectedGraph(WordGraph);
	        flush();
		}
	}
	
	private class beginRandomWalkAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			beginRandom();
		}
	}
	private class stopRandomWalkAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			t.cancel();
			SetAllTrue();
			StopRandom.setEnabled(false);
	    	BeginRandom2.setEnabled(true);
		}
	}
	private class beginRandomWalkAction2 implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			beginRandom();
			SetAllFalse();
			StopRandom.setEnabled(true);
	    	BeginRandom2.setEnabled(false);
			t = new Timer();
	    	t.schedule(new RandomTask(), 1000, 1000);
		}
	}
	void beginRandom(){
		randomWalk.clear(WordGraph);
		WordGraph.clearColor();
		RandomPath = "";
		LastNode = randomWalk.randomWalk(WordGraph);
		System.out.println(LastNode+"c");
		RandomPath += LastNode;
		WordGraph.setNodeColor(LastNode, "greenyellow");
		OutText.setText(RandomPath);
		ShowGraph.showDirectedGraph(WordGraph);
		CRandomWalk.setEnabled(true);
		flushOnly();
	}
	int continueRandom(){
		NowNode = randomWalk.randomWalk(WordGraph);
		if (NowNode.equals("")){
			JOptionPane.showMessageDialog(contentPane,"There is no edge or the same edge in path!","Sorry",JOptionPane.ERROR_MESSAGE);
			flush();
			return 0;
		}
		else {
			RandomPath += "->" + NowNode;
			WordGraph.setNodeColor(NowNode, "greenyellow");
			WordGraph.setEdgeColor(LastNode,NowNode, "greenyellow");
			LastNode = NowNode;
			OutText.setText(RandomPath);
			ShowGraph.showDirectedGraph(WordGraph);
			flushOnly();
			return 1;
		}
	}
	private class continueRandomWalkAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			continueRandom();
		}
	}
	private class generateNewTextAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String text = InText.getText();
	        String newText = GenerateNewText.generateNewText(WordGraph, text);
	        OutText.setText("The New Text is:"+newText);
		}
	}
	private class shortPathAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			WordGraph.clearColor();
			ShortestPathString = "";
			comboBoxNo.removeAllItems();
			if (comboBoxTo.getSelectedItem().toString().equals("All..")){
				List<Node> tempnode = WordGraph.getNodeList();
				int fi = 0;
				for (Node node : tempnode){
					ShortestPathString += String.format("%nDestination%d :  %s%n",fi,node.name);
					shortestPath = ShortestPath.calcShortestPath(WordGraph,comboBoxFr.getSelectedItem().toString(),node.name);
					if (shortestPath.size() == 0) {
						ShortestPathString += String.format("No Way! %n");
						continue;
					}
					int i = 0;
					int length = 0;
					for (String Path : shortestPath){
						ShortestPathString += String.format("Path%d :%n%s%n",i,Path);
						String[] tempNode = Path.split("->");
						for (int j = 0 ; j < tempNode.length ; j++){
							if (j+1<tempNode.length){
								//WordGraph.setEdgeColor(tempNode[j], tempNode[j+1],color[i%8]);
								if (i==0){
									length += WordGraph.getEdge(tempNode[j],tempNode[j+1]).weight;
								}
							}
							//WordGraph.setNodeColor(tempNode[j],color[i%8]);
						}
						++i;
					}
					fi++;
					ShortestPathString += String.format("PathLength : %d%n",length);
					OutText.setText(ShortestPathString);
				
				}
				ShowGraph.showDirectedGraph(WordGraph);
		        flush();
			}
			else {
				shortestPath = ShortestPath.calcShortestPath(WordGraph,comboBoxFr.getSelectedItem().toString(),comboBoxTo.getSelectedItem().toString());
				Integer i = 0;
				int length = 0;
				if (shortestPath.size() == 0) {
						ShortestPathString += String.format("No Way! %n");
				}
				for (String Path : shortestPath){
					ShortestPathString += String.format("Path%d :%n%s%n",i,Path);
					String[] tempNode = Path.split("->");
					for (int j = 0 ; j < tempNode.length ; j++){
						if (j+1<tempNode.length){
							WordGraph.setEdgeColor(tempNode[j], tempNode[j+1],color[i%8]);
							if (i==0){
								length += WordGraph.getEdge(tempNode[j],tempNode[j+1]).weight;
							}
						}
						WordGraph.setNodeColor(tempNode[j],color[i%8]);
					}
					comboBoxNo.addItem(i.toString());
					++i;
				}
				if (shortestPath.size() > 0) {
					ShortestPathString += String.format("PathLength : %d%n",length);
					ShowCertainPath.setEnabled(true);
					comboBoxNo.addItem("All..");
				}
				OutText.setText(ShortestPathString);
				ShowGraph.showDirectedGraph(WordGraph);
		        flushOnly();
			}
		}
	}
	private class showCertainPathAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			WordGraph.clearColor();
			ShortestPathString = "";
			if (comboBoxNo.getSelectedItem().equals("All..")){
				Integer i = 0;
				int length = 0;
				for (String Path : shortestPath){
					ShortestPathString += String.format("Path%d :%n%s%n",i,Path);
					String[] tempNode = Path.split("->");
					for (int j = 0 ; j < tempNode.length ; j++){
						if (j+1<tempNode.length){
							WordGraph.setEdgeColor(tempNode[j], tempNode[j+1],color[i%8]);
							if (i==0){
								length += WordGraph.getEdge(tempNode[j],tempNode[j+1]).weight;
							}
						}
						WordGraph.setNodeColor(tempNode[j],color[i%8]);
					}
					++i;
				}
				if (shortestPath.size() > 0) {
					ShortestPathString += String.format("PathLength : %d%n",length);
				}
				OutText.setText(ShortestPathString);
			}
			else {
				String temps = comboBoxNo.getSelectedItem().toString();
				System.out.println(temps);
				Integer i = Integer.valueOf(temps);
				String Path = shortestPath.get(i);
				ShortestPathString += String.format("Path :%n%s%n",Path);
				int length = 0;
				String[] tempNode = Path.split("->");
				for (int j = 0 ; j < tempNode.length ; j++){
					if (j+1<tempNode.length){
						WordGraph.setEdgeColor(tempNode[j], tempNode[j+1],color[i%8]);
						length += WordGraph.getEdge(tempNode[j],tempNode[j+1]).weight;
					}
					WordGraph.setNodeColor(tempNode[j],color[i%8]);
				}
				++i;
				ShortestPathString += String.format("PathLength : %d%n",length);
				OutText.setText(ShortestPathString);
			}
			ShowGraph.showDirectedGraph(WordGraph);
	        flushOnly();
		}
	}
	void flush(){
		Save.setEnabled(true);
		CRandomWalk.setEnabled(false);
		comboBoxNo.removeAllItems();
		ShowCertainPath.setEnabled(false);
		try {
			ImageIcon image = new ImageIcon(ImageIO.read(new File("tmp/img.png")));
			//image.setImage(image.getImage().getScaledInstance(label.getWidth(), label.getHeight(),Image.SCALE_DEFAULT ));
			label.setIcon(image);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	void  SetAllFalse(){
		CRandomWalk.setEnabled(false);
        QueryBridgeWords.setEnabled(false);
    	Show.setEnabled(false);
    	NewText.setEnabled(false);
    	BRandomWalk.setEnabled(false);
    	ShortPath.setEnabled(false);
    	ShowCertainPath.setEnabled(false);
    	Open.setEnabled(false);
	}
	void SetAllTrue(){
        QueryBridgeWords.setEnabled(true);
    	Show.setEnabled(true);
    	NewText.setEnabled(true);
    	BRandomWalk.setEnabled(true);
    	ShortPath.setEnabled(true);
    	Open.setEnabled(true);
	}
	void flushOnly(){
		Save.setEnabled(true);
		try {
			ImageIcon image = new ImageIcon(ImageIO.read(new File("tmp/img.png")));
			//image.setImage(image.getImage().getScaledInstance(label.getWidth(), label.getHeight(),Image.SCALE_DEFAULT ));
			label.setIcon(image);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	private void getNewString(List<Node> nodes){
		comboBoxFr.removeAllItems();
		comboBoxTo.removeAllItems();
		for (Node node : nodes){
			comboBoxFr.addItem(node.name);
			comboBoxTo.addItem(node.name);
		}
		comboBoxTo.addItem("All..");
	}
	class RandomTask extends TimerTask{

	    @Override
	    public void run() {
	    	int tmp = continueRandom();
	    	if (tmp == 0){
	    		t.cancel();
				SetAllTrue();
				StopRandom.setEnabled(false);
		    	BeginRandom2.setEnabled(true);
	    	}
	    }
	}
}
