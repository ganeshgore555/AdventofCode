package com.main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.Queue;


public class Day17P1 {

	private static final int ROW = 4;
	private static final int COL = 4;
	private static final String STR = "bwnlcvfs";
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
	    int[][] mat = new int [ROW][COL];

	    Point source = new Point(0, 0);
	    Point dest = new Point(3, 3);
	 
	    String path = BFS(mat, source, dest);
	 
	    if (path != "")
	    	System.out.println("Shortest Path is " + path);
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
		Point pt;  // The cordinates of a cell
	    int dist;  // cell's distance of from the source
	    String path; // path taken to reach from source
		public QueueNode(Point pt, int dist, String path) {
			super();
			this.pt = pt;
			this.dist = dist;
			this.path = path;
		}
	};
	 
	static boolean isValid(int row, int col)
	{
	    return (row >= 0) && (row < ROW) &&
	           (col >= 0) && (col < COL);
	}
	 
	static int rowNum[] = {-1, 1, 0, 0};
	static int colNum[] = {0, 0, -1, 1};
	static char dir[] = {'U', 'D', 'L', 'R'};
	
	static String BFS(int mat[][], Point src, Point dest) throws NoSuchAlgorithmException
	{
	    // Create a queue for BFS
	    Queue<QueueNode> q = new LinkedList<>(); 
	    // distance of source cell is 0
	    q.add(new QueueNode(src, 0, ""));
	 
	    // Do a BFS starting from source cell
	    while (!q.isEmpty())
	    {
	        QueueNode curr = q.poll();
	        Point pt = curr.pt;
	 
	        // If we have reached the destination cell,
	        // we are done
	        if (pt.x == dest.x && pt.y == dest.y)
	            return curr.path;

		    byte[] bytesOfMessage = (STR + curr.path).getBytes();
		    MessageDigest md = MessageDigest.getInstance("MD5");
		    byte[] thedigest = md.digest(bytesOfMessage);
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < thedigest.length; ++i) {
	          sb.append(Integer.toHexString((thedigest[i] & 0xFF) | 0x100).substring(1,3));
	        }
	        char[] ways = sb.toString().substring(0, 4).toCharArray();
	        
	        for (int i = 0; i < 4; i++)
	        {
	            int col = pt.x + colNum[i];
	            int row = pt.y + rowNum[i];
	            // if adjacent cell is valid, has path and
	            // not visited yet, enqueue it.
	            if (isValid(row, col) && mat[row][col] == 0 && isOpen(ways[i])){
	                QueueNode adjcell = new QueueNode(new Point(col, row), curr.dist + 1, curr.path + dir[i]);
	                q.add(adjcell);
	            }
	        }
	    }
	    //return -1 if destination cannot be reached
	    return "";
	}

	private static boolean isOpen(char c) {
		if(c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'f')
			return true;
		return false;
	}
}
