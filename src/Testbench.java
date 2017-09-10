
public class Testbench {
	public static void main(String args[]) {
		Graph G = new Graph();
		G.addEdge("A", "B");
		System.out.println(G.getNode("A").edges.get(0).to);
	}
}
