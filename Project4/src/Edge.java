/*
 * Thomas H. Craw
 * Project 4
 * Edge class
 */
public class Edge {

	public String id;    
	public Vertex v1;
	public Vertex v2;
	public static double weight;
	boolean known;
	
	public Edge(String street, Vertex v, Vertex t){   //Edge constructor takes in the id number given, and the two vertexs that it connects.
		                                              //Since the given file only gives me id#'s of the vertexes it connects I had to make a convert method to convert the vertex id#'s to actual vertexs.
		id = street;
		v1 = v;
		v2 = t;
		weight = Math.sqrt((v1.x - v2.x)*(v1.x - v2.x) + (v1.y - v2.y)*(v1.y - v2.y));  //this finds the weight of the edge, used in the algoritms.
		
	}
	
	public static double getWeight(){
		
		return weight;
		
	}
	
	
	
	

}
