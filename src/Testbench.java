
public class Testbench {
	public static void main(String args[]) {
		Graph G = new Graph();
		G.addEdge("A", "B");
		G.addEdge("A", "C");
		G.addEdge("A", "C");
		G.addEdge("A", "D");
		G.addEdge("A", "E");
		G.addEdge("D", "C");
		G.addEdge("C", "A");
		G.setNodeColor("A", "greenyellow");
		G.setNodeColor("B", "greenyellow");
		G.setEdgeColor("A", "B", "dodgerblue");
		ShowGraph.showDirectedGraph(G);
//		RandomWalk rw=new RandomWalk(G);
//		while(rw.hasNext()) {
//			System.out.println(rw.randomWalk(G));
//		}
		System.out.println(ShortestPath.calcShortestPath(G, "A","C"));
	}
}
