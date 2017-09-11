
public class querry {
	public static String queryBridgeWords(Graph wordGraph, String word1, String word2) {
		// TODO Auto-generated method stub
		
		Node node1 = wordGraph.getNode(word1);
		Node node2 = wordGraph.getNode(word2);
		String S = "";
		if (node1 == null && node2 == null) return "3";
		else if (node1 == null) return "1";
		else if (node2 == null) return "2";
		else {
			for(Edge edge1:node1.edges) {
				String tempans = edge1.to;
				Node tempnode = wordGraph.getNode(tempans);
				for(Edge edge2:tempnode.edges){
					if (edge2.to.equals(word2)){
						if (word2.equals(word1)&&word2.equals(tempans)&&edge2.weight < 2)continue;
						if (S.equals("")) S = S+tempans;
						else S = S + " " + tempans;
						break;
					}
				}
			}
		}
		if (S.equals("")){
			return "0";
		}
		else return S;
	}
}
