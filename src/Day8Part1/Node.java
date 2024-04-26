package Day8Part1;

public class Node {
	private String id;
	private String[] conn; //Connection: from point0 to point1	
	
	public Node (String id, String[] conns) {
		this.id = id;
		this.conn = conns;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String[] getConn() {
		return conn;
	}
	public void setConn(String[] c) {
		this.conn = c;
	}
}