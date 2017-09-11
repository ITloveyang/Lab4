import java.io.*;
import java.util.ArrayList;

public class MyFile {
	static ArrayList<String> Node = new ArrayList<>();
	static ArrayList<String> Edge = new ArrayList<>();
	public static String[] S = new String[3];
	public static void createDirectedGraph(String filename){
		File file = new File(filename);
		if  (!file.exists()){
			return ;
		}
		Reader reader = null;
		
		try {
			reader = new InputStreamReader(new FileInputStream(file));
			int tempint;
			char tempchar;
			String tempstring = null;
			while ((tempint = reader.read())!= -1){
				tempchar = (char) tempint;
				if ( IsChar( tempchar ) ){
					tempstring = "";
					while (IsChar(tempchar) && tempint != -1){
						tempstring += tempchar;
						tempint = reader.read();
						tempchar = (char)tempint;
						
					}
					Node.add(tempstring);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			 if (reader != null) {
	            try {
	                    reader.close();
	            } catch (IOException e1) {
	            }
	         }
		}
		for (int i = 1 ; i < Node.size() ; i++){
			Edge.add( Node.get( i-1 ) +" "+ Node.get( i ));
		}
		Edge.sort(null);
		int cnt = 1;
		for (int i = 0 ; i < Edge.size() ; i++){
			if (i == 0) cnt = 1;
			else if (Edge.get(i).equals( Edge.get(i - 1))){
				cnt++;
			}
			else{
				S = Edge.get(i).split(" ");
				//Gragh.addedge(S[0],s[1],cnt);
				System.out.println(S[0]+" "+S[1]+" " + cnt);
				cnt = 1;
			}
		}
		if (Node.size() > 1&&cnt!=1){
			S = Edge.get(Edge.size()-1).split(" ");
			//Gragh.addedge(S[0],s[1],cnt);
			System.out.println(S[0]+" "+S[1]+" " + cnt);
		}
		else{
			// 0 Node 1 Node 
		}
		return;
	}
	static boolean IsChar(char TestChar){
		if ((TestChar>='a'&& TestChar <='z')||(TestChar>='A'&& TestChar <='Z'))
			return true;
		else return  false;
	}
}
