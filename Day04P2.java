package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Day04P2 {

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("G:/Workspace/AdventOfCode/AdventOfCode/src/com/main/inputDay04.txt")));
			String line = null;
			int valid = 0;
			while((line = br.readLine()) != null){
				line = line.trim();
				String[] inputArr = line.split("-");
				String code = "";  
				for(int i = 0; i < inputArr.length - 1; i++)
				{
					code = code + inputArr[i];
				}
				System.out.println(code);
				char [] charArr = code.toCharArray();
				int id = Integer.parseInt(inputArr[inputArr.length - 1].split("\\[")[0]);
				System.out.println(id);
				for (int i = 0; i < charArr.length; ++i)
				{
					for(int j = 1; j <= id ; j++)
					{
						char ch = charArr[i];
						if(ch == 'z'){
							charArr[i] = 'a';
						}
						else
						{
							charArr[i] = (char) (ch + 1);
						}	
					}
				}
				System.out.println(charArr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
