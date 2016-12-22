package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class Day22P2 {
	private static final int ROW = 29;
	private static final int COL = 33;
	static HashMap<String, Node> map = new HashMap<>();
	static int rowNum[] = {-1, 0, 0, 1};
	static int colNum[] = {0, -1, 1, 0};
	static String goalData = "";
	static String empty = "";
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("G:/Workspace/AdventOfCode/AdventOfCode/src/com/main/inputDay22.txt")));
			String line = null;
			String[] input = null;
			String src = "x0-y0";
			String dest = "x2-y0";
			goalData = dest;
			while((line = br.readLine()) != null){
				line = line.trim();
				if(line.contains("node")){
					input = line.split("\\s+");
					String name = input[0].split("-",2)[1];
					Node node = new Node(name, Integer.parseInt(name.split("-")[0].replace("x", "")), Integer.parseInt(name.split("-")[1].replace("y", "")), Integer.parseInt(input[2].replace("T", "")), Integer.parseInt(input[1].replace("T", "")), Integer.parseInt(input[3].replace("T", "")), Integer.parseInt(input[4].replace("%", "")));
					map.put(name, node);
				}
			}
			Node destNode = map.get(dest);
		    Node[][] mat = new Node [ROW][COL];
		    // Generate array of 0,1 which represent the maze. 1 = Wall , 0 = Open space
		    for(int y = 0; y < ROW; y++){
			    for(int x = 0; x < COL; x++){
			    	String name = "x"+x+"-"+"y"+y;
			    	mat[y][x] = map.get(name);
			    	System.out.print(x + ":" + y + "-");
			    	System.out.print(mat[y][x] + " ");
			    	if(mat[y][x].avail >= destNode.used && (empty.equals("") || map.get(empty).avail < mat[y][x].avail))
			    		empty = name;
			    }
			    System.out.println();
		    }
		    System.out.println(goalData);
		    System.out.println(empty);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	static boolean isValid(int row, int col)
	{
	    return (row >= 0) && (row < ROW) &&
	           (col >= 0) && (col < COL);
	}
	
	static class Node{
		public Node(String name, int x, int y, int used, int size, int avail, int usedPercent) {
			super();
			this.name = name;
			this.x = x;
			this.y = y;
			this.used = used;
			this.size = size;
			this.avail = avail;
			this.usedPercent = usedPercent;
		}
		String name;
		int x;
		int y;
		int used;
		int size;
		int avail;
		int usedPercent;
		@Override
		public String toString() {
			return used+"/"+size;
		}
		
	}
}
