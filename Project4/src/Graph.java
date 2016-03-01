/*
 * Thomas H. Craw
 * Project 4
 * Graph class
 */
import java.util.ArrayList;

public class Graph {

	public ArrayList<Vertex> verticies;   //creates an arraylist of verticies
	
	
	public Graph(){    //constructor for the graph.
		
		verticies = new ArrayList<Vertex>();
		
	}
	
	
	public void insert(Vertex v){           //insert method inserts the verticies into the graph object.
		
		verticies.add(v);
		
	}
	
	
	public Vertex convert(String id){      //takes in id from edge object. This convert method was created so that way when the program is given an edge with two vertex id#'s, it can convert those id#'s into the actual vertex they represent.
		
		for(int i = 0; i < verticies.size(); i++){   //for every vertice in the array.
			
			if(verticies.get(i).id.equals(id))    //if the id of the verticie is equal to the id given.
				return verticies.get(i);          //then return that vertex.
			
		}
		return null;
		
	}
	
	public ArrayList<Edge> shortestPath(Vertex v1, Vertex v2){  //O(n^2).because im not using priority queue just looping through.
		
		for(Vertex v: verticies)   //loops through every vertex in the graph.
		{
			
			v.distance = Integer.MAX_VALUE;      //sets the distance to infinity(or javas max number)
			v.known = false;                     //sets every known to false.
			v.path = null;                       //creates a null path.
			
		}
		
		v1.distance = 0;              //the distance to anf rom the first vertx is zero.
		
		while(true){         //infinite loop intill break.
			
			double min = Double.MAX_VALUE;          
			Vertex minV = null;
			
			for(Vertex v: verticies){              //for every vertex in the graph.
				
				if(v.known == false && v.distance < min){          //if it is not known, and the distance is less then the current min distance.
					
					min = v.distance;             //set min value to that distance.
					minV = v;                     //set the next vertex to the vertex just checked.
					
				}
				
				
			}
			
			if( minV == null){    //if the current vertex is null, that means weve reached the end of the search and we can break out of the while loop.
				
				break;
				
			}
			
			minV.known = true;          //set the now checked vertex to known.
			
			for(Edge e: minV.edges){       //for every edge attached to that vertex.
				
				Vertex other = e.v1;         
				 
				if(minV == e.v1){   //this if checks weater or not the vertex being tested is the v1 or v2 of the edge not being looked at.
					
					other = e.v2;
					
				}
				
				if(other.known != true){         //is the next vertex attached to the other side of the edge is not known.
					
					double newDistance = minV.distance + e.weight;       //create a newDistance variable and set it equal to the current vertex distance plus the weight of the edge, which is found from distance formula.
					
					if(newDistance < other.distance){    //if this new distance is less then the distance from the other vertex, then set that distance to new distance.
						
						other.distance = newDistance;
						other.path = e;  //continues to move through the graph.
						
					}
					
					
				}
			}
			
		}
		
		
		if(v2.known == false){
			
			return null;
			
		}	
		return getPath(v2);
		
	}
	
	public ArrayList<Edge> getPath(Vertex v){        //this method finds a path of edges from a given vertex and returns an arraylist of those edges from the vertex.
		
		ArrayList<Edge>  edges1 = new ArrayList<Edge>();  //create array of edges.
		Vertex currentV = v; 
		
		while(currentV.path != null){    //while it has a path.
			
			edges1.add(0, currentV.path);    //add this edge to the array.
			
			if(currentV != currentV.path.v1){    //checks which vertex current v is to the path, wether it is v1 or v2.
			
				currentV = currentV.path.v1;	   //continues to move along the array graph.
			}
			else{
				
				currentV = currentV.path.v2;
				
			}
			
		}
			
		return edges1; //returns the arraylist of edges(path).
		
	}
	
	public ArrayList<Edge> Prims(){         //mst tree method. returns an array list of edges.
		
		ArrayList<Vertex> known = new ArrayList<Vertex>();   //create an arraylist of vertexs to keep track of the ones that are known.
		ArrayList<Edge> edges = new ArrayList<Edge>();         //used to keep track of the edges being used in the mst.
		
			Vertex v1 = verticies.get(0);       //set the first vertex to the be the one to start the minimum spanning tree at.
			known.add(v1);                     //add the first vertex to known.
			v1.known = true;                   //set first vertex to known.
			
			while(known.size() < verticies.size()){         //keep iterating through intill all verticies have been visited.
				
				double min = Double.MAX_VALUE;         //create min variable.
				Edge edge1 = null;                     //create an edge.
				
				for(int i = 0; i < known.size(); i++){      
				
					Vertex vertex = known.get(i);              //set vertex to the known vertex at index i.
					
					for(Edge edge: vertex.edges){       //for every edge in the current vertex edges.
						
						if(vertex == edge.v1 && edge.v2.known == true)//checks to see if the vertex being checked is already known and if the vertex attached to the other side of the edge is known as well.
							continue;                        //if it is skip rest of loops content and iterate through again.
						if(vertex == edge.v2 && edge.v1.known == true)    //checks the same thing but for oppisite cases.
							continue;
						
						if(edge.weight < min && edge.known == false ){        //if either of the checked vertex are not known then do this. check to see if the edge weight of the edge being check is less then the prev edge, and check if that edge has already been checked.
				
							min = edge.weight;   //set the min weight to the edge weight.
							edge1 =  edge;      //move along the edges.
				
						}
				
					}
			
			
				}
				
				
				if(edge1 == null)      //if there is ever an edge that is non existent then break.
					break;
				
				edge1.known = true;       //set the current edge to known.
				edges.add(edge1);         //add the edge.
				
				Vertex other;
				if(edge1.v1.known == true)     //check which vertex v1, or v2, is the one that known.
					other = edge1.v2;
				else
					other = edge1.v1;
				
				known.add(other);    //add that known vertex to the array list.
				other.known = true;    //set its known to true.
				
			}
			
			return edges;    //return the arraylist of edges.
	
	}
	
}
