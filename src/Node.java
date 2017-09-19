import java.util.ArrayList;
import java.util.List;

/**
 * Node class.
 * <p>each node has its name,color and a list of out edges.
 * @author HanYue
 *
 */
public class Node {
	public String name;
	public String color;
	public List<Edge> edges=new ArrayList<>();
	
	/**
	 * Initialize node with name, default color is black.
	 * @param name String node name=word
	 */
	public Node(String name) {
		this.name=name;
		this.color="black";
	}
	
	/**
	 * Set color to default.
	 */
	public void clearColor() {
		this.color="black";
	}
	
}
