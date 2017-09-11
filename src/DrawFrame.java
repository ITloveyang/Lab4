import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class DrawFrame extends JFrame {
	private JPanel MyPanel;
	String filename;
	public DrawFrame()
	{   
		
		try{
		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch (Exception e){e.printStackTrace();}
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(2*screenWidth/5,2*screenWidth/5);
		setLocationByPlatform(true);
		
		MyPanel = new JPanel();
		
		JButton Open = new JButton("Open");
		MyPanel.add(Open);
		OpenAction openAction = new OpenAction();
		Open.addActionListener(openAction);
		
		add(MyPanel);
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
	        MyFile myfile = new MyFile();
	        myfile.createDirectedGraph(filename);
	        
		}
	}
	
	
}
