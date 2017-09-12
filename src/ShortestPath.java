import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestPath {
	private static Map<String,Boolean> inQueue=new HashMap<>();
	private static Map<String,Integer> dis=new HashMap<>();
	private static final int INF=0x3f3f3f3f;
	private static List<String> tmpPath=new ArrayList<>();
	private static List<String> paths=new ArrayList<>();
	
	private static void init(Graph G) {
		inQueue.clear();
		dis.clear();
		for(Node node:G.getNodeList()) {
			inQueue.put(node.name, false);
			dis.put(node.name,INF);
		}
	}
	
	private static void spfa(Graph G, String st) {
		List<String> Q=new ArrayList<>();
		String u;
		
		init(G);
		Q.add(st);
		inQueue.put(st,true);
		dis.put(st, 0);
		while(!Q.isEmpty()) {
			u=Q.get(0);
			Q.remove(0);
			inQueue.put(u, false);
			for(Edge edge:G.getNode(u).edges) {
				if(dis.get(edge.to)>dis.get(u)+edge.weight) {
					dis.put(edge.to,dis.get(u)+edge.weight);
					if(!inQueue.get(edge.to)) {
						Q.add(edge.to);
						inQueue.put(edge.to, true);
					}
				}
			}
		}
	}
	
	private static void dfs(Graph G, String u, String ed) {
		if(u.equals(ed)) {
			StringBuilder path=new StringBuilder();
			for(String v:tmpPath) {
				path.append(v+"->");
			}
			path.append(ed);
			paths.add(path.toString());
			return;
		}
		
		for(Edge edge:G.getNode(u).edges) {
			if(dis.get(edge.to)==dis.get(u)+edge.weight) {
				tmpPath.add(u);
				dfs(G,edge.to, ed);
				tmpPath.remove(tmpPath.size()-1);
			}
		}
	}
	
	public static List<String> calcShortestPath(Graph G,String word1,String word2) {
		spfa(G,word1);
		tmpPath.clear();
		paths.clear();
		dfs(G,word1,word2);
		return paths;
	}
	
}
