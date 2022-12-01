package com.main;

public class Day16P1 {

	public static void main(String[] args) {
		int max = 35651584;
		StringBuffer a = new StringBuffer("11100010111110100");
		int len = a.length();
		while(len < max){
			StringBuffer b = new StringBuffer(a);
			b.reverse();
			String temp = b.toString();
			temp = temp.replace("0", "#");
			temp = temp.replace("1", "0");
			temp = temp.replace("#", "1");
			b = new StringBuffer(temp);
			a.append("0").append(b);
			len = a.length();
		}
		System.out.println(a);
		System.out.println(a.length());
		String str = a.substring(0, max);
		System.out.println(str);
		System.out.println(str.length());
		boolean done = false;
		String temp = "";
		while(!done){
			for(int i = 0; i < str.length(); i=i+2){
				String subStr = str.substring(i, i+2);
				if(subStr.equals("00") || subStr.equals("11")){
					temp = temp + "1";
				}
				else{
					temp = temp + "0";
				}
			}
			if(temp.length()%2 == 0){
				str = temp;
				temp = "";
			}else{
				done = true;
			}
		}
		System.out.println(temp);
	}
}
