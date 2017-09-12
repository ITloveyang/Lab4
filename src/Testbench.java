
public class Testbench {
	public static void main(String args[]) {
		Graph G = new Graph();
		G.addEdge("A", "B");
		G.addEdge("A", "C");
		G.addEdge("A", "D");
		G.addEdge("A", "E");
		G.addEdge("D", "C");
		G.addEdge("C", "A");
		ShowGraph.showDirectedGraph(G);
		RandomWalk rw=new RandomWalk(G,"A");
		while(rw.hasNext()) {
			System.out.println(rw.randomWalk(G));
		}
	}
}
