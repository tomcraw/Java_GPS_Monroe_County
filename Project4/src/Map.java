/*
 * Thomas H. Craw
 * Project 4
 * Map class.
 */
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;


public class Map extends Canvas{     

	public Graph graph;    //Creates Graph object.
	public ArrayList<Edge> path;
	
	public Map(Graph g){         //Map Constructor takes in a graph.
		
		graph = g;              
		
	}
	
	public void update(Graphics g){     //This update method works as a sort of buffer, found it to make the graph appear clearer on screen. because it constanly updates the frame.
		
		paint(g);
		
	}
	
	public Color drawColor;      //create variable color, to allow change in color.
	
	public void paint(Graphics g){        //Paint method.
		
		Image img = Toolkit.getDefaultToolkit().getImage("monroe.png");    //take in the image given, however I modified and re-saved image to make fit nicler under graph.
		g.drawImage(img, -30, -1, this);       //Draws image at location to best fit display.
		
		
		g.setColor(Color.RED);            //sets the color to black.
		Graphics2D g2D = (Graphics2D)g;          //use 2D graphics.
		g2D.setStroke(new BasicStroke((float) 1.5));
		
		for(Vertex v : graph.verticies)         //for every verticie in the graph do this.
		{
			for (Edge e : v.edges)              //for every edge in the edge array in the vertex.
			{
				g.drawLine((int)e.v1.x, (int)e.v1.y, (int)e.v2.x, (int)e.v2.y);   //draw this edge. from x,y coordinates of first vertex of edge, to the other.
			}
		}
		
		if(path != null){        //if the path input is not null, meaning it contains something.
			
			g.setColor(drawColor);                //set color to whatever specified color.
		
			g2D.setStroke(new BasicStroke((float) 2.5));   //sets the size of the lines to a bigger size, to make it clearer to see.
			
			for(Edge e: path){          //for every edge within the path.
				
				g.drawLine((int)e.v1.x, (int)e.v1.y, (int)e.v2.x, (int)e.v2.y);  //draw line from v1, to v2.
				
			}
		
		}
		
	}
}
