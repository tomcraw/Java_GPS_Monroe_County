/*
 * Thomas H. Craw
 * Project 4
 * Main class
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;


public class Main extends Canvas{    //Turns into applet display.

	static Graph g = new Graph();    //Creates Graph object.
	
	public static void main(String[] args) throws FileNotFoundException {
		
		if(args.length == 0){
			
			System.out.println("Please see README.");
			
		}
		
		File file = new File("monroe-county-1.tab");   //Creates file object from the file located within the directory.
		Scanner input = new Scanner(file);       //Reads through the file.

		while(input.hasNext()){       //While there is something to read.
			
			if(input.next().equals("i")){        //Checks to see if the first character in each line is an "i". for vertex.
				
				String id = input.next();        //sets the vertex id to String id.
				double x = input.nextDouble();   //sets the x coordinate to double x.
				double y = input.nextDouble();   //sets the y coordinate to double y.
				
				Vertex v = new Vertex(id, x, y);  //Creates new vertex object from information obtained.
				
				g.insert(v);          //Inserts the vertex into the Graph object.
				
				
			}
			else{               //If the first character is not "i", therefore it is "r".
				
				String id1 = input.next();             //sets edge id number to String id1.
				Vertex v1 = g.convert(input.next());   //creates a vertex object from the vertex id given from the file. uses convert method found in graph class.
				Vertex v2 = g.convert(input.next());   //creates a vertex object from the vertex id given from the file.
				
				Edge e = new Edge(id1, v1, v2);   //creates new edge class from given information.
				
				v1.insert(e);    //inserts the edge into the edges array located in the vertex class. this gives vertex a way to keep track of what edges are connected to it.
				v2.insert(e);    //inserts the edge into the edges array located in the vertex class. this gives vertex a way to keep track of what edges are connected to it.
				
			}
			
		}
		
		
		
		JFrame frame = new JFrame();    //Creates the frame to be used to display map.
		frame.setSize(700, 700);      //sets size of the frame.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //allows exit bypressing close button.
		Map map = new Map(g);      //Creates new Map object from the created graph object.
	
		
		if(args.length > 0 && args[0].equals("map")){   //if map is entered.
			

			frame.add(map);            //Adds map to the JFrame.
			frame.setVisible(true);   //Make Frame visible.
			
		}
		
		if(args.length > 0 && args[0].equals("gps")){    //if gps is entered.
			
			map.drawColor = Color.GREEN;    //set color red.
			Vertex v1, v2;                //create variables v1, v2.
			
			if(!args[1].equals("random") && !args[2].equals("random")){   
				
			
				v1 = g.convert(args[1]);    //use method convert, to convert from id number to vertex.
				v2 = g.convert(args[2]);
			
			}
			else{
		
				int x = (int) (Math.random() * g.verticies.size());   //call random vertex from array.
				int y = (int) (Math.random() * g.verticies.size());
				v1 = g.verticies.get(x);     
				v2 = g.verticies.get(y);
				
			}
			
			ArrayList<Edge> path =  g.shortestPath(v1, v2);   //create path from the method that creates mst.
	
			if(path == null){    //if the vertex entered is not connected to the other vertex entered return statement.
		
				System.out.println("Can not reach destination from current location.");
		
			}
			else{
	
				
				map.path = path;       //sets the path to be drawn in class map to the edge array created.
	
				Vertex prev = v1;
				System.out.println(v1.id + ", ");  //print out id of first vertex.
				
				for(Edge e: path){   //go through every edge in the given array.
		
					Vertex next;	
						
					if(e.v1 == prev)     //check to see if  the first vertex connected to the first edge is v1.
							next = e.v2;
					else
						next = e.v1;
					
					System.out.println(e.id + ", ");       //prints out the edge.
					System.out.println(next.id + ", ");    //prints out the second vertex asociated with the edge.
					
					
				}
				System.out.println();
	
				
			}
			
			frame.add(map);            //Adds map to the JFrame.
			frame.setVisible(true);   //Make Frame visible.
		}
		
		if(args.length > 0 && args[0].equals("mst")){    //is mst is entered.
			
			map.drawColor = Color.blue;   //set color blue.
			ArrayList<Edge> edges = g.Prims();        //create array of edges equal to the method prim.
			map.path = edges;          //set the map to be drawn to the edges array created.

			frame.add(map);            //Adds map to the JFrame.
			frame.setVisible(true);   //Make Frame visible.
			
		}		
	

	}
	


}
	

