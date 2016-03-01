/*
 * Thomas H. Craw
 * Project 4
 * Vertex class
 */
import java.util.ArrayList;  


public class Vertex {

	public String id;   //Takes in the id that represents the vertex(intersection).
	public double x;    //takes the x coordinate 
	public double y;    //takes the y coordinate.
	public ArrayList<Edge> edges;    //creates arraylist of edges to keep track of the edge that connects to this vertex.
	public double distance;       //to keep track of the current distance from a speciffic vertex.
	public boolean known;         //useful in algoritms keeping tack of the verticies already visited.
	public Edge path;             //the attached edge, that is used to find the path it takes from one vertex to another. what paths(edges ) does it use.
	
	public Vertex(String street, double xcoor, double ycoor){   //constructor for vertex object takes in the id number, x and y coordinate.
		
		id = street;
		x = xcoor;
		y = ycoor;
		edges = new ArrayList<Edge>();    //creates arraylist for its edges attached to it.
		
	}
	

	public void insert(Edge e){     //insert method is used so that verticies can keep track of the edges there connected to.
		
		edges.add(e); 
		
	}
	
	

}
