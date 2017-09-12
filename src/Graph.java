import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Graph {
	private Map<String,Node> nodes = new HashMap<>();
	
	/**
	 * Add a node to the graph with the node name, a node contains a edge list,
	 * its initial color will be set to "none" 
	 * @param name String
	 */
	public void addNode(String name) {
		nodes.put(name,new Node(name));
	}
	
	/**
	 * Delete a node.
	 * @param name String
	 */
	public void deleteNode(String name) {
		nodes.remove(name);
	}
	
	/**
	 * An auxiliary function which help find the edge index.
	 * @param from String
	 * @param to String
	 * @return Integer, the index of the target edge in the node "from"'s out edge list.
	 */
	private Integer findEdgeIndex(String from, String to) {
		Node node=nodes.get(from);
		if(node==null)return null;
		for(int i=0;i<node.edges.size();i++) {
			if(node.edges.get(i).to.equals(to)) {
				return i;
			}
		}
		return null;
	}
	
	/**
	 * Add an edge with from and to node name and weight,
	 * from---weight--->to.
	 * nodes will be created if it is not existed.
	 * if the edge has be in the graph, its weight will be add by 1.
	 * @param from String
	 * @param to String
	 * @param weight int
	 */
	public void addEdge(String from, String to, int weight) {
		if(nodes.get(from)==null)nodes.put(from, new Node(from));
		if(nodes.get(to)==null)nodes.put(to, new Node(to));
		Integer index=findEdgeIndex(from,to);
		if(index!=null)nodes.get(from).edges.get(index).weight+=weight;
		else nodes.get(from).edges.add(new Edge(from,to));
	}

	/**
	 * Add an edge with from and to node name, default edge weight is 1.
	 * @param from String
	 * @param to String
	 */
	public void addEdge(String from, String to) {
		addEdge(from,to,1);
	}
	
	/**
	 * Delete an existed edge.
	 * @param from String
	 * @param to String
	 */
	public void deleteEdge(String from, String to) {
		Integer index=findEdgeIndex(from,to);
		if(index!=null)nodes.get(from).edges.remove((int)index);
	}
	
	/**
	 * Set a node's color.
	 * @param name String
	 * @param color String
	 */
	public void setNodeColor(String name, String color) {
		Node node=nodes.get(name);
		if(node!=null)node.color=color;
	}
	
	/**
	 * Set an edge's color.
	 * @param from String
	 * @param to String
	 * @param color String
	 */
	public void setEdgeColor(String from, String to, String color) {
		Integer index=findEdgeIndex(from,to);
		if(index!=null)nodes.get(from).edges.get(index).color=color;
	}
	
	/**
	 * Get a reference of a node by the name.
	 * @param name String
	 * @return Node
	 */
	public Node getNode(String name) {
		return nodes.get(name);
	}
	
	/**
	 * Get a list of reference of all nodes.
	 * @return
	 */
	public List<Node> getNodeList(){
		List<Node> nodeList=new ArrayList<>();
		for(Node node:nodes.values())nodeList.add(node);
		return nodeList;
	}
	
	/**
	 * Clear the Graph. 
	 */
	public void clear() {
		nodes.clear();
	}
	
	/**
	 * Set all nodes' and edges'color to none.
	 */
	public void clearColor() {
		if(nodes.isEmpty())return;
		for(Node node:nodes.values()) {
			node.clearColor();
			for(Edge edge:node.edges) {
				edge.clearColor();
			}
		}
	}
	
}
