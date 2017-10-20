import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


/**
 * Make dot script for Graph and run graphviz to generate a png image of graph.
 * @author HanYue
 *
 */
public class ShowGraph {
    /*get new line*/static String newLine=System.getProperty("line.separator");    //获得当前系统的换行符
    static String osName=System.getProperty("os.name");
    
    /**
     * build dot script for graph G.
     * @param G Graph
     */
    public static void showDirectedGraph(Graph G) {
        StringBuilder dotText=new StringBuilder();    //StringBuilder在这里效率要高于用String加加加
        dotText.append(String.format("digraph G{"+newLine));    //写入开头
        for(Node node:G.getNodeList()) {    //遍历顶点写入顶点属性
            dotText.append(node.name);
            if(!node.color.equals("black"))dotText.append(String.format(" [style=filled, fillcolor=%s]",node.color));
            dotText.append(";"+newLine);
        }
        dotText.append(newLine);
        for(Node node:G.getNodeList()) {    //遍历边写入边属性
            for(Edge edge:node.edges) {
                dotText.append(String.format("%s->%s[label=%d]", edge.from,edge.to,edge.weight));
                if(!edge.color.equals("black"))dotText.append(String.format("[style=bold, color=%s]",edge.color));
                dotText.append(";"+newLine);
            }
        }
        dotText.append("}"+newLine);    //写入结束
        
        //把生成好的脚本写到指定的缓存路径下
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
    
    /**
     * run graphviz on different system. 
     * @param filename dot script filePath
     */
    private static void generateImage(String filename) {
        if(osName.startsWith("win")||osName.startsWith("Win")) {    //判断系统使用不同的调用方法
            generateImageForWindows(filename);
        }
        else {
            generateImageForLinux(filename);
        }
    }
    
    /**
     * run graphviz on windows. 
     * @param filename filename dot script filePath
     */
    private static void generateImageForWindows(String filename) {
        Runtime rt=Runtime.getRuntime();    //使用Runtime执行cmd命令
        try {
            String[] args= {Config.dotForWindows,filename,"-Tpng","-o",Config.tmpPath+"img.png"};
            Process process = rt.exec(args);
            process.waitFor();
            
        }catch (Exception e) {
            throw new RuntimeException("Failed to generate image.");
        }
        
    }
    
    /**
     * run graphviz on unix. 
     * @param filename filename dot script filePath
     */
    private static void generateImageForLinux(String filename) {
        Runtime rt=Runtime.getRuntime();    ////使用Runtime执行bash命令
        try {
            String[] args= {"/bin/sh", "-c", Config.dotForLinux,filename,"-Tpng","-o",Config.tmpPath+"img.png"};
            Process process = rt.exec(args);
            process.waitFor();
            
        }catch (Exception e) {
            throw new RuntimeException("Failed to generate image.");
        }
        
    }
    
}
