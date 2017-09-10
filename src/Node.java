import java.util.ArrayList;
import java.util.List;

public class Node {
	public String name;
	public String color;
	public List<Edge> edges=new ArrayList<>();
	
	public Node(String name) {
		this.name=name;
		this.color="none";
	}
}
