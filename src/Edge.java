
public class Edge {
	public String to;
	public int weight;
	public String color;
	
	public Edge(String to) {
		this.to=to;
		this.weight=1;
		this.color="none";
	}
	public Edge(String to, int weight) {
		this.to=to;
		this.weight=weight;
		this.color="none";
	}
	public Edge(String to, int weight, String color) {
		this.to=to;
		this.weight=weight;
		this.color=color;
	}
}
