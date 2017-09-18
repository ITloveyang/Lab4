import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;



public class ShowGraph {
	static String newLine=System.getProperty("line.separator");
	static String osName=System.getProperty("os.name");

	public static void showDirectedGraph(Graph G) {
		StringBuilder dotText=new StringBuilder();
		dotText.append(String.format("digraph G{"+newLine));
		for(Node node:G.getNodeList()) {
			dotText.append(node.name);
			if(!node.color.equals("black"))dotText.append(String.format(" [style=filled, fillcolor=%s]",node.color));
			dotText.append(";"+newLine);
		}
		dotText.append(newLine);
		for(Node node:G.getNodeList()) {
			for(Edge edge:node.edges) {
				dotText.append(String.format("%s->%s[label=%d]", edge.from,edge.to,edge.weight));
				if(!edge.color.equals("black"))dotText.append(String.format("[style=bold, color=%s]",edge.color));
				dotText.append(";"+newLine);
			}
		}
		dotText.append("}"+newLine);
		
		String graphFilePath=Config.tmpPath+"graph.gv";
		try {
			File tmpf=new File(Config.tmpPath);
			if(!tmpf.exists()) {
				tmpf.mkdirs();
			}
			FileWriter fw=new FileWriter(graphFilePath);
			BufferedWriter bufw = new BufferedWriter(fw);
			bufw.write(dotText.toString());
			bufw.close();
		}catch (Exception e) {
			throw new RuntimeException("Failed to open file");
		}
		
		generateImage(graphFilePath);
	}

	private static void generateImage(String filename) {
		if(osName.startsWith("win")||osName.startsWith("Win")) {
			generateImageForWindows(filename);
		}
		else {
			generateImageForLinux(filename);
		}
	}
	
	private static void generateImageForWindows(String filename) {
		Runtime rt=Runtime.getRuntime();
		try {
			String[] args= {Config.dotForWindows,filename,"-Tpng","-o",Config.tmpPath+"img.png"};
			Process process = rt.exec(args);
			process.waitFor();
			
		}catch (Exception e) {
			throw new RuntimeException("Failed to generate image.");
		}
		
	}

	private static void generateImageForLinux(String filename) {
		Runtime rt=Runtime.getRuntime();
		try {
			String[] args= {"/bin/sh", "-c", Config.dotForLinux,filename,"-Tpng","-o",Config.tmpPath+"img.png"};
			Process process = rt.exec(args);
			process.waitFor();
			
		}catch (Exception e) {
			throw new RuntimeException("Failed to generate image.");
		}
		
	}
	
}
