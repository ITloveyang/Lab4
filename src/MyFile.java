import java.io.*;
import java.util.ArrayList;

public class MyFile {
	
	/**
	 * open the file, read , and create a Graph;
	 * @param filename String
	 * @return Graph
	 */
	public static Graph createDirectedGraph(String filename){
		Graph G = new Graph();
		ArrayList<String> node = new ArrayList<>();
		ArrayList<String> edge = new ArrayList<>();
		String[] S = new String[3];
		File file = new File(filename);
		if  (!file.exists()){
			return null;
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
					node.add(tempstring);
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
		for (int i = 1 ; i < node.size() ; i++){
			edge.add( node.get( i-1 ).toLowerCase() +" "+ node.get( i ).toLowerCase());
		}
		edge.sort(null);
		int cnt = 1;
		for (int i = 0 ; i < edge.size() ; i++){
			if (i+1== edge.size() || !edge.get(i).equals(edge.get(i + 1))){
				S = edge.get(i).split(" ");
				G.addEdge(S[0],S[1],cnt);
				System.out.println(S[0]+" "+S[1]+" " + cnt);
				cnt = 1;
			}
			else{
				cnt++;
			}
		}
		if (node.size() == 1){
			 G.addNode(node.get(0));
		}
		else if (node.size() == 0){
			return null;
		}
		return G;
	}
	/**
	 * Judge weather a Char
	 * @param TestChar
	 * @return
	 */
	static boolean IsChar(char TestChar){
		if ((TestChar>='a'&& TestChar <='z')||(TestChar>='A'&& TestChar <='Z'))
			return true;
		else return  false;
	}
}
