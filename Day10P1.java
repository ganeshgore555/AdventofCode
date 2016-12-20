package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Day10P1 {

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("G:/Workspace/AdventOfCode/AdventOfCode/src/com/main/inputDay10.txt")));
			String line = null;
			int valid = 0;
			int botReady = 0;
			String[] inputArr = null;
			HashMap<String, Integer> outputMap = new HashMap<>();
			HashMap<String, Bot> botMap = new HashMap<>();
			String resultBot = null;
			while((line = br.readLine()) != null){
				line = line.trim();
				System.out.println(line);
				inputArr = line.split(" ");
				Bot bot;
				if(inputArr.length == 6){
					bot = botMap.get(inputArr[5]);
					if(bot == null){
						bot = new Bot();
						botMap.put(inputArr[5], bot);
					}
					bot.tokens.add(Integer.parseInt(inputArr[1]));
					if(bot.tokens.size() > 1){
						bot.ready = true;
						valid++;
					}
				}
				else if(inputArr.length == 12){
					bot = botMap.get(inputArr[1]);
					if(bot == null){
						bot = new Bot();
						botMap.put(inputArr[1], bot);
					}
					if(inputArr[5].equals("output")){bot.lowToOutput = true;}
					bot.low = inputArr[6];
					if(inputArr[10].equals("output")){bot.highToOutput = true;}
					bot.high = inputArr[11];
				}
			}
			System.out.println(botMap);
			while(valid > 0){
				for(String botNum : botMap.keySet()){
					Bot bot = botMap.get(botNum);
					Bot lowBot = null;
					Bot highBot = null;
					if(bot.ready){
						int lowValue = 0;
						int highValue = 0;
						if(bot.tokens.get(0) <= bot.tokens.get(1)){
							lowValue = bot.tokens.get(0);
							highValue = bot.tokens.get(1);
						}else{
							lowValue = bot.tokens.get(1);
							highValue = bot.tokens.get(0);
						}
						
						if(highValue == 61 && lowValue == 17){
							resultBot = botNum;
						}
						
						if(bot.lowToOutput){
							outputMap.put(bot.low, lowValue);
						}else{
							lowBot = botMap.get(bot.low);
							if(lowBot == null){
								lowBot =  new Bot();
								botMap.put(bot.low, lowBot);
							}
							lowBot.tokens.add(lowValue);
							if(lowBot.tokens.size() > 1){
								lowBot.ready = true;
								valid++;
							}
						}
						if(bot.highToOutput){
							outputMap.put(bot.high, highValue);
						}else{
							highBot = botMap.get(bot.high);
							if(highBot == null){
								highBot =  new Bot();
								botMap.put(bot.high, highBot);
							}
							highBot.tokens.add(highValue);
							if(highBot.tokens.size() > 1){
								highBot.ready = true;
								valid++;
							}
						}
						bot.ready = false;
						valid--;
					}
				}
			}
			System.out.println(outputMap);
			System.out.println(botMap);
			System.out.println(resultBot);
			System.out.println(outputMap.get("0")*outputMap.get("1")*outputMap.get("2"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	static class Bot{
		String low;
		boolean lowToOutput;
		String high;
		boolean highToOutput;
		ArrayList<Integer> tokens = new ArrayList(2);
		boolean ready;
		@Override
		public String toString() {
			return "Bot [low=" + low + ", lowToOutput=" + lowToOutput + ", high=" + high + ", highToOutput="
					+ highToOutput + ", tokens=" + tokens + ", ready=" + ready + "]";
		}
	}
}
