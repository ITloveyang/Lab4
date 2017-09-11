import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class showGraph {
	
	public static void showDirectedGraph(Graph G) {
		StringBuilder dotText=new StringBuilder();
		dotText.append("digraph G{\n");
		for(Node node:G.getNodeList()) {
			dotText.append(String.format("%s[color=%s];\n",node.name,node.color));
		}
		dotText.append("\n");
		for(Node node:G.getNodeList()) {
			for(Edge edge:node.edges) {
				dotText.append(String.format("%s->%s[label=%d, color=%s];\n", edge.from,edge.to,edge.weight,edge.color));
			}
		}
		dotText.append("}\n");
		
		try {
			FileWriter fw=new FileWriter("graph.gv");
			BufferedWriter bufw = new BufferedWriter(fw);
			bufw.write(dotText.toString());
			bufw.close();
		}catch (IOException e) {
			throw new RuntimeException("Failed to open file");
		}
		
		generateImage("graph.gv");
	}

	private static void generateImage(String filename) {
		String graphvizPath=".\\graphviz-2.38\\release\\bin\\dot.exe";
		Runtime rt=Runtime.getRuntime();
		try {
			rt.exec("cmd /c "+graphvizPath+" "+filename+" -Tpng -o img.png");
		}catch (Exception e) {
			throw new RuntimeException("Failed to generate image.");
		}
	}
	
}
