
public class Testbench {
	public static void main(String args[]) {
		Graph G = new Graph();
		G.addEdge("A", "B");
		G.addEdge("A", "C");
		G.setEdgeColor("A", "B", "purple");
		G.setNodeColor("B", "red");
		ShowGraph.showDirectedGraph(G);
	}
}
