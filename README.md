# Java_GPS_Monroe_County

Mini GPS software that takes in a data file of intersection(nodes) and roads(edges) to create a tree. Calculates shortest path from Destination A(Node A), to Destination B(Node B). Also can calculate the minimum spanning tree.

Author: Thomas H. Craw


TO RUN:

open src folder.
javac *.java
java Main

All tests should be tested from Main.java
If nothing is typed into command prompt for args, prints statement ”see README” 

Test 1:
  
  type at prompt: java Main map
    
    	ThomasCraw$ java Main map

      returns picture of “map.png”

Test 2:

	*(for two random intersections located from the file )
	type at prompt: java Main gps random random

  ThomasCraw$ java Main gps random random

      returns picture of the map with a green line from v1(random1), to v2(random2). Output of intersections and roads          traveled.

	
	*(for specific intersections located in the file)
		in command line type(without the quotes) 
			java Main gps “id of intersection” “id of intersection”

  ThomasCraw$ java Main gps i26759944 i212606879

      returns picture of the map with a green line from v1, to v2. Output of intersections and roads traveled.

Test 3:

	Type at prompt: java Main mst

  ThomasCraw$ java Main mst

      returns picture of “tree.png” 

  *(lines in blue are the minimum spanning tree, lines in red are from the previous graph.)


Description:

To Read .tab input data file -

 iterate through the given file, if it started with an i, then set the next input to the id of the vertex, and set the x and y coordinates accordingly. Then with this information I can create a Vertex with my vertex class. I then insert this vertex into my Graph g, created by by graph class. If it started with an r, then set next input to edge id, and v1, to first vertex and v2, to second, I can then create and edge from the given information. I then insert the edge into each of the vertices given.

Test 1:

To create the graph, I needed a Vertex, Edge, Map, and Graph class.
	
	Vertex class - My vertex class included variable for, the id, the x and y coordinates, an array of edges(for the edges that belonged to each vertex), distance(to keep track of the current distance from each vertex.), known(used for later algorithms to determine weather or not a vertex is known yet or not.), and path(to keep track of the path from one intersection to another.) I also included an insert class, to insert the edges into the vertex,so that I could keep track of what edge belonged to each vertex.
	
	Edge class - My edge class included variables for the id, each of the given vertices, the weight of the edge, and known(used for later algorithms to determine which edges have been used already). The weight of the edge was determined by the distance from its first vertex to its second using the distance formula. I also included a getter for the weight of 	the edge.
	
	Graph class - My graph class used an array of vertices(and since each vertex contained its edge, this could be used to draw the graph). I included an insert method to insert vertices into the graph. I also had to create a a convert method to convert from id numbers into actual vertexes, so when the command prompt is given to vertex id’s I can associate those numbers with actual vertices. The rest of the graph method consist of the methods for the shortest path and the minimum spanning tree which I will discuss in a later part of this README.
	
	Map class - This class is where I took care of all the graphics for my project. I extended canvas so that way everything can be drawn on and added to a frame. I created an arraylist of edges called path, which will be drawn into lines that represent roads once initialized. The map class takes in a graph, which contains an arraylist of vertices and has access from each vertex, edges. First thing however I imported the monroe.png picture to use as my background. I then had to use 2D graphics for the lines, and found that I could set the font size of the lines different fonts which was quite useful when drawing two graphs on the same canvas. I basically used for each loops to go through every vertex in the graph and then another for each loop to go through every edge, and then drew a line from the x and y coordinates of each edges vertex1, and vertex2. When the path contains edges in it, meaning I have added edges to the arrylist from outside methods, then it will draw another graph with those edges, and also with a different font size.

now that I have created all these classes, I can use them from main. to actually draw something. In Main I created a new JFrame, set the size to 700, 700, created a close operation, and then I created my map object with the already created graph. If args[0] = map then the frame adds the map to it, which draws the create graph on top of the frame.

Test 2: 

To find the shortest path from one vertex to another, I used Dijkstra’s algorithm. 

	getPath - This method takes in a vertex and returns an arraylist of edges. Since dijkstra’s is used to find the shortest path from one initial vertex to all other vertices, I created the method getPath to get a path to not all other vertices but to a selected vertex. I created an edge arraylist to keep track of the edges gone 	through. I then	set my current vertex to the inputed vertex. I then use a method of backtracking, so I will go through the path of the inputed vertex, all the way back to the first vertex that will be inialized in the shortestpath method. So I will continue along the path of the vertices, from currentV’s path, to the next vertex, the from that vertex path to the next vertex and so on in-till I reach the first vertex with a null path, which wil be the first vertex(v1). While its going through this path I am adding the edges that are along this path. The tricky part of this method is I have to constantly check weather or not the path(or the edge’s) next vertex is v1 or v2, by check wether or not the current vertex is v1, or v2. It then returns the arrayist of these edges.
	
	shortestPath - This method actually checks the weight of each of the edges in the path. The method starts out by looping through every vertex in the graph. It then sets the distance to that vertex to infinity(or javas highest number). It sets its known to false and the path to null. It then sets the first vertex to be checked distance to 0; It then enters an infinite loop that only exits on a break. I created 	two variables, min, to keep track of the smallest weight of an edge, and minV which will be the current Vertex that is being tested. The method loops through every vertex in the graph and determines at each vertex if it is known and if the distance from the initial vertex is less then the current distance. If it is the distance gets change to that distance and the current minV gets changed to that vertex. After the for loop but still within the while loop, the program checks to see if the minV is null, if it is it breaks out of the while loop. It then sets the 	minV(the vertex with the minimum distance to known) and checks through everyone of minV’s edges. It then checks wether or not minV is its edges v1 or v2 and then sets a variable for a vertex to what ever one v1 or v2 the minV is not. the program then takes this other vertex, checks wether it is known or not, if it is not then it adds its weighted 	edge to the total distance, then it checks weather or not this new distance is less then the total distance it would be from the first vertex to the other vertex. if the distance is less, then this is the new distance and the program continues to move to other vertexes in a 	looping fashion in till it reaches the second inputed vertex.The time complexity because im not using priority queues and just looping through the vertices and edges is O(n^2).

Test 3:

To create a minimum spanning tree from the create graph, I used Prims algorithm.
	
	Prims - This method takes in a graph, and returns an array of edges, which I can use to draw lines in my map class. Basically this algorithm required me to keep track of the vertices and edges that I have visited, so I create arraylist for those. Prims algorithm says that if I got from each vertex setting the ones that I have visited to known, and checking 	each of its edges, I chose the edge with the smallest weight, while keeping track of all the edges tested, I then go on to that next vertex that the edge connected to, and look at all of its edges, along with the previous vertices edges, and pick the smallest weight of those. I keep this going in till I the amount of vertices in my new vertices arraylist is equal to the amount of vertices in the original arraylist from the given graph. The only tricky part in 	this algorithm was the fact that I had to constantly check each edges verticies, to see if my current known vertex was the v1 or the v2 of the tested edge. By setting the visited vertices to known and the edges selected to known, I can determine when I have visited all the vertices. I then return the arraylist of the edges used and draw them in my map class. Because I am using loops and not Heaps The run time complexity for Prims algorithm is O(n^2).

In my Main if the args[0] equals mst then it changes the color to blue, and draws lines of blue over the already existing graph of red, so you can actually see the red underneath the graph, to se what edges where excluded.


Now that I have these methods I can use them on my created graph in the Main. If args[0] equals gps, then there are two options,
	1: random random follow gps, which means that the program will select two random vertices from the given arraylist of vertices.
	2: two vertex id’s follow gps which will be converted into the two represented vertexes.

In both options the vertices get inputed into into the shortestpath method, which returns an arrylist of edges. I use these edges to print out a list of corresponding edges and vertices from the path v1 to v2. I also use these edges in my map class to print out the “path” from v1 to v2, with a green line ontop of the red lined graph. I also have a check in the program for if there are ever two vertices that can not be connected by the graph, it returns a statement saying this.
