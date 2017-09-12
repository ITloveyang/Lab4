
public class GenerateNewText {
	static Graph tempGraph;
	public static String generateNewText(Graph wordGraph, String Text) {
		// TODO Auto-generated method stub
		tempGraph = wordGraph;
		String tempS = standardize(Text);
		String[] S = tempS.split(" ");
		String Ans = "";
		String lastString = "";
		for (String nowString : S){
			if (lastString.equals("")){
				lastString = nowString;
				if (Ans.equals("")) Ans = Ans+nowString;
				else Ans = Ans + " " + nowString;
				continue;
			}
			Node node1 = wordGraph.getNode(lastString);
			Node node2 = wordGraph.getNode(nowString);
			if (node1 == null && node2 == null) {
				if (Ans.equals("")) Ans = Ans+nowString;
				else Ans = Ans + " " + nowString;
				lastString = nowString;
				continue;
			}
			int flag = 1;
			for(Edge edge1:node1.edges) {
				String tempans = edge1.to;
				Node tempnode = wordGraph.getNode(tempans);
				for(Edge edge2:tempnode.edges){
					if (edge2.to.equals(nowString)){
						if (nowString.equals(lastString)&&nowString.equals(tempans)&&edge2.weight < 2)continue;
						Ans = Ans + " " + tempans;
						flag = 0;
						break;
					}
				}
				if (flag == 0) break;
			}
			if (Ans.equals("")) Ans = Ans+nowString;
			else Ans = Ans + " " + nowString;
			lastString = nowString;
		}
		return Ans;
	}
	private static String standardize(String text) {
		// TODO Auto-generated method stub
		char tempchar;
		String Ans = "";
		int L = text.length();
		int i = 0;
		String tempstring = null;
		while (i<L){
			tempchar = text.charAt(i);
			if ( IsChar( tempchar ) ){
				tempstring = "";
				do{
					tempchar = text.charAt(i);
					tempstring += tempchar;
					i++;
				}while (i<L && IsChar(text.charAt(i)));
				if (Ans.equals("")) Ans = Ans + tempstring;
				else Ans = Ans + " " + tempstring;
			}
			i++;
		}
		return Ans;
	}
	static boolean IsChar(char TestChar){
		if ((TestChar>='a'&& TestChar <='z')||(TestChar>='A'&& TestChar <='Z'))
			return true;
		else return  false;
	}
}
