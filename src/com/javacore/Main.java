package com.javacore;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void doTryCatchFinally() {
		char[] buff = new char[8];
		int length;

		try(Reader reader = Helper.openReader("/home/rquatela/Desktop/file1.txt")) {
			while((length = reader.read(buff)) >= 0) {
				System.out.println("\nlength: " + length);
				for(int i = 0; i < length; i++)
					System.out.print(buff[i]);
			}
		}
		catch(IOException e) {
			System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
		}

	}
	
	public static void doTryCatchFinallyMulti() {
		char[] buff = new char[8];
		int length;

		try(Reader reader = Helper.openReader("/home/rquatela/Desktop/file1.txt");
				Writer writer = Helper.openWriter("/home/rquatela/Desktop/file2.txt")) {
			while((length = reader.read(buff)) >= 0) {
				System.out.println("\nlength: " + length);
				writer.write(buff, 0, length);
			}
		}
		catch(IOException e) {
			System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
		}

	}
	
	public static void doCloseThing() {
		try(MyAutoCloseable ac = new MyAutoCloseable()) {
			ac.saySomething();
		} catch(IOException e) {
			System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
			
			for(Throwable t: e.getSuppressed())
				System.out.println("Suppressed: " + t.getMessage());
		}
	}
	
	public static void main(String[] args) {
		//doTryCatchFinallyMulti();
		//doCloseThing();
		
		//regex practice replace @ end of word
		String s1 = "apple, apple and orange please";
		String s2 = s1.replaceAll("ple\\b", "ricot");
		
		//regex using split and match methods
		String[] parts = s1.split("\\b");
		
		for(String part : parts) {
			if(part.matches("\\w+"))
				System.out.println(part);
		}
		
		System.out.println(s1);
		System.out.println(s2);
		
		//using pattern and matcher classes
		Pattern pattern = Pattern.compile("\\w+");
		Matcher matcher = pattern.matcher(s1);
		
		while(matcher.find()) {
			System.out.println(matcher.group());
		}
	}
}
