import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomWalk {
	private Node nowNode,startNode;
	private StringBuilder path;
	private boolean stop;
	private Set<String> eSet;
	
	public RandomWalk(Graph G, String name) {
		this.startNode=G.getNode(name);
		this.nowNode=null;
		this.path=new StringBuilder();
		this.stop=false;
		this.eSet=new HashSet<>();
	}
	
	/**
	 * random select a start node.
	 * @param G
	 */
	public RandomWalk(Graph G) {
		this(G,G.getNodeList().get(randomInt(0,G.getNodeList().size()-1)).name);
	}
	
	public void setStartNode(Graph G, String name) {
		clear(G);
		this.startNode=G.getNode(name);
	}
	
	public void clear(Graph G) {
		this.startNode=G.getNodeList().get(randomInt(0,G.getNodeList().size()-1));
		this.nowNode=null;
		this.path=new StringBuilder();
		this.stop=false;
		this.eSet.clear();
	}
	
	private static int randomInt(int l,int r) {
		if(r<=0 || l>r)return 0;
		return new Random().nextInt(((r+1+r-l)/(r-l+1))*(r-l+1))%(r-l+1)+l;
	}
	
	private Node getNextNode(Graph G) {
		if (startNode==null || stop)return null;
		if (nowNode==null) {
			nowNode=startNode;
			return nowNode;
		}
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
		return !stop && ((startNode!=null && nowNode==null) || (nowNode!=null && nowNode.edges.size()>0));
	}
	
	public String randomWalk(Graph G) {
		Node node = getNextNode(G);
		if(node==null)return "";
		else return node.name;
	}
	
	public String getPath() {
		return path.toString();
	}
}
