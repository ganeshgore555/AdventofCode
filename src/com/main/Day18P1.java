package com.main;

import java.util.ArrayList;
import java.util.Arrays;

public class Day18P1 {
	static final String input = ".^^.^^^..^.^..^.^^.^^^^.^^.^^...^..^...^^^..^^...^..^^^^^^..^.^^^..^.^^^^.^^^.^...^^^.^^.^^^.^.^^.^.";
	static final int ROW = 400000;
	static final int COL = input.length();
	static char[][] room = new char[ROW][COL];
	static final String[] arr = {"^^.",".^^","^..","..^"};
	static final ArrayList<String> trapCriteria = new ArrayList(Arrays.asList(arr));
	
	public static void main(String[] args) {
		int safe = 0;
		for(int i = 0; i < ROW; i++){
			for(int j = 0; j < COL; j++){
				if(i == 0)
					room[i][j] = input.charAt(j); // Copy input as it is
				else
					room[i][j] = isTrap(i,j) ? '^' : '.';
				
				if(room[i][j] == '.')
					safe++;
				//System.out.print(room[i][j] + " ");
			}
			//System.out.println();
		}
		System.out.println(safe);
	}
	
	private static boolean isTrap(int i, int j) {
		String prev = getTile(i-1,j-1) + getTile(i-1,j) + getTile(i-1,j+1);
		if(trapCriteria.contains(prev))
			return true;
		return false;
	}

	private static String getTile(int i, int j){
		if(!isValid(i,j))
			return ".";
		else
			return Character.toString(room[i][j]);
	}
	
	private static boolean isValid(int row, int col)
	{
	    return (row >= 0) && (row < ROW) &&
	           (col >= 0) && (col < COL);
	}
	
	
}
