import java.util.ArrayList;
import java.util.List;

public class Node {
	public String name;
	public String color;
	public List<Edge> edges=new ArrayList<>();
	
	/**
	 * Initialize node with name, default color is black.
	 * @param name
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
