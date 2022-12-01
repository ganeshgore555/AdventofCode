package com.main;

import java.util.LinkedList;
import java.util.Queue;


public class Day13P1 {
	private static final int ROW = 1000;
	private static final int COL = 1000;
	private static final int MAGIC_NUM = 1352;
	
	public static void main(String[] args) {
	    int[][] mat = new int [ROW][COL];
	    // Generate array of 0,1 which represent the maze. 1 = Wall , 0 = Open space
	    for(int y = 0; y < ROW; y++){
		    for(int x = 0; x < COL; x++){
		    	mat[y][x] = Long.toBinaryString((x*x + 3*x + 2*x*y + y + y*y) + MAGIC_NUM).replace("0", "").length()%2 == 0 ? 0 : 1;
		    	//System.out.print(mat[y][x] + " ");
		    }
		    //System.out.println();
	    }
	    
	    Point source = new Point(1, 1);
	    Point dest = new Point(31, 39);
	 
	    int dist = BFS(mat, source, dest);
	 
	    if (dist != -1)
	    	System.out.println("Shortest Path is " + dist);
	    else
	    	System.out.println("Shortest Path doesn't exist");		
	}
	 
	//to store matrix cell coordinates
	static class Point
	{
	    public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		int x;
	    int y;
	};
	 
	// An Data Structure for queue used in BFS
	static class QueueNode
	{
	    public QueueNode(Point pt, int dist) {
			super();
			this.pt = pt;
			this.dist = dist;
		}
		Point pt;  // The cordinates of a cell
	    int dist;  // cell's distance of from the source
	};
	 
	static boolean isValid(int row, int col)
	{
	    return (row >= 0) && (row < ROW) &&
	           (col >= 0) && (col < COL);
	}
	 
	static int rowNum[] = {-1, 0, 0, 1};
	static int colNum[] = {0, -1, 1, 0};
	 
	static int BFS(int mat[][], Point src, Point dest)
	{
	    // check source and destination cell
	    // of the matrix have value 1
	    if (mat[src.y][src.x] != 0 || mat[dest.y][dest.x] != 0){
	    	System.out.println(" src.x , src.y " + src.x + " , " + src.y + " dest.x , dest.y " + dest.x + " , " +  dest.y);
	        System.out.println(mat[src.y][src.x] + " " + mat[dest.y][dest.x]);
	    	return -1;
	    }
	 
	    boolean[][] visited = new boolean[ROW][COL];
	    // Mark the source cell as visited
	    visited[src.y][src.x] = true;
	    // Create a queue for BFS
	    Queue<QueueNode> q = new LinkedList<>(); 
	    // distance of source cell is 0
	    q.add(new QueueNode(src, 0));
	 
	    // Do a BFS starting from source cell
	    while (!q.isEmpty())
	    {
	        QueueNode curr = q.poll();
	        Point pt = curr.pt;
	 
	        // If we have reached the destination cell,
	        // we are done
	        if (pt.x == dest.x && pt.y == dest.y)
	            return curr.dist;
	 
	        for (int i = 0; i < 4; i++)
	        {
	            int col = pt.x + colNum[i];
	            int row = pt.y + rowNum[i];
	            // if adjacent cell is valid, has path and
	            // not visited yet, enqueue it.
	            if (isValid(row, col) && mat[row][col] == 0 && 
	               !visited[row][col])
	            {
	                // mark cell as visited and enqueue it
	                visited[row][col] = true;
	                QueueNode adjcell = new QueueNode(new Point(col, row), curr.dist + 1 );
	                q.add(adjcell);
	            }
	        }
	    }
	    //return -1 if destination cannot be reached
	    return -1;
	}
}
