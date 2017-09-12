import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomWalk {
	private Node nowNode;
	private StringBuilder path;
	private boolean stop;
	private Set<String> eSet;
	
	public RandomWalk(Graph G, String name) {
		this.nowNode=G.getNode(name);
		this.path=new StringBuilder();
		this.stop=false;
		this.eSet=new HashSet<>();
	}
	
	public RandomWalk(Graph G) {
		this(G,null);
	}
	
	public void setStartNode(Graph G, String name) {
		clear();
		this.nowNode=G.getNode(name);
		this.stop=false;
	}
	
	public void clear() {
		this.nowNode=null;
		this.path=new StringBuilder();
		this.stop=false;	
		this.eSet.clear();
	}
	
	private int randomInt(int l,int r) {
		return new Random().nextInt(r)%(r-l+1)+l;
	}
	
	private Node getNextNodeRef(Graph G) {
		if (nowNode==null || stop)return null;
		if (nowNode.edges.size()<=0) {
			stop=true;
			return null;
		}
		Edge nextEdge = nowNode.edges.get(randomInt(0,nowNode.edges.size()-1));
		Node nextNode=G.getNode(nextEdge.to);
		if(eSet.contains(nextEdge.from+"->"+nextEdge.to)) 
			stop=true;
		else 
			eSet.add(nextEdge.from+"->"+nextEdge.to);
		
		path.append(nextNode.name);
		nowNode=nextNode;
		
		return nowNode;
	}
	
	public boolean hasNext() {
		return !(stop || nowNode.edges.size()<=0);
	}
	
	public String nextNode(Graph G) {
		Node node = getNextNodeRef(G);
		if(node==null)return "";
		else return node.name;
	}
	
	public String getPath() {
		return path.toString();
	}
}
