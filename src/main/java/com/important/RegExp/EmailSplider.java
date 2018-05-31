package com.important.RegExp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailSplider {

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\a.html"));
			String line = "";
			while((line = br.readLine()) != null){
				parse(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void parse(String line) {
		Pattern p = Pattern.compile("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+");
		Matcher m = p.matcher(line);
		while(m.find()) {
			System.out.println(m.group());
		}
	}
}
