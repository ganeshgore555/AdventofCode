package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Day04P1 {

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
				char [] charArr = code.toCharArray();
				
				Map<Character, Integer> numChars = new HashMap<Character, Integer>();
				//System.out.println(code);
				for (int i = 0; i < charArr.length; ++i)
				{
				    char charAt = charArr[i];
				    //System.out.println(charAt);
				    if (!numChars.containsKey(charAt))
				    {
				        numChars.put(charAt, 1);
				    }
				    else
				    {
				        numChars.put(charAt, numChars.get(charAt) + 1);
				    }
				}
				System.out.println(numChars);
				
				int max = 0;
				boolean isValid = true;
				ArrayList<Integer> intArr = new ArrayList<>();
				for(char ch : numChars.keySet()){
					intArr.add(numChars.get(ch));
				}
				Collections.sort(intArr);
				System.out.println(intArr);
				String seq = inputArr[inputArr.length - 1].split("\\[")[1].split("\\]")[0];
				String id = inputArr[inputArr.length - 1].split("\\[")[0];
				System.out.println(seq);
				int index = 1;
				for(char ch : seq.toCharArray()){
					System.out.println(ch + " " + numChars.get(ch));
					max = (int) intArr.get(intArr.size() - index);
					System.out.println(max);
					if (numChars.get(ch) == null || numChars.get(ch) != max){
						isValid = false;
						break;
					}
					index++;
				}
				if(isValid)
					valid = valid + Integer.parseInt(id);
			}
			System.out.println(valid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
