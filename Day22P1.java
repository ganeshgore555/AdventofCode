package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class Day22P1 {
	private static final int ROW = 3;
	private static final int COL = 3;
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("G:/Workspace/AdventOfCode/AdventOfCode/src/com/main/inputDay22.txt")));
			String line = null;
			String[] input = null;
			HashMap<String, Node> map = new HashMap<>();
			while((line = br.readLine()) != null){
				line = line.trim();
				if(line.contains("node")){
					input = line.split("\\s+");
					String name = input[0].split("-",2)[1];
					map.put(name, new Node(name, Integer.parseInt(input[2].replace("T", "")), Integer.parseInt(input[1].replace("T", "")), Integer.parseInt(input[3].replace("T", "")), Integer.parseInt(input[4].replace("%", ""))));
				}
			}
			
			String name1;
			String name2;
			int count = 0;
		    for(int y = 0; y < ROW; y++){
			    for(int x = 0; x < COL; x++){
			    	name1 = "x"+x+"-"+"y"+y;
				    for(int j = 0; j < ROW; j++){
					    for(int i = 0; i < COL; i++){
					    	name2 = "x"+i+"-"+"y"+j;
					    	if(!name1.equals(name2)){
					    		Node node1 = map.get(name1);
					    		Node node2 = map.get(name2);
					    		if(node1.used != 0 && node1.used <= node2.avail){
					    			count++;
					    		}
					    	}
					    }
				    }
			    }
		    }
		    
			System.out.println(count);
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	static class Node{
		public Node(String name, int used, int size, int avail, int usedPercent) {
			super();
			this.name = name;
			this.used = used;
			this.size = size;
			this.avail = avail;
			this.usedPercent = usedPercent;
		}
		String name;
		int used;
		int size;
		int avail;
		int usedPercent;
		@Override
		public String toString() {
			return "Node [name=" + name + ", used=" + used + ", size=" + size + ", avail=" + avail + ", usedPercent="
					+ usedPercent + "]";
		}
		
	}
}
