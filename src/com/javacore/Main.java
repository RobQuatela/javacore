package com.javacore;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

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
		doCloseThing();
	}
}
